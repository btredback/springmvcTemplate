package com.demo.common.bean;

public class DocDefItem
{
    private int docDefID;

    private String docDefName;

    private int docType;

    private int bizID;

    private int copies;

    private int pages;

    private Integer groupID;

    private Integer groupType;

    public int getCopies()
    {
        return this.copies;
    }

    public void setCopies(int copies)
    {
        this.copies = copies;
    }

    public int getPages()
    {
        return this.pages;
    }

    public void setPages(int pages)
    {
        this.pages = pages;
    }

    public Integer getGroupID()
    {
        return this.groupID;
    }

    public void setGroupID(Integer groupID)
    {
        this.groupID = groupID;
    }

    public Integer getGroupType()
    {
        return this.groupType;
    }

    public void setGroupType(Integer groupType)
    {
        this.groupType = groupType;
    }

    public int getDocDefID()
    {
        return this.docDefID;
    }

    public void setDocDefID(int docDefID)
    {
        this.docDefID = docDefID;
    }

    public String getDocDefName()
    {
        return this.docDefName;
    }

    public void setDocDefName(String docDefName)
    {
        this.docDefName = docDefName;
    }

    public int getDocType()
    {
        return this.docType;
    }

    public void setDocType(int docType)
    {
        this.docType = docType;
    }

    public int getBizID()
    {
        return this.bizID;
    }

    public void setBizID(int bizID)
    {
        this.bizID = bizID;
    }
}