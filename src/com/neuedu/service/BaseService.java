package com.neuedu.service;

import java.io.IOException;
import java.util.List;

/**
 * 业务基接口(设立原因:避免本包中各接口的方法冗余)
 *
 * @author 高军
 * @date 2021-7-8
 */
public interface BaseService {

    /**
     * 保存操作
     *
     * @param obj 需要保存的对象
     * @return 无返回值
     * @throws IOException
     */
    public void save(Object obj, String fileName, boolean mode) throws IOException;

    /**
     * 全部查询操作
     *
     * @param 无
     * @return 对象集合
     * @throws 无
     */
    public List<Object> queryAll() throws IOException;
}