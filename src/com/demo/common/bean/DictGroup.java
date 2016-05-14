package com.demo.common.bean;

public class DictGroup
{
    private int groupId;

    private String groupName;

    private String comments;

    private int parentId;

    private int dispOrder;

    public DictGroup() {
    }

    public DictGroup(int groupId, int parentId, int dispOrder) {
        this.groupId = groupId;
        this.parentId = parentId;
        this.groupName = ("新增分组" + groupId);
        this.dispOrder = dispOrder;
    }

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

    public int getParentId()
    {
        return this.parentId;
    }

    public void setParentId(int parentId)
    {
        this.parentId = parentId;
    }

    public int getDispOrder()
    {
        return this.dispOrder;
    }

    public void setDispOrder(int dispOrder)
    {
        this.dispOrder = dispOrder;
    }

    public String getComments()
    {
        return this.comments;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
    }
}