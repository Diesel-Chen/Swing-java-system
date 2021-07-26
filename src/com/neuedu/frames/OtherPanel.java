package com.neuedu.frames;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 测试切换功能用的面板类
 * @author
 * @date   2021-7-10
 */
public class OtherPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
    
	//创建面板是传入字符串对象msg,也可以是其他对象如用户对象等
	public OtherPanel(String msg) {
		Font font_01 = new Font("黑体",1,30);
		JLabel jla = new JLabel(msg);
		jla.setFont(font_01);
		//this 当前类的默认对象
		this.setLayout(null);
		jla.setSize(180, 40);//width, height
		jla.setLocation(300, 30);//列 行 
		
		this.add(jla);
		this.setSize(850, 600);
		this.setLocation(0, 0);
	}
}