package fr.zen.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import fr.zen.entities.ZnUser;

@Named
public class ZnUsersDao {

	@Inject
    private DAO dao;

    public ZnUser create(String firstName, String lastName, String pwd, String mail) {
        ZnUser ZnUser = new ZnUser();
        ZnUser.setFirstName(firstName);
        ZnUser.setLastName(lastName);
        ZnUser.setPassword(pwd);
        ZnUser.setEmail(mail);
        return dao.create(ZnUser);
    }

    public List<ZnUser> list(int first, int max) {
        return dao.namedFind(ZnUser.class, "ZnUser.list", first, max);
    }

    public ZnUser find(long id) {
        return dao.find(ZnUser.class, id);
    }

    public void delete(long id) {
        dao.delete(ZnUser.class, id);
    }

    public ZnUser update(long id, String firstName, String lastName, String pwd, String mail) {
        ZnUser ZnUser = dao.find(ZnUser.class, id);
        if (ZnUser == null) {
            throw new IllegalArgumentException("setZnUser id " + id + " not found");
        }

        ZnUser.setFirstName(firstName);
        ZnUser.setLastName(lastName);
        ZnUser.setPassword(pwd);
        ZnUser.setEmail(mail);
        return dao.update(ZnUser);
    }

}
