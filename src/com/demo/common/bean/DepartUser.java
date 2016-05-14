package com.demo.common.bean;

public class DepartUser
{
    private int humanID;

    private String telephone;

    private String cellphone;

    private String unitname;

    private int homephone;

    private int transittype;

    private int exitconfirm;

    private boolean exitConfirm1;

    public int getTransittype()
    {
        return this.transittype;
    }

    public void setTransittype(int transittype)
    {
        this.transittype = transittype;
    }

    public int getExitconfirm()
    {
        return this.exitconfirm;
    }

    public void setExitconfirm(int exitconfirm)
    {
        this.exitconfirm = exitconfirm;
    }

    public boolean isExitConfirm1()
    {
        return this.exitConfirm1;
    }

    public void setExitConfirm1(boolean exitConfirm1)
    {
        this.exitConfirm1 = exitConfirm1;
    }

    public int getHumanID()
    {
        return this.humanID;
    }

    public void setHumanID(int humanID)
    {
        this.humanID = humanID;
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

    public String getUnitname()
    {
        return this.unitname;
    }

    public void setUnitname(String unitname)
    {
        this.unitname = unitname;
    }

    public int getHomephone()
    {
        return this.homephone;
    }

    public void setHomephone(int homephone)
    {
        this.homephone = homephone;
    }
}