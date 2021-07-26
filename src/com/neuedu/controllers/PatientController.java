package com.neuedu.controllers;

import java.io.IOException;
import java.util.List;

import javax.swing.JTable;

import com.neuedu.entity.Patient;
import com.neuedu.entity.Patient;
import com.neuedu.factory.ServiceFactory;
import com.neuedu.service.PatientService;

/**
 * 病患管理 controller
 *
 * @author
 * @Date 2021/07/16
 */
public class PatientController extends BaseController {
    //MVC模式 调用关系
    //窗口类-->控制器类-->业务类-->工具类
    private PatientService patientService;

    public PatientController(String msg) {
        super(msg);
        patientService = (PatientService) ServiceFactory.createService(msg);
    }

    /**
     * 注册方法
     *
     * @param patient 对象
     * @return boolean  注册成功返回真  失败返回假
     * @throws IOException
     */
    public boolean addPatient(Patient patient) throws IOException {
        return patientService.addPatient(patient);
    }


    /**
     * 删除方法
     *
     * @param List<Patient>
     * @return boolean  删除成功返回真  失败返回假
     * @throws IOException
     */
    public boolean delPatient(List<Patient> patients) throws IOException {
        return patientService.delPatient(patients);
    }

    /**
     * 更新方法
     *
     * @param JTable对象
     * @return boolean  更新成功返回真  失败返回假
     * @throws IOException
     */
    public boolean updatePatient(JTable table) throws IOException {
        return patientService.updatePatient(table);
    }


    /**
     * 多组合条件查询
     *
     * @param Patient
     * @return List<Patient
     * @throws IOException
     */
    public List<Patient> queryByPatient(Patient Patient) throws IOException {
        return patientService.queryByPatient(Patient);
    }
}