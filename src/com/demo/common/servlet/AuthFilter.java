package com.demo.common.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.util.ServletUtils;

public class AuthFilter extends HttpServlet implements Filter {

	/**
	 * 登陆验证过滤器
	 * 
	 * @author bt
	 */
	private static final long serialVersionUID = 5132795993513609347L;
	
	//web.xml配置参数映射
	private FilterConfig filterConfig = null;
	//白名单
    private List<Pattern> whitelist = null;
    //黑名单
    private List<Pattern> blacklist = null;
    //重定向页面
    private String redirectPage = null;
    
    /**
     * 过滤条件
     * 
     */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		 try
	        {
	            HttpServletRequest req = (HttpServletRequest) request;
	            HttpSession session = req.getSession();
	            //false通过 true拦截
	            boolean block = true;
	            String contextPath = req.getContextPath();
	            
	            if (!contextPath.endsWith("/"))
	            {
	                contextPath = contextPath + "/";
	            }
	            
	            String strURI = req.getRequestURI().replace(contextPath, "");
	            // 通过session判断是否在登陆状态，设置拦截flag
	            if ((session == null) || (session.getAttribute("USER") == null))
	            {
	            	//黑白名单过滤
	                for (Iterator it = this.whitelist.iterator(); it.hasNext();)
	                {
	                    Pattern pattern = (Pattern) it.next();
	                    if (pattern.matcher(strURI).matches())
	                    {
	                        block = false;
	                        break;
	                    }
	                }
	                for (Iterator it = this.blacklist.iterator(); it.hasNext();)
	                {
	                    Pattern pattern = (Pattern) it.next();
	                    if (pattern.matcher(strURI).matches())
	                    {
	                        block = true;
	                        break;
	                    }
	                }
	            } else
	            {
	                block = false;
	            }

	            if (!block)
	            {
	                filterChain.doFilter(request, response);
	            } else
	            {
	                HttpServletResponse httpResponse = (HttpServletResponse) response;
	                if ("XMLHttpRequest".equals(req.getHeader("X-Requested-With")))
	                {
	                    httpResponse.setStatus(401);
	                    response.setContentType("application/json;charset=UTF-8");
	                    PrintWriter out = null;
	                    try
	                    {
	                        out = response.getWriter();
	                        out.println("{code:-1,message:'session过期'}");
	                    } catch (IOException e)
	                    {
	                    } finally
	                    {
	                        if (out != null)
	                            out.close();
	                    }
	                } else
	                {
	                    httpResponse.sendRedirect(contextPath + ServletUtils.getLoginPage((HttpServletRequest) request, this.redirectPage));
	                }
	            }
	        } catch (Exception e)
	        {
	        }
	}
	
	/**
	 * 初始化方法
	 * @param whitelistProp 配置的白名单
	 * @param blacklistProp 配置的黑名单
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		this.filterConfig = filterConfig;
        this.whitelist = new ArrayList();
        this.blacklist = new ArrayList();
        this.redirectPage = filterConfig.getInitParameter("redirectPage");
        
        String whitelistProp = filterConfig.getInitParameter("whitelist");
        String blacklistProp = filterConfig.getInitParameter("blacklist");
        
        //根据，分隔符逐一将名单内容添加到属性中
        if (whitelistProp != null)
        {
            StringTokenizer tokenizer = new StringTokenizer(whitelistProp, ",");
            while (tokenizer.hasMoreTokens())
            {
                this.whitelist.add(Pattern.compile(tokenizer.nextToken().trim(), 4));
            }
        }
        
        if (blacklistProp != null)
        {
            StringTokenizer tokenizer = new StringTokenizer(blacklistProp, ",");
            while (tokenizer.hasMoreTokens())
            {
                this.blacklist.add(Pattern.compile(tokenizer.nextToken().trim()));
            }
        }      
	}
	
	/**
	 * 销毁
	 */
	public void destroy()
    {
        this.filterConfig = null;
        this.whitelist = null;
        this.blacklist = null;
    }

}
