package org.marker.certificate.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.marker.certificate.bean.Certificate;
import org.marker.certificate.bean.Page;

/**
 * 证书管理
 * @author marker
 * @version 1.0
 */
public interface ICertificateService extends ICommonService<Certificate> {

	
	/**
	 * 检查证书编号是否存在
	 * @param code
	 * @return
	 */
	public boolean checkCode(String code);
	
	/**
	 * 根据Id查询证书详情况
	 * @param id
	 * @return
	 */
	public Certificate findById(Serializable id);
	
	
	/**
	 * 证书分页查询
	 * 条件包含两种：
	 *      全部信息：all
	 *      证书编号：ccode
	 *      企业名称：ename 
	 *      企业地址：ecity 
	 *      
	 *      都采用模糊查询实现
	 * @param currentPageNo 当前页码
	 * @param pageSize  页面条数
	 * @param condition 条件
	 * @return
	 */
	public Page<Certificate> queryByPage(int currentPageNo, int pageSize, Map<String, Object> condition);
	
	
	/**
	 * 查询当前队列中的打印任务
	 * @return
	 */
	public List<Certificate> queryCurrentQueue();


	
	/**
	 * 提交打印任务
	 * @param id
	 */
	public void submitPrintQueue(Serializable id);

	

	/**
	 * 抓取一条证书数据，并清除该数据
	 * @return
	 */
	public Certificate fetch();
	
	
	
	/**
	 * 获取未打印证书数量
	 * @return
	 */
	public int getNotPrinter();
	
	
	
	/**
	 * 提交未打印记录到打印队列
	 */
	public void submitNotPrintToQueue();
	
	
	
	/**
	 * 更新状态
	 * @param cid
	 */
	public void updateCertificate(Serializable cid);


	
	/**
	 * 查询所有
	 * @return
	 */
	public List<Certificate> query();
	
}
