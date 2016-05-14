package com.demo.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;

public class DocumentUtils
{
    private static final String DOC_HEADER = "<!DOCTYPE HTML>\n<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n<style type=\"text/css\">\nbody {\n    background-color:#ffffff;\n    color:#353833;\n    font-family:\"Courier New\", \"微软雅黑\";\n    font-size:10pt;\n}\n\ntable{\n\tborder-collapse:collapse;\n\twidth:540px;\nword-wrap: break-word; \nword-break: break-all;\nwhite-space: normal;}\n\ntable.method td {\n\tborder: 1px solid #9eadc0;\n}\n\ntable.method th{\n\tbackground-color: #dee3e9;\n\tborder: 1px solid #9eadc0;\n\tfont-size: 1.3em;\n\tpadding:3px;\n\ttext-align:left;\n}\n\n.subTitle,.inheritance{\n\tdisplay:none;\n}\n\npre {\n    font-size:1.3em;\n}\n\ncode, tt {\n    font-size:1.2em;\n}\n\n.title {\n    color:#2c4557;\n    margin:10px 0;\n}\n\n.altColor {\n    background-color:#eeeeef;\n}\n.rowColor {\n    background-color:#ffffff;\n}\n.overviewSummary th,.overviewSummary td{\n    border:1px solid #9eadc0;\n    padding:5px 5px 5px 15px;\n}\n\n.colFirst{\n    width:25%;\n    vertical-align:middle;\n    \n}\n.block {\n    display:block;\n    margin:3px 0 0 0;\n}\n.strong {\n    font-weight:bold;\n}\n</style></head><body>\n";

    private static final String DOC_FOOTER = "</body></html>\n";

    private static final Map<String, String> NAME_MAP = new HashMap();

    private static FilenameFilter filter;

    public static void main(String[] args)
    {
        String rootDir = args[0];
        String docPath = args[1];
        try
        {
            BufferedWriter out = new BufferedWriter(new FileWriter(docPath));
            out.write("<!DOCTYPE HTML>\n<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n<style type=\"text/css\">\nbody {\n    background-color:#ffffff;\n    color:#353833;\n    font-family:\"Courier New\", \"微软雅黑\";\n    font-size:10pt;\n}\n\ntable{\n\tborder-collapse:collapse;\n\twidth:540px;\nword-wrap: break-word; \nword-break: break-all;\nwhite-space: normal;}\n\ntable.method td {\n\tborder: 1px solid #9eadc0;\n}\n\ntable.method th{\n\tbackground-color: #dee3e9;\n\tborder: 1px solid #9eadc0;\n\tfont-size: 1.3em;\n\tpadding:3px;\n\ttext-align:left;\n}\n\n.subTitle,.inheritance{\n\tdisplay:none;\n}\n\npre {\n    font-size:1.3em;\n}\n\ncode, tt {\n    font-size:1.2em;\n}\n\n.title {\n    color:#2c4557;\n    margin:10px 0;\n}\n\n.altColor {\n    background-color:#eeeeef;\n}\n.rowColor {\n    background-color:#ffffff;\n}\n.overviewSummary th,.overviewSummary td{\n    border:1px solid #9eadc0;\n    padding:5px 5px 5px 15px;\n}\n\n.colFirst{\n    width:25%;\n    vertical-align:middle;\n    \n}\n.block {\n    display:block;\n    margin:3px 0 0 0;\n}\n.strong {\n    font-weight:bold;\n}\n</style></head><body>\n");
            File root = new File(rootDir);
            for (File file : root.listFiles())
            {
                String name = file.getName();
                if (!NAME_MAP.containsKey(name))
                {
                    continue;
                }
                out.write("<h1>" + (String) NAME_MAP.get(name) + "管理" + "</h1>\n");
                if (!doIt(file, out))
                {
                    break;
                }
            }
            out.write("</body></html>\n");
            out.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static boolean doIt(File file, BufferedWriter out)
    {
        if (file.isDirectory())
        {
            for (File f : file.listFiles(filter))
                if (!doIt(f, out))
                    return false;
        } else
        {
            try
            {
                String content = FileUtils.readFileToString(file);
                int beginIndex = content.indexOf("<div class=\"header\">");
                int endIndex = content.indexOf("<!-- ========= END OF CLASS DATA ========= -->");
                content = content.substring(beginIndex, endIndex);
                content = content.replaceAll("(?i)(\\w+\\.){2,}(\\w+)", "$2");
                content = content.replaceAll("(?si)<ul class=\"blockList\\w*\">\r\n<li class=\"blockList\">\r\n<h4>(\\w+)</h4>(.*?)\r\n</li>\r\n</ul>",
                        "<table class=\"method\"  cellpadding=\"0\"><tr><th align=\"left\">$1</th></tr><tr><td>$2</td></tr></table><br/><br/>\n\n");
                content = content.replaceAll("(?si)(<li.*?>)|(<ul.*?>)|(<a.*?>)|(</a>)|(</ul>)|(</li>)|(<hr>)|(<caption>.*?</caption>)", "");
                content = content.replaceAll("<!--   -->", "").replaceAll("\\n\\r", "").replaceAll("\\s{3,}", "\r\n\t").replaceAll("(?s)<h3>从类继承的方�?*?</code>", "");
                out.write(content);
                out.write(10);
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return true;
    }

    static
    {
        NAME_MAP.put("workflow", "工作");
        NAME_MAP.put("util", "工具");
        NAME_MAP.put("stat", "统计");
        NAME_MAP.put("report", "报表");
        NAME_MAP.put("recsup", "督办");
        NAME_MAP.put("record", "案卷");
        NAME_MAP.put("property", "");
        NAME_MAP.put("portal", "门户");
        NAME_MAP.put("news", "新闻");
        NAME_MAP.put("message", "消息");
        NAME_MAP.put("map", "地图");
        NAME_MAP.put("main", "主页");
        NAME_MAP.put("mail", "邮件");
        NAME_MAP.put("form", "表单");
        NAME_MAP.put("filter", "过滤");
        NAME_MAP.put("doc", "要件");
        NAME_MAP.put("dictionary", "字典");
        NAME_MAP.put("dataview", "视图");
        NAME_MAP.put("datagrid", "列表");
        NAME_MAP.put("chart", "图表");
        NAME_MAP.put("archive", "档案");
        NAME_MAP.put("application", "应用");
        NAME_MAP.put("admin", "配置");
        NAME_MAP.put("etl", "数据交换");

        filter = new FilenameFilter() {
            public boolean accept(File dir, String name)
            {
                return (!name.equals("package-summary.html")) && (!name.equals("package-frame.html"));
            }
        };
    }
}