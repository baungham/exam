package org.marker.certificate.service.impl;/**
 * Created by marker on 2018/2/24.
 */

import org.marker.certificate.bean.Enterprise;
import org.marker.certificate.bean.Grade;
import org.marker.certificate.bean.Page;
import org.marker.certificate.bean.ServiceMessage;
import org.marker.certificate.dao.DaoEngine;
import org.marker.certificate.dao.mapper.ObjectMapper;
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
public class GradeServiceImpl implements GradeService{



    // dao
    private DaoEngine dao = new DaoEngine();


    @Override
    public ServiceMessage save(Grade entity) {
        entity.setCreateTime(new Date());

        String sql = "INSERT INTO `t_grade`(name,desc,createTime) VALUES(?,?,?)";
        Object[] params = new Object[]{
                entity.getName(),
                entity.getDesc(),
                entity.getCreateTime()
        };
        if(dao.update(sql, params)){
            return new ServiceMessage(true, "添加成功!");
        }
        return new ServiceMessage(false, "添加失败!");
    }

    @Override
    public ServiceMessage update(Grade entity) {
        String sql = "update `t_grade` set name=?, desc =? where name=? ";
        Object[] params = new Object[]{
                entity.getName(),
                entity.getDesc(),
                entity.getName()
        };
        if(dao.update(sql, params)){
            return new ServiceMessage(true, "更新成功!");
        }
        return new ServiceMessage(false, "更新失败!");
    }

    @Override
    public ServiceMessage deleteById(Serializable id) {
        return null;
    }


    @Override
    public ServiceMessage deleteByName(Object name) {
        String sql = "delete from t_grade where name = ?";
        if(dao.update(sql, name)){
            return new ServiceMessage(true, "操作成功!");
        }
        return new ServiceMessage(false, "操作失败!");
    }

    @Override
    public Page<Grade> queryByPage(int currentPageNo, int pageSize, Map<String, Object> condition) {

        StringBuilder sql = new StringBuilder("select * from t_grade e where 1=1 ");
        StringBuilder count = new StringBuilder("select count(*) from t_grade e where 1=1 ");
        List<Grade> list ;

        List<Object> params = new ArrayList<Object>();
        if(condition != null){
            String ename = (String) condition.get("name");
            if(ename != null){
                sql.append(" and e.name like ?");params.add('%'+ename+'%');
                count.append(" and e.name like ?");
            }
        }

        int c = dao.queryForInt(count.toString(), params.toArray());

        Page<Grade> page = new Page<>();
        page.setCurrentPageNo(currentPageNo);
        page.setTotalRows(c);
        page.setPageSize(pageSize);

        sql.append(" limit ?,? ");
        params.add((currentPageNo-1)*pageSize);
        params.add(pageSize);
        list = dao.queryForList(sql.toString(), new ObjectMapper.MapperGrade() , params.toArray());

        page.setData(list);
        return page;
    }

    @Override
    public List<Grade> getAll() {
        StringBuilder sql = new StringBuilder("select * from t_grade e where 1=1 order by name desc ");
        return dao.queryForList(sql.toString(), new ObjectMapper.MapperGrade() );
    }

    @Override
    public Grade findById(int selectId) {
        return null;
    }

    @Override
    public Grade findByName(Object name) {
        StringBuilder sql = new StringBuilder("select * from t_grade where name = ? ");
        return dao.queryForObject(sql.toString(), new ObjectMapper.MapperGrade(), name );
    }
}
