package com.lambdaschool.shoppingcart.services;

import com.lambdaschool.shoppingcart.exceptions.ResourceNotFoundException;
import com.lambdaschool.shoppingcart.models.User;
import com.lambdaschool.shoppingcart.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "securityUserService")
public class SecurityUserServiceImpl implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    @Transactional // make sure to add this because we are changing data
    @Override
    // this is where we check for username and password validation
    public UserDetails loadUserByUsername(String s) throws ResourceNotFoundException
    {
        User user = userRepository.findByUsername(s);
        if (user == null)
        {
            throw new ResourceNotFoundException("Invalid username or password");
        }

        // this converts user model to spring security
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthority());
    }
}
