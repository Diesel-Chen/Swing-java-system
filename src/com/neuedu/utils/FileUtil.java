package com.neuedu.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件读写工具
 *
 * @author 高军
 * @date 2021-7-8
 */
public class FileUtil {

    /**
     * @param data     需要写入文件的字符串
     * @param fileName 需要写入的文件名
     * @param mode     写入模式  false为覆盖模式,true为追加模式
     * @return 无返回值
     * @throws IOException
     */
    public static void writeData(String data, String fileName, boolean mode) throws IOException {
        String fileName2 = "data/" + fileName;
        FileWriter fw = new FileWriter(fileName2, mode);//false覆盖模式,true追加模式
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(data);
        bw.newLine();//加入换行
        //刷新流
        bw.flush();
        bw.close();
        fw.close();//关闭流时候先关闭大的再关闭小的
    }

    /**
     * @param fileName 需要读取的文件名
     * @param Class<?> C 操作类型   C:类类型
     * @return List <Employee>
     * @throws IOException
     */
    public static List<Object> readData(String fileName, Class<?> C) throws IOException {
        String filePath = "data/" + fileName;
        List<Object> list = new ArrayList<Object>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String str = null;
        while ((str = br.readLine()) != null) {
            str = "[" + str + "]";
            ;
            Object obj = GsonUtil.toObj(str, C);
            list.add(obj);
        }
        br.close();
        return list;
    }

    /**
     * 解决删除最后一个对象时，""写入文件后。读取文件后 文件不为null的bug
     *
     * @param fileName 需要读取的文件名
     * @throws IOException
     */
    public static void ClearFile(String fileName) throws IOException {
        String fileName2 = "data/" + fileName;
        File file = new File(fileName2);
        file.delete();
        file.createNewFile();
    }
}