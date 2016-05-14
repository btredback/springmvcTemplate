package com.demo.common.bean;

public class LogonHostInfo
{
    private String hostName;

    private String IP;

    private String MAC;

    public String getMAC()
    {
        return this.MAC;
    }

    public void setMAC(String mac)
    {
        this.MAC = mac;
    }

    public String getHostName()
    {
        return this.hostName;
    }

    public void setHostName(String hostName)
    {
        this.hostName = hostName;
    }

    public String getIP()
    {
        return this.IP;
    }

    public void setIP(String ip)
    {
        this.IP = ip;
    }
}