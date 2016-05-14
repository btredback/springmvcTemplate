package com.demo.util;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils
{
    public static List<List<String>> readWorkbook(InputStream is)
    {
        List data = new ArrayList();
        try
        {
            Workbook wb = WorkbookFactory.create(is);
            Sheet sheet = wb.getSheetAt(0);
            if (sheet == null)
            {
                return data;
            }

            int numOfRows = sheet.getPhysicalNumberOfRows();
            for (int rowNum = sheet.getFirstRowNum(); rowNum < numOfRows; rowNum++)
            {
                Row row = sheet.getRow(rowNum);
                if (row == null)
                {
                    continue;
                }
                List rowList = new ArrayList();

                for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++)
                {
                    Cell cell = row.getCell(cellNum, Row.CREATE_NULL_AS_BLANK);
                    if (cell == null)
                    {
                        rowList.add("");
                    } else if (cell.getCellType() == 4)
                    {
                        rowList.add(String.valueOf(cell.getBooleanCellValue()));
                    } else if (cell.getCellType() == 0)
                    {
                        double d = cell.getNumericCellValue();
                        if (DateUtil.isCellDateFormatted(cell))
                        {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            rowList.add(sdf.format(DateUtil.getJavaDate(d)));
                        } else
                        {
                            rowList.add(String.valueOf(d));
                        }
                    } else
                    {
                        rowList.add(cell.getStringCellValue());
                    }
                }

                if (!StringUtils.isBlank(StringUtils.join(rowList, ' ')))
                    data.add(rowList);
            }
        } catch (Exception e)
        {
            LoggerUtils.error(e);
        }
        return data;
    }
}