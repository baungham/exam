package org.marker.certificate.service;

import java.io.Serializable;
import java.util.List;

import org.marker.certificate.bean.City;


/**
 * 城市Service 
 * @author marker
 * @version 1.0
 */
public interface ICityService {

	/**
	 * 根据省份ID查询城市
	 * @return
	 */
	List<City> queryByProvinceId(Serializable id);
	
	
	
}
