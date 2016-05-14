package com.demo.common.bean;

import java.util.Date;

public class Reldef
{
    private int relDefId;

    private String relDefName;

    private int srcBizId;

    private String srcBizName;

    private int dstBizId;

    private String dstBizName;

    private int disporder;

    private int createProc;

    private int groupId;

    private Date createDate;

    private int useStoredProc;

    private String stroedProcName;

    private int viewId;

    private int fetchDoc;

    private int delDoc;

    public int getRelDefId()
    {
        return this.relDefId;
    }

    public void setRelDefId(int relDefId)
    {
        this.relDefId = relDefId;
    }

    public String getRelDefName()
    {
        return this.relDefName;
    }

    public void setRelDefName(String relDefName)
    {
        this.relDefName = relDefName;
    }

    public int getSrcBizId()
    {
        return this.srcBizId;
    }

    public void setSrcBizId(int srcBizId)
    {
        this.srcBizId = srcBizId;
    }

    public String getSrcBizName()
    {
        return this.srcBizName;
    }

    public void setSrcBizName(String srcBizName)
    {
        this.srcBizName = srcBizName;
    }

    public int getDstBizId()
    {
        return this.dstBizId;
    }

    public void setDstBizId(int dstBizId)
    {
        this.dstBizId = dstBizId;
    }

    public String getDstBizName()
    {
        return this.dstBizName;
    }

    public void setDstBizName(String dstBizName)
    {
        this.dstBizName = dstBizName;
    }

    public int getDisporder()
    {
        return this.disporder;
    }

    public void setDisporder(int disporder)
    {
        this.disporder = disporder;
    }

    public int getCreateProc()
    {
        return this.createProc;
    }

    public void setCreateProc(int createProc)
    {
        this.createProc = createProc;
    }

    public int getGroupId()
    {
        return this.groupId;
    }

    public void setGroupId(int groupId)
    {
        this.groupId = groupId;
    }

    public Date getCreateDate()
    {
        return this.createDate;
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    public int getUseStoredProc()
    {
        return this.useStoredProc;
    }

    public void setUseStoredProc(int useStoredProc)
    {
        this.useStoredProc = useStoredProc;
    }

    public String getStroedProcName()
    {
        return this.stroedProcName;
    }

    public void setStroedProcName(String stroedProcName)
    {
        this.stroedProcName = stroedProcName;
    }

    public int getViewId()
    {
        return this.viewId;
    }

    public void setViewId(int viewId)
    {
        this.viewId = viewId;
    }

    public int getFetchDoc()
    {
        return this.fetchDoc;
    }

    public void setFetchDoc(int fetchDoc)
    {
        this.fetchDoc = fetchDoc;
    }

    public int getDelDoc()
    {
        return this.delDoc;
    }

    public void setDelDoc(int delDoc)
    {
        this.delDoc = delDoc;
    }
}