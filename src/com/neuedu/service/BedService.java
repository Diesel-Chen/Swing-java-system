package com.neuedu.service;

import com.neuedu.entity.Bed;

import javax.swing.*;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * 床位管理业务接口
 *
 * @author 高军
 * @date 2021-7-9
 */
public interface BedService extends BaseService {


    /**
     * 注册方法
     *
     * @param Bed 用户对象
     * @return boolean  注册成功返回真  失败返回假
     * @throws IOException
     */
    public boolean addBed(Bed Bed) throws IOException;




    /**
     * 多组合条件查询
     *
     * @param Bed
     * @return List<Bed>
     * @throws IOException
     */
    public List<Bed> queryByBed(Bed Bed) throws IOException;


}