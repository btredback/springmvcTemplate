package com.demo.common.bean;

import com.demo.util.ValueUtils;

public class FTPConfig
{
    private String host;

    private String userName;

    private String password;

    private Integer port;

    

    public FTPConfig(String host, String userName, String password, Integer port) {
        this.host = host;
        this.userName = userName;
        this.password = password;
        this.port = port;
    }

    public String getHost()
    {
        return this.host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public String getUserName()
    {
        return this.userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Integer getPort()
    {
        return this.port;
    }

    public void setPort(Integer port)
    {
        this.port = port;
    }
}