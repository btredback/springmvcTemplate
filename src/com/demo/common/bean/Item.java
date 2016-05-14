package com.demo.common.bean;

public class Item
{
    private String type;

    private String name;

    private String value;

    public Item() {
    }

    public Item(String type, String name, String value) {
        setType(type);
        setName(name);
        setValue(value);
    }

    public String getType()
    {
        return this.type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getValue()
    {
        return this.value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }
}