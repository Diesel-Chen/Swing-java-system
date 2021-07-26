package com.neuedu.service;

import java.io.IOException;
import javax.swing.JTable;

import com.neuedu.entity.Admin;

/**
 * 超级管理员业务接口
 *
 * @author
 * @date 2021-7-14
 */
public interface AdminService extends BaseService {

    /**
     * 校验(登录)方法
     *
     * @param id       用户编号
     * @param password 用户密码
     * @return boolean  登录成功返回真  失败返回假
     * @throws IOException
     */
    public boolean validate(String id, String password) throws IOException;

    /**
     * 管理员注册方法
     *
     * @param Admin 用户对象
     * @return boolean  注册成功返回真  失败返回假
     * @throws IOException
     */
    public boolean addAdmin(Admin Admin) throws IOException;

    /**
     * 按ID查询
     *
     * @param id 用户编号
     * @return User对象
     * @throws IOException
     */
    public Admin queryByID(String id) throws IOException;

    //---优化后新增方法如下=======================================

    /**
     * 删除用户方法
     *
     * @param JTable对象
     * @return boolean  删除成功返回真  失败返回假
     * @throws IOException
     */
    public boolean delAdmin(JTable table) throws IOException;

    /**
     * 更新用户方法
     *
     * @param JTable对象
     * @return boolean  更新成功返回真  失败返回假
     * @throws IOException
     */
    public boolean updateAdmin(JTable table) throws IOException;
}