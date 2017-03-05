package cz.simek.phm.init;

import cz.simek.phm.dao.UserDao;
import cz.simek.phm.enums.UserRole;
import cz.simek.phm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Created by jenik on 3.3.17.
 */
@Service
@Configurable
public class AuthenticationProviderFromDB implements AuthenticationManager {

    @Autowired
    private UserDao userDao;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = userDao.findByLogin(authentication.getPrincipal().toString());
        if (null == user) {
            throw new BadCredentialsException("No User Found.");
        }
        if (user.isDisabled()) {
            throw new DisabledException("User is disabled");
        }
        //if (!encoder.matches(password, user.getPassword())) {
        if(!Objects.equals(authentication.getCredentials().toString(), user.getPassword())){
            throw new BadCredentialsException("Password does not match.");
        }

        //return null;
        return new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword(),getAuthorities(user));
    }


    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        List<GrantedAuthority> authList = getGrantedAuthorities(user.getUserRole());
        return authList;
    }

    private static List<GrantedAuthority> getGrantedAuthorities(List<UserRole> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (UserRole role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.toString()));
        }
        return authorities;
    }
}
