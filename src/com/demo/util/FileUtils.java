package com.demo.util;

import de.schlichtherle.io.ArchiveDetector;
import de.schlichtherle.io.DefaultArchiveDetector;
import de.schlichtherle.io.archive.zip.CheckedZipDriver;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.TimeZone;



public class FileUtils
{
    private static ArchiveDetector detector = new DefaultArchiveDetector(ArchiveDetector.ALL, new Object[] { "zip", new CheckedZipDriver("GBK") });

    public static String[] searchFile(String dir, FilenameFilter filenameFilter)
    {
        java.io.File file = new java.io.File(dir);
        if (file.isDirectory())
        {
            return file.list(filenameFilter);
        }
        return new String[0];
    }

    public static boolean mkdir(String dir)
    {
        java.io.File file = new java.io.File(dir);
        return file.mkdir();
    }

    public static de.schlichtherle.io.File unzipFile(String srcZipFileName, String tempDir)
    {
        de.schlichtherle.io.File zipfile = new de.schlichtherle.io.File(srcZipFileName, detector);
        String[] files = zipfile.list();
        mkdir(tempDir);
        for (String file : files)
        {
            String destFileName = tempDir + file;
            new de.schlichtherle.io.File(zipfile, file, detector).copyTo(new de.schlichtherle.io.File(destFileName, detector));
        }

        return zipfile;
    }

    public static String getFileExtendName(String fileName)
    {
        String fileExtendName = "";
        if (fileName != null)
        {
            int index = fileName.lastIndexOf(".");
            if (index > 0)
            {
                fileExtendName = fileName.substring(index + 1, fileName.length());
            }
        }

        return fileExtendName.toLowerCase();
    }

    public static void deleteFile(java.io.File file)
    {
        java.io.File[] fileList = file.listFiles();
        if ((fileList != null) && (fileList.length != 0))
        {
            int i = 0;
            for (int len = fileList.length; i < len; i++)
            {
                if (fileList[i].isDirectory())
                    deleteFile(fileList[i]);
                else
                {
                    fileList[i].delete();
                }
            }
            file.delete();
        } else
        {
            file.delete();
        }
    }

    public static void copyFile(java.io.File in, java.io.File out) throws Exception
    {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try
        {
            bis = new BufferedInputStream(new FileInputStream(in));
            bos = new BufferedOutputStream(new FileOutputStream(out));
            byte[] bytes = new byte[bis.available()];
            bos.write(bytes);
        } finally
        {
            if (bis != null)
            {
                bis.close();
            }
            if (bos != null)
                bos.close();
        }
    }

    public static void cutFile(java.io.File in, java.io.File out) throws Exception
    {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        byte[] bytes = new byte[1024];
        try
        {
            bis = new BufferedInputStream(new FileInputStream(in));
            bos = new BufferedOutputStream(new FileOutputStream(out));
            while (bis.read(bytes) != -1)
            {
                bos.write(bytes);
            }
            bos.flush();
        } finally
        {
            if (bis != null)
            {
                bis.close();
            }
            if (bos != null)
            {
                bos.close();
            }
            System.gc();
            deleteFile(in);
        }
    }

    public static String genFilePath4FTP(String dir)
    {
        Calendar ca = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        int month = ca.get(2) + 1;
        String monthStr = month < 10 ? "0" + month : String.valueOf(month);
        return "/" + dir + "/" + ca.get(1) + "/" + monthStr + "/" + ca.get(5);
    }
}