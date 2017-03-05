package cz.simek.phm.service;

import cz.simek.phm.dao.UserDao;
import cz.simek.phm.enums.UserRole;
import cz.simek.phm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jenik on 31.1.17.
 */
@Service
@Transactional
public class UserService {


    @Autowired
    private UserDao userDao;

    public User printFirstUser() {
        List<User> allUsers = userDao.findAllUsers();
        if (allUsers != null) {
            return allUsers.get(0);
        }
        return null;
    }

    public void createUser(){
        User user = new User();
        user.setLogin("jenik");
        user.setName("Jenik");
        user.setPassword("jenik");
        user.setEmail("jenik");
        user.setDisabled(false);
        user.setSurename("jenik");
        List<UserRole> userRoleList = new ArrayList<>();
        userRoleList.add(UserRole.ADMIN);
        userRoleList.add(UserRole.AUTHENTICATED);
        user.setUserRole(userRoleList);
        userDao.saveNew(user);
    }



}
