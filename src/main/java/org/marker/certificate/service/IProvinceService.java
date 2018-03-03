package org.marker.certificate.service;

import java.util.List;

import org.marker.certificate.bean.Province;


/**
 * 省份Service
 * @author marker
 * @version 1.0
 */
public interface IProvinceService {

	/**
	 * 查询所有省份
	 * @return
	 */
	List<Province> queryForList();
	
	
	
}
