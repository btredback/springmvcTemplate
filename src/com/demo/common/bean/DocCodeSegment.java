package com.demo.common.bean;

public class DocCodeSegment
{
    private int docCodeID;

    private int segmentID;

    private int segType;

    private String segValue;

    private int segProp;

    private int dispOrder;

    private String cantonCode;

    private int initValue;

    public int getDocCodeID()
    {
        return this.docCodeID;
    }

    public void setDocCodeID(int docCodeID)
    {
        this.docCodeID = docCodeID;
    }

    public int getSegmentID()
    {
        return this.segmentID;
    }

    public void setSegmentID(int segmentID)
    {
        this.segmentID = segmentID;
    }

    public int getSegType()
    {
        return this.segType;
    }

    public void setSegType(int segType)
    {
        this.segType = segType;
    }

    public String getSegValue()
    {
        return this.segValue;
    }

    public void setSegValue(String segValue)
    {
        this.segValue = segValue;
    }

    public int getSegProp()
    {
        return this.segProp;
    }

    public void setSegProp(int segProp)
    {
        this.segProp = segProp;
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

    public int getInitValue()
    {
        return this.initValue;
    }

    public void setInitValue(int initValue)
    {
        this.initValue = initValue;
    }
}