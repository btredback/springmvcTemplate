package com.demo.common.bean;

public class StatGroup
{
    private int groupId;

    private String groupName;

    private int disporder;

    private int parentId;

    private Integer gisStatGroup;

    public int getGroupId()
    {
        return this.groupId;
    }

    public void setGroupId(int groupId)
    {
        this.groupId = groupId;
    }

    public String getGroupName()
    {
        return this.groupName;
    }

    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }

    public int getDisporder()
    {
        return this.disporder;
    }

    public void setDisporder(int disporder)
    {
        this.disporder = disporder;
    }

    public int getParentId()
    {
        return this.parentId;
    }

    public void setParentId(int parentId)
    {
        this.parentId = parentId;
    }

    public Integer getGisStatGroup()
    {
        return this.gisStatGroup;
    }

    public void setGisStatGroup(Integer gisStatGroup)
    {
        this.gisStatGroup = gisStatGroup;
    }
}