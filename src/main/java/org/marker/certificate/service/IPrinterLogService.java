/**
 * 
 */
package org.marker.certificate.service;

import java.util.Map;

import org.marker.certificate.bean.Certificate;
import org.marker.certificate.bean.Page;
import org.marker.certificate.bean.PrinterLog;

/**
 * 
 * 打印日志service
 * @author marker
 * @version 1.0
 */
public interface IPrinterLogService {

  
	
	/**
	 * 打印记录，并返回记录ID，方便后续修改打印状态
	 * @param enterprise
	 * @return
	 */
	public int save(Certificate cer);
	
	
	
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
	public Page<PrinterLog> queryByPage(int currentPageNo, int pageSize, Map<String, Object> condition);
	
	
	
	
	
	
	
	
}
