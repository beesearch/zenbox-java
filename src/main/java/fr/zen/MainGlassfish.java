package fr.zen;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.glassfish.embeddable.CommandResult;
import org.glassfish.embeddable.CommandRunner;
import org.glassfish.embeddable.Deployer;
import org.glassfish.embeddable.GlassFish;
import org.glassfish.embeddable.GlassFishException;
import org.glassfish.embeddable.GlassFishProperties;
import org.glassfish.embeddable.GlassFishRuntime;
import org.glassfish.embeddable.archive.ScatteredArchive;

public class MainGlassfish {

	/**
	 * @param args
	 * @throws GlassFishException
	 * @throws IOException
	 */
	public static void main(String[] args) throws GlassFishException, IOException {

		System.out.println("### Starting Zen Box...");
		try {

			// Creation et demarage d'un serveur glassfish
			String webPort = System.getenv("PORT");
			if (webPort == null || webPort.isEmpty()) {
				webPort = "8080";
			}
			GlassFishProperties gfProps = new GlassFishProperties();
			gfProps.setPort("http-listener", Integer.valueOf(webPort));
			GlassFish glassfish = GlassFishRuntime.bootstrap().newGlassFish(gfProps);
			glassfish.start();
			System.out.println("### Glassfish started");

			// Récupération des infos de connexion a la base
			String dbUrl = System.getenv("DATABASE_URL");
			System.out.println("-------db url: " + dbUrl);
			Matcher matcher = Pattern.compile("postgres://(.*):(.*)@(.*)/(.*)").matcher(dbUrl);
			matcher.find();
			String host = matcher.group(3);
			String database = matcher.group(4);
			String user = matcher.group(1);
			String password = matcher.group(2);
			String properties = "user=" + user + ":password=" + password + ":databasename=" + database + ":loglevel=4:servername=" + host;
			System.out.println("-------properties: " + properties);
			System.out.println("### database properties: " + properties);

			// Creation du pool de connection
			CommandRunner runner = glassfish.getCommandRunner();
			CommandResult result = runner.run("create-jdbc-connection-pool", "--datasourceclassname", "org.postgresql.ds.PGSimpleDataSource", "--restype", "javax.sql.DataSource", "--maxpoolsize",
					"20", "--property", properties, "app/jdbc/zendb_pool");
			System.out.println("### output of create conn pool: " + result.getOutput());

			// Creation de la ressource JDBC
			result = runner.run("create-jdbc-resource", "--connectionpoolid", "app/jdbc/zendb_pool", "app/jdbc/zendb");
			System.out.println("### output of create jdbc: " + result.getOutput());

			// Creation de l'archive
			String webappDirLocation = "src/main/webapp/";
			ScatteredArchive archive = new ScatteredArchive("myApp", ScatteredArchive.Type.WAR, new File(webappDirLocation));

			// Ajout du repertoire target/classes contenant la compilation
			archive.addClassPath(new File("target", "classes"));

			// Deploiement de l'archive
			Deployer deployer = glassfish.getDeployer();
			deployer.deploy(archive.toURI());
			System.out.println("### application started :)");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
