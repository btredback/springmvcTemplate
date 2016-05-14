package com.demo.common.bean;

public class PageGroup
{
    private int groupID;

    private String groupName;

    private Integer groupType;

    private String groupTip;

    private Integer dispField;

    private Integer tabView;

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

    public Integer getGroupType()
    {
        return this.groupType;
    }

    public void setGroupType(Integer groupType)
    {
        this.groupType = groupType;
    }

    public String getGroupTip()
    {
        return this.groupTip;
    }

    public void setGroupTip(String groupTip)
    {
        this.groupTip = groupTip;
    }

    public Integer getDispField()
    {
        return this.dispField;
    }

    public void setDispField(Integer dispField)
    {
        this.dispField = dispField;
    }

    public Integer getTabView()
    {
        return this.tabView;
    }

    public void setTabView(Integer tabView)
    {
        this.tabView = tabView;
    }
}