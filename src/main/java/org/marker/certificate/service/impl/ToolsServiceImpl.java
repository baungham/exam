package org.marker.certificate.service.impl;
/**
 * Created by marker on 2018/2/26.
 */

import org.apache.commons.lang3.StringUtils;
import org.marker.certificate.bean.ExamSience;
import org.marker.certificate.bean.PrinterQueue;
import org.marker.certificate.dao.DaoEngine;
import org.marker.certificate.dao.mapper.ObjectMapper;
import org.marker.certificate.service.ToolsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author marker
 * @create 2018-02-26 下午3:26
 **/
public class ToolsServiceImpl implements ToolsService {

    Logger logger = LoggerFactory.getLogger(ToolsServiceImpl.class);

    // dao
    private DaoEngine dao = new DaoEngine();


    @Override
    public void importExcel() {

    }

    @Override
    public void exportExcel() {

    }

    @Override
    public PrinterQueue fetch() {
        StringBuilder sql = new StringBuilder("select * from t_printer_queue where status=0 limit 1 ");
        PrinterQueue queue = dao.queryForObject(sql.toString(), new ObjectMapper.MapperPrinterQueue() );


        if(queue== null)
            return null;

        StringBuilder sqlExt = new StringBuilder();

        List<Object> params = new ArrayList<>();
        if(!StringUtils.isBlank(queue.getGradeName())){
            sqlExt.append(" and e.gradeName=? ");
            params.add(queue.getGradeName());
        }

        if(!StringUtils.isBlank(queue.getStudentNo())){
            sqlExt.append(" and e.studentNo = ?  ");
            params.add(queue.getStudentNo());
        }
        if(!StringUtils.isBlank(queue.getClassName())){
            sqlExt.append(" and e.className=?  ");
            params.add(queue.getClassName());
        }
        if(queue.getSemesterId() > 0){
            sqlExt.append(" and e.semesterId=?  ");
            params.add(queue.getSemesterId());
        }

        List<ExamSience> list;

        // 查询考试
        if(1 == queue.getType()){// 通知单
            StringBuilder sql2 = new StringBuilder("select b.name, e.* from t_exam_sience e left join t_exam b on b.id = e.examId");
            sql2.append(" where 1=1 ").append(sqlExt);
            list = dao.queryForList(sql2.toString(), new ObjectMapper.MapperExamSience(),  params.toArray() );
        } else {// 汇总单
            StringBuilder sql2 = new StringBuilder("select b.name, e.* from t_exam_sience e left join t_exam b on b.id = e.examId");
            sql2.append(" where 1=1 ").append(sqlExt);
            list = dao.queryForList(sql2.toString(), new ObjectMapper.MapperExamSience(),params.toArray() );
        }
         queue.setExamSiences(list);
        return queue;
    }

    @Override
    public void submitPrintToQueue(String time, String studentNo, String gradeName, String className, int semesterId) {

        // TODO 查询满足条件的学生。

        StringBuilder sql = new StringBuilder("insert into t_printer_queue(studentNo,gradeName,className,semesterId,type,status, studentName, time) select a.studentNo, a.gradeName, a.className,");
        sql.append(semesterId).append(",1,0, a.name,'")
                .append(time).append("' from t_student a where 1=1 and (select count(1) from t_exam where semesterId =?) ");

        List<Object> params = new ArrayList<>(5);
        params.add(semesterId);

        if(!StringUtils.isBlank(studentNo)){
            sql.append(" and a.studentNo=?");
            params.add(studentNo);
        }
        if(!StringUtils.isBlank(gradeName)){
            sql.append(" and a.gradeName=?");
            params.add(gradeName);
        }
        if(!StringUtils.isBlank(className)){
            sql.append(" and a.className=?");
            params.add(className);
        }
        if(!StringUtils.isBlank(className)){
            sql.append(" and a.className=?");
            params.add(className);
        }



        //  写入到未打印记录
        boolean status = dao.update(sql.toString(),params.toArray());// 批量提交打印到队列
        logger.info("status = {}", status);
    }

    @Override
    public void updateComplete(int id) {
        StringBuilder sql = new StringBuilder("update t_printer_queue set status = 1 where id=? ");
        boolean status = dao.update(sql.toString(), id);// 批量提交打印到队列
        logger.info("status = {}", status);
    }

    @Override
    public List<PrinterQueue> queryCurrentQueue() {
        StringBuilder sql = new StringBuilder("select * from t_printer_queue where status=0 order by id desc");
        return dao.queryForList(sql.toString(), new ObjectMapper.MapperPrinterQueue() );

    }

    @Override
    public void submitPrintCollectToQueue(String studentNo, String gradeName, String className) {

        // TODO 查询满足条件的学生。

        StringBuilder sql = new StringBuilder("insert into t_printer_queue(studentNo,gradeName,className, type, status, studentName) select a.studentNo, a.gradeName, a.className,");
        sql.append("").append("2,0, a.name from t_student a where 1=1 and (select count(1) from t_exam where gradeName =?) ");

        List<Object> params = new ArrayList<>(5);
        params.add(gradeName);

        if(!StringUtils.isBlank(studentNo)){
            sql.append(" and a.studentNo=?");
            params.add(studentNo);
        }
        if(!StringUtils.isBlank(gradeName)){
            sql.append(" and a.gradeName=?");
            params.add(gradeName);
        }
        if(!StringUtils.isBlank(className)){
            sql.append(" and a.className=?");
            params.add(className);
        }


        //  写入到未打印记录
        boolean status = dao.update(sql.toString(),params.toArray());// 批量提交打印到队列
        logger.info("status = {}", status);
    }


}
