package com.demo.common.bean;

public class DataType
{
    private Integer dataTypeID;

    private String dataTypeCN;

    private String dataType;

    private String defaultLenthInfo;

    private String maxLengthInfo;

    public Integer getDataTypeID()
    {
        return this.dataTypeID;
    }

    public void setDataTypeID(Integer dataTypeID)
    {
        this.dataTypeID = dataTypeID;
    }

    public String getDataTypeCN()
    {
        return this.dataTypeCN;
    }

    public void setDataTypeCN(String dataTypeCN)
    {
        this.dataTypeCN = dataTypeCN;
    }

    public String getDataType()
    {
        return this.dataType;
    }

    public void setDataType(String dataType)
    {
        this.dataType = dataType;
    }

    public String getDefaultLenthInfo()
    {
        return this.defaultLenthInfo;
    }

    public void setDefaultLenthInfo(String defaultLenthInfo)
    {
        this.defaultLenthInfo = defaultLenthInfo;
    }

    public String getMaxLengthInfo()
    {
        return this.maxLengthInfo;
    }

    public void setMaxLengthInfo(String maxLengthInfo)
    {
        this.maxLengthInfo = maxLengthInfo;
    }
}