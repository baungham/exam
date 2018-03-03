package org.marker.certificate.service;

import org.marker.certificate.bean.Class;
import org.marker.certificate.bean.Grade;
import org.marker.certificate.bean.Page;
import org.marker.certificate.bean.ServiceMessage;

import java.util.List;
import java.util.Map;

/**
 * 班级管理
 * @author marker
 * @version 1.0
 */
public interface ClassService extends ICommonService<Class> {


    /**
     * 分页查询年级
     * @param currentPageNo
     * @param pageSize
     * @param params
     * @return
     */
    Page<Class> queryByPage(int currentPageNo, int pageSize, Map<String, Object> params);

    /**
     * 获取所有年级
     * @return
     */
    List<Class> getAll();


    /**
     * 获取班级对象
     * @param className
     * @return
     */
    Class get(String className);

    ServiceMessage deleteByName(Object selectId);
}
