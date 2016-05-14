package com.demo.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XmlUtils
{
    public static Document datasetToXML(Map<String, List<Map<String, Object>>> dataset)
    {
        Element rowsNode;
        Iterator i$;
        Map row;
        Element rowNode;
        Document document = DocumentHelper.createDocument();

        Element datasetNode = document.addElement("dataset");
        Set<String> nodes = dataset.keySet();
        for (String node : nodes)
        {
            rowsNode = datasetNode.addElement(node);
            List rows = (List) dataset.get(node);
            for (i$ = rows.iterator(); i$.hasNext();)
            {
                row = (Map) i$.next();

                rowNode = rowsNode.addElement("row");
                Set<String> fieldNames = row.keySet();
                for (String fieldName : fieldNames)
                {
                    Object obj = row.get(fieldName);

                    rowNode.addElement(fieldName).addText(obj != null ? obj.toString() : "");
                }
            }
        }

        return document;
    }

    public static String objectToXML(Object obj, String nodeName)
    {
        Class clazz = obj.getClass();
        StringBuffer result = new StringBuffer(new StringBuilder().append("<").append(nodeName).append(">\n").toString());
        try
        {
            Field[] fields = clazz.getDeclaredFields();
            if (fields.length == 0)
                throw new Exception("no fields declared in class ".concat(clazz.getName()));
            for (Field f : fields)
            {
                String key = f.getName();
                f.setAccessible(true);
                result.append(new StringBuilder().append("\t<").append(key).append(">").append(f.get(obj)).append("</").append(key).append(">\n").toString());
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        result.append(new StringBuilder().append("</").append(nodeName).append(">\n").toString());
        return result.toString();
    }

    public static String listToXML(List<?> list, String listNodeName, String objectNodeName)
    {
        StringBuffer result = new StringBuffer(new StringBuilder().append("<").append(listNodeName).append(">\n").toString());
        for (Iterator i$ = list.iterator(); i$.hasNext();)
        {
            Object object = i$.next();
            result.append(objectToXML(object, objectNodeName));
        }
        result.append(new StringBuilder().append("</").append(listNodeName).append(">\n").toString());
        return result.toString();
    }

    public static Map<String, List<Map<String, Object>>> xmlToDataset(Document document)
    {
        Map tables = new HashMap();
        Element root = document.getRootElement();

        Iterator tableIterator = root.elementIterator();
        while (tableIterator.hasNext())
        {
            Element table = (Element) tableIterator.next();

            Iterator recordIterator = table.elementIterator();

            List records = new ArrayList();
            while (recordIterator.hasNext())
            {
                Element record = (Element) recordIterator.next();

                List<Element> propertyList = record.elements();
                Map data = new HashMap();
                for (Element property : propertyList)
                {
                    data.put(property.getName(), property.getTextTrim());
                }
                records.add(data);
            }
            tables.put(table.getName(), records);
        }
        return tables;
    }

    public static String wrapXml(String xml)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sb.append(xml);
        return sb.toString();
    }

    public static String toTaskListXml4Node(List<Map<String, Object>> data, String nodeName)
    {
        StringBuilder sb = new StringBuilder();

        for (Map map : data)
        {
            sb.append("<").append(nodeName).append(">\n");
            Set keys = map.keySet();
            Iterator iterator = keys.iterator();
            while (iterator.hasNext())
            {
                String key = (String) iterator.next();
                sb.append("<").append(key).append(">");
                Object val = map.get(key);
                sb.append(val == null ? "" : val);
                sb.append("</").append(key).append(">\n");
            }
            sb.append("</").append(nodeName).append(">\n");
        }

        return sb.toString();
    }

    public static String toTaskMapXml4Node(Map<String, Object> data)
    {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : data.entrySet())
        {
            sb.append("<").append((String) entry.getKey()).append(">").append(entry.getValue() == null ? "" : entry.getValue()).append("</").append((String) entry.getKey()).append(">\n");
        }

        return sb.toString();
    }

    public static String toTaskListXml4Root(String xml)
    {
        return new StringBuilder().append("<LIST>\n").append(xml).append("</LIST>\n").toString();
    }
}