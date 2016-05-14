package com.demo.common.bean;

public class QueryGroup
{
    private int groupID;

    private String groupName;

    private String groupDesc;

    private Integer disporder;

    public String getGroupDesc()
    {
        return this.groupDesc;
    }

    public void setGroupDesc(String groupDesc)
    {
        this.groupDesc = groupDesc;
    }

    public Integer getDisporder()
    {
        return this.disporder;
    }

    public void setDisporder(Integer disporder)
    {
        this.disporder = disporder;
    }

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
}