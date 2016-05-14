package com.demo.common.bean;

import com.demo.util.BitUtils;

public class Bis
{
    private int bizID;

    private String bizName;

    private int parentID;

    private Integer dispOrder;

    private int bizType;

    private Integer isNoneProc;

    private Integer scopeType;

    private Integer bizDoccodeID;

    private Integer digDoccodeID;

    private Integer selfEditable;

    private Integer delRecFilterID;

    private Integer mapOpenMode;

    private String viewName;

    private Integer timerMode;

    private Double limitTime;

    private Double warningTime;

    public Integer getMapOpenMode()
    {
        return this.mapOpenMode;
    }

    public void setMapOpenMode(Integer mapOpenMode)
    {
        this.mapOpenMode = mapOpenMode;
    }

    public Integer getDelRecFilterID()
    {
        return this.delRecFilterID;
    }

    public void setDelRecFilterID(Integer delRecFilterID)
    {
        this.delRecFilterID = delRecFilterID;
    }

    public Integer getTimerMode()
    {
        return this.timerMode;
    }

    public void setTimerMode(Integer timerMode)
    {
        this.timerMode = timerMode;
    }

    public Double getLimitTime()
    {
        return this.limitTime;
    }

    public void setLimitTime(Double limitTime)
    {
        this.limitTime = limitTime;
    }

    public Double getWarningTime()
    {
        return this.warningTime;
    }

    public void setWarningTime(Double warningTime)
    {
        this.warningTime = warningTime;
    }

    public Integer getScopeType()
    {
        return this.scopeType;
    }

    public Integer getSelfEditable()
    {
        return this.selfEditable;
    }

    public void setSelfEditable(Integer selfEditable)
    {
        this.selfEditable = selfEditable;
    }

    public Integer getBizDoccodeID()
    {
        return this.bizDoccodeID;
    }

    public void setBizDoccodeID(Integer bizDoccodeID)
    {
        this.bizDoccodeID = bizDoccodeID;
    }

    public Integer getDigDoccodeID()
    {
        return this.digDoccodeID;
    }

    public void setDigDoccodeID(Integer digDoccodeID)
    {
        this.digDoccodeID = digDoccodeID;
    }

    public void setScopeType(Integer scopeType)
    {
        this.scopeType = scopeType;
    }

    public Integer getIsNoneProc()
    {
        return this.isNoneProc == null ? null : Integer.valueOf(BitUtils.getBitValue(this.isNoneProc.intValue(), 1));
    }

    public void setIsNoneProc(Integer isNoneProc)
    {
        this.isNoneProc = isNoneProc;
    }

    public int getBizID()
    {
        return this.bizID;
    }

    public void setBizID(int bizID)
    {
        this.bizID = bizID;
    }

    public String getBizName()
    {
        return this.bizName;
    }

    public void setBizName(String bizName)
    {
        this.bizName = bizName;
    }

    public int getParentID()
    {
        return this.parentID;
    }

    public void setParentID(int parentID)
    {
        this.parentID = parentID;
    }

    public Integer getDispOrder()
    {
        return this.dispOrder;
    }

    public void setDispOrder(Integer dispOrder)
    {
        this.dispOrder = dispOrder;
    }

    public int getBizType()
    {
        return this.bizType;
    }

    public void setBizType(int bizType)
    {
        this.bizType = bizType;
    }

    public String getViewName()
    {
        return this.viewName;
    }

    public void setViewName(String viewName)
    {
        this.viewName = viewName;
    }
}