package com.demo.common.bean;

public class DictItem
{
    private Integer itemId;

    private Integer dictId;

    private Integer itemLabel;

    private String itemValue;

    private Integer parentId = Integer.valueOf(-1);

    private Integer disporder;

    public Integer getItemId()
    {
        return this.itemId;
    }

    public void setItemId(Integer itemId)
    {
        this.itemId = itemId;
    }

    public Integer getDictId()
    {
        return this.dictId;
    }

    public void setDictId(Integer dictId)
    {
        this.dictId = dictId;
    }

    public Integer getItemLabel()
    {
        return this.itemLabel;
    }

    public void setItemLabel(Integer itemLabel)
    {
        this.itemLabel = itemLabel;
    }

    public String getItemValue()
    {
        return this.itemValue;
    }

    public void setItemValue(String itemValue)
    {
        this.itemValue = itemValue;
    }

    public Integer getParentId()
    {
        return this.parentId;
    }

    public void setParentId(Integer parentId)
    {
        this.parentId = parentId;
    }

    public Integer getDisporder()
    {
        return this.disporder;
    }

    public void setDisporder(Integer disporder)
    {
        this.disporder = disporder;
    }
}