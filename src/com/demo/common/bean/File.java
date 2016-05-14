package com.demo.common.bean;

import java.util.Date;

public class File
{
    private String fileID;

    private String fileName;

    private String filePath;

    private int fileSize;

    private Date lastUpdate;

    public String getFileID()
    {
        return this.fileID;
    }

    public void setFileID(String fileID)
    {
        this.fileID = fileID;
    }

    public String getFileName()
    {
        return this.fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFilePath()
    {
        return this.filePath;
    }

    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public int getFileSize()
    {
        return this.fileSize;
    }

    public void setFileSize(int fileSize)
    {
        this.fileSize = fileSize;
    }

    public Date getLastUpdate()
    {
        return this.lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate)
    {
        this.lastUpdate = lastUpdate;
    }
}