package fr.zen.dao;

import javax.inject.Named;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import fr.zen.entities.*;

@Named
public class HelloDao {

	@PersistenceUnit()
	EntityManagerFactory emf;
	
	public String getHelloWorld() {
		ZnUser z = (ZnUser)emf.createEntityManager().createQuery("select u from ZnUser u").getResultList().get(0);
		return "Hello World " + z.getFirstName() + " from database";
	}

}
