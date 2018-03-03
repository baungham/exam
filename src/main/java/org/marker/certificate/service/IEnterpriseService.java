package org.marker.certificate.service;

import java.io.Serializable;
import java.util.Map;

import org.marker.certificate.bean.Enterprise;
import org.marker.certificate.bean.Page;



/**
 * 企业管理Service
 * 实现根据用户名模糊查询[企业名称，城市]，需要分页
 * (注意：不用问为什么做)
 * @author marker
 * @version 1.0
 */
public interface IEnterpriseService extends ICommonService<Enterprise> {
	
	
	
	
	/**
	 * 通过Id查询
	 * @param id
	 * @return
	 */
	Enterprise findById(Serializable id);
	
	
	/**
	 * 分业查询
	 * 条件包含两种：
	 *      全部信息：all
	 *      企业名称：ename
	 *      企业城市：ecity
	 *      生产地址：eaddress
	 *      都采用模糊查询实现
	 * @param currentPageNo 当前页码
	 * @param pageSize  页面条数
	 * @param condition 条件
	 * @return
	 */
	public Page<Enterprise> queryByPage(int currentPageNo, int pageSize, Map<String, Object> condition);


	
	/**
	 * 根据企业名称查询企业信息
	 * @param ename 企业名称
	 * @return
	 */
	Enterprise findByName(String ename);
	
	
	
	/**
	 * 保存数据
	 * 若失败，需要返回错误信息
	 * @param enterprise
	 */
	public int saveForId(Enterprise enterprise);
}
