package com.demo.common.bean;

import java.util.Date;

public class Message_rf
{
    private int messageID;

    private String senderName;

    private String receiverList;

    private String copyList;

    private int messageType;

    private String messageTopic;

    private String messageBody;

    private Date senddate;

    private String attachName;

    private String reciverID;

    private int senderID;

    private String humanname;

    private int humanid;

    private String messageText;

    public String getMessageText()
    {
        return this.messageText;
    }

    public void setMessageText(String messageText)
    {
        this.messageText = messageText;
    }

    public void setHumanid(int humanid)
    {
        this.humanid = humanid;
    }

    public int getHumanid()
    {
        return this.humanid;
    }

    public void setHumanname(String humanname)
    {
        this.humanname = humanname;
    }

    public String getHumanname()
    {
        return this.humanname;
    }

    public int getMessageID()
    {
        return this.messageID;
    }

    public void setMessageID(int messageID)
    {
        this.messageID = messageID;
    }

    public String getSenderName()
    {
        return this.senderName;
    }

    public void setSenderName(String senderName)
    {
        this.senderName = senderName;
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

    public Date getSenddate()
    {
        return this.senddate;
    }

    public void setSenddate(Date senddate)
    {
        this.senddate = senddate;
    }

    public String getAttachName()
    {
        return this.attachName;
    }

    public void setAttachName(String attachName)
    {
        this.attachName = attachName;
    }

    public String getReciverID()
    {
        return this.reciverID;
    }

    public void setReciverID(String reciverID)
    {
        this.reciverID = reciverID;
    }

    public int getSenderID()
    {
        return this.senderID;
    }

    public void setSenderID(int senderID)
    {
        this.senderID = senderID;
    }
}