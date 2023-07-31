package com.example.zhangirakzhan.config;

import com.example.zhangirakzhan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.Authentication;

import java.util.Arrays;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    private final UserService myService;

    @Autowired
    public MyAuthenticationProvider(UserService myService) {
        this.myService = myService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName=authentication.getName();
        String password=authentication.getCredentials().toString();

        var user = myService.findByUsername(userName);

        if(user.getPassword().equals(password))
        {
            return new UsernamePasswordAuthenticationToken(userName,password, Arrays.asList());
        }
        else
        {
            throw new RuntimeException("Wrong credentials.");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
