package com.neuedu.service.impl;

import com.neuedu.entity.Template;
import com.neuedu.entity.Worker;
import com.neuedu.exception.IDRepeatException;
import com.neuedu.service.TemplateService;
import com.neuedu.utils.CommonUtil;
import com.neuedu.utils.FileUtil;
import com.neuedu.utils.GsonUtil;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.IOException;
import java.util.*;

public class TemplateServiceImpl implements TemplateService {

    @Override
    public void save(Object obj, String fileName, boolean mode) throws IOException {
        String json = GsonUtil.toJson(obj);
        FileUtil.writeData(json, fileName, mode);
    }

    @Override
    public List<Object> queryAll() throws IOException {
        List<Object> list = FileUtil.readData("Template.txt", Template.class);
        return list;
    }


    @Override
    public boolean addTemplate(Template Template) throws IOException {
        String json_str = GsonUtil.toJson(Template);
        if (json_str.length() > 0)
            FileUtil.writeData(json_str, "Template.txt", true);
        return true;
    }

    //优化后新增方法如下
    @Override
    public boolean delTemplate(List<Template> Templates) throws IOException {
        List<Object> list = FileUtil.readData("Template.txt", Template.class);
        List cpList = new ArrayList();
        //纯赋值
        if (list != null) {
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                Template Template02 = (Template) iterator.next();
                cpList.add(Template02);
            }
        }
        if (list != null) {
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                Template Template02 = (Template) iterator.next();
                System.out.println(Template02);
                for (int j = 0; j < Templates.size(); j++) {
                    if (Templates.get(j).getId().equals(Template02.getId())) {
                        cpList.remove(Template02);
                    }
                }
            }
        }
        if (cpList.size() == 0) {
            FileUtil.ClearFile("Template.txt");
        }
        for (int i = 0; i < cpList.size(); i++) {
            if (i == 0) {
                save(cpList.get(i), "Template.txt", false);
            } else {
                save(cpList.get(i), "Template.txt", true);
            }
        }
        return true;
    }

    @Override
    public boolean updateTemplate(JTable table) throws IOException {
        int r = table.getRowCount();
        TableModel model = table.getModel();
        String str1 = null, str2 = null, str3 = null, str4 = null, str5 = null, str6 = null, str7 = null;
        int n = 0;
        for (int i = 0; i < r; i++) {
            str1 = (String) model.getValueAt(i, 0);
            str2 = (String) model.getValueAt(i, 1);
            str3 = (String) model.getValueAt(i, 2);

            if ((str1 == null || str1.equals("")) || (str2 == null || str2.equals("")))
                continue;
            n++;
            Template Template = new Template();

            Template.setId(str1);
            Template.setName(str2);
            Template.setType(str3);

            if (n == 1) {
                save(Template, "Template.txt", false);
            } else {
                save(Template, "Template.txt", true);
            }
        }
        return true;
    }

    //多条件组合查询
    @Override
    public List<Template> queryByTemplate(Template TemplateReq) throws IOException {
        List<Object> list = FileUtil.readData("Template.txt", Template.class);
        List<Template> returnList = new ArrayList<>();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Template w = (Template) list.get(i);
                if (CommonUtil.JudgeSearchTemplateIsInResList(TemplateReq, w)) {
                    returnList.add(w);
                }
            }
        }
        return returnList;
    }

    //查询模版类别
    @Override
    public Set queryAllType() throws IOException {
        Set resSet = new HashSet();
        List<Object> list = FileUtil.readData("Template.txt", Template.class);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Template w = (Template) list.get(i);
                resSet.add(w.getType());
            }
        }
        return resSet;
    }
}