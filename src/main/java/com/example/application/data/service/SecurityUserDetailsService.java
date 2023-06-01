package com.example.application.data.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.application.data.repository.UserRepository;
import com.example.application.data.entity.User;

@Service
public class SecurityUserDetailsService implements UserDetailsService  {
    @Autowired
    private  UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).get();
        System.out.println("retrieved user:" + user.getUsername() +
                user.getPassword());
        return new MyUserPrincipal(user);
    }
    public void createUser(User user) {

        userRepository.save((User) user);
    }
    public void deleteUser(User user) {

        userRepository.delete((User) user);
    }

}