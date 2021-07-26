package com.neuedu.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import javax.swing.JTable;
import com.neuedu.entity.Worker;
/**
 * 工作人员 管理业务接口
 * 
 * @author
 * @date 2021-7-14
 */
public interface WorkerService extends BaseService {
	
	/**
	 * 校验(登录)方法
	 * @param id 用户编号
	 * @param password 用户密码
     * @return boolean  登录成功返回真  失败返回假
	 * @throws IOException
	 */
	public boolean validate(String id, String password) throws IOException;
	
	/**
	 * 注册方法
	 * @param worker 工作人员对象
	 * @return boolean  注册成功返回真  失败返回假
	 * @throws IOException
	 */
	public boolean addWorker(Worker worker) throws IOException;

	/**
	 * 删除方法
	 * @param List<Worker>
	 * @return boolean  删除成功返回真  失败返回假
	 * @throws IOException
	 */
	public boolean delWorker(List<Worker> workers) throws IOException;
	
	/**
	 * 更新方法
	 * @param JTable对象
	 * @return boolean  更新成功返回真  失败返回假
	 * @throws IOException
	 */
	public boolean updateWorker(JTable table) throws IOException;

	/**
	 * 多组合条件查询
	 *
	 * @param Worker
	 * @return List<Worker>
	 * @throws IOException
	 */
	public List<Worker> queryByWorker(Worker worker) throws IOException;

	/**
	 * 查询全部不重复的职称类别
	 *
	 * @return Set
	 * @throws IOException
	 */
	public Set queryAllPost() throws  IOException;
}