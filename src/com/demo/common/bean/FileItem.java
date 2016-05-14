package com.demo.common.bean;

import java.util.Date;

public class FileItem
{
    private String fileID;

    private String fileName;

    private String filePath;

    private Integer fileSize;

    private Date lastUpdate;

    private Integer humanID;

    private Integer converted;

    private String storeFileName;

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

    public Integer getFileSize()
    {
        return this.fileSize;
    }

    public void setFileSize(Integer fileSize)
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

    public Integer getHumanID()
    {
        return this.humanID;
    }

    public void setHumanID(Integer humanID)
    {
        this.humanID = humanID;
    }

    public Integer getConverted()
    {
        return this.converted;
    }

    public void setConverted(Integer converted)
    {
        this.converted = converted;
    }

    public String getStoreFileName()
    {
        return this.storeFileName;
    }

    public void setStoreFileName(String storeFileName)
    {
        this.storeFileName = storeFileName;
    }
}