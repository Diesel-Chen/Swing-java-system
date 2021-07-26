package com.neuedu.service.impl;

import com.neuedu.entity.Test;
import com.neuedu.entity.Test;
import com.neuedu.service.TestService;
import com.neuedu.utils.CommonUtil;
import com.neuedu.utils.FileUtil;
import com.neuedu.utils.GsonUtil;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.IOException;
import java.util.*;

public class TestServiceImpl implements TestService {

    @Override
    public void save(Object obj, String fileName, boolean mode) throws IOException {
        String json = GsonUtil.toJson(obj);
        FileUtil.writeData(json, fileName, mode);
    }

    @Override
    public List<Object> queryAll() throws IOException {
        List<Object> list = FileUtil.readData("Test.txt", Test.class);
        return list;
    }
    
    @Override
    public boolean updateTest(JTable table) throws IOException {
        int r = table.getRowCount();
        TableModel model = table.getModel();
        String str1 = null, str2 = null, str3 = null, str4 = null, str5 = null, str6 = null ,str8 = null;
        Integer int7 =null;
        int n = 0;
        for (int i = 0; i < r; i++) {
            str1 = (String) model.getValueAt(i, 0);
            str2 = (String) model.getValueAt(i, 1);
            str3 = (String) model.getValueAt(i, 2);
            str4 = (String) model.getValueAt(i, 3);
            str5 = (String) model.getValueAt(i, 4);
            str6 = (String) model.getValueAt(i, 5);
            int7 = (Integer) model.getValueAt(i, 6);
            str8 = (String) model.getValueAt(i, 7);

            if ((str1 == null || str1.equals("")) || (str2 == null || str2.equals("")))
                continue;
            n++;
            Test test = new Test(str1,str2,str3,str4,str5,str6,int7,str8);
            if (n == 1) {
                save(test, "Test.txt", false);
            } else {
                save(test, "Test.txt", true);
            }
        }
        return true;
    }


    @Override
    public boolean addTest(Test Test) throws IOException {
        String json_str = GsonUtil.toJson(Test);
        if (json_str.length() > 0)
            FileUtil.writeData(json_str, "Test.txt", true);
        return true;
    }


}