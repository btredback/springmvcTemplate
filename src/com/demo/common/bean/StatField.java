package com.demo.common.bean;

public class StatField
{
    private int fieldId;

    private int tableId;

    private String fieldName;

    private String fieldCnName;

    private int dataType;

    private String dataLength;

    private int dispOrder;

    private String href;

    public String getHref()
    {
        return this.href;
    }

    public void setHref(String href)
    {
        this.href = href;
    }

    public int getFieldId()
    {
        return this.fieldId;
    }

    public void setFieldId(int fieldId)
    {
        this.fieldId = fieldId;
    }

    public int getTableId()
    {
        return this.tableId;
    }

    public void setTableId(int tableId)
    {
        this.tableId = tableId;
    }

    public String getFieldName()
    {
        return this.fieldName;
    }

    public void setFieldName(String fieldName)
    {
        this.fieldName = fieldName;
    }

    public String getFieldCnName()
    {
        return this.fieldCnName;
    }

    public void setFieldCnName(String fieldCnName)
    {
        this.fieldCnName = fieldCnName;
    }

    public int getDataType()
    {
        return this.dataType;
    }

    public void setDataType(int dataType)
    {
        this.dataType = dataType;
    }

    public String getDataLength()
    {
        return this.dataLength;
    }

    public void setDataLength(String dataLength)
    {
        this.dataLength = dataLength;
    }

    public int getDispOrder()
    {
        return this.dispOrder;
    }

    public void setDispOrder(int dispOrder)
    {
        this.dispOrder = dispOrder;
    }
}