package com.demo.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.apache.commons.lang.StringUtils;


public class LicenseTool
{
  private static final String A = "peacemap";

  public static String getMachineCode()
  {
    String str = null;
    try
    {
      Process localProcess = Runtime.getRuntime().exec(new String[] { "wmic", "bios", "get", "serialnumber" });

      localProcess.getOutputStream().close();
      Scanner localScanner = new Scanner(localProcess.getInputStream());

      localScanner.next();
      str = localScanner.next();
      localScanner.close();
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return str;
  }

  public static String makeKeyFile(String paramString1, int paramInt1, String paramString2, int paramInt2)
  {
    return A(paramString1, paramInt1, paramString2, paramInt2);
  }

  private static String A(String paramString1, int paramInt1, String paramString2, int paramInt2)
  {
    String[] arrayOfString = new String[5];
    arrayOfString[0] = paramString1;
    arrayOfString[1] = String.valueOf(paramInt1);
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    arrayOfString[2] = localSimpleDateFormat.format(new Date());
    String str1 = "-1";
    if (paramInt1 == 2)
    {
      Calendar calender = Calendar.getInstance();
      calender.add(6, paramInt2);
      str1 = localSimpleDateFormat.format(calender.getTime());
    }
    else if (paramInt1 == 3)
    {
      arrayOfString[0] = EncryptUtils.encryptToDES(paramString2, "smartall");
    }
    arrayOfString[3] = str1;
    arrayOfString[4] = paramString2;
    Object localObject = StringUtils.join(arrayOfString, ";");
    String str2 = paramInt1 != 3 ? EncryptUtils.encryptToDES((String)localObject, arrayOfString[0]) : EncryptUtils.encryptToDES((String)localObject, "smartall");
    return str2;
  }

  public static Map<String, String> validate(String paramString)
  {
    return A(paramString);
  }

  private static Map<String, String> A(String paramString)
  {
    HashMap localHashMap = new HashMap();
    try
    {
      System.out.println("版本类型：专业版");
      System.out.println("授权日期：2013-11-28");
      System.out.println("用户名称：各行各业");
      localHashMap.put("SYS_LICENSE_OWNER", "XX");
      localHashMap.put("SYS_LICENSE_TYPE", "专业版");
      localHashMap.put("SYS_LICENSE_STARTDATE", "2013-11-30");
      localHashMap.put("SYS_LICENSE_ENDDATE", "9999-11-30");
    }
    catch (Exception localException)
    {
      System.err.println(localException.getMessage());
      System.exit(-1);
    }
    return localHashMap;
  }

  public static void main(String[] paramArrayOfString)
  {
    String str1 = makeKeyFile("5CG3283GJH", 2, "smartall", 365);
    String str2 = EncryptUtils.decryptByDES(str1, "smartall");

    System.out.println(str1);
    System.out.println(str2);
  }
}