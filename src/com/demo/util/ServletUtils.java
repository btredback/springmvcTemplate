package com.demo.util;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.util.WebUtils;

import com.demo.common.bean.Application;
import com.demo.common.bean.KeyValue;
import com.demo.common.bean.LogonHostInfo;
import com.demo.common.bean.TreeNode;
import com.demo.common.bean.User;


public class ServletUtils
{
    public static int getLogonHumanID(HttpServletRequest request)
    {
        KeyValue obj = (KeyValue) request.getSession().getAttribute("USER_SESSION");
        if (obj != null)
        {
            return Integer.parseInt(obj.getKey());
        }
        return -1;
    }

    public static int getProxyHumanID(HttpServletRequest request)
    {
        User h = getLogonHuman(request);
        return (h == null) || (h.getAgent() == null) ? 0 : h.getAgent().getHumanID();
    }

    public static User getLogonHuman(HttpServletRequest request)
    {
        Object o = WebUtils.getSessionAttribute(request, "USER_OBJ_SESSION");
        if ((o != null) && ((o instanceof User)))
        {
            return (User) o;
        }
        return null;
    }

    public static String getSSOInfo(HttpServletRequest request)
    {
        User h = getLogonHuman(request);
        String q = null;
        if (h != null)
        {
            q = h.getHumanID() + "!" + h.getPassword();
        }
        return "q=" + q;
    }

    public static Application getApplication(HttpServletRequest request, String appCode)
    {
        Application app = null;
        try
        {
            app = (Application) WebUtils.getSessionAttribute(request, String.format("APP_SESSION_%s", new Object[] { appCode }));
        } catch (Exception ex)
        {
        }
        return app;
    }

    public static String getLoginPage(HttpServletRequest request, String defaultPage)
    {
        return WebUtils.getSessionAttribute(request, "LOGIN_SESSION") == null ? defaultPage : WebUtils.getSessionAttribute(request, "LOGIN_SESSION").toString();
    }

    public static KeyValue getLogonHumanInfo(HttpServletRequest request)
    {
        return (KeyValue) request.getSession().getAttribute("USER_SESSION");
    }

    public static String getSysProperty(HttpServletRequest request, String name)
    {
        return (String) request.getSession().getServletContext().getAttribute(name);
    }

