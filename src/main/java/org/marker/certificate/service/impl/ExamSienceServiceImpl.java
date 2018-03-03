package org.marker.certificate.service.impl;/**
 * Created by marker on 2018/2/24.
 */

import org.marker.certificate.bean.Exam;
import org.marker.certificate.bean.ExamSience;
import org.marker.certificate.bean.Page;
import org.marker.certificate.bean.ServiceMessage;
import org.marker.certificate.dao.DaoEngine;
import org.marker.certificate.dao.mapper.ObjectMapper;
import org.marker.certificate.service.ExamService;
import org.marker.certificate.service.ExamSienceService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author marker
 * @create 2018-02-24 上午11:27
 **/
public class ExamSienceServiceImpl implements ExamSienceService {



    // dao
    private DaoEngine dao = new DaoEngine();


    @Override
    public ServiceMessage save(ExamSience entity) {

        String fields = "examId, gradeName, className, semesterId, studentNo, studentName, language, languageOrder, mathematics, mathematicsOrder, english, englishOrder, physics, physicsOrder, chemistry, chemistryOrder, biology, biologyOrder, scienceTotalAchievement, scienceTotalAchievementOrder, scienceClassOrder, politics, politicsOrder, history, historyOrder, geography, geographyOrder, literalTotalAchievement, literalTotalAchievementOrder, literalClassOrder, infomation, infomationOrder, general, generalOrder, sports, sportsOrder, music, musicOrder, art, artOrder, psychology, psychologyOrder, count, nineTotal, nineTotalOrder";
        String values = "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
        String sql = "INSERT INTO `t_exam_sience`(" + fields + ") VALUES(" + values + ")";
        Object[] params = new Object[]{
                entity.getExamId(),
                entity.getGradeName(),
                entity.getClassName(),
                entity.getSemesterId(),
                entity.getStudentNo(),
                entity.getStudentName(),
                entity.getLanguage(),
                entity.getLanguageOrder(),
                entity.getMathematics(),
                entity.getMathematicsOrder(),
                entity.getEnglish(),
                entity.getEnglishOrder(),
                entity.getPhysics(),
                entity.getPhysicsOrder(),
                entity.getChemistry(),
                entity.getChemistryOrder(),
                entity.getBiology(),
                entity.getBiologyOrder(),
                entity.getScienceTotalAchievement(),
                entity.getScienceTotalAchievementOrder(),
                entity.getScienceClassOrder(),
                entity.getPolitics(),
                entity.getPoliticsOrder(),
                entity.getHistory(),
                entity.getHistoryOrder(),
                entity.getGeography(),
                entity.getGeographyOrder(),
                entity.getLiteralTotalAchievement(),
                entity.getLiteralTotalAchievementOrder(),
                entity.getLiteralClassOrder(),
                entity.getInfomation(),
                entity.getInfomationOrder(),
                entity.getGeneral(),
                entity.getGeneralOrder(),
                entity.getSports(),
                entity.getSportsOrder(),
                entity.getMusic(),
                entity.getMusicOrder(),
                entity.getArt(),
                entity.getArtOrder(),
                entity.getPsychology(),
                entity.getPsychologyOrder(),
                entity.getCount(), // 参与人数
                entity.getNineTotal(),
                entity.getNineTotalOrder()

        };
        if(dao.update(sql, params)){
            return new ServiceMessage(true, "添加成功!");
        }
        return new ServiceMessage(false, "添加失败!");
    }

    @Override
    public ServiceMessage update(ExamSience enterprise) {
        return null;
    }

    @Override
    public ServiceMessage deleteById(Serializable id) {
        return null;
    }

    @Override
    public Page<ExamSience> queryByPage(int currentPageNo, int pageSize, Map<String, Object> condition) {

        StringBuilder sql = new StringBuilder("select * from t_exam_sience e where 1=1 ");
        StringBuilder count = new StringBuilder("select count(*) from t_exam_sience e where 1=1 ");
        List<ExamSience> list ;

        List<Object> params = new ArrayList<Object>();
        if(condition != null){
            String ename = (String) condition.get("name");
            if(ename != null){
                sql.append(" and e.studentName like ?");params.add('%'+ename+'%');
                count.append(" and e.studentName like ?");
            }
        }

        int c = dao.queryForInt(count.toString(), params.toArray());

        Page<ExamSience> page = new Page<>();
        page.setCurrentPageNo(currentPageNo);
        page.setTotalRows(c);
        page.setPageSize(pageSize);

        sql.append(" limit ?,? ");
        params.add((currentPageNo-1)*pageSize);
        params.add(pageSize);
        list = dao.queryForList(sql.toString(), new ObjectMapper.MapperExamSience() , params.toArray());

        page.setData(list);
        return page;
    }

    @Override
    public List<ExamSience> getAll() {
        StringBuilder sql = new StringBuilder("select * from t_exam e where 1=1 ");
        return dao.queryForList(sql.toString(), new ObjectMapper.MapperExamSience() );
    }

    @Override
    public ExamSience get(String className) {
        StringBuilder sql = new StringBuilder("select * from t_exam e where name=? ");
        return dao.queryForObject(sql.toString(), new ObjectMapper.MapperExamSience(), className);
    }
}
