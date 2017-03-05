package cz.simek.phm.service;

import cz.simek.phm.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by aleksandrs on 1/7/14.
 */
@Service
@Component(value = "userDetailsService")
@Transactional(readOnly = true)
public class MyCustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDAO;

    public MyCustomUserDetailsService(UserDao userDAO) {
        this.userDAO = userDAO;
    }

    public MyCustomUserDetailsService() {
    }

    //    @Autowired
//    private RoleDAO roleDAO;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        System.out.println(login);
        cz.simek.phm.model.User domainUser = userDAO.findByLogin(login);

        System.out.println(domainUser);

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        return new User(
            domainUser.getLogin(),
            domainUser.getPassword(),
            enabled,
            accountNonExpired,
            credentialsNonExpired,
            accountNonLocked,
            //getAuthorities((int)domainUser.getRole().getId())
            getAuthorities(null)
        );
    }

    public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
        List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
        return authList;
    }

    public List<String> getRoles(Integer role) {

        List<String> roles = new ArrayList<String>();

        if (role.intValue() == 1) {
            roles.add("ADMIN");
        } else if (role.intValue() == 2) {
            roles.add("USER");
        }
        return roles;
    }

    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
