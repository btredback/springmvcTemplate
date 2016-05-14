package com.demo.common.bean;

public class ScriptTemplate
{
    private String scriptType;

    private String scriptName;

    private String sourceText;

    private String scriptDesc;

    public String getScriptType()
    {
        return this.scriptType;
    }

    public void setScriptType(String scriptType)
    {
        this.scriptType = scriptType;
    }

    public String getScriptName()
    {
        return this.scriptName;
    }

    public void setScriptName(String scriptName)
    {
        this.scriptName = scriptName;
    }

    public String getSourceText()
    {
        return this.sourceText;
    }

    public void setSourceText(String sourceText)
    {
        this.sourceText = sourceText;
    }

    public String getScriptDesc()
    {
        return this.scriptDesc;
    }

    public void setScriptDesc(String scriptDesc)
    {
        this.scriptDesc = scriptDesc;
    }
}