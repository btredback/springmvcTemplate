package com.demo.common.bean;

import java.util.Date;

public class ProcDef
{
    private Integer bizID = null;

    private String procDefName = null;

    private Integer procProp = null;

    private Integer scopeType = null;

    private Integer selfEditable = null;

    private Integer timerMode;

    private Integer limitTime;

    private Integer warningTime;

    private Integer delRecFilterID;

    private Integer mapOpenMode;

    private String xml;

    private Integer relView;

    private String relViewName;

    private String procID;

    private Integer active;

    private Date createDate;

    private String procDesc;

    public Integer getBizID()
    {
        return this.bizID;
    }

    public void setBizID(Integer bizID)
    {
        this.bizID = bizID;
    }

    public String getProcDefName()
    {
        return this.procDefName;
    }

    public void setProcDefName(String procDefName)
    {
        this.procDefName = procDefName;
    }

    public Integer getProcProp()
    {
        return this.procProp;
    }

    public void setProcProp(Integer procProp)
    {
        this.procProp = procProp;
    }

    public Integer getScopeType()
    {
        return this.scopeType;
    }

    public void setScopeType(Integer scopeType)
    {
        this.scopeType = scopeType;
    }

    public Integer getSelfEditable()
    {
        return this.selfEditable;
    }

    public void setSelfEditable(Integer selfEditable)
    {
        this.selfEditable = selfEditable;
    }

    public Integer getTimerMode()
    {
        return this.timerMode;
    }

    public void setTimerMode(Integer timerMode)
    {
        this.timerMode = timerMode;
    }

    public Integer getLimitTime()
    {
        return this.limitTime;
    }

    public void setLimitTime(Integer limitTime)
    {
        this.limitTime = limitTime;
    }

    public Integer getWarningTime()
    {
        return this.warningTime;
    }

    public void setWarningTime(Integer warningTime)
    {
        this.warningTime = warningTime;
    }

    public Integer getDelRecFilterID()
    {
        return this.delRecFilterID;
    }

    public void setDelRecFilterID(Integer delRecFilterID)
    {
        this.delRecFilterID = delRecFilterID;
    }

    public Integer getMapOpenMode()
    {
        return this.mapOpenMode;
    }

    public void setMapOpenMode(Integer mapOpenMode)
    {
        this.mapOpenMode = mapOpenMode;
    }

    public String getXml()
    {
        return this.xml;
    }

    public void setXml(String xml)
    {
        this.xml = xml;
    }

    public Integer getRelView()
    {
        return this.relView;
    }

    public void setRelView(Integer relView)
    {
        this.relView = relView;
    }

    public String getProcID()
    {
        return this.procID;
    }

    public void setProcID(String procID)
    {
        this.procID = procID;
    }

    public Integer getActive()
    {
        return this.active;
    }

    public void setActive(Integer active)
    {
        this.active = active;
    }

    public Date getCreateDate()
    {
        return this.createDate;
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    public String getProcDesc()
    {
        return this.procDesc;
    }

    public void setProcDesc(String procDesc)
    {
        this.procDesc = procDesc;
    }

    public String getRelViewName()
    {
        return this.relViewName;
    }

    public void setRelViewName(String relViewName)
    {
        this.relViewName = relViewName;
    }
}