package com.example.demo_restfulapi.Models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    //username: admin, password: admin - BCrypt 12 Rounds
    private static User admin = new User("admin","$2a$10$utmEDAcZEC3kj5g9F1VVA.l5arMmnJ1oq7UfIhVTrdZDT28Iao5lW",true);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.equals("admin"))
            return new UserPrincipal(admin);
        else
            return null;
    }
}