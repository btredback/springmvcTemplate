package com.demo.util;

import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class LoggerUtils
{

    @Autowired
    private JdbcTemplate jt;

    private static final Log logger = LogFactory.getLog(LoggerUtils.class);

    public static void error(Log logger, Exception ex)
    {
        logger.error(ex.getMessage(), ex);
        ex.printStackTrace();
    }

    public static void error(Exception ex)
    {
        error(logger, ex);
    }

    public static void error(String msg, Exception ex)
    {
        error(logger, new Exception(msg, ex));
    }

    public static void error(Object obj)
    {
        logger.error(obj);
    }

    public static void info(Object obj)
    {
        logger.info(obj);
    }

    public static void warn(Object obj)
    {
        logger.warn(obj);
    }

    public static void debug(Object obj)
    {
        logger.debug(obj);
    }

    public void log2DB(String log)
    {
        try
        {
            this.jt.update("insert into comm_log_app(logid,logcontent,logtime)values(sys_guid(),?,systimestamp)", new Object[] { log });
        } catch (Exception ex)
        {
            error("log2db error :" + ex.getMessage(), ex);
        }
    }

    public void log(String type, String content, Date date, int userID, String userName)
    {
        String sql = "insert into comm_log(Logtype, Logmessage, Logdate, userid, username) values(?,?,?,?,?)";
        this.jt.update(sql, new Object[] { type, content, date, Integer.valueOf(userID), userName });
    }

    public static final class LoggerType
    {
        public static final String FTP_DELETEFILE = "[FTP_DELETEFILE]从FTP删除文件[%s]";

        public static final String TYPE_ORG = "组织机构";

        public static final String TYPE_BIZ = "业务";

        public static final String TYPE_TABLE = "表单";

        public static final String TYPE_REPORT = "报表";

        public static final String TYPE_WORKFLOW = "工作";

        public static final String TYPE_APP = "业务";

        public static final String TYPE_REC = "案卷";

        public static final String TYPE_LOGON = "登录";

        public static final String TYPE_HUMAN = "人员";

        public static final String TYPE_ROLE = "岗位";

        public static final String TYPE_APPLICARION = "应用系统";

        public static final String TYPE_LOGOUT = "退出";

        public static final String TYPE_EMPTYAPP = "业务数据";

        public static final String TYPE_PASSWORD = "密码";
    }
}