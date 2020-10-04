package com.lambdaschool.shoppingcart.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "userroles")
@IdClass(UserRolesId.class)
public class UserRoles extends Auditable implements Serializable
{
    // foreign key. relationship to User
    @Id
    @ManyToOne
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties(value = "roles", allowSetters = true)
    private User user;

    // foreign key. relationship to Role
    @Id
    @ManyToOne
    @JoinColumn(name = "roleid")
    @JsonIgnoreProperties(value = "users", allowSetters = true)
    private Roles role;

    public UserRoles()
    {
        // default constructor
    }

    // constructor
    public UserRoles(User user, Roles role)
    {
        this.user = user;
        this.role = role;
    }

    // getters and setters
    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Roles getRole()
    {
        return role;
    }

    public void setRole(Roles role)
    {
        this.role = role;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (!(obj instanceof UserRoles))
        {
            return false;
        }
        UserRoles that = (UserRoles) obj;
        return ((user == null) ? 0 : user.getUserid()) == ((that.user == null) ? 0 : that.user.getUserid()) &&
                ((role == null) ? 0 : role.getRoleid()) == ((that.role == null) ? 0 : that.role.getRoleid());
    }

    @Override
    public int hashCode()
    {
        return 69;
    }
}
