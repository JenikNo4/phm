package cz.simek.phm.dao;

import cz.simek.phm.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jenik on 28.1.17.
 */
@Repository
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {


    public User findById(Long id) {
        User user = (User) getEntityManager().createQuery("SELECT u FROM User u WHERE u.id=:id", User.class).setParameter("id", id).getSingleResult();
        return user;
    }

    public void saveNew(User user) {
        persist(user);
    }

    public List<User> findAllUsers() {
        List<User> users = getEntityManager()
                .createQuery("SELECT u FROM User u ORDER BY u.name ASC", User.class)
                .getResultList();
        return users;
    }

    public User findByLogin(String login) {
        List<User> users = getEntityManager().createQuery("SELECT u FROM User u WHERE u.login=:login", User.class).setParameter("login", login).getResultList();
        if(users.isEmpty()){
            return null;
        }else if (users.size() > 1) {
            //TODO implemnent LOGGING!!!
            System.out.println("LOG more than one record found - possible data inconsistency!");
        }
        return users.get(0);
    }

    public void deleteUser(User user){
        delete(user);
    }
}
