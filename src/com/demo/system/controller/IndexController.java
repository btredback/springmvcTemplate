package com.demo.system.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.common.bean.ResultInfo;
import com.demo.common.bean.SessionInfo;
import com.demo.system.bean.User;
/**
 * 登录
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin")
public class IndexController {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@RequestMapping("/index.htm")
	public String index (HttpServletRequest request,ModelMap modelMap){
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute("sessionInfo");
		if((sessionInfo != null) && (sessionInfo.getId() != null) ){
			//return "/admin/login.html";
			return "/admin/main.jsp";
		}
		return "/admin/main.jsp";
	}
	
	@RequestMapping("/login.htm")
	@ResponseBody
	public ResultInfo login(User user, HttpSession session){
		/*//test
		String sql = "select * from HELP";
		ArrayList test = (ArrayList) jdbcTemplate.query(sql, new RowMapper<Map>(){
			public Map mapRow(ResultSet rs,int rowNum) throws SQLException{
				Map row = new HashMap();  
		        row.put("INFO", rs.getString("INFO"));		     
		        return row;  
			}
		});*/
		ResultInfo resultInfo=new ResultInfo();
		//TODO userservice根据参数user在数据库查找匹配值，赋值给变量
		System.out.println("loginname----->"+user.getLoginname());
		System.out.println("password----->"+user.getPassword());
		User sysuser = null;
		if( sysuser != null){
			resultInfo.setCode(1);
			resultInfo.setMessage("登录成功");
			//相关信息封入session
			SessionInfo sessionInfo = new SessionInfo();
			//TODO User.bean
			//sessionInfo.setId(id);
			//sessionInfo.setLoginname(loginname);
			//sessionInfo.setName(name);
			//sessionInfo.setResourceList(resourceList);//该用户可访问的URI列表
			//sessionInfo.setResourceAllList(resourceAllList);//所有URI列表
			session.setAttribute("sessionInfo", sessionInfo);
		}else{
			resultInfo.setMessage("用户名或密码错误！");
		}
		return resultInfo;
	}
	
	@RequestMapping("/logout.htm")
	@ResponseBody
	 public ResultInfo logout(HttpSession session){
		ResultInfo resultInfo = new ResultInfo();
		if(session != null){
			session.invalidate();
		}
		resultInfo.setCode(1);
		resultInfo.setMessage("注销成功！");
		return resultInfo;
	}

}
