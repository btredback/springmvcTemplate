package com.demo.common.bean;

public class Canton
{
    private int cantonID;

    private String cantonCode;

    private String cantonName;

    private String letterCode;

    private Double cantonArea;

    private int cantonLevel;

    private int dispOrder;

    private int parentID;

    private String managerUnit;

    private String forshort;

    public String getManagerUnit()
    {
        return this.managerUnit;
    }

    public void setManagerUnit(String managerUnit)
    {
        this.managerUnit = managerUnit;
    }

    public int getCantonID()
    {
        return this.cantonID;
    }

    public void setCantonID(int cantonID)
    {
        this.cantonID = cantonID;
    }

    public String getCantonName()
    {
        return this.cantonName;
    }

    public void setCantonName(String cantonName)
    {
        this.cantonName = cantonName;
    }

    public String getCantonCode()
    {
        return this.cantonCode;
    }

    public void setCantonCode(String cantonCode)
    {
        this.cantonCode = cantonCode;
    }

    public String getLetterCode()
    {
        return this.letterCode;
    }

    public void setLetterCode(String letterCode)
    {
        this.letterCode = letterCode;
    }

    public Double getCantonArea()
    {
        return this.cantonArea;
    }

    public void setCantonArea(Double cantonArea)
    {
        this.cantonArea = cantonArea;
    }

    public int getCantonLevel()
    {
        return this.cantonLevel;
    }

    public void setCantonLevel(int cantonLevel)
    {
        this.cantonLevel = cantonLevel;
    }

    public int getDispOrder()
    {
        return this.dispOrder;
    }

    public void setDispOrder(int dispOrder)
    {
        this.dispOrder = dispOrder;
    }

    public int getParentID()
    {
        return this.parentID;
    }

    public void setParentID(int parentID)
    {
        this.parentID = parentID;
    }

    public String getForshort()
    {
        return this.forshort;
    }

    public void setForshort(String forshort)
    {
        this.forshort = forshort;
    }
}