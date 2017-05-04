package com.endava.spring.service;

import com.endava.spring.dao.UserDao;
import com.endava.spring.model.Role;
import com.endava.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by sbogdanschi on 2/05/2017.
 */
@Service("userDetailsService")
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUserName(username);
        List<GrantedAuthority> authorities =
                buildUserAuthority(user.getRoles());

        return buildUserForAuthentication(user, authorities);
    }

    private UserDetails buildUserForAuthentication(User user,
                                            List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<Role> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<>();

        // Build user's authorities
        for (Role userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
        }

        return new ArrayList<>(setAuths);
    }

}
