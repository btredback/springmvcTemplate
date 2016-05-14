package com.demo.common.bean;

public class DocCode
{
    private int docCodeID;

    private String docCodeName;

    private int groupID;

    private int recreatable;

    private int deletable;

    private int autoCreate;

    private int selectIndex;

    private int reusable;

    public int getDocCodeID()
    {
        return this.docCodeID;
    }

    public void setDocCodeID(int docCodeID)
    {
        this.docCodeID = docCodeID;
    }

    public String getDocCodeName()
    {
        return this.docCodeName;
    }

    public void setDocCodeName(String docCodeName)
    {
        this.docCodeName = docCodeName;
    }

    public int getGroupID()
    {
        return this.groupID;
    }

    public void setGroupID(int groupID)
    {
        this.groupID = groupID;
    }

    public int getRecreatable()
    {
        return this.recreatable;
    }

    public void setRecreatable(int recreatable)
    {
        this.recreatable = recreatable;
    }

    public int getDeletable()
    {
        return this.deletable;
    }

    public void setDeletable(int deletable)
    {
        this.deletable = deletable;
    }

    public int getAutoCreate()
    {
        return this.autoCreate;
    }

    public void setAutoCreate(int autoCreate)
    {
        this.autoCreate = autoCreate;
    }

    public int getReusable()
    {
        return this.reusable;
    }

    public void setReusable(int reusable)
    {
        this.reusable = reusable;
    }

    public int getSelectIndex()
    {
        return this.selectIndex;
    }

    public void setSelectIndex(int selectIndex)
    {
        this.selectIndex = selectIndex;
    }
}