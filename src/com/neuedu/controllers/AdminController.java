package com.neuedu.controllers;

import java.io.IOException;
import javax.swing.JTable;

import com.neuedu.entity.Admin;
import com.neuedu.factory.ServiceFactory;
import com.neuedu.service.AdminService;

/**
 * 管理员 controller
 *
 * @author
 * @Date 2021/07/16
 */
public class AdminController extends BaseController {
    //MVC模式 调用关系
    //窗口类-->控制器类-->业务类-->工具类
    private AdminService adminService;

    public AdminController(String msg) {
        super(msg);
        adminService = (AdminService) ServiceFactory.createService(msg);
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
        return adminService.validate(id, password);
    }

    /**
     * 管理员注册方法
     *
     * @param Admin 用户对象
     * @return boolean  注册成功返回真  失败返回假
     * @throws IOException
     */
    public boolean addAdmin(Admin Admin) throws IOException {
        return adminService.addAdmin(Admin);
    }

    /**
     * 按ID查询
     * @param id 用户编号
     * @return User对象
     * @throws IOException
     */
	/*public Admin queryByID(String id) throws IOException {
		return AdminService.queryByID(id);
	}*/

    /**
     * 删除用户方法
     *
     * @param JTable对象
     * @return boolean  删除成功返回真  失败返回假
     * @throws IOException
     */
    public boolean delAdmin(JTable table) throws IOException {
        return adminService.delAdmin(table);
    }

    /**
     * 更新用户方法
     *
     * @param JTable对象
     * @return boolean  更新成功返回真  失败返回假
     * @throws IOException
     */
    public boolean updateAdmin(JTable table) throws IOException {
        return adminService.updateAdmin(table);
    }
}