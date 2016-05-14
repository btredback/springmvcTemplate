package com.demo.common.bean;

import java.util.Date;

public class Dict
{
    private int dictID;

    private String dictName;

    private String dictCnName;

    private Date createDate;

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    public Date getCreateDate()
    {
        return this.createDate;
    }

    public int getDictID()
    {
        return this.dictID;
    }

    public void setDictID(int dictID)
    {
        this.dictID = dictID;
    }

    public String getDictName()
    {
        return this.dictName;
    }

    public void setDictName(String dictName)
    {
        this.dictName = dictName;
    }

    public String getDictCnName()
    {
        return this.dictCnName;
    }

    public void setDictCnName(String dictCnName)
    {
        this.dictCnName = dictCnName;
    }
}