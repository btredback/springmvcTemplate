package com.demo.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.springframework.jdbc.support.lob.LobHandler;

import com.demo.common.bean.TreeNode;


public class TreeNodeWrapper
{
    private ResultSet rs;

    private LobHandler lobHandler;

    public TreeNodeWrapper(ResultSet rs) {
        this(rs, null);
    }

    public TreeNodeWrapper(ResultSet rs, LobHandler lobHandler) {
        this.rs = rs;
        this.lobHandler = lobHandler;
    }

    public TreeNode getTreeNode() throws SQLException
    {
        TreeNode node = new TreeNode(this.rs.getString(1), this.rs.getString(2), this.rs.getString(3), this.rs.getString(4));
        node.setState("open");

        if (this.rs.getMetaData().getColumnCount() <= 4)
        {
            return node;
        }

        node.setChecked(this.rs.getInt(5) == 1);

        for (int i = 6; i <= this.rs.getMetaData().getColumnCount(); i++)
        {
            String dataType = this.rs.getMetaData().getColumnTypeName(i);
            Object value = null;
            if (("clob".equalsIgnoreCase(dataType)) && (this.lobHandler != null))
                value = this.lobHandler.getClobAsString(this.rs, i);
            else if (!"blob".equalsIgnoreCase(dataType))
            {
                value = this.rs.getObject(i);
            }
            node.addAttribute(this.rs.getMetaData().getColumnLabel(i), value);
        }
        return node;
    }
}