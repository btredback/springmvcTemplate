package com.demo.common.bean;

import java.util.Date;

public class Table
{
    private int tableID;

    private int tableType;

    private String tableName;

    private String tableCnName;

    private int property;

    private String refObjName;

    private Integer refObjType;

    private Integer multilevel;

    private Date createDate;

    public Integer getMultilevel()
    {
        return this.multilevel;
    }

    public void setMultilevel(Integer multilevel)
    {
        this.multilevel = multilevel;
    }

    public String getRefObjName()
    {
        return this.refObjName;
    }

    public void setRefObjName(String refObjName)
    {
        this.refObjName = refObjName;
    }

    public Integer getRefObjType()
    {
        return this.refObjType;
    }

    public void setRefObjType(Integer refObjType)
    {
        this.refObjType = refObjType;
    }

    public int getTableID()
    {
        return this.tableID;
    }

    public void setTableID(int tableID)
    {
        this.tableID = tableID;
    }

    public int getTableType()
    {
        return this.tableType;
    }

    public void setTableType(int tableType)
    {
        this.tableType = tableType;
    }

    public String getTableName()
    {
        return this.tableName;
    }

    public void setTableName(String tableName)
    {
        this.tableName = tableName;
    }

    public String getTableCnName()
    {
        return this.tableCnName;
    }

    public void setTableCnName(String tableCnName)
    {
        this.tableCnName = tableCnName;
    }

    public int getProperty()
    {
        return this.property;
    }

    public void setProperty(int property)
    {
        this.property = property;
    }

    public Date getCreateDate()
    {
        return this.createDate;
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }
}