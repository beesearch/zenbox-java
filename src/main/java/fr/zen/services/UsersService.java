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

    @Path("/create")
    @PUT
    public ZnUser create(@QueryParam("firstname") String firstname,
                       @QueryParam("lastname") String lastname,
                       @QueryParam("pwd") String pwd,
                       @QueryParam("mail") String mail) {
        return dao.create(firstname, lastname, pwd, mail);
    }

    @Path("/list")
    @Consumes(MediaType.APPLICATION_JSON)
    @GET
    public List<ZnUser> list(@QueryParam("first") @DefaultValue("0") int first,
                           @QueryParam("max") @DefaultValue("20") int max) {
    	System.out.println("### GET Users List (first:" + first + ", max:" + max + ")");
    	List<ZnUser> users = dao.list(first, max);
    	System.out.println("### GET Users List (response:" + users + ")");
        return users;
    }

    @Path("/show/{id}")
    @GET
    public ZnUser show(@PathParam("id") long id) {
        return dao.find(id);
    }

    @Path("/delete/{id}")
    @DELETE
    public void delete(@PathParam("id") long id) {
        dao.delete(id);
    }

    @Path("/update/{id}")
    @POST
    public ZnUser update(@PathParam("id") long id,
    		           @QueryParam("firstname") String firstname,
                       @QueryParam("lastname") String lastname,
                       @QueryParam("pwd") String pwd,
                       @QueryParam("mail") String mail) {
        return dao.update(id, firstname, lastname, pwd, mail);
    }

}
