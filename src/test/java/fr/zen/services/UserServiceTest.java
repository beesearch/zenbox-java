package fr.zen.services;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

import fr.zen.entities.ZnUser;

public class UserServiceTest {

	private final static String SERVICE_URL = "http://localhost:8080";
	private static Client c;

	@BeforeClass
	public static void init() {
		// Configuration du client rest
		ClientConfig clientConfig = new DefaultClientConfig();
	    clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
	    c = Client.create(clientConfig);
	}

	@Test
    public void list() {
    	WebResource r = c.resource(SERVICE_URL + "/zenbox-demo/rest/users/list");
    	List<ZnUser> users = r.get(new GenericType<List<ZnUser>>(){});
    	System.out.println(users);
    }

    @Test
    public void show() {
    	WebResource r = c.resource(SERVICE_URL + "/zenbox-demo/rest/users/list");
    	List<ZnUser> users = r.get(new GenericType<List<ZnUser>>(){});
    	r = c.resource(SERVICE_URL + "/zenbox-demo/rest/users/show/" + users.get(0).getId());
    	ZnUser user = r.get(ZnUser.class);
    	System.out.println(user);
    }

    @Test
    public void create() {
    	WebResource r = c.resource(SERVICE_URL + "/zenbox-demo/rest/users/create");
    	ZnUser input = new ZnUser();
    	input.setFirstName("Donald");
    	input.setLastName("Duck");
    	input.setPassword("313");
    	input.setEmail("donald.duck@disney.com");
    	ZnUser user = r.type(MediaType.APPLICATION_JSON_TYPE).put(ZnUser.class, input);
    	System.out.println(user);
    }

    @Test
    public void update() {
    	WebResource r = c.resource(SERVICE_URL + "/zenbox-demo/rest/users/list");
    	List<ZnUser> users = r.get(new GenericType<List<ZnUser>>(){});
    	ZnUser input = users.get(0);
    	input.setPassword("313313");
    	r = c.resource(SERVICE_URL + "/zenbox-demo/rest/users/update/" + users.get(0).getId());
    	ZnUser user2 = r.type(MediaType.APPLICATION_JSON_TYPE).post(ZnUser.class, input);
    	System.out.println(user2);
    }

    @Test
    public void delete() {
    	WebResource r = c.resource(SERVICE_URL + "/zenbox-demo/rest/users/list");
    	List<ZnUser> users = r.get(new GenericType<List<ZnUser>>(){});
    	r = c.resource(SERVICE_URL + "/zenbox-demo/rest/users/delete/" + users.get(0).getId());
    	r.delete();
    }

}
