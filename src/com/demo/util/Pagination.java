package com.demo.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class Pagination
{
    private Log logger = LogFactory.getLog(Pagination.class);

    public static final int NUMBERS_PER_PAGE = 10;

    private int numPerPage = 10;

    private int totalRows = 0;

    private int totalPages = 0;

    private int currentPage = 1;

    private int startIndex = 0;

    private int lastIndex = 0;

    private List<Map<String, Object>> resultList = null;

    private JdbcTemplate jdbcTemplate = null;

    private String querySql = null;

    private Object[] args = null;

    public Pagination() {
    }

    public Pagination(String sql, int currentPage, int numPerPage, JdbcTemplate jdbcTemplate) {
        this(sql, null, currentPage, numPerPage, jdbcTemplate);
    }

    public Pagination(String sql, Object[] args, int currentPage, int numPerPage, JdbcTemplate jdbcTemplate) {
        setQuerySql(sql);

        setArgs(args);

        setNumPerPage(numPerPage);

        setCurrentPage(currentPage);

        this.jdbcTemplate = jdbcTemplate;
    }

    public void queryForList()
    {
        setTotalRows(this.jdbcTemplate.queryForInt("select count(*) from (" + this.querySql + ")", this.args));

        setTotalPages();

        setStartIndex();

        setLastIndex();

        StringBuffer paginationSQL = new StringBuffer(" SELECT * FROM ( ");
        paginationSQL.append(" SELECT temp.* ,ROWNUM num FROM ( ");
        paginationSQL.append(this.querySql);
        paginationSQL.append("�? temp where ROWNUM <= " + this.lastIndex);
        paginationSQL.append(" ) WHERE�?um > " + this.startIndex);

        this.logger.debug("paginationSQL[" + paginationSQL + "]");

        setResultList(this.jdbcTemplate.queryForList(paginationSQL.toString(), this.args == null ? new Object[0] : this.args));
    }

    public Pagination execute()
    {
        queryForList();
        return this;
    }

    public Map<String, Object> executeForMap()
    {
        Map result = new HashMap();
        execute();
        result.put("total", Integer.valueOf(this.totalRows));
        result.put("rows", getResultList());
        return result;
    }

    public void setQuerySql(String querySql)
    {
        this.querySql = querySql;
    }

    public String getQuerySql()
    {
        return this.querySql;
    }

    public int getCurrentPage()
    {
        return this.currentPage;
    }

    public void setCurrentPage(int currentPage)
    {
        this.currentPage = currentPage;
    }

    public int getNumPerPage()
    {
        return this.numPerPage;
    }

    public void setNumPerPage(int numPerPage)
    {
        this.numPerPage = numPerPage;
    }

    public List<Map<String, Object>> getResultList()
    {
        return this.resultList;
    }

    public void setResultList(List<Map<String, Object>> resultList)
    {
        this.resultList = resultList;
    }

    public int getTotalPages()
    {
        return this.totalPages;
    }

    public void setTotalPages()
    {
        if (this.totalRows % this.numPerPage == 0)
            this.totalPages = (this.totalRows / this.numPerPage);
        else
            this.totalPages = (this.totalRows / this.numPerPage + 1);
    }

    public int getTotalRows()
    {
        return this.totalRows;
    }

    public void setTotalRows(int totalRows)
    {
        this.totalRows = totalRows;
    }

    public int getStartIndex()
    {
        return this.startIndex;
    }

    public void setStartIndex()
    {
        this.startIndex = ((this.currentPage - 1) * this.numPerPage);
    }

    public int getLastIndex()
    {
        return this.lastIndex;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setLastIndex()
    {
        if (this.totalRows < this.numPerPage)
            this.lastIndex = this.totalRows;
        else if ((this.totalRows % this.numPerPage == 0) || ((this.totalRows % this.numPerPage != 0) && (this.currentPage < this.totalPages)))
            this.lastIndex = (this.currentPage * this.numPerPage);
        else if ((this.totalRows % this.numPerPage != 0) && (this.currentPage == this.totalPages))
            this.lastIndex = this.totalRows;
    }

    public Object[] getArgs()
    {
        return this.args;
    }

    public void setArgs(Object[] args)
    {
        this.args = args;
    }

    public <T> Map<String, Object> getPaginationList(int currentPage, int numPerPage, List<T> list)
    {
        setCurrentPage(currentPage);
        setNumPerPage(numPerPage);

        setTotalRows(list.size());

        setTotalPages();

        setStartIndex();

        setLastIndex();
        Map result = new HashMap();
        List resultList = list.subList(this.startIndex, this.lastIndex);
        result.put("total", Integer.valueOf(this.totalRows));
        result.put("rows", resultList);
        return result;
    }
}