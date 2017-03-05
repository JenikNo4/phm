package cz.simek.phm.config;

import cz.simek.phm.dao.UserDao;
import cz.simek.phm.model.User;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jenik on 3.3.17.
 */
//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaTestConfig.class, TestConfig.class})
@Ignore
public class AuthenticationProviderFromDBTest {


    @Autowired
    private UserDao userDao;

    @Mock
    private Authentication authentication;

//
//
//    @Test
//    public void authenticateSunny() throws Exception {
//        when(userDao.findByLogin("Jenik")).thenReturn(createUser());
//        when(authentication.getPrincipal()).thenReturn("Jenik");
//        when(authentication.getCredentials()).thenReturn("password");
//        AuthenticationProviderFromDB apfd = new AuthenticationProviderFromDB();
//        Authentication authenticate = apfd.authenticate(authentication);
//        assertEquals(authenticate.getCredentials().toString(),"Jenik");
//
//    }

    private User createUser(){
        User user = new User();
        user. setId(1L);
        user.setLogin("Jenik");
        user.setPassword("password");
        user.setSurename("Novak");
        return user;
    }



}