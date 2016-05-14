package com.demo.common.bean;

import java.util.HashMap;
import java.util.Map;

public class Param
{
    Integer paramId;

    String displayName;

    String paramValue;

    Map<String, String> options = new HashMap<String, String>();

    String remark;

    String groupName;

    Integer dataTypeId;

    Integer property;

    public Integer getProperty()
    {
        return this.property;
    }

    public void setProperty(Integer property)
    {
        this.property = property;
    }

    public Integer getParamId()
    {
        return this.paramId;
    }

    public void setParamId(Integer paramId)
    {
        this.paramId = paramId;
    }

    public String getDisplayName()
    {
        return this.displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    public String getParamValue()
    {
        return this.paramValue;
    }

    public void setParamValue(String paramValue)
    {
        this.paramValue = paramValue;
    }

    public Map<String, String> getOptions()
    {
        return this.options;
    }

    public void setOptions(Map<String, String> options)
    {
        this.options = options;
    }

    public String getRemark()
    {
        return this.remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getGroupName()
    {
        return this.groupName;
    }

    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }

    public Integer getDataTypeId()
    {
        return this.dataTypeId;
    }

    public void setDataTypeId(Integer dataTypeId)
    {
        this.dataTypeId = dataTypeId;
    }
}