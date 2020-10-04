package com.lambdaschool.shoppingcart.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service(value = "helperFunctionsService")
public class HelperFunctionsServiceImpl implements HelperFunctionsService
{
    @Override
    public boolean isAuthorizedToMakeChange(String username)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(username.equalsIgnoreCase(auth.getName()) || auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
        {
            return true;
        } else
        {
            throw new EntityNotFoundException(auth.getName() + " not authorized to make changes");
        }
    }


}
