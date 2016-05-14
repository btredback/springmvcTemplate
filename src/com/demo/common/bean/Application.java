package com.demo.common.bean;

import java.util.HashMap;
import java.util.Map;

public class Application
{
    private int applicationID;

    private String applicationName;

    private String guiStyle = "default";

    private String appCode;

    private String mainPage;

    private String loginPage;

    private String mainType = "default";

    private String loginType = "default";

    private String loginImage;

    private String bannerImage;

    private String uuid;

    private Map<String, String> params = new HashMap();

    public Application() {
    }

    public Application(Integer applicationID, String appCode, String guiStyle) {
        this.applicationID = applicationID.intValue();
        this.appCode = appCode;
        this.guiStyle = guiStyle;
    }

    public Application(Integer appID, String appName) {
        this.applicationID = appID.intValue();
        this.applicationName = appName;
    }

    public int getApplicationID()
    {
        return this.applicationID;
    }

    public void setApplicationID(int applicationID)
    {
        this.applicationID = applicationID;
    }

    public String getApplicationName()
    {
        return this.applicationName;
    }

    public void setApplicationName(String applicationName)
    {
        this.applicationName = applicationName;
    }

    public String getGuiStyle()
    {
        return this.guiStyle;
    }

    public void setGuiStyle(String guiStyle)
    {
        this.guiStyle = guiStyle;
    }

    public String getAppCode()
    {
        return this.appCode;
    }

    public void setAppCode(String appCode)
    {
        this.appCode = appCode;
    }

    public void setParam(String key, String value)
    {
        this.params.put(key, value);
    }

    public String getParam(String key)
    {
        return (String) this.params.get(key);
    }

    public String getMainPage()
    {
        return this.mainPage;
    }

    public void setMainPage(String mainPage)
    {
        this.mainPage = mainPage;
    }

    public String getLoginPage()
    {
        return this.loginPage;
    }

    public void setLoginPage(String loginPage)
    {
        this.loginPage = loginPage;
    }

    public Map<String, String> getParams()
    {
        return this.params;
    }

    public void setParams(Map<String, String> params)
    {
        this.params = params;
    }

    public String toString()
    {
        return this.applicationID + "," + this.applicationName;
    }

    public String getMainType()
    {
        return this.mainType;
    }

    public void setMainType(String mainType)
    {
        this.mainType = mainType;
    }

    public String getLoginType()
    {
        return this.loginType;
    }

    public void setLoginType(String loginType)
    {
        this.loginType = loginType;
    }

    public String getLoginImage()
    {
        return this.loginImage;
    }

    public void setLoginImage(String loginImage)
    {
        this.loginImage = loginImage;
    }

    public String getBannerImage()
    {
        return this.bannerImage;
    }

    public void setBannerImage(String bannerImage)
    {
        this.bannerImage = bannerImage;
    }

    public String getUuid()
    {
        return this.uuid;
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }
}