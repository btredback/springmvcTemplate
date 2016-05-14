package com.demo.common.bean;

public class SoftwareItem
{
    private int id;

    private int parentId;

    private String name;

    private String location;

    private boolean dir;

    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getParentId()
    {
        return this.parentId;
    }

    public void setParentId(int parentId)
    {
        this.parentId = parentId;
    }

    public boolean isDir()
    {
        return this.dir;
    }

    public void setDir(boolean dir)
    {
        this.dir = dir;
    }

    public SoftwareItem() {
    }

    public SoftwareItem(String location, String name) {
        this.name = name;
        this.location = location;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLocation()
    {
        return this.location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }
}