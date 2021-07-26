package com.neuedu.service;

import com.neuedu.entity.Test;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * 评估管理业务接口
 *
 * @author 高军
 * @date 2021-7-9
 */
public interface TestService extends BaseService {


    /**
     * 注册方法
     *
     * @param Test 用户对象
     * @return boolean  注册成功返回真  失败返回假
     * @throws IOException
     */
    public boolean addTest(Test Test) throws IOException;


    /**
     * 更新方法
     *
     * @param JTable对象
     * @return boolean  更新成功返回真  失败返回假
     * @throws IOException
     */
    public boolean updateTest(JTable table) throws IOException;



}