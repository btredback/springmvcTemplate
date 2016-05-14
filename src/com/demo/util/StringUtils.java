package com.demo.util;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

public class StringUtils
{
    private static Log logger = LogFactory.getLog(StringUtils.class);

    private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    private static final String[] dateReg = { "^(\\d{4})-(0\\d{1}|1[0-2])-(0\\d{1}|[12]\\d{1}|3[01])\\s(0\\d{1}|1\\d{1}|2[0-3]):[0-5]\\d{1}:([0-5]\\d{1})$", "^(\\d{4})-(0\\d{1}|1[0-2])-(0\\d{1}|[12]\\d{1}|3[01])\\s(0\\d{1}|1\\d{1}|2[0-3])$",
            "^(\\d{4})-(0\\d{1}|1[0-2])-(0\\d{1}|[12]\\d{1}|3[01])$", "^(\\d{4})-(0\\d{1}|1[0-2])$" };

    private static final Map<String, String> datePattern = new HashMap();

    public static String byteArrayToHexString(byte[] b)
    {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
        {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b)
    {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return new StringBuilder().append(hexDigits[d1]).append(hexDigits[d2]).toString();
    }

    public static String MD5Encode(String origin)
    {
        String resultString = null;
        try
        {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
        } catch (Exception ex)
        {
        }
        return resultString.toUpperCase();
    }

    public static double getDisplayWidth(String text, Font font)
    {
        if ((text == null) || (text.trim().equals("")))
        {
            return -1.0D;
        }
        Graphics g = null;
        try
        {
            g = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())).getGraphics();
        } catch (Exception e)
        {
        }
        Graphics2D g2d = (Graphics2D) g;
        FontRenderContext frc = g2d.getFontRenderContext();
        TextLayout layout = new TextLayout(text, font, frc);
        Rectangle2D bounds = layout.getBounds();
        double width = bounds.getWidth();
        return width + 5.0D;
    }

    public static double getDisplayWidth(String text, Graphics2D g2d, Font font)
    {
        if ((text == null) || (text.trim().equals("")))
        {
            return -1.0D;
        }
        FontRenderContext frc = g2d.getFontRenderContext();
        TextLayout layout = new TextLayout(text, font, frc);
        Rectangle2D bounds = layout.getBounds();
        double width = bounds.getWidth();
        return width + 5.0D;
    }

    public static String serializeToJson(Object object) throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer();
        return writer.writeValueAsString(object);
    }

    public static <T> T parseJSON(String json, Class<T> clazz) throws JsonProcessingException, IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, clazz);
    }

    public static String arrayToString(int[] array, String separate)
    {
        StringBuilder sb = new StringBuilder();
        for (int i : array)
        {
            sb.append(i).append(separate);
        }
        String temp = sb.toString();
        return "".equals(temp) ? temp : temp.substring(0, temp.length() - 1);
    }

    public static String arrayToString(long[] array, String separate)
    {
        StringBuilder sb = new StringBuilder();
        for (long i : array)
        {
            sb.append(i).append(separate);
        }
        String temp = sb.toString();
        return "".equals(temp) ? temp : temp.substring(0, temp.length() - 1);
    }

    public static String arrayToString(String[] array, String separate)
    {
        StringBuilder sb = new StringBuilder();
        for (String i : array)
        {
            sb.append(i).append(separate);
        }
        String temp = sb.toString();
        return "".equals(temp) ? temp : temp.substring(0, temp.length() - 1);
    }

    public static String listToString(List<String> list, String separate)
    {
        StringBuilder sb = new StringBuilder();
        Object[] array = list.toArray();
        for (Object i : array)
        {
            sb.append(i.toString()).append(separate);
        }
        String temp = sb.toString();
        return "".equals(temp) ? temp : temp.substring(0, temp.length() - 1).trim();
    }

    public static String makeUUID()
    {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    public static Date parseDate(String pattern, String source) throws ParseException
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.parse(source);
    }

    public static Date parseDate(String source) throws ParseException
    {
        for (String regex : dateReg)
        {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(source);
            if (matcher.matches())
            {
                return parseDate((String) datePattern.get(regex), source);
            }
        }
        throw new ParseException(new StringBuilder().append(source).append(" 不支持的时间格式").toString(), 0);
    }

    public static String getFullPinyinString(String chinese)
    {
        if (org.apache.commons.lang.StringUtils.isEmpty(chinese))
        {
            return chinese;
        }
        StringBuffer pybf = new StringBuffer();
        char[] arr = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        for (int i = 0; i < arr.length; i++)
        {
            if (arr[i] > '')
                try
                {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);
                    if ((temp == null) || (temp.length == 0))
                        logger.debug(new StringBuilder().append(arr[i]).append(" get null for pinyin translation").toString());
                    else
                        pybf.append(temp[0]);
                } catch (BadHanyuPinyinOutputFormatCombination e)
                {
                    e.printStackTrace();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            else
            {
                pybf.append(arr[i]);
            }
        }
        return pybf.toString().trim();
    }

    static
    {
        datePattern.put("^(\\d{4})-(0\\d{1}|1[0-2])-(0\\d{1}|[12]\\d{1}|3[01])\\s(0\\d{1}|1\\d{1}|2[0-3]):[0-5]\\d{1}:([0-5]\\d{1})$", "yyyy-MM-dd hh:mm:ss");
        datePattern.put("^(\\d{4})-(0\\d{1}|1[0-2])-(0\\d{1}|[12]\\d{1}|3[01])\\s(0\\d{1}|1\\d{1}|2[0-3])$", "yyyy-MM-dd hh");
        datePattern.put("^(\\d{4})-(0\\d{1}|1[0-2])-(0\\d{1}|[12]\\d{1}|3[01])$", "yyyy-MM-dd");
        datePattern.put("^(\\d{4})-(0\\d{1}|1[0-2])$", "yyyy-MM");
    }
}