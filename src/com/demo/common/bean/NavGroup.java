package com.demo.common.bean;

public class NavGroup
{
    private int groupID;

    private String groupName;

    private String iconClass;

    private int dispOrder;

    private int applicationID;

    public int getGroupID()
    {
        return this.groupID;
    }

    public void setGroupID(int groupID)
    {
        this.groupID = groupID;
    }

    public String getGroupName()
    {
        return this.groupName;
    }

    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }

    public String getIconClass()
    {
        return this.iconClass;
    }

    public void setIconClass(String iconClass)
    {
        this.iconClass = iconClass;
    }

    public int getDispOrder()
    {
        return this.dispOrder;
    }

    public void setDispOrder(int dispOrder)
    {
        this.dispOrder = dispOrder;
    }

    public int getApplicationID()
    {
        return this.applicationID;
    }

    public void setApplicationID(int applicationID)
    {
        this.applicationID = applicationID;
    }
}