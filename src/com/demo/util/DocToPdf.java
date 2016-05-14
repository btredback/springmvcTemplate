package com.demo.util;

import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import java.io.File;
import java.net.ConnectException;

public class DocToPdf
{
    public static boolean doc2pdf(String docFilePath, String pdfFilePath) throws Exception
    {
        File docFile = new File(docFilePath);
        File pdfFile = new File(pdfFilePath);
        DefaultDocumentFormatRegistry formatReg;

        if (docFile.exists())
        {
            OpenOfficeConnection connection = null;
            try
            {
                connection = new SocketOpenOfficeConnection(8100);
                connection.connect();
                OpenOfficeDocumentConverter converter = new OpenOfficeDocumentConverter(connection);
                if (docFilePath.toLowerCase().endsWith(".txt"))
                {
                    formatReg = new DefaultDocumentFormatRegistry();
                    DocumentFormat txt = formatReg.getFormatByFileExtension("odt");
                    DocumentFormat pdf = formatReg.getFormatByFileExtension("pdf");
                    converter.convert(docFile, txt, pdfFile, pdf);
                } else
                {
                    converter.convert(docFile, pdfFile);
                }
                // DefaultDocumentFormatRegistry formatReg = 1;
                // return formatReg;
                return true;
            } catch (ConnectException ex)
            {
                LoggerUtils.error("office文件转化为pdf时出现网络连接异常：");
                LoggerUtils.error(ex.getMessage());
            } catch (OpenOfficeException ex)
            {
                LoggerUtils.error("office文件转化为pdf时转换异常：");
                LoggerUtils.error(ex.getMessage());
            } catch (Exception ex)
            {
                LoggerUtils.error("office文件转化为pdf时出现异常：");
                LoggerUtils.error(ex.getMessage());
            } finally
            {
                if (connection != null)
                    try
                    {
                        connection.disconnect();
                    } catch (Exception ex)
                    {
                    }
            }
        }
        return false;
    }
}