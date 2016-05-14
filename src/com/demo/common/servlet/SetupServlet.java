package com.demo.common.servlet;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.common.bean.ResultInfo;
import com.demo.common.dao.DecryptingDataSource;
import com.demo.util.EncryptUtils;
import com.demo.util.ServletUtils;

public class SetupServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9132030607755532160L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
        Properties p = new Properties();
        String file = request.getSession().getServletContext().getRealPath("/WEB-INF/conf.properties");
        p.load(new FileReader(file));
        String password = p.getProperty("jdbc.password");
        String url = p.getProperty("jdbc.url");
        String username = p.getProperty("jdbc.username");
        String driverClassName = p.getProperty("jdbc.driverClassName");
        String encrypted = p.getProperty("password.encrypted");
        if ("load".equals(type))
        {
            Map data = new HashMap();
            data.put("url", url);
            data.put("username", username);
            data.put("password", password);
            data.put("encrypted", encrypted);
            ServletUtils.writeJsonToResponse(response, data);
        } else if ("save".equals(type))
        {
            password = request.getParameter("password");
            url = request.getParameter("url");
            username = request.getParameter("username");
            encrypted = request.getParameter("encrypted");
            String pwd = encrypted == null ? password : EncryptUtils.encryptToDES(password);
            p.setProperty("jdbc.url", url);
            p.setProperty("jdbc.username", username);
            p.setProperty("jdbc.password", pwd);
            p.setProperty("password.encrypted", String.valueOf(encrypted != null));
            p.store(new FileWriter(file), null);
            ServletUtils.writeJsonToResponse(response, new ResultInfo(0, "保存成功"));
        } else if ("test".equals(type))
        {
            try
            {
                DecryptingDataSource ds = new DecryptingDataSource();
                ds.setUrl(url);
                ds.setPassword(password);
                ds.setDriverClassName(driverClassName);
                ds.setUsername(username);
                ds.afterPropertiesSet();
                ds.getConnection();
                ServletUtils.writeJsonToResponse(response, new ResultInfo(0, "连接成功"));
            } catch (Exception e)
            {
                e.printStackTrace();
                ServletUtils.writeJsonToResponse(response, new ResultInfo(-1, "连接失败，错误原因：" + e.getMessage()));
            }
        }
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}



}
