package com.demo.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;

import com.demo.common.bean.FTPConfig;
import com.demo.common.exception.FtpErrorException;


public class FtpUtils
{
    private Log log = LogFactory.getLog(FtpUtils.class);

    private FTPClient ftpClient = null;

    private LoggerUtils logger = null;

    public FtpUtils() {
    }

    public FtpUtils(LoggerUtils logger) {
        this.logger = logger;
    }

    public boolean connect(String host, String username, String password, int port)
    {
        try
        {
            if (this.ftpClient == null)
            {
                this.ftpClient = new FTPClient();
            }
            this.ftpClient.connect(host, port);
            boolean loginSuccess = this.ftpClient.login(username, password);
            if (!loginSuccess)
                throw new FtpErrorException("FTP用户名或密码错误");
            this.ftpClient.setFileType(2);
            this.ftpClient.enterLocalActiveMode();
            this.ftpClient.setControlEncoding("GBK");
            return true;
        } catch (Exception e)
        {
            this.log.error("连接FTP失败:" + e.getMessage());
        }
        return false;
    }

    public void setLogger(LoggerUtils logger)
    {
        this.logger = logger;
    }

    public boolean connect(FTPConfig conf)
    {
        return connect(conf.getHost(), conf.getUserName(), conf.getPassword(), conf.getPort().intValue());
    }

    public void disconnect()
    {
        try
        {
            this.ftpClient.logout();
            this.ftpClient.disconnect();
        } catch (Exception e)
        {
            this.log.error("断开FTP失败:" + e.getMessage());
        }
    }

    public boolean delete(String remoteFilePath)
    {
        boolean result = false;
        try
        {
            String ftpFilePath = new String(remoteFilePath.replace("\\", "/").getBytes("GBK"), "iso-8859-1");
            result = this.ftpClient.deleteFile(ftpFilePath.replace("\\", "/"));
            String deleteLog = String.format("[FTP_DELETEFILE]从FTP删除文件[%s]", new Object[] { remoteFilePath });
            if (this.logger != null)
            {
                this.logger.log2DB(deleteLog);
            }
            LoggerUtils.info(deleteLog);
        } catch (IOException e)
        {
            this.log.error("删除文件失败" + remoteFilePath);
        }
        return result;
    }

    public boolean download(String srcFilePath, OutputStream outputStream)
    {
        try
        {
            String ftpFilePath = new String(srcFilePath.replace("\\", "/").getBytes("GBK"), "iso-8859-1");
            if (!this.ftpClient.retrieveFile(ftpFilePath, outputStream))
                return false;
        } catch (IOException e)
        {
            this.log.error("下载文件失败" + srcFilePath);
            return false;
        }
        return true;
    }

    public boolean download(String srcFilePath, String dstDir, String dstFileName)
    {
        try
        {
            File localFile = new File(dstDir + "\\" + dstFileName);
            OutputStream local = new FileOutputStream(localFile);

            String ftpFilePath = new String(srcFilePath.replace("\\", "/").getBytes("GBK"), "iso-8859-1");
            if (!this.ftpClient.retrieveFile(ftpFilePath, local))
            {
                local.close();
                localFile.delete();
            } else
            {
                local.close();
            }
        } catch (IOException e)
        {
            this.log.error("下载文件失败" + srcFilePath);
            return false;
        }
        return true;
    }

    public InputStream read(String srcFilePath)
    {
        InputStream is = null;
        try
        {
            is = this.ftpClient.retrieveFileStream(new String(srcFilePath.getBytes("GBK"), "ISO-8859-1"));
        } catch (IOException e)
        {
            this.log.error("读取文件失败" + srcFilePath);
        }
        return is;
    }

    private boolean createDirectory(String directory)
    {
        String[] names = directory.split("/");
        try
        {
            this.ftpClient.changeWorkingDirectory("/");
            for (int i = 0; i < names.length; i++)
                if (!this.ftpClient.changeWorkingDirectory(names[i]))
                {
                    this.ftpClient.makeDirectory(names[i]);
                    this.ftpClient.changeWorkingDirectory(names[i]);
                }
        } catch (IOException e)
        {
            this.log.error("创建目录失败:" + e.getMessage());
        }
        return true;
    }

    public boolean upload(String remoteDir, String remoteFileName, String localFilePath)
    {
        boolean result = true;
        try
        {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(localFilePath));
            result = upload(remoteDir, remoteFileName, inputStream, true);
            inputStream.close();
        } catch (IOException e)
        {
            this.log.error("上传文件到FTP失败:" + e.getMessage());
        }
        return result;
    }

    public boolean upload(String remoteDir, String remoteFileName, InputStream inputStream, boolean toEncoding)
    {
        boolean result = true;
        try
        {
            String remotePath = "";
            String fileName = "";
            if (toEncoding)
            {
                remotePath = new String(remoteDir.replace("\\", "/").getBytes("GBK"), "iso-8859-1");
                fileName = new String(remoteFileName.getBytes("GB2312"), "iso-8859-1");
            } else
            {
                remotePath = remoteDir.replace("\\", "/");
                fileName = remoteFileName;
            }

            if (!this.ftpClient.changeWorkingDirectory(remotePath))
            {
                createDirectory(remotePath);
            }
            result = this.ftpClient.storeFile(fileName, inputStream);
            if (!result)
                this.log.error("上传文件失败" + this.ftpClient.getReplyString());
        } catch (IOException e)
        {
            this.log.error("上传文件到FTP失败:" + e.getMessage());
        }
        return result;
    }

    public boolean copyRemoteFile(String srcRemote, String targetRemote)
    {
        boolean result = false;
        ByteArrayOutputStream output = null;
        InputStream input = null;
        try
        {
            output = new ByteArrayOutputStream();
            result = this.ftpClient.retrieveFile(new String(srcRemote.getBytes("GBK"), "iso-8859-1"), output);

            if (!result)
            {
                boolean bool1 = result;
                return bool1;
            }
            input = new ByteArrayInputStream(output.toByteArray());
            String remotePath = new String(targetRemote.replace("\\", "/").getBytes("GBK"), "iso-8859-1");
            remotePath = remotePath.substring(0, remotePath.lastIndexOf("/"));

            if (!this.ftpClient.changeWorkingDirectory(remotePath))
            {
                createDirectory(remotePath);
            }
            result = this.ftpClient.storeFile(new String(targetRemote.getBytes("GBK"), "iso-8859-1"), input);
        } catch (Exception ex)
        {
            LoggerUtils.error("FTP远程复制文件失败:" + ex.getMessage(), ex);
            result = false;
        } finally
        {
            try
            {
                output.close();
                input.close();
            } catch (Exception ex)
            {
            }
        }
        return result;
    }

    public boolean removeDirectory(String remoteDir)
    {
        boolean result = false;
        try
        {
            result = this.ftpClient.removeDirectory(new String(remoteDir.getBytes("GBK"), "iso-8859-1"));
            String deleteLog = String.format("[FTP_DELETEFILE]从FTP删除文件[%s]", new Object[] { remoteDir });
            if (this.logger != null)
            {
                this.logger.log2DB(deleteLog);
            }
            LoggerUtils.info(deleteLog);
        } catch (IOException e)
        {
            this.log.error("删除目录失败" + remoteDir);
            e.printStackTrace();
        }
        return result;
    }
}