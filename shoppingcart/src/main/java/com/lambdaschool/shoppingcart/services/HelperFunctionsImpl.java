package com.lambdaschool.shoppingcart.services;

import com.lambdaschool.shoppingcart.models.User;
import com.lambdaschool.shoppingcart.models.ValidationError;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "helperFunctions")
public class HelperFunctionsImpl implements HelperFunctions
{
    @Override
    public List<ValidationError> getConstraintViolation(Throwable cause)
    {
        while ((cause != null) && !(cause instanceof ConstraintViolationException))
        {
            cause = cause.getCause();
        }

        List<ValidationError> listVE = new ArrayList<>();

        if (cause != null)
        {
            ConstraintViolationException ex = (ConstraintViolationException) cause;
            for (ConstraintViolation cv : ex.getConstraintViolations())
            {
                ValidationError newVe = new ValidationError();
                newVe.setCode(cv.getInvalidValue()
                        .toString());
                newVe.setMessage(cv.getMessage());
                listVE.add(newVe);
            }
        }
        return listVE;
    }

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
