package com.demo.common.bean;

public class PhraseItem
{
    private int phraseID;

    private int humanID;

    private int bizID;

    private String phraseName;

    private String phraseContent;

    private int displayOrder;

    public int getPhraseID()
    {
        return this.phraseID;
    }

    public void setPhraseID(int phraseID)
    {
        this.phraseID = phraseID;
    }

    public int getHumanID()
    {
        return this.humanID;
    }

    public void setHumanID(int humanID)
    {
        this.humanID = humanID;
    }

    public int getBizID()
    {
        return this.bizID;
    }

    public void setBizID(int bizID)
    {
        this.bizID = bizID;
    }

    public String getPhraseName()
    {
        return this.phraseName;
    }

    public void setPhraseName(String phraseName)
    {
        this.phraseName = phraseName;
    }

    public String getPhraseContent()
    {
        return this.phraseContent;
    }

    public void setPhraseContent(String phraseContent)
    {
        this.phraseContent = phraseContent;
    }

    public int getDisplayOrder()
    {
        return this.displayOrder;
    }

    public void setDisplayOrder(int displayOrder)
    {
        this.displayOrder = displayOrder;
    }
}