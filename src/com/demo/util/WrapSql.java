package com.demo.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class WrapSql
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
//        File dir = new File("D:\\个人资料\\纯净版\\db\\unwrap\\pkg");
//    	File dir = new File("E:\\temp\\unwrap\\pkg");
    	File dir = new File("E:\\temp\\unwrap\\fn");
        File[] files = dir.listFiles();
        ArrayList<String> exePlbCMD=new ArrayList<String>();
        for (int i = 0; i < files.length; i++) {
//          if(!files[i].isDirectory()){
//             files[i];
//          }
            if(files[i].isFile())
            {
                File file=files[i];
                String fileFullName=file.getName();
                String path=file.getParent()+"\\";
                String plbPath=path.replaceAll("unwrap", "wrap");
//                if(fileFullName.endsWith("bdy"))
                if(fileFullName.endsWith("fnc"))
                {
                    String plbFileName=fileFullName.substring(0,fileFullName.length()-3)+"plb";
                    System.out.println("wrap iname=\""+path+fileFullName+"\"  oname=\""+plbPath+plbFileName+"\"");
                    exePlbCMD.add("@\""+plbPath+plbFileName+"\"");
                }
                
            }
        }
        System.out.println("------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------");
        for (Iterator iterator = exePlbCMD.iterator(); iterator.hasNext();)
        {
            String string = (String) iterator.next();
            System.out.println(string);
        }

    }

}
