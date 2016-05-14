package com.demo.common.bean;

public class Depart
{
    private int unitID;

    private String unitName;

    private int parentID;

    private String cantonCode;

    private int dispOrder;

    public int getUnitID()
    {
        return this.unitID;
    }

    public void setUnitID(int unitID)
    {
        this.unitID = unitID;
    }

    public String getUnitName()
    {
        return this.unitName;
    }

    public void setUnitName(String unitName)
    {
        this.unitName = unitName;
    }

    public int getParentID()
    {
        return this.parentID;
    }

    public void setParentID(int parentID)
    {
        this.parentID = parentID;
    }

    public int getDispOrder()
    {
        return this.dispOrder;
    }

    public void setDispOrder(int dispOrder)
    {
        this.dispOrder = dispOrder;
    }

    public String getCantonCode()
    {
        return this.cantonCode;
    }

    public void setCantonCode(String cantonCode)
    {
        this.cantonCode = cantonCode;
    }
}