package com.demo.system.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.common.bean.ResultInfo;
import com.lowagie.text.List;

/**
 * 登录
 * @author Administrator
 *
 */
@Controller
public class IndexController {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping("/admin/index.htm")
	public String index (HttpServletRequest request,ModelMap modelMap){
		return "main.jsp";
	}
	
	@RequestMapping("/admin/login.htm")
	@ResponseBody
	public ResultInfo login(String username,String password){
		/*//test
		String sql = "select * from HELP";
		ArrayList test = (ArrayList) jdbcTemplate.query(sql, new RowMapper<Map>(){
			public Map mapRow(ResultSet rs,int rowNum) throws SQLException{
				Map row = new HashMap();  
		        row.put("INFO", rs.getString("INFO"));		     
		        return row;  
			}
		});*/
		ResultInfo resultInfo=new ResultInfo(1,"登陆成功");
		return resultInfo;
	}

}
