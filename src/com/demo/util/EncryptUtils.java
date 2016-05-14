package com.demo.util;

import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptUtils
{
  private static SecretKey secretKey = null;

  static
  {
    byte[] arrayOfByte = { 11, 25, 79, 110, 49, 41, 16, 25 };
    secretKey = new SecretKeySpec(arrayOfByte, 0, arrayOfByte.length, "DES");
  }

  public static String encryptToMD5(String hammcode)
  {
    byte[] arrayOfByte = null;
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(hammcode.getBytes());
      arrayOfByte = localMessageDigest.digest();
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      localNoSuchAlgorithmException.printStackTrace();
    }
    String str = byte2hex(arrayOfByte);
    return str;
  }

  public static String encryptToSHA(String paramString)
  {
    byte[] arrayOfByte = null;
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-1");
      localMessageDigest.update(paramString.getBytes());
      arrayOfByte = localMessageDigest.digest();
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      localNoSuchAlgorithmException.printStackTrace();
    }
    return byte2hex(arrayOfByte);
  }

  public static String encryptToDES(String hammcode)
  {
    return encryptToDES(hammcode, secretKey);
  }

  public static String encryptToDES(String hammcode, String hammcodeKey)
  {
    return encryptToDES(hammcode, generateSecretKey(hammcodeKey));
  }

  public static String encryptToDES(String hammcode, SecretKey secretKey)
  {
    String str = "DES";
    SecureRandom localSecureRandom = new SecureRandom();
    byte[] arrayOfByte = null;
    try
    {
      Cipher localCipher = Cipher.getInstance(str);
      localCipher.init(1, secretKey, localSecureRandom);
      arrayOfByte = localCipher.doFinal(hammcode.getBytes());
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return byte2hex(arrayOfByte);
  }

  private static SecretKey generateSecretKey(String hummcodeKey)
  {
    byte[] arrayOfByte = hexToBytes(hummcodeKey);
    return new SecretKeySpec(Arrays.copyOfRange(arrayOfByte, 0, 8), "DES");
  }

  public static String decryptByDES(String ciphertext)
  {
    return decryptByDES(ciphertext, secretKey);
  }

  public static String decryptByDES(String miwen, String hammcodeKey)
  {
    return decryptByDES(miwen, generateSecretKey(hammcodeKey));
  }

  public static String decryptByDES(String miwen, SecretKey secretKey)
  {
    String str = "DES";
    SecureRandom localSecureRandom = new SecureRandom();
    byte[] arrayOfByte = null;
    try
    {
      Cipher localCipher = Cipher.getInstance(str);
      localCipher.init(2, secretKey, localSecureRandom);
      arrayOfByte = localCipher.doFinal(hexToBytes(miwen));
    }
    catch (Exception localException)
    {
      return null;
    }
    return new String(arrayOfByte);
  }

  public static String byte2hex(byte[] paramArrayOfByte)
  {
    String str = "";
    for (int i = 0; i < paramArrayOfByte.length; i++)
      str = str + Integer.toString((paramArrayOfByte[i] & 0xFF) + 256, 16).substring(1);
    return str.toUpperCase();
  }

  public static byte[] hexToBytes(char[] paramArrayOfChar)
  {
    int i = paramArrayOfChar.length / 2;
    byte[] arrayOfByte = new byte[i];
    for (int j = 0; j < i; j++)
    {
      int k = Character.digit(paramArrayOfChar[(j * 2)], 16);
      int m = Character.digit(paramArrayOfChar[(j * 2 + 1)], 16);
      int n = k << 4 | m;
      if (n > 127)
        n -= 256;
      arrayOfByte[j] = (byte)n;
    }
    return arrayOfByte;
  }

  public static byte[] hexToBytes(String paramString)
  {
    return hexToBytes(paramString.toCharArray());
  }

  public static void main(String[] paramArrayOfString)
  {
    String hammcode = "F268730B62F3ACC76C98053AF6B6D99F;4;2013-08-21;2015-12-28;数字天地";
    String hammkey = "megalosaurus";
    String miwen = encryptToDES(hammcode, hammkey);
    System.out.println("license:" + miwen);
  }
}