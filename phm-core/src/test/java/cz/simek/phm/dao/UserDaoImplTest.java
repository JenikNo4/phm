package cz.simek.phm.dao;

import cz.simek.phm.config.JpaTestConfig;
import cz.simek.phm.config.TestConfig;
import cz.simek.phm.enums.UserRole;
import cz.simek.phm.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by jenik on 31.1.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaTestConfig.class, TestConfig.class})
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:UserDaoImplTestRunBefore.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:UserDaoImplTestRunAfter.sql")})
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ApplicationContext app;

    @Test
    public void findById() throws Exception {
        User user = userDao.findById(1L);
        assertEquals(user.getId().longValue(), 1L);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testSaveUser() throws Exception {
        User user1 = createAndFillUser();
        userDao.saveNew(user1);
        User savedUser = userDao.findByLogin(user1.getLogin());
        assertEquals(user1.getEmail(), savedUser.getEmail());
        assertEquals(user1.getLogin(), savedUser.getLogin());
        assertEquals(user1.getPassword(), savedUser.getPassword());
        assertEquals(user1.getName(), savedUser.getName());
        assertEquals(user1.getPhone(), savedUser.getPhone());
        assertEquals(user1.getSurename(), savedUser.getSurename());
    }

    @Test
    public void findAllUsers() throws Exception {
        List<User> userList = userDao.findAllUsers();
        assertEquals(userList.size(), 1);
    }

    @Test
    public void findByLogin() {
        User user = userDao.findByLogin("importedLogin");
        assertEquals(user.getEmail(), "importedEmail");
        assertEquals(user.getName(), "importedName");
        assertEquals(user.getPassword(), "importedPassword");
        assertEquals(user.getPhone(), "importedPhone");
        assertEquals(user.getSurename(), "importedSurename");
        assertEquals(user.isDisabled(),false);
    }

    @Test
    public void deleteUser(){
        User user1 = createAndFillUser();
        userDao.saveNew(user1);
        User savedUser = userDao.findByLogin(user1.getLogin());
        assertEquals(savedUser.getUserRole().size(),2);
        userDao.deleteUser(savedUser);
        User login = userDao.findByLogin("login");
        assertEquals(login,null);
    }

    private User createAndFillUser() {
        User user = new User();
        user.setName("name");
        user.setSurename("surename");
        user.setLogin("login");
        user.setPassword("password");
        user.setEmail("name.surename@domain.tld");
        user.setDisabled(false);
        List<UserRole> userRoleList = new ArrayList<>();
        userRoleList.add(UserRole.ADMIN);
        userRoleList.add(UserRole.AUTHENTICATED);
        user.setUserRole(userRoleList);
        return user;
    }

}