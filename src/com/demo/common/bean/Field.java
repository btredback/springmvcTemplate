package com.demo.common.bean;

public class Field
{
    private Integer fieldID;

    private Integer tableID;

    private String fieldName;

    private String fieldCnName;

    private Integer fieldType;

    private Integer dataType;

    private Integer dispOrder;

    private Integer property;

    private String dataTypeInfo;

    private Integer nullable;

    private Integer dictType;

    private String defaultValue;

    private String fieldDesc;

    private int autoCreate;

    private int phraseEnable;

    private String fieldTypeInfo;

    private String extraInfo;

    public String getFieldTypeInfo()
    {
        return this.fieldTypeInfo;
    }

    public void setFieldTypeInfo(String fieldTypeInfo)
    {
        this.fieldTypeInfo = fieldTypeInfo;
    }

    public int getPhraseEnable()
    {
        return this.phraseEnable;
    }

    public void setPhraseEnable(int phraseEnable)
    {
        this.phraseEnable = phraseEnable;
    }

    public String getDefaultValue()
    {
        return this.defaultValue;
    }

    public void setDefaultValue(String defaultValue)
    {
        this.defaultValue = defaultValue;
    }

    public Integer getDictType()
    {
        return this.dictType;
    }

    public void setDictType(Integer dictType)
    {
        this.dictType = dictType;
    }

    public String getDataTypeInfo()
    {
        return this.dataTypeInfo;
    }

    public void setDataTypeInfo(String dataTypeInfo)
    {
        this.dataTypeInfo = dataTypeInfo;
    }

    public Integer getNullable()
    {
        return Integer.valueOf(this.nullable == null ? 1 : this.nullable.intValue());
    }

    public void setNullable(Integer nullable)
    {
        this.nullable = nullable;
    }

    public Integer getFieldID()
    {
        return this.fieldID;
    }

    public void setFieldID(Integer fieldID)
    {
        this.fieldID = fieldID;
    }

    public Integer getTableID()
    {
        return this.tableID;
    }

    public void setTableID(Integer tableID)
    {
        this.tableID = tableID;
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

    public Integer getFieldType()
    {
        return this.fieldType;
    }

    public void setFieldType(Integer fieldType)
    {
        this.fieldType = fieldType;
    }

    public Integer getDataType()
    {
        return this.dataType;
    }

    public void setDataType(Integer dataType)
    {
        this.dataType = dataType;
    }

    public Integer getDispOrder()
    {
        return this.dispOrder;
    }

    public void setDispOrder(Integer dispOrder)
    {
        this.dispOrder = dispOrder;
    }

    public Integer getProperty()
    {
        return this.property;
    }

    public void setProperty(Integer property)
    {
        this.property = property;
    }

    public String getExtraInfo()
    {
        return this.extraInfo;
    }

    public void setExtraInfo(String extraInfo)
    {
        this.extraInfo = extraInfo;
    }

    public String getFieldDesc()
    {
        return this.fieldDesc;
    }

    public void setFieldDesc(String fieldDesc)
    {
        this.fieldDesc = fieldDesc;
    }

    public int getAutoCreate()
    {
        return this.autoCreate;
    }

    public void setAutoCreate(int autoCreate)
    {
        this.autoCreate = autoCreate;
    }
}