    public static void writeJsonToResponse(ServletResponse response, Object object)
    {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = null;
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            writer = response.getWriter();
            mapper.writeValue(writer, object);
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if (writer != null)
            {
                writer.flush();
                writer.close();
            }
        }
    }

    public static Map<String, Object> makeGrid(List<?> list)
    {
        Map m = new HashMap();
        m.put("total", Integer.valueOf(list.size()));
        m.put("rows", list);
        return m;
    }

    public static Map<String, String> getParameters(HttpServletRequest request, boolean toUpperCase)
    {
        Map params = request.getParameterMap();
        Map result = new CaseInsensitiveMap();
        Set<Entry> entrySet = params.entrySet();
        for (Map.Entry entry : entrySet)
        {
            if (toUpperCase)
                result.put(((String) entry.getKey()).toUpperCase(), ((String[]) entry.getValue())[0]);
            else
            {
                result.put(entry.getKey(), ((String[]) entry.getValue())[0]);
            }
        }
        return result;
    }

    public static List<TreeNode> removeEmptyGroup(List<TreeNode> list, String groupTypeName)
    {
        String className = "icon-" + groupTypeName;
        for (int i = list.size() - 1; i >= 0; i--)
        {
            TreeNode node = (TreeNode) list.get(i);
            if (node.getIconCls().equals(className))
            {
                if (node.getChildren().size() == 0)
                {
                    list.remove(i);
                } else
                {
                    List children = removeEmptyGroup(node.getChildren(), groupTypeName);
                    if (children.size() == 0)
                        list.remove(i);
                    else
                    {
                        node.setChildren(children);
                    }
                }
            }
        }
        return list;
    }

    public static List<TreeNode> removeEmptyGroup(List<TreeNode> list, List<String> iconClsList)
    {
        for (int i = list.size() - 1; i >= 0; i--)
        {
            TreeNode node = (TreeNode) list.get(i);
            if (iconClsList.indexOf(node.getIconCls()) != -1)
            {
                if (node.getChildren().size() == 0)
                {
                    list.remove(i);
                } else
                {
                    List children = removeEmptyGroup(node.getChildren(), iconClsList);
                    if (children.size() == 0)
                        list.remove(i);
                    else
                    {
                        node.setChildren(children);
                    }
                }
            }
        }
        return list;
    }

    public static boolean checkHeaderCache(long adddays, long modelLastModifiedDate, HttpServletRequest request, HttpServletResponse response)
    {
        long adddaysM = adddays * 1000L;
        long header = request.getDateHeader("If-Modified-Since");
        long now = System.currentTimeMillis();
        if ((header > 0L) && (adddaysM > 0L))
        {
            if (modelLastModifiedDate > header)
            {
                response.setStatus(200);
                return true;
            }
            if (header + adddaysM > now)
            {
                response.setStatus(304);
                return false;
            }

        }

        String previousToken = request.getHeader("If-None-Match");
        if ((previousToken != null) && (previousToken.equals(Long.toString(modelLastModifiedDate))))
        {
            response.setStatus(304);
            return false;
        }

        response.setHeader("ETag", Long.toString(modelLastModifiedDate));
        setRespHeaderCache(adddays, request, response);
        return true;
    }

    public static boolean setRespHeaderCache(long adddays, HttpServletRequest request, HttpServletResponse response)
    {
        long adddaysM = adddays * 1000L;
        String maxAgeDirective = "max-age=" + adddays;
        response.setHeader("Cache-Control", maxAgeDirective);
        response.setStatus(200);
        response.addDateHeader("Last-Modified", System.currentTimeMillis());
        response.addDateHeader("Expires", System.currentTimeMillis() + adddaysM);
        return true;
    }

    public static LogonHostInfo getLogonHostInfo(HttpServletRequest request)
    {
        LogonHostInfo logonHostInfo = new LogonHostInfo();
        String ip = request.getHeader("x-forwarded-for");
        if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip)))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip)))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip)))
        {
            ip = request.getRemoteAddr();
        }
        if (("0:0:0:0:0:0:0:1".equals(ip)) || ("127.0.0.1".equals(ip)))
        {
            try
            {
                InetAddress localHost = InetAddress.getLocalHost();
                ip = localHost.getHostAddress();
            } catch (Exception e)
            {
                LoggerUtils.error(e);
            }
        }
        logonHostInfo.setIP(ip);
        logonHostInfo.setHostName(null);
        return logonHostInfo;
    }

    public static List<String> otherOnlineApp(HttpServletRequest request, String appCode)
    {
        List others = new ArrayList();
        HttpSession session = request.getSession();
        Enumeration enumeration = session.getAttributeNames();
        while (enumeration.hasMoreElements())
        {
            String sessionName = (String) enumeration.nextElement();
            if (sessionName.startsWith("APP_SESSION_"))
                if (!String.format("APP_SESSION_%s", new Object[] { appCode }).equals(sessionName))
                {
                    others.add(sessionName.replaceAll("APP_SESSION_", ""));
                }
        }
        return others;
    }

    public static void invalidateAppSession(HttpServletRequest request, String appCode)
    {
        HttpSession session = request.getSession();
        Enumeration enumeration = session.getAttributeNames();
        while (enumeration.hasMoreElements())
        {
            String sessionName = (String) enumeration.nextElement();
            if (sessionName.endsWith("_" + appCode))
            {
                LoggerUtils.debug("logout:" + sessionName + " removed");
                session.removeAttribute(sessionName);
            }
        }
    }

    public static int invalidateSession(HttpServletRequest request, String appCode)
    {
        if (otherOnlineApp(request, appCode).size() > 0)
        {
            invalidateAppSession(request, appCode);
            return 1;
        }

        HttpSession session = request.getSession();
        Enumeration enumeration = session.getAttributeNames();
        while (enumeration.hasMoreElements())
        {
            String sessionName = (String) enumeration.nextElement();
            LoggerUtils.debug("logout:" + sessionName + " removed");
            session.removeAttribute(sessionName);
        }
        request.getSession().invalidate();
        return 0;
    }
}