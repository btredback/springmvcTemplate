package com.demo.common.bean;

import java.util.Date;

public class MessageStatus
{
    private int messageID;

    private int receiverID;

    private int readFlag;

    private int forwardFlag;

    private int replyFlag;

    private Date lastUpdate;

    public int getMessageID()
    {
        return this.messageID;
    }

    public void setMessageID(int messageID)
    {
        this.messageID = messageID;
    }

    public int getReceiverID()
    {
        return this.receiverID;
    }

    public void setReceiverID(int receiverID)
    {
        this.receiverID = receiverID;
    }

    public int getReadFlag()
    {
        return this.readFlag;
    }

    public void setReadFlag(int readFlag)
    {
        this.readFlag = readFlag;
    }

    public int getForwardFlag()
    {
        return this.forwardFlag;
    }

    public void setForwardFlag(int forwardFlag)
    {
        this.forwardFlag = forwardFlag;
    }

    public int getReplyFlag()
    {
        return this.replyFlag;
    }

    public void setReplyFlag(int replyFlag)
    {
        this.replyFlag = replyFlag;
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