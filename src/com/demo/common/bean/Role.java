package com.demo.common.bean;

public class Role
{
    private int roleID;

    private String roleName;

    private int unitID;

    private Integer dispOrder;

    public int getRoleID()
    {
        return this.roleID;
    }

    public void setRoleID(int roleID)
    {
        this.roleID = roleID;
    }

    public String getRoleName()
    {
        return this.roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public int getUnitID()
    {
        return this.unitID;
    }

    public void setUnitID(int unitID)
    {
        this.unitID = unitID;
    }

    public Integer getDispOrder()
    {
        return this.dispOrder;
    }

    public void setDispOrder(Integer dispOrder)
    {
        this.dispOrder = dispOrder;
    }
}