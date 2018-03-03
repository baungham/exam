package org.marker.certificate.service.impl;

import java.io.Serializable;
import java.util.List;

import org.marker.certificate.bean.City;
import org.marker.certificate.dao.DaoEngine;
import org.marker.certificate.dao.mapper.ObjectMapper.MapperCity;
import org.marker.certificate.service.ICityService;


/**
 * 城市业务
 * @author marker
 * @version 1.0
 */
public class CityServiceImpl implements ICityService {

	// dao
	private DaoEngine dao = new DaoEngine();
	
	
	// 根据身份ID查询城市
	@Override
	public List<City> queryByProvinceId(Serializable id) {
		String sql = "select * from cer_city where province_id=?";
		return dao.queryForList(sql, new MapperCity(), id);
	}

}
