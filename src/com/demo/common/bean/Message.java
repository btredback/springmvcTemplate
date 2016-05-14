package com.demo.common.bean;

import java.util.Date;

public class Message
{
    private int messageID;

    private int senderID;

    private String receiverList;

    private String copyList;

    private int messageType;

    private String messageTopic;

    private String messageBody;

    private String reciverID;

    private Date senddate;

    private String messageText;

    public String getMessageText()
    {
        return this.messageText;
    }

    public void setMessageText(String messageText)
    {
        this.messageText = messageText;
    }

    public int getMessageID()
    {
        return this.messageID;
    }

    public void setMessageID(int messageID)
    {
        this.messageID = messageID;
    }

    public int getSenderID()
    {
        return this.senderID;
    }

    public void setSenderID(int senderID)
    {
        this.senderID = senderID;
    }

    public String getReceiverList()
    {
        return this.receiverList;
    }

    public void setReceiverList(String receiverList)
    {
        this.receiverList = receiverList;
    }

    public String getCopyList()
    {
        return this.copyList;
    }

    public void setCopyList(String copyList)
    {
        this.copyList = copyList;
    }

    public int getMessageType()
    {
        return this.messageType;
    }

    public void setMessageType(int messageType)
    {
        this.messageType = messageType;
    }

    public Date getSenddate()
    {
        return this.senddate;
    }

    public void setSenddate(Date senddate)
    {
        this.senddate = senddate;
    }

    public String getReciverID()
    {
        return this.reciverID;
    }

    public void setReciverID(String reciverID)
    {
        this.reciverID = reciverID;
    }

    public String getMessageTopic()
    {
        return this.messageTopic;
    }

    public void setMessageTopic(String messageTopic)
    {
        this.messageTopic = messageTopic;
    }

    public String getMessageBody()
    {
        return this.messageBody;
    }

    public void setMessageBody(String messageBody)
    {
        this.messageBody = messageBody;
    }
}