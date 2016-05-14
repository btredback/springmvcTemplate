package com.demo.common.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.demo.util.ValueUtils;



public class TreeNode
{
    public static final String TREENODE_STATE_OPEN = "open";

    public static final String TREENODE_STATE_CLOSED = "closed";

    public static final String TREE_ROOT_ID = "-1";

    public static final String TREENODE_ICON_FOLDER = "icon-group";

    public static final String TREENODE_ICON_FILE = "icon-file";

    private String id;

    private String text;

    private String pID;

    private String iconCls;

    private String state;

    private boolean checked;

    private List<TreeNode> children = new ArrayList<TreeNode>();

    private Map<String, Object> attributes;

    private String rootID;

    public TreeNode() {
    }

    public TreeNode(String id, String text) {
        this(id, text, "", "", "open", false);
    }

    public TreeNode(String id, String text, String pID, String iconCls) {
        this(id, text, pID, iconCls, "open", false);
    }

    public boolean isRootNode()
    {
        return null == this.id ? true : getRootID().equalsIgnoreCase(this.id);
    }

    public TreeNode(String id, String text, String pID, String iconCls, String state, boolean checked) {
        this.id = id;
        this.text = text;
        this.pID = pID;
        this.iconCls = iconCls;
        this.state = state;
        this.checked = checked;
    }

    @JsonIgnore
    public Object getAttribute(String key)
    {
        return this.attributes.get(key);
    }

    @JsonIgnore
    public Integer getNodeLevel()
    {
        return Integer.valueOf(ValueUtils.getInt(getAttribute("NODELEVEL"), -1));
    }

    @JsonIgnore
    public TreeNode clone()
    {
        TreeNode newNode = new TreeNode(this.id, this.text, this.pID, this.iconCls, this.state, this.checked);
        if (this.attributes == null)
            return newNode;
        Set<String> keySet = this.attributes.keySet();
        if (keySet.size() == 0)
            return newNode;
        for (Iterator<String> itr = keySet.iterator(); itr.hasNext();)
        {
            String key = (String) itr.next();
            newNode.addAttribute(key, this.attributes.get(key));
        }

        for (Iterator<TreeNode> itr = getChildren().iterator(); itr.hasNext();)
        {
            TreeNode child = (TreeNode) itr.next();
            newNode.getChildren().add(child.clone());
        }
        return newNode;
    }

    public String getId()
    {
        return this.id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getText()
    {
        return this.text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getpID()
    {
        return (null == this.pID) || ("".equals(this.pID)) ? "-1" : this.pID;
    }

    public void setpID(String pID)
    {
        this.pID = pID;
    }

    public String getIconCls()
    {
        return this.iconCls;
    }

    public void setIconCls(String iconCls)
    {
        this.iconCls = iconCls;
    }

    public String getState()
    {
        return this.state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public List<TreeNode> getChildren()
    {
        return this.children;
    }

    public void setChildren(List<TreeNode> children)
    {
        this.children = children;
    }

    public Map<String, Object> getAttributes()
    {
        return this.attributes == null ? new HashMap<String, Object>() : this.attributes;
    }

    public void setAttributes(Map<String, Object> attributes)
    {
        this.attributes = attributes;
    }

    public boolean isChecked()
    {
        return this.checked;
    }

    public void setChecked(boolean checked)
    {
        this.checked = checked;
    }

    public String getRootID()
    {
        return null == this.rootID ? "-1" : this.rootID;
    }

    public void setRootID(String rootID)
    {
        this.rootID = rootID;
    }

    public void addAttribute(String key, Object value)
    {
        if (null == this.attributes)
            this.attributes = new HashMap<String, Object>();
        this.attributes.put(key, value);
    }
}