package com.demo.util;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProductTool {
	  static final String B = "smartall";
	  private static final String A = "WEIJDXVNXCKLSDF";

	  public static String getMachineCode()
	  {
	    String str = null;
	    try
	    {
	      if (System.getProperty("os.name").startsWith("Windows"))
	      {
	        Object localObject1 = Runtime.getRuntime().exec(new String[] { "c:\\Windows\\System32\\wbem\\WMIC.exe", "bios", "get", "serialnumber" });
	        ((Process)localObject1).getOutputStream().close();
	        Object localObject2 = new Scanner(((Process)localObject1).getInputStream());
	        ((Scanner)localObject2).next();
	        str = ((Scanner)localObject2).next();
	        ((Scanner)localObject2).close();
	      }
	      else
	      {
	        Object localObject1 = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
	        Object localObject2 = ((NetworkInterface)localObject1).getHardwareAddress();
	        StringBuilder localStringBuilder = new StringBuilder();
	        byte[] localObject2byte = (byte[])localObject2;
	        for (int i = 0; i < localObject2byte.length; i++)
	          localStringBuilder.append(String.format("%02X", new Object[] { Byte.valueOf(localObject2byte[i]) }));
	        str = localStringBuilder.toString();
	      }
	      str = EncryptUtils.encryptToSHA(str);
	      str = (str + "WEIJDXVNXCKLSDF").substring(0, 16);
	    }
	    catch (IOException localIOException)
	    {
	      localIOException.printStackTrace();
	    }
	    return str;
	  }

	  public static Map<String, String> check(String paramString)
	  {
	    return getInfoMapFromLicense(paramString);
	  }

	  private static Map<String, String> getInfoMapFromLicense(String keyFilePath)
	  {
	    HashMap localHashMap = new HashMap();
	    try
	    {
	    	
	      String biosSerialnumber = getMachineCode();

	      File licenseFile = new File(keyFilePath);
	      
	      if (!licenseFile.exists())
	      {
	        throw new RuntimeException("");
	      }
	      String licenseFileContent = org.apache.commons.io.FileUtils.readFileToString(licenseFile);
	      String minwen = EncryptUtils.decryptByDES(licenseFileContent, biosSerialnumber);
	      if (minwen == null)
	      {
	        minwen = EncryptUtils.decryptByDES(licenseFileContent, "megalosaurus");
	      }
	      if (minwen == null)
	      {
	        throw new RuntimeException("");
	      }
	      String[] arrayOfString = minwen.split(";");
	      int i = Integer.parseInt(arrayOfString[1]);
	      if (arrayOfString.length != 5)
	      {
	        throw new RuntimeException("");
	      }

	      if ((i != 1) && (i != 2) && (i != 3) && (i != 4))
	      {
	        throw new RuntimeException("");
	      }

	      if (((i == 2) && (!arrayOfString[0].equals(EncryptUtils.encryptToDES(arrayOfString[4], "megalosaurus")))) || (
	        (i == 3) && (!arrayOfString[0].equals(biosSerialnumber))))
	      {
	        throw new RuntimeException("");
	      }

	      Calendar localObject = Calendar.getInstance();
	      localObject.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(arrayOfString[3]));
	      int j = Calendar.getInstance().compareTo(localObject);
	      if (j > 0) {
	        throw new RuntimeException("");
	      }
	      
	      
	      String version = i == 3 ? "专业版" : i == 2 ? "试用版" : i == 1 ? "开发版" : "旗舰版";
	      localHashMap.put("SYS_LICENSE_OWNER", arrayOfString[4]);
	      localHashMap.put("SYS_LICENSE_TYPE", version);
	      localHashMap.put("SYS_LICENSE_STARTDATE", arrayOfString[2]);
	      if (i != 4)
	      {
	        localHashMap.put("SYS_LICENSE_ENDDATE", arrayOfString[3]);
	      }
	      else localHashMap.put("SYS_LICENSE_ENDDATE", "无");

	    }
	    catch (Exception localException)
	    {
	      System.err.println(localException.getMessage());
	      try
	      {
	        Thread.sleep(807L);
	        System.exit(0);
	      }
	      catch (InterruptedException localInterruptedException)
	      {
	        localInterruptedException.printStackTrace();
	      }
	    }
	    return localHashMap;
	  }

	  public static void main(String[] paramArrayOfString)
	  {
	  }
}
