package com.code.generate.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 描述：文件操作工具类
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE             PERSON          REASON
 *  1    2018/12/4      fengjiajia         Create
 * ****************************************************************************
 * </pre>
 * @author fengjiajia
 * @since 1.0
 */
public class FileUtils {
    public static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static File getResourceFile(String url) {
        try {
            return  ResourceUtils.getFile("classpath:"+url);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("找不到文件["+url+"]");
        }
    }

    public static List<File> getResourceDirListFiles(String url, boolean hasChild) {
        try {
            System.out.println("查找模板文件----目录["+url+"]---------------");
            File file = ResourceUtils.getFile("classpath:" + url);
            return getChildFiles(hasChild, file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("找不到文件["+url+"]");
        }
    }

    /**
     * 描述: 递归查询文件夹下所有文件
     * @author 2018年12月13日  fengjiajia
     * @param hasChild 是否递归
     * @param file 目录
     */
    public static List<File> getChildFiles(boolean hasChild, File file) {
        File fileArray[] = file.listFiles();
        if (fileArray == null) {
            return new ArrayList<>(0);
        }
        if (hasChild) {
            List<File> list = new ArrayList<>();
            for (File childFile : fileArray) {
                if (childFile.isDirectory()) {
                    System.out.println("找到模板目录["+childFile.getName()+"]---------------");
                    list.addAll(getChildFiles(hasChild, childFile));
                }else{
                    System.out.println("找到模板文件["+childFile.getName()+"]---------------");
                    list.add(childFile);
                }
            }
            return list;
        }else {
            return Arrays.asList(fileArray);
        }
    }

    public static String getFileContent(File f) {
        try( FileInputStream input = new FileInputStream(f)) {
            StringBuilder strBuffer = new StringBuilder();
            byte[] b = new byte[1024];
            if(f.exists()){
                while(input.available()!=0){
                    int len = input.read(b);
                    strBuffer.append(new String(b, 0, len));
                }
            }
            return strBuffer.toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("读取文件出现io异常, file:" + f.getName());
        } catch(IOException e){
            throw new RuntimeException("读取文件出现io异常: , file:" + f.getName());
        }
    }

    /**
     * 描述: 获取文件，写入文件
     */
    public static void generateFile(String str, String path, String fileName) {
        Boolean appendFlag = Boolean.FALSE;
        writeFile(str, getFile(path, fileName, appendFlag), appendFlag);
    }

    /**
     * 描述: 添加的方式获取文件
     */
    public static void appendGenerateFile(String str, String path, String fileName) {
        Boolean appendFlag = Boolean.TRUE;
        writeFile(str, getFile(path, fileName, appendFlag), appendFlag);
    }


    /**
     * 描述: 通过文件路径和文件名删除，创建新的文件夹，或者获取已经存在的文件夹
     */
    private static File getFile(String path, String fileName, boolean appendFlag) {
        // 创建文件，并获得文件
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(path + File.separatorChar + fileName);
        // 文件是否存在
        if (file.exists()) {
            // 判断是否是附加
            if (!appendFlag) {
                // 不是附加删除再新增
                file.delete();
                // 不存在新增
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    System.out.println("ERROR--------创建文件失败：" + "path：" + path + ", fileName:" + fileName + "。");
                    e.printStackTrace();
                }
            }
        } else {
            // 不存在新增
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("ERROR--------创建文件失败：" + "path：" + path + ", fileName:" + fileName + "。");
                e.printStackTrace();
            }
        }
        return file;
    }
    /**
     * 描述: 将数据写入文件中
     */
    private static void writeFile(String str, File file, boolean appendFlag) {
        // 获取输入到文件系统的流
        try (FileOutputStream output = new FileOutputStream(file, appendFlag);) {
            // 文件系统写入文本内容的字节数据
            output.write(str.getBytes());
            // 写入数据
            output.flush();
        } catch (IOException e) {
            // 由于生成文件失败，不影响其他文件生成，所以继续执行，将异常打印到系统中即可。
            System.out.println("ERROR--------生成文件写入数据失败：" + "File：" + file + "。");
            e.printStackTrace();
        }
    }
}
