package org.marker.certificate.service;

import org.marker.certificate.bean.Certificate;
import org.marker.certificate.bean.Grade;
import org.marker.certificate.bean.Page;
import org.marker.certificate.bean.ServiceMessage;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 年级管理
 * @author marker
 * @version 1.0
 */
public interface GradeService extends ICommonService<Grade> {


    /**
     * 分页查询年级
     * @param currentPageNo
     * @param pageSize
     * @param params
     * @return
     */
    Page<Grade> queryByPage(int currentPageNo, int pageSize, Map<String, Object> params);

    /**
     * 获取所有年级
     * @return
     */
    List<Grade> getAll();


    /**
     *
     * @param selectId
     * @return
     */
    Grade findById(int selectId);


    /**
     * 通过名称查询
     * @param name
     * @return
     */
    Grade findByName(Object name);

    ServiceMessage deleteByName(Object selectId);
}
