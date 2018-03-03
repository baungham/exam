package org.marker.certificate.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.marker.certificate.bean.AreaCount;
import org.marker.certificate.bean.TimeCount;


/**
 * 统计
 * @author marker
 * @version 1.0
 */
public interface ICountService {

	/**
	 * 统计城市下面的证书数量和企业数量
	 */
	public List<AreaCount> areaCount();
	
	
	/**
	 * 统计城市下面的证书数量和企业数量
	 */
	public ByteArrayOutputStream areaCountPie();
	
	
	
	
	/**
	 * 有效时间统计
	 */
	public List<TimeCount> timeCount();
}
