package fr.zen.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import fr.zen.dao.ZnUsersDao;
import fr.zen.entities.ZnUser;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class UsersService {

	@Inject
	private ZnUsersDao dao;

	@Path("/list")
	@GET
	public List<ZnUser> list(@QueryParam("search") @DefaultValue("%") String search, @QueryParam("first") @DefaultValue("0") int first, @QueryParam("max") @DefaultValue("20") int max) {
		System.out.println("### GET users list (search:" + search + ", first:" + first + ", max:" + max + ")");
		List<ZnUser> users = dao.list(search, first, max);
		System.out.println("### GET users list (response:" + users + ")");
		return users;
	}

	@Path("/show/{id}")
	@GET
	public ZnUser show(@PathParam("id") long id) {
		System.out.println("### GET user show (id:" + id + ")");
		ZnUser user = dao.find(id);
		System.out.println("### GET user show (response:" + user + ")");
		return user;
	}

	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@PUT
	public ZnUser create(ZnUser user) {
		System.out.println("### PUT users create (user:" + user + ")");
		return dao.create(user);
	}

	@Path("/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public ZnUser update(ZnUser user) {
		System.out.println("### POST users update (user:" + user + ")");
		return dao.update(user);
	}

	@Path("/delete/{id}")
	@DELETE
	public void delete(@PathParam("id") long id) {
		System.out.println("### DELETE users delete (id:" + id + ")");
		dao.delete(id);
	}

}
