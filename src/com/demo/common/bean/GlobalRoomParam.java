package com.demo.common.bean;

public class GlobalRoomParam
{
    private Integer upperLimit;

    private Integer lowerLimit;

    private String mapConfigID;

    public String getMapConfigID()
    {
        return this.mapConfigID;
    }

    public void setMapConfigID(String mapConfigID)
    {
        this.mapConfigID = mapConfigID;
    }

    public Integer getUpperLimit()
    {
        return this.upperLimit;
    }

    public void setUpperLimit(Integer upperLimit)
    {
        this.upperLimit = upperLimit;
    }

    public Integer getLowerLimit()
    {
        return this.lowerLimit;
    }

    public void setLowerLimit(Integer lowerLimit)
    {
        this.lowerLimit = lowerLimit;
    }
}