package com.demo.util;

import de.schlichtherle.io.ArchiveDetector;
import de.schlichtherle.io.ArchiveException;
import de.schlichtherle.io.DefaultArchiveDetector;
import de.schlichtherle.io.File;
import de.schlichtherle.io.archive.zip.CheckedZipDriver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ZipUtils
{
    private static Log log = LogFactory.getLog(ZipUtils.class);

    private ArchiveDetector detector = new DefaultArchiveDetector(ArchiveDetector.ALL, new Object[] { "zip", new CheckedZipDriver("gbk") });

    public static ZipUtils getInstance()
    {
        return new ZipUtils();
    }

    public boolean zipDir(String srcDir, String dstFile, boolean clean)
    {
        File src = new File(srcDir, ArchiveDetector.ALL);
        File zip = new File(dstFile, this.detector);

        if (zip.exists())
        {
            zip.deleteAll();
        }
        log.info("正在生成压缩文件：" + zip.getName() + " ...");
        boolean result = src.copyAllTo(zip, this.detector);
        if ((result) && (clean))
        {
            src.deleteAll();
        }
        try
        {
            File.umount();
        } catch (ArchiveException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public boolean unZipFile(String srcFile, String dstDir, boolean clean)
    {
        File zip = new File(srcFile, this.detector);
        File dir = new File(dstDir, this.detector);

        if (dir.exists())
        {
            dir.deleteAll();
        }
        log.info("正在解压文件：" + zip.getName() + " ...");
        boolean result = zip.copyAllTo(dir, this.detector);
        try
        {
            File.umount();
        } catch (ArchiveException e)
        {
            e.printStackTrace();
        }
        return result;
    }
}