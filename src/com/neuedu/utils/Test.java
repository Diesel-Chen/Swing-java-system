package com.neuedu.utils;

import java.io.IOException;
import java.util.List;

import com.neuedu.entity.Patient;

/**
 * @author ¬���
 * @date 2021/07/08
 */
public class Test {

    public static void main(String[] args) {
	  /*
		User user = new User();
		user.setId("1001");
		user.setPassword("aaaaaa");
		String str = GsonUtil.toJson(user);
		try {
			FileUtil.writeData(str, "Patient.txt", true);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("д���ļ�ʧ��");
		}
	  */

        try {
            List<Object> list = FileUtil.readData("Patient.txt", Patient.class);
            Patient patient = (Patient) list.get(0);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("�ļ���ȡʧ��");
        }
    }
}