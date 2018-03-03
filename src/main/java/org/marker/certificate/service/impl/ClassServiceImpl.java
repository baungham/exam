package org.marker.certificate.service.impl;/**
 * Created by marker on 2018/2/24.
 */

import org.marker.certificate.bean.Class;
import org.marker.certificate.bean.Grade;
import org.marker.certificate.bean.Page;
import org.marker.certificate.bean.ServiceMessage;
import org.marker.certificate.dao.DaoEngine;
import org.marker.certificate.dao.mapper.ObjectMapper;
import org.marker.certificate.service.ClassService;
import org.marker.certificate.service.GradeService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author marker
 * @create 2018-02-24 上午11:27
 **/
public class ClassServiceImpl implements ClassService{



    // dao
    private DaoEngine dao = new DaoEngine();


    @Override
    public ServiceMessage save(Class entity) {
        entity.setCreateTime(new Date());

        String sql = "INSERT INTO `t_class`(name, gradeName, desc, createTime) VALUES(?,?,?,?)";
        Object[] params = new Object[]{
                entity.getName(),
                entity.getGradeName(),
                entity.getDesc(),
                entity.getCreateTime(),};
        if(dao.update(sql, params)){
            return new ServiceMessage(true, "添加成功!");
        }
        return new ServiceMessage(false, "添加失败!");
    }

    @Override
    public ServiceMessage update(Class enterprise) {
        return null;
    }

    @Override
    public ServiceMessage deleteById(Serializable id) {
        return null;
    }

    @Override
    public Page<Class> queryByPage(int currentPageNo, int pageSize, Map<String, Object> condition) {

        StringBuilder sql = new StringBuilder("select * from t_class e where 1=1 ");
        StringBuilder count = new StringBuilder("select count(*) from t_class e where 1=1 ");
        List<Class> list ;

        List<Object> params = new ArrayList<Object>();
        if(condition != null){
            String ename = (String) condition.get("name");
            if(ename != null){
                sql.append(" and e.name like ?");params.add('%'+ename+'%');
                count.append(" and e.name like ?");
            }
        }

        int c = dao.queryForInt(count.toString(), params.toArray());

        Page<Class> page = new Page<>();
        page.setCurrentPageNo(currentPageNo);
        page.setTotalRows(c);
        page.setPageSize(pageSize);

        sql.append(" limit ?,? ");
        params.add((currentPageNo-1)*pageSize);
        params.add(pageSize);
        list = dao.queryForList(sql.toString(), new ObjectMapper.MapperClass() , params.toArray());

        page.setData(list);
        return page;
    }

    @Override
    public List<Class> getAll() {
        StringBuilder sql = new StringBuilder("select * from t_class e where 1=1 ");
        return dao.queryForList(sql.toString(), new ObjectMapper.MapperClass() );
    }

    @Override
    public Class get(String className) {
        StringBuilder sql = new StringBuilder("select * from t_class e where name=? ");
        return dao.queryForObject(sql.toString(), new ObjectMapper.MapperClass(), className);
    }

    @Override
    public ServiceMessage deleteByName(Object name) {
        String sql = "delete from t_class where name = ?";
        if(dao.update(sql, name)){
            return new ServiceMessage(true, "操作成功!");
        }
        return new ServiceMessage(false, "操作失败!");
    }
}
