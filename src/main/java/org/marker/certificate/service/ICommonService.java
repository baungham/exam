package org.marker.certificate.service;

import java.io.Serializable;

import org.marker.certificate.bean.ServiceMessage;


/**
 * 通用业务接口
 * @author marker
 * @version 1.0
 * @param <T>
 */
public interface ICommonService<T> {

	/**
	 * 保存数据
	 * 若失败，需要返回错误信息
	 * @param enterprise
	 */
	public ServiceMessage save(T enterprise);
	
	
	
	/**
	 * 更新数据
	 * 若失败，需要返回错误信息
	 * @param enterprise
	 */
	public ServiceMessage update(T enterprise);
	
	
	
	/**
	 * 根据id删除数据 
	 * @param id
	 */
	public ServiceMessage deleteById(Serializable id);
	
}
