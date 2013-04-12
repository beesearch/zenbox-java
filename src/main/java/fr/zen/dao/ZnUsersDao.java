package fr.zen.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import fr.zen.entities.ZnUser;

@Named
public class ZnUsersDao {

	@Inject
    private DAO dao;

    public ZnUser create(ZnUser user) {
        return dao.create(user);
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

    public ZnUser update(ZnUser user) {
        ZnUser ZnUser = dao.find(ZnUser.class, user.getId());
        if (ZnUser == null) {
            throw new IllegalArgumentException("setZnUser id " + user.getId() + " not found");
        }

        return dao.update(user);
    }

}
