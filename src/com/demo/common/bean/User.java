package com.demo.common.bean;

import java.util.Date;

public class User
{
    private int humanID;

    private String humanName;

    private int unitID;

    private int gender;

    private int status;

    private String telephone;

    private String cellphone;

    private String email;

    private int dispOrder;

    private String homephone;

    private String zipcode;

    private String address;

    private String cantonCode;

    private Integer adminEnable;

    private String humanCode;

    private String password;

    private User agent;

    private Date loginFailDate;

    private Integer loginFailCount;

    public Date getLoginFailDate()
    {
        return this.loginFailDate;
    }

    public void setLoginFailDate(Date loginFailDate)
    {
        this.loginFailDate = loginFailDate;
    }

    public Integer getLoginFailCount()
    {
        return this.loginFailCount;
    }

    public void setLoginFailCount(Integer loginFailCount)
    {
        this.loginFailCount = loginFailCount;
    }

    public String getHumanCode()
    {
        return this.humanCode;
    }

    public void setHumanCode(String humanCode)
    {
        this.humanCode = humanCode;
    }

    public int getHumanID()
    {
        return this.humanID;
    }

    public void setHumanID(int humanID)
    {
        this.humanID = humanID;
    }

    public String getHumanName()
    {
        return this.humanName;
    }

    public void setHumanName(String humanName)
    {
        this.humanName = humanName;
    }

    public int getUnitID()
    {
        return this.unitID;
    }

    public void setUnitID(int unitID)
    {
        this.unitID = unitID;
    }

    public int getGender()
    {
        return this.gender;
    }

    public void setGender(int gender)
    {
        this.gender = gender;
    }

    public int getStatus()
    {
        return this.status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getTelephone()
    {
        return this.telephone;
    }

    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    public String getCellphone()
    {
        return this.cellphone;
    }

    public void setCellphone(String cellphone)
    {
        this.cellphone = cellphone;
    }

    public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public int getDispOrder()
    {
        return this.dispOrder;
    }

    public void setDispOrder(int dispOrder)
    {
        this.dispOrder = dispOrder;
    }

    public String getHomephone()
    {
        return this.homephone;
    }

    public void setHomephone(String homephone)
    {
        this.homephone = homephone;
    }

    public String getZipcode()
    {
        return this.zipcode;
    }

    public void setZipcode(String zipcode)
    {
        this.zipcode = zipcode;
    }

    public String getAddress()
    {
        return this.address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getCantonCode()
    {
        return this.cantonCode;
    }

    public void setCantonCode(String cantonCode)
    {
        this.cantonCode = cantonCode;
    }

    public Integer getAdminEnable()
    {
        return this.adminEnable;
    }

    public void setAdminEnable(Integer adminEnable)
    {
        this.adminEnable = adminEnable;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public User getAgent()
    {
        return this.agent;
    }

    public void setAgent(User agent)
    {
        this.agent = agent;
    }
}