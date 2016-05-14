package com.demo.common.bean;

public class StatParam
{
    private Integer paramId;

    private Integer statId;

    private String paramName;

    private String paramCnName;

    private Integer dataType;

    private Integer defaultOperator;

    private String availableOperator;

    private String defaultValue;

    private Integer dispOrder;

    private Integer paramDictID;

    public Integer getParamDictID()
    {
        return this.paramDictID;
    }

    public void setParamDictID(Integer paramDictID)
    {
        this.paramDictID = paramDictID;
    }

    public Integer getParamId()
    {
        return this.paramId;
    }

    public void setParamId(Integer paramId)
    {
        this.paramId = paramId;
    }

    public Integer getStatId()
    {
        return this.statId;
    }

    public void setStatId(Integer statId)
    {
        this.statId = statId;
    }

    public String getParamName()
    {
        return this.paramName;
    }

    public void setParamName(String paramName)
    {
        this.paramName = paramName;
    }

    public Integer getDataType()
    {
        return this.dataType;
    }

    public void setDataType(Integer dataType)
    {
        this.dataType = dataType;
    }

    public Integer getDefaultOperator()
    {
        return this.defaultOperator;
    }

    public void setDefaultOperator(Integer defaultOperator)
    {
        this.defaultOperator = defaultOperator;
    }

    public String getDefaultValue()
    {
        return this.defaultValue;
    }

    public void setDefaultValue(String defaultValue)
    {
        this.defaultValue = defaultValue;
    }

    public Integer getDispOrder()
    {
        return this.dispOrder;
    }

    public void setDispOrder(Integer dispOrder)
    {
        this.dispOrder = dispOrder;
    }

    public String getParamCnName()
    {
        return this.paramCnName;
    }

    public void setParamCnName(String paramCnName)
    {
        this.paramCnName = paramCnName;
    }

    public String getAvailableOperator()
    {
        return this.availableOperator;
    }

    public void setAvailableOperator(String availableOperator)
    {
        this.availableOperator = availableOperator;
    }
}