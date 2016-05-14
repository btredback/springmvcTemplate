package com.demo.common.bean;

public class Group
{
    private Integer groupID;

    private String groupName;

    private Integer parentID;

    private Integer dispOrder;

    public Integer getGroupID()
    {
        return this.groupID;
    }

    public void setGroupID(Integer groupID)
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

    public Integer getParentID()
    {
        return this.parentID;
    }

    public void setParentID(Integer parentID)
    {
        this.parentID = parentID;
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