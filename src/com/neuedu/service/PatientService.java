package com.neuedu.service;

import java.io.IOException;
import java.util.List;
import javax.swing.JTable;

import com.neuedu.entity.Patient;

/**
 * 病患管理业务接口
 *
 * @author 高军
 * @date 2021-7-9
 */
public interface PatientService extends BaseService {


    /**
     * 注册方法
     *
     * @param patient 对象
     * @return boolean  注册成功返回真  失败返回假
     * @throws IOException
     */
    public boolean addPatient(Patient patient) throws IOException;


    /**
     * 删除方法
     *
     * @param List<Patient>
     * @return boolean  删除成功返回真  失败返回假
     * @throws IOException
     */
    public boolean delPatient(List<Patient> patients) throws IOException;

    /**
     * 更新方法
     *
     * @param JTable对象
     * @return boolean  更新成功返回真  失败返回假
     * @throws IOException
     */
    public boolean updatePatient(JTable table) throws IOException;

    /**
     * 多组合条件查询
     *
     * @param Patient
     * @return List<Patient>
     * @throws IOException
     */
    public List<Patient> queryByPatient(Patient Patient) throws IOException;
}