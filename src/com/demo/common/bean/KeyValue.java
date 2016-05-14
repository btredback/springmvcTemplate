package com.demo.common.bean;

public class KeyValue
{
    private String key;

    private String value;

    public String getKey()
    {
        if (this.key == null)
        {
            return "";
        }
        return this.key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getValue()
    {
        if (this.value == null)
        {
            return "";
        }
        return this.value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public boolean equals(Object obj)
    {
        if ((obj == null) || (!(obj instanceof KeyValue)))
        {
            return false;
        }
        KeyValue o = (KeyValue) obj;
        return (o.getKey().equals(getKey())) && (o.getValue().equals(getValue()));
    }
}