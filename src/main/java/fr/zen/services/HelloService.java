package fr.zen.services;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.zen.dao.HelloDao;

@Path("/hello")
@Produces(MediaType.APPLICATION_XML)
@Stateless
public class HelloService {

	@Inject
	private HelloDao helloDao;

	@GET
	public String getHelloWorld() {
		return "<text>" + helloDao.getHelloWorld() + "</text>";
	}

}
