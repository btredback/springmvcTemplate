package com.demo.util;

import java.io.IOException;
import java.lang.reflect.Field;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.base.JRBaseReport;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXhtmlExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

public class JasperExportUtils
{
    public static void prepareReport(JasperReport jasperReport)
    {
        try
        {
            Field margin = JRBaseReport.class.getDeclaredField("leftMargin");
            margin.setAccessible(true);
            margin.setInt(jasperReport, 0);
            margin = JRBaseReport.class.getDeclaredField("topMargin");
            margin.setAccessible(true);
            margin.setInt(jasperReport, 0);
            margin = JRBaseReport.class.getDeclaredField("bottomMargin");
            margin.setAccessible(true);
            margin.setInt(jasperReport, 0);
            Field pageHeight = JRBaseReport.class.getDeclaredField("pageHeight");
            pageHeight.setAccessible(true);
            pageHeight.setInt(jasperReport, 2147483647);
        } catch (Exception exception)
        {
        }
    }

    public static void exportExcel(JasperPrint jasperPrint, HttpServletRequest request, HttpServletResponse response, String reportName, String disposition) throws IOException, JRException
    {
        response.setContentType("application/vnd.ms-excel");
        String fileName = new String((reportName + ".xls").getBytes("GBK"), "ISO-8859-1");
        response.setHeader("Content-disposition", disposition + "; filename=" + fileName);

        ServletOutputStream ouputStream = response.getOutputStream();
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
        exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
        exporter.exportReport();
        ouputStream.flush();
        ouputStream.close();
    }

    public static void exportPdf(JasperPrint jasperPrint, HttpServletRequest request, HttpServletResponse response, String reportName, String disposition) throws IOException, JRException
    {
        response.setContentType("application/pdf");
        String fileName = new String((reportName + ".pdf").getBytes("GBK"), "ISO-8859-1");
        response.setHeader("Content-disposition", disposition + "; filename=" + fileName);
        ServletOutputStream ouputStream = response.getOutputStream();
        try
        {
            JasperExportManager.exportReportToPdfStream(jasperPrint, ouputStream);
            ouputStream.flush();
            ouputStream.close();
        } catch (Exception e)
        {
        }
    }

    public static void exportHtml(JasperPrint jasperPrint, HttpServletRequest request, HttpServletResponse response) throws IOException, JRException
    {
        String header = "<!DOCTYPE html><html><head>  <title>report</title>  <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>  <style type='text/css'> a {text-decoration: none} </style></head><body><table width='100%' cellpadding='0' cellspacing='0' border='0'><tr><td width='50%'>&nbsp;</td><td align='center'>";

        String footer = "</td><td width='50%'>&nbsp;</td></tr></table></body></html>";
        response.setContentType("text/html");
        ServletOutputStream ouputStream = response.getOutputStream();
        JRXhtmlExporter exporter = new JRXhtmlExporter();
        exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
        exporter.setParameter(JRHtmlExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);
        exporter.setParameter(JRHtmlExporterParameter.SIZE_UNIT, "pt");
        exporter.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        exporter.setParameter(JRHtmlExporterParameter.FRAMES_AS_NESTED_TABLES, Boolean.TRUE);
        exporter.setParameter(JRHtmlExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
        exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, header);
        exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, footer);
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
        exporter.exportReport();

        ouputStream.flush();
        ouputStream.close();
    }

    public static void exportWord(JasperPrint jasperPrint, HttpServletRequest request, HttpServletResponse response, String reportName, String disposition) throws JRException, IOException
    {
        response.setContentType("application/msword;charset=utf-8");
        String fileName = new String((reportName + ".doc").getBytes("GBK"), "ISO-8859-1");
        response.setHeader("Content-disposition", disposition + "; filename=" + fileName);
        JRExporter exporter = new JRRtfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());

        exporter.exportReport();
    }
}