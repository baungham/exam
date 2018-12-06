package org.marker.certificate.service.impl;
/**
 * Created by marker on 2018/2/24.
 */

import org.marker.certificate.bean.Exam;
import org.marker.certificate.bean.Page;
import org.marker.certificate.bean.ServiceMessage;
import org.marker.certificate.dao.DaoEngine;
import org.marker.certificate.dao.mapper.ObjectMapper;
import org.marker.certificate.service.ExamService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author marker
 * @create 2018-02-24 上午11:27
 **/
public class ExamServiceImpl implements ExamService {



    // dao
    private DaoEngine dao = new DaoEngine();


    @Override
    public ServiceMessage save(Exam entity) {
        entity.setCreateTime(new Date());

        String sql = "INSERT INTO `t_exam`(name,semesterId, gradeName, sortNum, createTime, count) VALUES(?,?,?,?,?,?)";
        Object[] params = new Object[]{
                entity.getName(),
                entity.getSemesterId(),
                entity.getGradeName(),
                entity.getSortNum(),
                entity.getCreateTime(),
                entity.getCount()
        };
        int id = dao.updateReturnId(sql, params);

        if(id != -1){
            entity.setId(id);
            return new ServiceMessage(true, "添加成功!");
        }
        return new ServiceMessage(false, "添加失败!");
    }

    @Override
    public ServiceMessage update(Exam entity) {
        String sql = "UPDATE `t_exam` set name = ?, semesterId = ?, gradeName = ?, sortNum = ?, count = ? WHERE id = ?";
        Object[] params = new Object[]{
                entity.getName(),
                entity.getSemesterId(),
                entity.getGradeName(),
                entity.getSortNum(),
                entity.getCount(),
                entity.getId()
        };
        boolean succeed = dao.update(sql, params);

        if (succeed) {
            return new ServiceMessage(true, "修改成功!");
        }
        return new ServiceMessage(false, "修改失败!");
    }

    @Override
    public ServiceMessage deleteById(Serializable id) {
        String sql = "delete from t_exam where id = ?";
        if(dao.update(sql, id)){
            // 删除成绩
            String sql2 = "delete from t_exam_sience where examId = ?";
            dao.update(sql2, id);
            return new ServiceMessage(true, "操作成功!");
        }
        return new ServiceMessage(false, "操作失败!");
    }

    @Override
    public Page<Exam> queryByPage(int currentPageNo, int pageSize, Map<String, Object> condition) {

        StringBuilder sql = new StringBuilder("select * from t_exam e where 1=1 ");
        StringBuilder count = new StringBuilder("select count(*) from t_exam e where 1=1 ");
        List<Exam> list ;

        List<Object> params = new ArrayList<Object>();
        if(condition != null){
            String ename = (String) condition.get("name");
            if(ename != null){
                sql.append(" and e.name like ?");params.add('%'+ename+'%');
                count.append(" and e.name like ?");
            }
        }

        int c = dao.queryForInt(count.toString(), params.toArray());

        Page<Exam> page = new Page<>();
        page.setCurrentPageNo(currentPageNo);
        page.setTotalRows(c);
        page.setPageSize(pageSize);

        sql.append(" limit ?,? ");
        params.add((currentPageNo-1)*pageSize);
        params.add(pageSize);
        list = dao.queryForList(sql.toString(), new ObjectMapper.MapperExam() , params.toArray());

        page.setData(list);
        return page;
    }

    @Override
    public List<Exam> getAll() {
        StringBuilder sql = new StringBuilder("select * from t_exam e where 1=1 ");
        return dao.queryForList(sql.toString(), new ObjectMapper.MapperExam() );
    }

    @Override
    public Exam get(String className) {
        StringBuilder sql = new StringBuilder("select * from t_exam e where name=? ");
        return dao.queryForObject(sql.toString(), new ObjectMapper.MapperExam(), className);
    }
}
