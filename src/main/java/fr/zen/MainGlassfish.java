package fr.zen;

import java.io.File;
import java.io.IOException;

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

			
			//Propriete de la base
			String host = "ec2-23-21-89-241.compute-1.amazonaws.com";
			String database = "dbltotnp6kmisv";
			String user = "zpmgjuuhfeufhf";
			String password = "pCdV3z8KBjbvqEnGGHFOfEnhxQ";			
			String properties = "user=" + user + ":password=" + password + ":databasename=" + database + ":loglevel=4:servername=" + host + ":ssl=true" + ":sslfactory=org.postgresql.ssl.NonValidatingFactory";
			System.out.println("### database properties: " + properties);
			
			CommandRunner runner = glassfish.getCommandRunner();        
			
			//Creation du pool de connection
			CommandResult result = runner.run("create-jdbc-connection-pool", 
					"--datasourceclassname", "org.postgresql.ds.PGSimpleDataSource", 
					"--restype", "javax.sql.DataSource", 
					"--maxpoolsize", "20",
					"--property", properties,
					"app/jdbc/petcatalog_pool");
			System.out.println("### output of create conn pool: " + result.getOutput());
			
			//Creation de la ressource JDBC
			result = runner.run("create-jdbc-resource", "--connectionpoolid", "app/jdbc/petcatalog_pool", "app/jdbc/petcatalog");
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
