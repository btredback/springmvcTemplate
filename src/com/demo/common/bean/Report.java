package com.demo.common.bean;

import java.util.Date;

public class Report
{
    private Integer reportID;

    private String reportName;

    private String reportCnName;

    private Date createDate;

    private String reportType;

    private String pageSize;

    private int isLandscape;

    private String pageID;

    private String isTemplate;

    private Integer isPrintLog = Integer.valueOf(0);

    private Integer printCount;

    private Integer overPrint = Integer.valueOf(0);

    private Date lastupdate;

    private int listMultiple;

    private String listTableName;

    private String listFieldName;

    private String reportEngine;

    public int getListMultiple()
    {
        return this.listMultiple;
    }

    public void setListMultiple(int listMultiple)
    {
        this.listMultiple = listMultiple;
    }

    public String getListTableName()
    {
        return this.listTableName;
    }

    public void setListTableName(String listTableName)
    {
        this.listTableName = listTableName;
    }

    public String getListFieldName()
    {
        return this.listFieldName;
    }

    public void setListFieldName(String listFieldName)
    {
        this.listFieldName = listFieldName;
    }

    public Date getLastupdate()
    {
        return this.lastupdate;
    }

    public void setLastupdate(Date lastupdate)
    {
        this.lastupdate = lastupdate;
    }

    public Integer getOverPrint()
    {
        return this.overPrint;
    }

    public void setOverPrint(Integer overPrint)
    {
        this.overPrint = overPrint;
    }

    public Integer getPrintCount()
    {
        return this.printCount;
    }

    public void setPrintCount(Integer printCount)
    {
        this.printCount = Integer.valueOf(printCount.intValue() < -1 ? -1 : printCount.intValue());
    }

    public Integer getIsPrintLog()
    {
        return this.isPrintLog;
    }

    public void setIsPrintLog(Integer isPrintLog)
    {
        this.isPrintLog = isPrintLog;
    }

    public Integer getReportID()
    {
        return this.reportID;
    }

    public void setReportID(Integer reportID)
    {
        this.reportID = reportID;
    }

    public String getReportName()
    {
        return this.reportName;
    }

    public void setReportName(String reportName)
    {
        this.reportName = reportName;
    }

    public Date getCreateDate()
    {
        return this.createDate;
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    public String getReportCnName()
    {
        return this.reportCnName;
    }

    public void setReportCnName(String reportCnName)
    {
        this.reportCnName = reportCnName;
    }

    public String getReportType()
    {
        return this.reportType;
    }

    public void setReportType(String reportType)
    {
        this.reportType = reportType;
    }

    public String getPageID()
    {
        return this.pageID;
    }

    public void setPageID(String pageID)
    {
        this.pageID = pageID;
    }

    public String getIsTemplate()
    {
        return this.isTemplate;
    }

    public void setIsTemplate(String isTemplate)
    {
        this.isTemplate = isTemplate;
    }

    public String toString()
    {
        return this.reportCnName + ", " + this.reportID;
    }

    public String getPageSize()
    {
        return this.pageSize;
    }

    public void setPageSize(String pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getIsLandscape()
    {
        return this.isLandscape;
    }

    public void setIsLandscape(int isLandscape)
    {
        this.isLandscape = isLandscape;
    }

    public String getReportEngine()
    {
        return this.reportEngine;
    }

    public void setReportEngine(String reportEngine)
    {
        this.reportEngine = reportEngine;
    }
}