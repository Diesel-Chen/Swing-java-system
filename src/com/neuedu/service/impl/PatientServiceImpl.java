package com.neuedu.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import com.neuedu.entity.Patient;
import com.neuedu.entity.Patient;
import com.neuedu.exception.IDRepeatException;
import com.neuedu.service.PatientService;
import com.neuedu.utils.CommonUtil;
import com.neuedu.utils.FileUtil;
import com.neuedu.utils.GsonUtil;

public class PatientServiceImpl implements PatientService {

    @Override
    public void save(Object obj, String fileName, boolean mode) throws IOException {
        String json = GsonUtil.toJson(obj);
        FileUtil.writeData(json, fileName, mode);
    }

    @Override
    public List<Object> queryAll() throws IOException {
        List<Object> list = FileUtil.readData("Patient.txt", Patient.class);
        return list;
    }


    @Override
    public boolean addPatient(Patient patient) throws IOException {
        List<Object> list = FileUtil.readData("Patient.txt", Patient.class);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Patient patient2 = (Patient) list.get(i);
                String name = patient2.getName();
                if (name.equals(patient.getName())) {
                    throw new IDRepeatException("姓名重复，同名人请做好标记");
                }
            }
        }
        String json_str = GsonUtil.toJson(patient);
        if (json_str.length() > 0)
            FileUtil.writeData(json_str, "Patient.txt", true);
        return true;
    }

    //优化后新增方法如下
    @Override
    public boolean delPatient(List<Patient> patients) throws IOException {
        List<Object> list = FileUtil.readData("Patient.txt", Patient.class);
        List cpList = new ArrayList();
        //纯赋值
        if (list != null) {
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                Patient patient02 = (Patient) iterator.next();
                cpList.add(patient02);
            }
        }
        if (list != null) {
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                Patient patient02 = (Patient) iterator.next();
                System.out.println(patient02);
                for (int j = 0; j < patients.size(); j++) {
                    if (patients.get(j).getName().equals(patient02.getName())) {
                        cpList.remove(patient02);
                    }
                }
            }
        }
        if (cpList.size() == 0) {
            FileUtil.ClearFile("Patient.txt");
        }
        for (int i = 0; i < cpList.size(); i++) {
            if (i == 0) {
                save(cpList.get(i), "Patient.txt", false);
            } else {
                save(cpList.get(i), "Patient.txt", true);
            }
        }
        return true;
    }

    @Override
    public boolean updatePatient(JTable table) throws IOException {
        int r = table.getRowCount();
        TableModel model = table.getModel();
        String str1 = null, str2 = null, str3 = null, str4 = null, str5 = null, str6 = null, str7 = null;
        int n = 0;
        for (int i = 0; i < r; i++) {
            str1 = (String) model.getValueAt(i, 0);
            str2 = (String) model.getValueAt(i, 1);
            str3 = (String) model.getValueAt(i, 2);
            str4 = (String) model.getValueAt(i, 3);
            str5 = (String) model.getValueAt(i, 4);
            str6 = (String) model.getValueAt(i, 5);
            str7 = (String) model.getValueAt(i, 6);

            if ((str1 == null || str1.equals("")) || (str2 == null || str2.equals("")))
                continue;
            n++;
            Patient patient = new Patient();

            patient.setName(str1);
            patient.setBirthday(str2);
            patient.setSex(str3);
            patient.setIdentity(str4);
            patient.setTelephone(str5);
            patient.setFamily(str6);
            patient.setFamilytel(str7);
            if (n == 1) {
                save(patient, "Patient.txt", false);
            } else {
                save(patient, "Patient.txt", true);
            }
        }
        return true;
    }

    //多条件组合查询
    @Override
    public List<Patient> queryByPatient(Patient PatientReq) throws IOException {
        List<Object> list = FileUtil.readData("Patient.txt", Patient.class);
        List<Patient> returnList = new ArrayList<>();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Patient w = (Patient) list.get(i);
                if (CommonUtil.JudgeSearchPatientIsInResList(PatientReq, w)) {
                    returnList.add(w);
                }
            }
        }
        return returnList;
    }
}