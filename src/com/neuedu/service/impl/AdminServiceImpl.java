package com.neuedu.service.impl;

import java.io.IOException;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import com.neuedu.entity.Admin;
import com.neuedu.exception.IDRepeatException;
import com.neuedu.service.AdminService;
import com.neuedu.utils.FileUtil;
import com.neuedu.utils.GsonUtil;

public class AdminServiceImpl implements AdminService {
    public void save(Object obj, String fileName, boolean mode) throws IOException {
        String json = GsonUtil.toJson(obj);
        FileUtil.writeData(json, fileName, mode);
    }

    public List<Object> queryAll() throws IOException {
        List<Object> list = FileUtil.readData("Admin.txt", Admin.class);
        return list;
    }

    /**
     * 校验(登录)方法
     *
     * @param id       用户编号
     * @param password 用户密码
     * @return boolean  登录成功返回真  失败返回假
     * @throws IOException
     */
    public boolean validate(String id, String password) throws IOException {
        List<Object> list = FileUtil.readData("Admin.txt", Admin.class);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Admin Admin02 = (Admin) list.get(i);
                String uid = Admin02.getId();
                String pwd = Admin02.getPassword();
                if (uid.equals(id) && pwd.equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    //注册
    public boolean addAdmin(Admin Admin) throws IOException {
        List<Object> list = FileUtil.readData("Admin.txt", Admin.class);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Admin Admin02 = (Admin) list.get(i);
                String uid = Admin02.getId();
                if (uid.equals(Admin.getId())) {
                    throw new IDRepeatException("ID重复请核对");
                }
            }
        }
        String json_str = GsonUtil.toJson(Admin);
        if (json_str.length() > 0)
            FileUtil.writeData(json_str, "Admin.txt", true);
        return true;
    }

    public Admin queryByID(String id) throws IOException {
        Admin Admin = null;
        List<Object> list = FileUtil.readData("Admin.txt", Admin.class);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Admin Admin02 = (Admin) list.get(i);
                if (id.equals(Admin02.getId())) {
                    Admin = Admin02;
                    break;
                }
            }
        }
        return Admin;
    }

    //优化后新增方法如下
    public boolean delAdmin(JTable table) throws IOException {
        int r = table.getRowCount();
        TableModel model = table.getModel();
        String str1 = null, str2 = null;
        int n = 0;
        for (int i = 0; i < r; i++) {
            if (table.isRowSelected(i) == false) {
                str1 = (String) model.getValueAt(i, 0);
                str2 = (String) model.getValueAt(i, 1);
                n++;
                Admin Admin = new Admin();
                Admin.setId(str1);
                Admin.setPassword(str2);
                if (n == 1) {
                    save(Admin, "Admin.txt", false);
                } else {
                    save(Admin, "Admin.txt", true);
                }
            }
        }
        return true;
    }

    @Override
    public boolean updateAdmin(JTable table) throws IOException {
        int r = table.getRowCount();
        TableModel model = table.getModel();
        String str1 = null, str2 = null;
        int n = 0;
        for (int i = 0; i < r; i++) {
            str1 = (String) model.getValueAt(i, 0);
            str2 = (String) model.getValueAt(i, 1);

            if ((str1 == null || str1.equals("")) || (str2 == null || str2.equals("")))
                continue;
            n++;
            Admin Admin = new Admin();
            Admin.setId(str1);
            Admin.setPassword(str2);
            if (n == 1) {
                save(Admin, "Admin.txt", false);
            } else {
                save(Admin, "Admin.txt", true);
            }
        }
        return true;
    }
}