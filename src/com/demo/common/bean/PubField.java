package com.demo.common.bean;

public class PubField
{
    private Integer pubFieldId;

    private String pubFieldName;

    private String pubFieldCnName;

    private Integer pubFieldType;

    private Integer dataType;

    private Integer dataLength;

    public Integer getPubFieldId()
    {
        return this.pubFieldId;
    }

    public void setPubFieldId(Integer pubFieldId)
    {
        this.pubFieldId = pubFieldId;
    }

    public String getPubFieldName()
    {
        return this.pubFieldName;
    }

    public void setPubFieldName(String pubFieldName)
    {
        this.pubFieldName = pubFieldName;
    }

    public String getPubFieldCnName()
    {
        return this.pubFieldCnName;
    }

    public void setPubFieldCnName(String pubFieldCnName)
    {
        this.pubFieldCnName = pubFieldCnName;
    }

    public Integer getPubFieldType()
    {
        return this.pubFieldType;
    }

    public void setPubFieldType(Integer pubFieldType)
    {
        this.pubFieldType = pubFieldType;
    }

    public Integer getDataType()
    {
        return this.dataType;
    }

    public void setDataType(Integer dataType)
    {
        this.dataType = dataType;
    }

    public Integer getDataLength()
    {
        return this.dataLength;
    }

    public void setDataLength(Integer dataLength)
    {
        this.dataLength = dataLength;
    }

    public String toString()
    {
        return "pubFieldId:" + this.pubFieldId + "pubFieldName:" + this.pubFieldName + "pubFieldCnName:" + this.pubFieldCnName + "pubFieldType:" + this.pubFieldType + "dataType:" + this.dataType + "dataLength:" + this.dataLength;
    }
}