package com.lambdaschool.shoppingcart.models;

import java.io.Serializable;

public class UserRolesId implements Serializable
{
    private long userid;
    private long roleid;

    public UserRolesId()
    {
        // default contructor
    }

    // getters and setters
    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public long getRoleid() {
        return roleid;
    }

    public void setRoleid(long roleid) {
        this.roleid = roleid;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        UserRolesId that = (UserRolesId) o;
        return userid == that.userid && roleid == that.roleid;
    }

    @Override
    public int hashCode()
    {
        return 69;
    }
}
