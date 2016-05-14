package com.demo.common.bean;

public class FormButton
{
    int buttonID;

    int bizID;

    int subBizID;

    String buttonName;

    int tableID;

    int fieldID;

    String iconCls;

    String actionType;

    String actionParams;

    String actionUrl;

    String validateBeforeAction;

    String buttonContent;

    String filterSql;

    public int getButtonID()
    {
        return this.buttonID;
    }

    public void setButtonID(int buttonID)
    {
        this.buttonID = buttonID;
    }

    public int getBizID()
    {
        return this.bizID;
    }

    public void setBizID(int bizID)
    {
        this.bizID = bizID;
    }

    public int getSubBizID()
    {
        return this.subBizID;
    }

    public void setSubBizID(int subBizID)
    {
        this.subBizID = subBizID;
    }

    public String getButtonName()
    {
        return this.buttonName;
    }

    public void setButtonName(String buttonName)
    {
        this.buttonName = buttonName;
    }

    public int getTableID()
    {
        return this.tableID;
    }

    public void setTableID(int tableID)
    {
        this.tableID = tableID;
    }

    public int getFieldID()
    {
        return this.fieldID;
    }

    public void setFieldID(int fieldID)
    {
        this.fieldID = fieldID;
    }

    public String getIconCls()
    {
        return this.iconCls;
    }

    public void setIconCls(String iconCls)
    {
        this.iconCls = iconCls;
    }

    public String getActionType()
    {
        return this.actionType;
    }

    public void setActionType(String actionType)
    {
        this.actionType = actionType;
    }

    public String getActionParams()
    {
        return this.actionParams;
    }

    public void setActionParams(String actionParams)
    {
        this.actionParams = actionParams;
    }

    public String getActionUrl()
    {
        return this.actionUrl;
    }

    public void setActionUrl(String actionUrl)
    {
        this.actionUrl = actionUrl;
    }

    public String getValidateBeforeAction()
    {
        return this.validateBeforeAction;
    }

    public void setValidateBeforeAction(String validateBeforeAction)
    {
        this.validateBeforeAction = validateBeforeAction;
    }

    public String getButtonContent()
    {
        return this.buttonContent;
    }

    public void setButtonContent(String buttonContent)
    {
        this.buttonContent = buttonContent;
    }

    public String getFilterSql()
    {
        return this.filterSql;
    }

    public void setFilterSql(String filterSql)
    {
        this.filterSql = filterSql;
    }
}