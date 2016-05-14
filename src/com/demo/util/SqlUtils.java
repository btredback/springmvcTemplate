package com.demo.util;

import de.schlichtherle.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.demo.common.bean.ResultInfo;


@Component
public class SqlUtils
{

    @Autowired
    private JdbcTemplate jt;

    public ResultInfo runSqlFile(String filePath)
    {
        ResultInfo result = new ResultInfo();
        try
        {
            String fileRootPath = FilenameUtils.getPath(filePath);
            String sqlText = FileUtils.readFileToString(new File(filePath), "utf-8");
            result = runSqlText(sqlText, fileRootPath);
        } catch (IOException e)
        {
            result.setCode(-1);
            result.setMessage(e.getMessage());
            LoggerUtils.error(e);
        }
        return result;
    }

    public ResultInfo runSqlText(String sqlText, String fileRootPath)
    {
        ResultInfo result = new ResultInfo();

        String pattern = "((create |insert |update |delete |alter |drop |comment |@).*?;\\s*$)|((declare|begin).*?/\\s*$)";
        Pattern regex = Pattern.compile(pattern, 40);
        Matcher regexMatcher = regex.matcher(sqlText);
        StringBuffer messages = new StringBuffer();
        while (regexMatcher.find())
        {
            String sql = regexMatcher.group();
            try
            {
                if (sql.startsWith("@"))
                {
                    File file = new File(fileRootPath + "/" + sql.substring(1));

                    if ((sql.endsWith("fnc")) || (sql.endsWith("prc")))
                    {
                        String content = FileUtils.readFileToString(file, "utf-8");
                        this.jt.execute(StringUtils.stripEnd(content, "/"));
                    } else if (sql.endsWith("pck"))
                    {
                        String[] sqls = sql.split("^/$");
                        for (int i = 0; i < sqls.length; i++)
                            this.jt.execute(sqls[i]);
                    } else
                    {
                        runSqlText(fileRootPath + sql.substring(1), fileRootPath);
                    }
                } else
                {
                    this.jt.execute(StringUtils.stripEnd(sql.trim(), ";"));
                }
            } catch (Exception e)
            {
                messages.append(e.getMessage() + "\n");
                LoggerUtils.error("执行脚本出错：" + sql, e);
            }
        }
        result.setMessage(messages.toString());
        return result;
    }

    private static void createCommentsFile(String rootPath, String outputFilePath)
    {
        try
        {
            File dir = new File(rootPath);
            BufferedWriter out = new BufferedWriter(new FileWriter(outputFilePath));
            out.write("prompt create comments...\nset define off\n");
            File[] files = dir.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name)
                {
                    return name.startsWith("tb");
                }
            });
            for (File file : files)
            {
                Pattern regex = Pattern.compile("comment\\s+on.*?';", 32);
                Matcher regexMatcher = regex.matcher(FileUtils.readFileToString(file));
                while (regexMatcher.find())
                {
                    out.write(regexMatcher.group() + "\n");
                }
            }
            out.write("prompt done.\n");
            out.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        createCommentsFile(args[0], args[1]);
    }
}