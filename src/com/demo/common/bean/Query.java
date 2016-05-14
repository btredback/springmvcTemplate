package com.demo.common.bean;

public class Query
{
    private int queryID;

    private String queryName;

    private int parentID;

    private Integer viewID;

    private String queryDesc;

    private Integer disporder;

    public Integer getDisporder()
    {
        return this.disporder;
    }

    public void setDisporder(Integer disporder)
    {
        this.disporder = disporder;
    }

    public String getQueryDesc()
    {
        return this.queryDesc;
    }

    public void setQueryDesc(String queryDesc)
    {
        this.queryDesc = queryDesc;
    }

    public Integer getViewID()
    {
        return this.viewID;
    }

    public void setViewID(Integer viewID)
    {
        this.viewID = viewID;
    }

    public int getQueryID()
    {
        return this.queryID;
    }

    public void setQueryID(int queryID)
    {
        this.queryID = queryID;
    }

    public String getQueryName()
    {
        return this.queryName;
    }

    public void setQueryName(String queryName)
    {
        this.queryName = queryName;
    }

    public int getParentID()
    {
        return this.parentID;
    }

    public void setParentID(int parentID)
    {
        this.parentID = parentID;
    }
}