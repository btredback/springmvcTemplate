package com.demo.util;

import java.util.Iterator;
import java.util.List;

import com.demo.common.bean.TreeNode;


public class TreeBuilder
{
    private List<TreeNode> nodes;

    public TreeBuilder(List<TreeNode> nodes) {
        this.nodes = nodes;
    }

    public List<TreeNode> buildTree()
    {
        return buildTree(true);
    }

    public List<TreeNode> buildTree(boolean stateSetting)
    {
        for (Iterator itr = this.nodes.iterator(); itr.hasNext();)
        {
            TreeNode info = (TreeNode) itr.next();
            if (!"-1".equalsIgnoreCase(info.getpID()))
            {
                TreeNode parentNode = getParentNode(this.nodes, info.getpID(), info.getNodeLevel());
                if (parentNode == null)
                {
                    getParentNode(this.nodes, info.getpID(), info.getNodeLevel());
                }
                if (parentNode != null)
                {
                    parentNode.getChildren().add(info);
                    itr.remove();
                }
            }
        }
        if (stateSetting)
            setTreeNodeStates(this.nodes, true);
        return this.nodes;
    }

    private TreeNode getParentNode(List<TreeNode> nodes, String id, Integer nodeLevel)
    {
        for (Iterator itr = nodes.iterator(); itr.hasNext();)
        {
            Object obj = itr.next();
            if (!(obj instanceof TreeNode))
                continue;
            TreeNode node = (TreeNode) obj;
            if ((node.getId().equalsIgnoreCase(id)) && ((node.getNodeLevel().intValue() < nodeLevel.intValue()) || (node.getNodeLevel().intValue() == -1)))
                return node;
            if (node.getChildren().size() != 0)
            {
                TreeNode target = getParentNode(node.getChildren(), id, nodeLevel);
                if (target != null)
                    return target;
            }
        }
        return null;
    }

    private void setTreeNodeStates(List<TreeNode> nodes, boolean root)
    {
        for (Iterator itr = nodes.iterator(); itr.hasNext();)
        {
            TreeNode node = (TreeNode) itr.next();
            if (root)
                node.setState("open");
            else
            {
                node.setState(node.getChildren().size() == 0 ? "open" : "closed");
            }
            if (node.getChildren().size() > 0)
                setTreeNodeStates(node.getChildren(), false);
        }
    }
}