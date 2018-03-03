package org.marker.certificate.service.impl;/**
 * Created by marker on 2018/2/24.
 */

import org.marker.certificate.bean.Class;
import org.marker.certificate.bean.Page;
import org.marker.certificate.bean.ServiceMessage;
import org.marker.certificate.bean.Student;
import org.marker.certificate.dao.DaoEngine;
import org.marker.certificate.dao.mapper.ObjectMapper;
import org.marker.certificate.service.ClassService;
import org.marker.certificate.service.StudentService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author marker
 * @create 2018-02-24 上午11:27
 **/
public class StudentServiceImpl implements StudentService {



    // dao
    private DaoEngine dao = new DaoEngine();


    @Override
    public ServiceMessage save(Student entity) {

        String sql = "INSERT INTO `t_student`(studentNo,name,gradeName, className) VALUES(?,?,?,?)";
        Object[] params = new Object[]{
                entity.getStudentNo(),
                entity.getName(),
                entity.getGradeName(),
                entity.getClassName(), };
        if(dao.update(sql, params)){
            return new ServiceMessage(true, "添加成功!");
        }
        return new ServiceMessage(false, "添加失败!");
    }

    @Override
    public ServiceMessage update(Student enterprise) {
        return null;
    }

    @Override
    public ServiceMessage deleteById(Serializable id) {
        return null;
    }

    @Override
    public Page<Student> queryByPage(int currentPageNo, int pageSize, Map<String, Object> condition) {

        StringBuilder sql = new StringBuilder("select * from t_student e where 1=1 ");
        StringBuilder count = new StringBuilder("select count(*) from t_student e where 1=1 ");
        List<Student> list ;

        List<Object> params = new ArrayList<Object>();
        if(condition != null){
            String ename = (String) condition.get("name");
            if(ename != null){
                sql.append(" and e.name like ?");params.add('%'+ename+'%');
                count.append(" and e.name like ?");
            }
            String studentNo = (String) condition.get("studentNo");
            if(studentNo != null){
                sql.append(" and e.studentNo like ?");params.add('%'+studentNo+'%');
                count.append(" and e.studentNo like ?");
            }
        }

        int c = dao.queryForInt(count.toString(), params.toArray());

        Page<Student> page = new Page<>();
        page.setCurrentPageNo(currentPageNo);
        page.setTotalRows(c);
        page.setPageSize(pageSize);

        sql.append(" limit ?,? ");
        params.add((currentPageNo-1)*pageSize);
        params.add(pageSize);
        list = dao.queryForList(sql.toString(), new ObjectMapper.MapperStudent() , params.toArray());

        page.setData(list);
        return page;
    }

    @Override
    public List<Student> getAll() {
        StringBuilder sql = new StringBuilder("select * from t_student e where 1=1 ");
        return dao.queryForList(sql.toString(), new ObjectMapper.MapperStudent() );
    }

    @Override
    public Student get(String studentNo) {
        StringBuilder sql = new StringBuilder("select * from t_student where studentNo=? ");
        return dao.queryForObject(sql.toString(), new ObjectMapper.MapperStudent(), studentNo );
    }

    @Override
    public void updateClassName(String studentNo, String className) {
        StringBuilder sql = new StringBuilder("update t_student set oldClassName=className, className=? where studentNo=? ");
        dao.update(sql.toString(), className, studentNo );
    }

    @Override
    public ServiceMessage deleteByStudentNo(Object studentNo) {
        String sql = "delete from t_student where studentNo = ?";
        if(dao.update(sql, studentNo)){
            return new ServiceMessage(true, "操作成功!");
        }
        return new ServiceMessage(false, "操作失败!");


    }
}
