package com.neuedu.utils;

import com.neuedu.entity.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CommonUtil {
    /**
     * description :多条件查询工作人员
     *
     * @param workerReq 多条件请求封装的worker
     * @param workerRes 遍历全部存在worker的单个对象
     * @return 搜索的接过是否在文本中
     * @throws IOException
     */
    public static Boolean JudgeSearchWorkerIsInResList(Worker workerReq, Worker workerRes) {
        //cnt为0时查询全部
        int cnt = 0;
        if (CommonUtil.objCheckIsNull(workerReq)){
            return true;
        }
        if (!workerReq.getId().equals("") && workerReq.getId() != null) {
            cnt++;
        }
        if (!workerReq.getName().equals("") && workerReq.getName() != null) {
            cnt++;
        }
        if (!workerReq.getPost().equals("") && workerReq.getPost() != null) {
            cnt++;
        }
        //单条件查询
        if (cnt == 1) {
            return workerReq.getId().equals(workerRes.getId()) || workerReq.getName().equals(workerRes.getName()) || workerReq.getPost().equals(workerRes.getPost());
        } else if (cnt == 2) {
            return (workerReq.getId().equals(workerRes.getId()) && workerReq.getName().equals(workerRes.getName()))
                    ||
                    (workerReq.getId().equals(workerRes.getId()) && workerReq.getPost().equals(workerRes.getPost()))
                    ||
                    (workerReq.getName().equals(workerRes.getName()) && workerReq.getPost().equals(workerRes.getPost()));
        } else if (cnt == 3) {
            return workerReq.getId().equals(workerRes.getId()) && workerReq.getName().equals(workerRes.getName()) && workerReq.getPost().equals(workerRes.getPost());
        } else if (cnt == 0) {
            return true;
        }
        return false;
    }


    /**
     * description :多条件查询病患
     *
     * @param patientReq 多条件请求封装的Patient
     * @param patientRes 遍历全部存在Patient的单个对象
     * @return 搜索的接过是否在文本中
     * @throws IOException
     */
    public static Boolean JudgeSearchPatientIsInResList(Patient patientReq, Patient patientRes) {
        int cnt = 0;
        if (CommonUtil.objCheckIsNull(patientReq)){
            return true;
        }
        if (!patientReq.getName().equals("") && patientReq.getName() != null) {
            cnt++;
        }
        //单条件查询
        if (cnt == 1) {
            return patientReq.getName().equals(patientRes.getName());
        } else if (cnt == 0) {
            return true;
        }
        return false;
    }

    /**
     * description :多条件查询模版
     *
     * @param templateReq 多条件请求封装的Template
     * @param templateRes 遍历全部存在Template的单个对象
     * @return 搜索的接过是否在文本中
     * @throws IOException
     */
    public static Boolean JudgeSearchTemplateIsInResList(Template templateReq, Template templateRes) {
        int cnt = 0;
        if (CommonUtil.objCheckIsNull(templateReq)){
            return true;
        }
        if (!templateReq.getName().equals("") && templateReq.getName() != null) {
            cnt++;
        }
        if (!templateReq.getType().equals("") && templateReq.getType() != null){
            cnt++;
        }
        //单条件查询
        if (cnt == 1) {
            return templateReq.getName().equals(templateRes.getName())||templateReq.getType().equals(templateRes.getType());
        } else if (cnt == 0) {
            return true;
        } else if (cnt ==2){
            return templateReq.getName().equals(templateRes.getName())&&templateReq.getType().equals(templateRes.getType());
        }
        return false;
    }

    /**
     * description :多条件查询模版
     *
     * @param questionReq 多条件请求封装的Question
     * @param questionRes 遍历全部存在Question的单个对象
     * @return 搜索的接过是否在文本中
     * @throws IOException
     */
    public static Boolean JudgeSearchQuestionIsInResList(Question questionReq, Question questionRes) {
        int cnt = 0;
        if (CommonUtil.objCheckIsNull(questionReq)){
            return true;
        }
        if (!questionReq.getName().equals("") && questionReq.getName() != null) {
            cnt++;
        }
        if (!questionReq.getType().equals("") && questionReq.getType() != null){
            cnt++;
        }
        //单条件查询
        if (cnt == 1) {
            return questionReq.getName().equals(questionRes.getName())||questionReq.getType().equals(questionRes.getType());
        } else if (cnt == 0) {
            return true;
        } else if (cnt ==2){
            return questionReq.getName().equals(questionRes.getName())&&questionReq.getType().equals(questionRes.getType());
        }
        return false;
    }

    /**
     * description :多条件查询模版
     *
     * @param bedReq 多条件请求封装的Bed
     * @param bedRes 遍历全部存在Bed的单个对象
     * @return 搜索的接过是否在文本中
     * @throws IOException
     */
    public static Boolean JudgeSearchBedIsInResList(Bed bedReq, Bed bedRes) {
        int cnt = 0;
        if (CommonUtil.objCheckIsNull(bedReq)){
            return true;
        }
        if (!bedReq.getPatientName().equals("") && bedRes.getPatientName() != null) {
            cnt++;
        }
        //单条件查询
        if (cnt == 1) {
            return bedReq.getPatientName().equals(bedRes.getPatientName());
        } else if (cnt == 0) {
            return true;
        }
        return false;
    }



    /**
     * description:判断当前对象是否为空（包括所有属性为空）
     *
     * @param object 入参类
     * @return boolean
     **/
    public static boolean objCheckIsNull(Object object) {
        if (object == null) {
            return true;
        }
        // 得到类对象
        Class clazz = object.getClass();
        // 得到所有属性
        Field[] fields = clazz.getDeclaredFields();
        //定义返回结果，默认为true
        boolean flag = true;
        for (Field field : fields) {
            //设置权限（很重要，否则获取不到private的属性，不了解的同学补习一下反射知识）
            field.setAccessible(true);
            Object fieldValue = null;
            String fieldName = null;
            try {
                //得到属性值
                fieldValue = field.get(object);
                //得到属性类型
                Type fieldType = field.getGenericType();
                //得到属性名
                fieldName = field.getName();
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
            //只要有一个属性值不为null 就返回false 表示对象不为null
            if (fieldValue != null) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * description:生成唯一ID
     *
     * @return String
     **/
    public static String GenerateId() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //long millis = System.nanoTime();
        //加上两位随机数
        Random random = new Random();
        int end2 = random.nextInt(99);
        //如果不足两位前面补0
        String str = millis + String.format("%02d", end2);
        return str;
    }

    /**
     * description:获取当前时间字符串
     *
     * @return String
     **/
    public static String GetCurrentTime(){
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }


}
