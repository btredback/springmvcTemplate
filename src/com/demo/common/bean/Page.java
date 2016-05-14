package com.demo.common.bean;

import java.util.Date;

public class Page
{
    private int pageID;

    private String pageName;

    private Date createDate;

    private String pageUrl;

    private int external;

    private Integer pageType;

    private String script;

    private int alignCenter;

    public int getAlignCenter()
    {
        return this.alignCenter;
    }

    public void setAlignCenter(int alignCenter)
    {
        this.alignCenter = alignCenter;
    }

    public String getScript()
    {
        return this.script;
    }

    public void setScript(String script)
    {
        this.script = script;
    }

    public int getPageID()
    {
        return this.pageID;
    }

    public void setPageID(int pageID)
    {
        this.pageID = pageID;
    }

    public String getPageName()
    {
        return this.pageName;
    }

    public void setPageName(String pageName)
    {
        this.pageName = pageName;
    }

    public Date getCreateDate()
    {
        return this.createDate;
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    public String getPageUrl()
    {
        return this.pageUrl;
    }

    public void setPageUrl(String pageUrl)
    {
        this.pageUrl = pageUrl;
    }

    public int getExternal()
    {
        return this.external;
    }

    public void setExternal(int external)
    {
        this.external = external;
    }

    public Integer getPageType()
    {
        return this.pageType;
    }

    public void setPageType(Integer pageType)
    {
        this.pageType = pageType;
    }
}