package com.demo.common.servlet;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.demo.util.LoggerUtils;
import com.demo.util.ProductTool;
import com.demo.util.ValueUtils;

@Component
public class Config implements ServletContextAware
{
    private ServletContext servletContext;


    private Map<String, String> properties = new HashMap();

    @Autowired
    private JdbcTemplate jt;

    public void setServletContext(ServletContext servletContext)
    {
        this.servletContext = servletContext;
    }

    public void load()
    {
        try
        {
            String sql = "select propName, propValue from comm_param where uuid='PARAM_SYS'";
            this.jt.query(sql, new ResultSetExtractor() {
                public Object extractData(ResultSet rs) throws SQLException, DataAccessException
                {
                    while (rs.next())
                    {
                        Config.this.setProperty(rs.getString("propName"), rs.getString("propValue"));
                    }
                    return null;
                }
            });
        } catch (DataAccessException e)
        {
            LoggerUtils.error(e);
        }
        String url = getProperty("SYS_ARCGIS_JSAPI_URL");
        if (url.startsWith("."))
        {
            url = this.servletContext.getContextPath() + "/map/" + url;
            setProperty("SYS_ARCGIS_JSAPI_URL", url);
            this.servletContext.setAttribute("SYS_ARCGIS_JSAPI_URL", url);
        }
    }

    @PostConstruct
    public void init()
    {
        String keyFilePath = this.servletContext.getRealPath("lisence.lic");
        Map licenseInfo = ProductTool.check(keyFilePath);
        Set<String> keySet = licenseInfo.keySet();
        for (String param : keySet)
        {
            this.servletContext.setAttribute(param, licenseInfo.get(param));
        }
        load();
        this.servletContext.setAttribute("conf", this);
    }

    public String getBasePath()
    {
        String path = this.servletContext.getRealPath("/");
        if (!path.endsWith(File.separator))
        {
            return path + File.separator;
        }
        return path;
    }

    public String getProperty(String key)
    {
        return ValueUtils.getString(this.properties.get(key), "");
    }

    public Map<String, String> getProperties()
    {
        return this.properties;
    }

    public void setProperties(Map<String, String> properties)
    {
        this.properties = properties;
    }

    private void setProperty(String paramName, String paramValue)
    {
        this.properties.put(paramName, paramValue);
        this.servletContext.setAttribute(paramName, paramValue);
    }

    
}