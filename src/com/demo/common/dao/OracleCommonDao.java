package com.demo.common.dao;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.stereotype.Component;

import com.demo.common.bean.TreeNode;
import com.demo.common.constants.Constants;



@Component
public class OracleCommonDao implements CommonDao
{
    private final Log logger = LogFactory.getLog(getClass());

    @Autowired
    protected JdbcTemplate jt = null;

    @Autowired
    protected LobHandler lobHandler;

    public NamedParameterJdbcTemplate getNamedJdbcTemplate()
    {
        return new NamedParameterJdbcTemplate(this.jt);
    }

    public SimpleJdbcCall getSimpleJdbcCall()
    {
        return new SimpleJdbcCall(this.jt);
    }

    public List select(String table, String[] filedNames, Class clazz, Object[] value)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < filedNames.length; i++)
        {
            sb.append(" and ").append(filedNames[i]).append("=? ");
        }

        String sql = String.format("select * from %1$s where 1 = 1 ", new Object[] { table });
        return this.jt.query(new StringBuilder().append(sql).append(sb.toString()).toString(), value, new BeanPropertyRowMapper(clazz));
    }

    public Object select(String table, String primaryKey, Class clazz, Object value)
    {
        String sql = String.format("select * from %1$s where %2$s = ?", new Object[] { table, primaryKey });
        List result = this.jt.query(sql, new Object[] { value }, new BeanPropertyRowMapper(clazz));
        return result.size() > 0 ? result.get(0) : null;
    }

    public List<Map<String, Object>> select(String table, String primaryKey, Object value)
    {
        String sql = String.format("select * from %1$s where %2$s = ?", new Object[] { table, primaryKey });
        return this.jt.queryForList(sql, new Object[] { value });
    }

    public <T> List<T> select(String table, Map<String, String> aliasMap, Class<T> clazz, Map<String, ?> conds)
    {
        String sql = buildSql(table, aliasMap, clazz, conds);
        Object[] values = new Object[conds.keySet().size()];
        Integer index = Integer.valueOf(0);
        for (Iterator itr = conds.keySet().iterator(); itr.hasNext();)
        {
            String key = (String) itr.next();
            Integer localInteger1 = index;
            Integer localInteger2 = index = Integer.valueOf(index.intValue() + 1);
            values[localInteger1.intValue()] = conds.get(key);
        }
        this.logger.debug(new StringBuilder().append("sqlParameters:").append(StringUtils.join(values, ",")).toString());
        return this.jt.query(sql, values, new BeanPropertyRowMapper(clazz));
    }

    public <T> List<T> select(String table, Map<String, String> aliasMap, Class<T> clazz)
    {
        String sql = buildSql(table, aliasMap, clazz);
        return this.jt.query(sql, new BeanPropertyRowMapper(clazz));
    }

    public <T> List<T> select(String table, Class<T> clazz)
    {
        String sql = buildSql(table, clazz);
        this.logger.debug(sql);
        return this.jt.query(sql, new BeanPropertyRowMapper(clazz));
    }

    public Integer queryForInt(String fieldName, String table, String[] fieldNames, Object[] values)
    {
        String sql = String.format("select %1$s from %2$s where %3$s", new Object[] { fieldName, table, new StringBuilder().append(StringUtils.join(fieldNames, " = ? and ")).append(" = ? ").toString() });

        return Integer.valueOf(this.jt.queryForInt(sql, values));
    }

    public int insert(String table, Object[] data, String[] fields)
    {
        String sql = String.format("insert into %1$s (%2$s) values (%3$s)", new Object[] { table, StringUtils.join(fields, ','), StringUtils.rightPad("?", fields.length * 2 - 1, ",?") });

        return this.jt.update(sql, data);
    }

    public List<Map<String, Object>> select(String table, Object[] data, String[] fields, String orderKey, boolean asc)
    {
        String sql = String.format("select * from %1$s where %2$s order by %3$s", new Object[] { table, new StringBuilder().append(StringUtils.join(fields, " = ? and ")).append(" = ?").toString(),
                new StringBuilder().append(orderKey).append(" ").append(asc ? "asc" : "desc").toString() });

        return this.jt.queryForList(sql, data);
    }

    public List<Map<String, Object>> selectForList(String tableName, String[] primaryKeys, String[] fieldNames, Object[] values)
    {
        String sql = String.format("select %1$s from %2$s where %3$s", new Object[] { StringUtils.join(fieldNames, ","), tableName, new StringBuilder().append(StringUtils.join(primaryKeys, " = ? and ")).append(" = ?").toString() });

        this.logger.debug(sql);
        this.logger.debug(new StringBuilder().append("sqlparameters : >>> ").append(Arrays.toString(values)).toString());
        return this.jt.queryForList(sql, values);
    }

    public List<Map<String, Object>> selectForList(String sql, Object[] params)
    {
        return this.jt.queryForList(sql, params);
    }

    public List<Map<String, Object>> selectForList(String sql)
    {
        return this.jt.queryForList(sql);
    }

    public boolean isTableExists(String tableName)
    {
        StoredProcedure func = new StoredProcedure(this.jt, " fnIsTableExist") {
        };
        func.setFunction(true);
        func.declareParameter(new SqlOutParameter("return", 4));
        func.declareParameter(new SqlParameter("TableName", 12));
        func.compile();
        Map result = func.execute(new Object[] { tableName });
        return Integer.parseInt(result.get("return").toString()) != 0;
    }

    public Object selectForObject(String tableName, String[] primaryKeys, String fieldName, Object[] values)
    {
        String sql = String.format("select %1$s from %2$s where %3$s", new Object[] { fieldName, tableName, new StringBuilder().append(StringUtils.join(primaryKeys, " = ? and ")).append(" = ?").toString() });

        return this.jt.queryForObject(sql, values, Object.class);
    }

    private <T> String buildSql(String table, Map<String, String> aliasMap, Class<T> clazz, Map<String, ?> conds)
    {
        StringBuffer sql = new StringBuffer(buildSql(table, aliasMap, clazz));
        if (conds.isEmpty())
            return sql.toString();
        sql.append(" \n where ");
        for (Iterator itr = conds.keySet().iterator(); itr.hasNext();)
        {
            String key = (String) itr.next();
            sql.append(key).append("=?,");
        }
        sql.deleteCharAt(sql.length() - 1);
        this.logger.debug(new StringBuilder().append("buildsql:").append(sql.toString()).toString());
        return sql.toString();
    }

    private <T> String buildSql(String table, Map<String, String> aliasMap, Class<T> clazz)
    {
        Field[] fields = clazz.getDeclaredFields();
        if (fields.length == 0)
            throw new RuntimeException("no fields declared in class ".concat(clazz.getName()));
        StringBuilder sb = new StringBuilder("select ");
        for (Field f : fields)
        {
            String key = f.getName();
            String keyAlias = (String) aliasMap.get(key);
            if (StringUtils.isNotBlank(keyAlias))
            {
                sb.append(keyAlias).append(" ").append(key).append(",");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" from ").append(table);
        this.logger.debug(new StringBuilder().append("buildsql:").append(sb.toString()).toString());
        return sb.toString();
    }

    private String buildSql(String table, Class<?> clazz)
    {
        Field[] fields = clazz.getDeclaredFields();
        if (fields.length == 0)
            throw new RuntimeException("no fields declared in class".concat(clazz.getName()));
        StringBuilder sb = new StringBuilder("select ");
        for (Field f : fields)
        {
            String key = f.getName();
            sb.append(key).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" from ").append(table);
        this.logger.debug(new StringBuilder().append("buildsql:").append(sb.toString()).toString());
        return sb.toString();
    }

    public int insert(String table, Class clazz, Object bean)
    {
        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(bean);
        Field[] fields = clazz.getDeclaredFields();
        List names = new ArrayList();
        List values = new ArrayList();
        for (int i = 0; i < fields.length; i++)
        {
            Field f = fields[i];
            names.add(f.getName());
            values.add(bw.getPropertyValue(f.getName()));
        }
        String sql = String.format("insert into %1$s (%2$s) values (%3$s)", new Object[] { table, StringUtils.join(names, ','), StringUtils.rightPad("?", names.size() * 2 - 1, ",?") });

        this.logger.debug(new StringBuilder().append("sqlParameters:").append(StringUtils.join(values, ",")).toString());
        this.logger.debug(sql);
        return this.jt.update(sql, values.toArray());
    }

    public int insert(String table, Class clazz, Object bean, String fields)
    {
        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(bean);
        String[] names = StringUtils.split(fields, ",");
        List values = new ArrayList();
        for (int i = 0; i < names.length; i++)
        {
            values.add(bw.getPropertyValue(names[i]));
        }
        String sql = String.format("insert into %1$s (%2$s) values (%3$s)", new Object[] { table, StringUtils.join(names, ','), StringUtils.rightPad("?", names.length * 2 - 1, ",?") });

        this.logger.debug(new StringBuilder().append("sqlParameters:").append(StringUtils.join(values, ",")).toString());
        this.logger.debug(sql);
        return this.jt.update(sql, values.toArray());
    }

    public int insert(String table, Object[] data, String fields)
    {
        String[] names = StringUtils.split(fields, ",");
        String sql = String.format("insert into %1$s (%2$s) values (%3$s)", new Object[] { table, StringUtils.join(names, ','), StringUtils.rightPad("?", names.length * 2 - 1, ",?") });

        this.logger.debug(new StringBuilder().append("sqlParameters:").append(StringUtils.join(data, ",")).toString());
        this.logger.debug(sql);
        return this.jt.update(sql, data);
    }

    public int insert(String table, Map data, String fields)
    {
        String[] names = StringUtils.split(fields, ",");
        String sql = String.format("insert into %1$s (%2$s) values (%3$s)", new Object[] { table, StringUtils.join(names, ','), StringUtils.rightPad("?", names.length * 2 - 1, ",?") });

        List list = new ArrayList();
        for (String name : names)
        {
            list.add(data.get(name));
        }
        this.logger.debug(new StringBuilder().append("sqlParameters:").append(StringUtils.join(list, ",")).toString());
        this.logger.debug(sql);
        return this.jt.update(sql, list.toArray());
    }

    public int update(String table, String primaryKey, Class clazz, Object bean)
    {
        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(bean);
        Field[] fields = clazz.getDeclaredFields();
        List names = new ArrayList();
        List values = new ArrayList();
        Object pkValue = null;
        for (int i = 0; i < fields.length; i++)
        {
            Field f = fields[i];
            if (primaryKey.equalsIgnoreCase(f.getName()))
            {
                pkValue = bw.getPropertyValue(f.getName());
            } else
            {
                names.add(f.getName());
                values.add(bw.getPropertyValue(f.getName()));
            }
        }
        values.add(pkValue);
        String sql = String.format("update %1$s set %2$s where %3$s", new Object[] { table, new StringBuilder().append(StringUtils.join(names, "=?,")).append("=?").toString(), new StringBuilder().append(primaryKey).append(" = ?").toString() });

        this.logger.debug(new StringBuilder().append("sqlParameters:").append(StringUtils.join(values, ",")).toString());
        this.logger.debug(sql);
        return this.jt.update(sql, values.toArray());
    }

    public int updateWithIgnoreFields(String table, String primaryKey, Class clazz, Object bean, String[] ignoreFields)
    {
        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(bean);
        Field[] fields = clazz.getDeclaredFields();
        List names = new ArrayList();
        List values = new ArrayList();
        Object pkValue = null;
        for (int i = 0; i < fields.length; i++)
        {
            Field f = fields[i];
            if (primaryKey.equalsIgnoreCase(f.getName()))
            {
                pkValue = bw.getPropertyValue(f.getName());
            } else
            {
                boolean isIgnore = false;
                int j = 0;
                for (int len = ignoreFields.length; j < len; j++)
                {
                    if (ignoreFields[j].equalsIgnoreCase(f.getName()))
                    {
                        isIgnore = true;
                        break;
                    }
                }
                if (!isIgnore)
                {
                    names.add(f.getName());
                    values.add(bw.getPropertyValue(f.getName()));
                }
            }
        }
        values.add(pkValue);
        String sql = String.format("update %1$s set %2$s where %3$s", new Object[] { table, new StringBuilder().append(StringUtils.join(names, "=?,")).append("=?").toString(), new StringBuilder().append(primaryKey).append(" = ?").toString() });

        this.logger.debug(new StringBuilder().append(sql).append(",params:").append(values.toString()).toString());
        return this.jt.update(sql, values.toArray());
    }

    public int update(String table, String primaryKey, Map data, String fields)
    {
        String[] names = fields.split(",");
        List values = new ArrayList();
        Object pkValue = null;
        for (int i = 0; i < names.length; i++)
        {
            if (primaryKey.equalsIgnoreCase(names[i]))
            {
                pkValue = data.get(primaryKey);
            }
            values.add(data.get(names[i]));
        }

        values.add(pkValue);
        String sql = String.format("update %1$s set %2$s where %3$s", new Object[] { table, new StringBuilder().append(StringUtils.join(names, "=?,")).append("=?").toString(), new StringBuilder().append(primaryKey).append(" = ?").toString() });

        this.logger.debug(new StringBuilder().append("sqlParameters:").append(StringUtils.join(values, ",")).toString());
        this.logger.debug(sql);
        return this.jt.update(sql, values.toArray());
    }

    public int update(String table, String primaryKey, Object bean, String fields)
    {
        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(bean);
        String[] names = fields.split(",");
        List values = new ArrayList();
        Object pkValue = null;
        for (int i = 0; i < names.length; i++)
        {
            if (primaryKey.equalsIgnoreCase(names[i]))
            {
                continue;
            }
            values.add(bw.getPropertyValue(names[i]));
        }
        pkValue = bw.getPropertyValue(primaryKey);
        values.add(pkValue);
        String sql = String.format("update %1$s set %2$s where %3$s", new Object[] { table, new StringBuilder().append(StringUtils.join(names, "=?,")).append("=?").toString(), new StringBuilder().append(primaryKey).append(" = ?").toString() });

        this.logger.debug(new StringBuilder().append("sqlParameters:").append(StringUtils.join(values, ",")).toString());
        this.logger.debug(sql);
        return this.jt.update(sql, values.toArray());
    }

    public int delete(String table, String primaryKey, int value)
    {
        String sql = String.format("delete from %1$s where %2$s = ?", new Object[] { table, primaryKey });
        this.logger.debug(new StringBuilder().append("sqlParameters:").append(value).toString());
        this.logger.debug(sql);
        return this.jt.update(sql, new Object[] { Integer.valueOf(value) });
    }

    public int delete(String table, String primaryKey, String value)
    {
        String sql = String.format("delete from %1$s where %2$s = ?", new Object[] { table, primaryKey });
        this.logger.debug(new StringBuilder().append("sqlParameters:").append(value).toString());
        this.logger.debug(sql);
        return this.jt.update(sql, new Object[] { value });
    }

    public int delete(String table, Object[] values, String primarykeys)
    {
        String sql = String.format("delete from %1$s where %2$s", new Object[] { table, new StringBuilder().append(primarykeys.replaceAll(",", "=? and ")).append("=?").toString() });
        this.logger.debug(new StringBuilder().append("sqlParameters:").append(StringUtils.join(values, ",")).toString());
        this.logger.debug(sql);
        return this.jt.update(sql, values);
    }

    public synchronized int getAvaiableID(String table, String primaryKey, boolean asc)
    {
        String sql = null;
        if (asc)
            sql = String.format("select nvl(max(%1$s),0) + 1 from  %2$s", new Object[] { primaryKey, table });
        else
        {
            sql = String.format("select nvl(min(%1$s),-1) - 1 from  %2$s", new Object[] { primaryKey, table });
        }
        this.logger.debug(sql);
        return this.jt.queryForInt(sql);
    }

    public List<TreeNode> makeTree(String sql, final String[] params, final String expandTypes, final boolean sync)
    {
        return (List) this.jt.query(sql, new ResultSetExtractor() {
            @Override
            public List<TreeNode> extractData(ResultSet rs) throws SQLException, DataAccessException
            {
                List expands = null;
                if (!StringUtils.isEmpty(expandTypes))
                    expands = Arrays.asList(expandTypes.split(","));
                else
                {
                    expands = new ArrayList();
                }
                List<TreeNode> list = new ArrayList();
                Stack pNodes = new Stack();
                int columnCount = rs.getMetaData().getColumnCount();
                boolean hasCheckedColumn = (columnCount >= 6) && ("checked".equalsIgnoreCase(rs.getMetaData().getColumnLabel(6)));
                int pLevel = 0;
                while (rs.next())
                {
                    TreeNode node = new TreeNode();
                    node.setId(rs.getString(1));
                    node.setText(rs.getString(2));
                    if (pNodes.size() == 0)
                    {
                        pNodes.push(node);
                    }
                    List children = new ArrayList();
                    node.setChildren(children);
                    int level = rs.getInt(4);
                    if (level == 1)
                    {
                        list.add(node);
                    } else
                    {
                        while (level - pLevel != 1)
                        {
                            pNodes.pop();
                            pLevel--;
                        }
                        ((TreeNode) pNodes.peek()).getChildren().add(node);
                    }
                    pNodes.push(node);
                    Map attributes = new HashMap();
                    String type = rs.getString(5);
                    attributes.put("type", type);
                    if ((expands.contains(type)) || (level == 1))
                        node.setState("open");
                    else
                    {
                        node.setState("closed");
                    }

                    if (hasCheckedColumn)
                    {
                        node.setChecked(rs.getInt(6) == 1);
                    }
                    node.setAttributes(attributes);
                    node.setIconCls("icon-" + type);

                    if ((params != null) && (params.length > 0))
                    {
                        for (int i = 0; i < params.length; i++)
                        {
                            attributes.put(params[i], rs.getString(6 + i));
                        }
                    } else
                    {
                        for (int i = 7; i <= columnCount; i++)
                        {
                            attributes.put(rs.getMetaData().getColumnName(i), rs.getString(i));
                        }
                    }
                    pLevel = level;
                }
                if (sync)
                {
                    for (TreeNode treeNode : list)
                    {
                        OracleCommonDao.this.setNodeState(treeNode);
                    }
                }
                return list;
            }
        });
    }

    private void setNodeState(TreeNode node)
    {
        if ((node.getChildren().size() == 0) && (node.getState().equals("closed")))
        {
            node.setState("open");
        }
        for (TreeNode treeNode : node.getChildren())
            setNodeState(treeNode);
    }

    public List<TreeNode> makeTree(String sql)
    {
        return makeTree(sql, null, null, true);
    }

    public List<TreeNode> makeTree(String sql, String[] params)
    {
        return makeTree(sql, params, null, true);
    }

    public List<TreeNode> makeTree(String sql, String[] params, String expandTypes)
    {
        return makeTree(sql, params, expandTypes, true);
    }

    public Map<String, Object> selectForMap(String tableName, String[] primaryKeys, String fieldName, Object[] values)
    {
        String sql = String.format("select %1$s from %2$s where %3$s", new Object[] { fieldName, tableName, new StringBuilder().append(StringUtils.join(primaryKeys, " = ? and ")).append(" = ?").toString() });

        return this.jt.queryForMap(sql, values);
    }

    public Map<String, Object> selectForMap(String tableName, String[] primaryKeys, String[] fieldNames, Object[] values)
    {
        String sql = String.format("select %1$s from %2$s where %3$s", new Object[] { StringUtils.join(fieldNames, ","), tableName, new StringBuilder().append(StringUtils.join(primaryKeys, " = ? and ")).append(" = ?").toString() });

        return this.jt.queryForMap(sql, values);
    }

    public String selectForString(String tableName, String[] primaryKeys, String fieldName, Object[] values)
    {
        String sql = String.format("select %1$s from %2$s where %3$s", new Object[] { fieldName, tableName, new StringBuilder().append(StringUtils.join(primaryKeys, " = ? and ")).append(" = ?").toString() });

        this.logger.debug(sql);
        this.logger.debug(new StringBuilder().append("sqlParameters : >>> ").append(Arrays.toString(values)).toString());
        List list = this.jt.query(sql, values, new SingleColumnRowMapper(String.class));
        return list.size() == 0 ? "" : (String) list.get(0);
    }

    public int update(String table, String[] fields, String[] primaryKeys, Object[] values)
    {
        String sql = String.format("update %1$s set %2$s where %3$s", new Object[] { table, new StringBuilder().append(StringUtils.join(fields, "=?,")).append("=?").toString(),
                new StringBuilder().append(StringUtils.join(primaryKeys, " = ? and ")).append(" = ?").toString() });

        this.logger.debug(new StringBuilder().append("updateSQL-").append(sql).append(String.format(new StringBuilder().append(",\nparams:").append(StringUtils.rightPad("%s", values.length * 3 - 1, ",%s")).toString(), values)).toString());
        return this.jt.update(sql, values);
    }

    public SqlRowSetMetaData getMetaData(String tableName)
    {
        String sql = MessageFormat.format("select * from {0} where 1 = 2", new Object[] { tableName });
        return this.jt.queryForRowSet(sql).getMetaData();
    }

    public void execFilter(List<TreeNode> list, int recID)
    {
        for (Iterator itr = list.iterator(); itr.hasNext();)
        {
            TreeNode node = (TreeNode) itr.next();
            Integer filterID = Integer.valueOf(-1);
            try
            {
                Integer attr = Integer.valueOf(Integer.parseInt(node.getAttributes().get("FILTERID").toString()));
                if (attr.intValue() == 0)
                    continue;
                filterID = attr;
            } catch (Exception ex)
            {
                this.logger.error(ex);
            }
            if (filterID.intValue() == -1)
            {
                continue;
            }
            boolean result = getFilterResult(filterID, Integer.valueOf(recID));
            if (!result)
            {
                itr.remove();
                continue;
            }
            if ((null != node.getChildren()) && (node.getChildren().size() != 0))
                execFilter(node.getChildren(), recID);
        }
    }

    public boolean getFilterResult(Integer filterID, Integer recID)
    {
        boolean result = true;
        String sql = "select SQLEXP from tbfilter t where t.filterID = ?";
        List list = this.jt.query(sql, new Object[] { filterID }, new RowMapper() {
            @Override
            public String mapRow(ResultSet rs, int index) throws SQLException
            {
                return rs.getString("SQLEXP");
            }
        });
        if ((list == null) || (list.size() == 0))
            return result;
        try
        {
            result = this.jt.queryForInt(((String) list.get(0)).replaceAll("%d", recID.toString())) > 0;
        } catch (Exception ex)
        {
            this.logger.error(new StringBuilder().append("exec filter error,SQL is[:").append((String) list.get(0)).append("]").toString());
        }
        return result;
    }

    public void batchUpdate(String sql, final Object[] arr, final Object[] ids)
    {
        int[] result = this.jt.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException
            {
                int index = 0;
                if (arr != null)
                {
                    for (index = 0; index < arr.length; index++)
                    {
                        ps.setObject(index + 1, arr[index]);
                    }
                }
                ps.setObject(index + 1, ids[i]);
            }

            @Override
            public int getBatchSize()
            {
                return ids.length;
            }
        });
        if ((result == null) || (result.length != ids.length))
            throw new RuntimeException("批量更新错误");
    }

    public void updateClobAsString(String table, String clobFiled, String[] primaryKeys, final Object[] params)
    {
        String sql = String.format("update %1$s set %2$s where %3$s", new Object[] { table, new StringBuilder().append(clobFiled).append(" = ? ").toString(),
                new StringBuilder().append(StringUtils.join(primaryKeys, " = ? and ")).append(" = ?").toString() });

        this.jt.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException
            {
                OracleCommonDao.this.lobHandler.getLobCreator().setClobAsString(ps, 1, (String) params[0]);
                for (int i = 1; i < params.length; i++)
                    ps.setObject(i + 1, params[i]);
            }
        });
    }

    public int count(String table, String[] primaryKeys, Object[] params)
    {
        String sql = String.format("select count(1) from %1$s where %2$s", new Object[] { table, new StringBuilder().append(StringUtils.join(primaryKeys, " = ? and ")).append(" = ? ").toString() });

        return this.jt.queryForInt(sql, params);
    }

    public int count(String sql, Object[] params)
    {
        return this.jt.queryForInt(sql, params);
    }

    public int update(String sql, Object[] params)
    {
        return this.jt.update(sql, params);
    }

    public int update(String table, String[] primaryKeys, String[] fields, Object bean)
    {
        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(bean);
        List values = new ArrayList();

        for (int i = 0; i < fields.length; i++)
        {
            values.add(bw.getPropertyValue(fields[i]));
        }

        for (int i = 0; i < primaryKeys.length; i++)
        {
            values.add(bw.getPropertyValue(primaryKeys[i]));
        }
        String sql = String.format("update %1$s set %2$s where %3$s",
                new Object[] { table, new StringBuilder().append(StringUtils.join(fields, " = ?,")).append(" = ?").toString(), new StringBuilder().append(StringUtils.join(primaryKeys, " = ? and ")).append(" = ?").toString() });

        this.logger.debug(sql);
        this.logger.debug(new StringBuilder().append("parameters : ").append(values.toArray()).toString());
        return this.jt.update(sql, values.toArray());
    }

    public int update(String table, String[] primaryKeys, String[] fields, Map data)
    {
        List values = new ArrayList();

        int len = fields.length;
        for (int i = 0; i < len; i++)
        {
            values.add(data.get(fields[i]));
        }
        // int i = 0;
        for (int i = 0; i < primaryKeys.length; i++)
        {
            values.add(data.get(primaryKeys[i]));
        }
        String sql = String.format("update %1$s set %2$s where %3$s",
                new Object[] { table, new StringBuilder().append(StringUtils.join(fields, " = ?,")).append(" = ?").toString(), new StringBuilder().append(StringUtils.join(primaryKeys, " = ? and ")).append(" = ?").toString() });

        return this.jt.update(sql, values.toArray());
    }

    public int delete(String table, String[] primaryKeys, Map data)
    {
        List values = new ArrayList();
        for (String key : primaryKeys)
        {
            values.add(data.get(key));
        }
        String sql = String.format("delete from %1$s where %2$s", new Object[] { table, new StringBuilder().append(StringUtils.join(primaryKeys, " = ? and ")).append(" = ?").toString() });

        return this.jt.update(sql, values.toArray());
    }

    public int getSeqID(String seqName)
    {
        String sql = "select %1$s.nextval from dual";
        return this.jt.queryForInt(String.format(sql, new Object[] { seqName }));
    }

    public String getScopeFilter(Integer actType)
    {
        return getScopeFilter(actType, null);
    }

    public String getScopeFilter(Integer actType, Integer humanID)
    {
        StringBuffer buff = new StringBuffer();
        buff.append("                   and exists (select 1");
        buff.append("                          from tbactpart c,tbactdef d,tbrolehuman e");
        buff.append("                         where (nvl(c.scopeType, -1) = %3$s");
        buff.append("                               or (c.scopeType = %4$s and exists(");
        buff.append("                                  select 1 from tbactinst ax");
        buff.append("                                      where ax.recid = a.recID");
        buff.append("                                      and ax.partid = %1$s");
        buff.append("                               ))");
        buff.append("                               or (c.scopeType = %5$s and exists(");
        buff.append("                                  select 1 from tbactinst ax,tbHuman az,tbhuman bx");
        buff.append("                                      where ax.recid = a.recID");
        buff.append("                                      and ax.partid = az.humanID");
        buff.append("                                      and az.unitid = bx.unitid");
        buff.append("                                      and bx.humanid = %1$s");
        buff.append("\t\t\t\t\t\t\t\t   union all ");
        buff.append("                                  select 1 from tbactinst ax,tbrole az,tbhuman bx");
        buff.append("                                      where ax.recid = a.recID");
        buff.append("                                      and ax.roleid = az.roleID");
        buff.append("                                      and az.unitid = bx.unitid");
        buff.append("                                      and bx.humanid = %1$s");
        buff.append("                               ))");
        buff.append("                               or (c.scopeType = %6$s and exists(");
        buff.append("                                  select 1 from tbrolehuman a,tbrolecanton b,tbrec c");
        buff.append("                                        where a.humanid = %1$s");
        buff.append("                                        and b.roleid = a.roleid");
        buff.append("                                        and b.cantoncode = c.cantoncode");
        buff.append("                                        and c.recid = a.recID");
        buff.append("                               ))");
        buff.append("                               or (c.scopeType = %7$s and exists(");
        buff.append("                                  select 1 from tbactinst ax");
        buff.append("                                      where ax.recID = a.recID");
        buff.append("                                      and instr(','||c.scopeValue||',',','||ax.partID||',') > 0");
        buff.append("                               ))");
        buff.append("                               or (c.scopeType = %8$s and exists(");
        buff.append("                                  select 1 from tbactinst ax,tbunit ay,tbhuman az");
        buff.append("                                         where ax.recID = a.recID");
        buff.append("                                         and ax.partID = az.humanid");
        buff.append("                                         and az.unitid = ay.unitid");
        buff.append("                                         and instr(','||c.scopeValue||',',','||ay.unitid||',') > 0");
        buff.append("                               ))");
        buff.append("                           )");
        buff.append("                           and c.partid = e.roleid");
        buff.append("                           and c.actdefid = d.actdefid");
        buff.append("                           and d.acttype = %2$s");
        buff.append("                           and d.procid = a.procID");
        buff.append("                           and e.humanid = %1$s)");
        return String.format(buff.toString(), new Object[] { humanID == null ? "%2$s" : humanID, actType, Constants.ScopeType.SCOPE_ALL, Constants.ScopeType.SCOPE_HUMAN_ME, Constants.ScopeType.SCOPE_UNIT_ME, Constants.ScopeType.SCOPE_CANTON_ME,
                Constants.ScopeType.SCOPE_HUMAN, Constants.ScopeType.SCOPE_UNIT });
    }

    public void dropTable(String tableName)
    {
        this.jt.execute(new StringBuilder().append("drop table ").append(tableName).toString());
    }
}