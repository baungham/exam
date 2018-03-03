package org.marker.certificate.util;

/**
 * Created by marker on 2018/2/26.
 */

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.marker.certificate.bean.ExamSience;
import org.marker.certificate.bean.PrinterQueue;
import org.marker.certificate.config.impl.SystemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Excel
 *
 * @author marker
 * @create 2018-02-26 下午8:02
 **/
public class ExcelTools {

    private static Logger logger = LoggerFactory.getLogger(ExcelTools.class);

    /**
     * 生成Excel
     * @param one
     * @return
     */
    public static String generateExcel(PrinterQueue one) throws IOException {

        String excelTpl = SystemConfig.getInstance().getExcelNoticePath();
        String excelOut = SystemConfig.getInstance().getExcelOutPath();


        Workbook wb = ExcelUtils.createWorkBook(excelTpl);

        Sheet sheet = wb.getSheetAt(0);

        // 修改通知标题时间
        Row row2 = sheet.getRow(0);
        String title = ExcelUtils.getValue(row2.getCell(0));
        title = title.replaceAll("\\{time\\}", one.getTime());
        ExcelUtils.sheetAddCell(0, title ,row2, false);

        Row row3 = sheet.getRow(3);
        String title2 = ExcelUtils.getValue(row3.getCell(0));
        title2 = title2.replaceAll("\\{time\\}", one.getTime());
        ExcelUtils.sheetAddCell(0, title2 ,row3, false);



        // 处理姓名
        Row row1 = sheet.getRow(2);
        String studentInfo = "高"  + one.getGradeName() + "级"
                + one.getClassName() + "班" + one.getStudentName() + "同学的家长：";
        ExcelUtils.sheetAddCell(0, studentInfo ,row1, false);





        // 处理成绩
        List<ExamSience> list = one.getExamSiences();
        int rowIndex = 5 ;
        int len = list.size();


        if(list != null){
            for(int i=0; i < len; i++){

                ExamSience examSience = list.get(i);
                String name = examSience.getName();// 考试名称

                if(!"非高考学科".equals(name)){
                    // 成绩写入
                    int index = rowIndex + i * 2;
                    Row row = ExcelUtils.getRow(sheet, index);
                    // 合并单元格
                    sheet.addMergedRegion(new CellRangeAddress(index,index,0,1));

                    ExcelUtils.sheetAddCell(0,  examSience.getName() ,row);
                    ExcelUtils.sheetAddCell(2, "成绩",row);
                    ExcelUtils.sheetAddCell(3,  examSience.getLanguage() ,row);// 语文
                    ExcelUtils.sheetAddCell(4,  examSience.getMathematics() ,row);// 数学
                    ExcelUtils.sheetAddCell(5,  examSience.getEnglish() ,row);// 英语
                    ExcelUtils.sheetAddCell(6,  examSience.getPhysics() ,row);// 物理
                    ExcelUtils.sheetAddCell(7,  examSience.getChemistry() ,row);// 化学
                    ExcelUtils.sheetAddCell(8,  examSience.getBiology() ,row);// 生物
                    ExcelUtils.sheetAddCell(9,  examSience.getPolitics() ,row);// 政治
                    ExcelUtils.sheetAddCell(10,  examSience.getHistory() ,row);// 历史
                    ExcelUtils.sheetAddCell(11, examSience.getGeography() ,row);// 地理
                    ExcelUtils.sheetAddCell(12, examSience.getScienceTotalAchievement() ,row); // 理6科
                    ExcelUtils.sheetAddCell(13, examSience.getScienceClassOrder() ,row); //理6班次
                    ExcelUtils.sheetAddCell(14, examSience.getLiteralTotalAchievement() ,row); //文6科
                    ExcelUtils.sheetAddCell(15, examSience.getLiteralClassOrder() ,row); //文6班次
                    ExcelUtils.sheetAddCell(16, examSience.getNineTotal() ,row);
                    ExcelUtils.sheetAddCell(17, "" ,row);

                    // 名次写入
                    Row row4 = ExcelUtils.getRow(sheet, rowIndex + i*2 + 1);
                    ExcelUtils.sheetAddCell(0, "人数",row4);
                    ExcelUtils.sheetAddCell(1,  examSience.getCount() ,row4);// 人数
                    ExcelUtils.sheetAddCell(2, "名次",row4);
                    ExcelUtils.sheetAddCell(3,  examSience.getLanguageOrder() ,row4);// 语文
                    ExcelUtils.sheetAddCell(4,  examSience.getMathematicsOrder() ,row4);// 数学
                    ExcelUtils.sheetAddCell(5,  examSience.getEnglishOrder() ,row4);// 英语
                    ExcelUtils.sheetAddCell(6,  examSience.getPhysicsOrder() ,row4);// 物理
                    ExcelUtils.sheetAddCell(7,  examSience.getChemistryOrder() ,row4);// 化学
                    ExcelUtils.sheetAddCell(8,  examSience.getBiologyOrder() ,row4);// 生物
                    ExcelUtils.sheetAddCell(9,  examSience.getPoliticsOrder() ,row4);// 政治
                    ExcelUtils.sheetAddCell(10,  examSience.getHistoryOrder() ,row4);// 历史
                    ExcelUtils.sheetAddCell(11, examSience.getGeographyOrder() ,row4);// 地理
                    ExcelUtils.sheetAddCell(12, examSience.getScienceTotalAchievementOrder() ,row4); // 理6科
                    ExcelUtils.sheetAddCell(13, examSience.getScienceClassOrder() ,row4); //理6班次
                    ExcelUtils.sheetAddCell(14, examSience.getLiteralTotalAchievementOrder() ,row4); //文6科
                    ExcelUtils.sheetAddCell(15, examSience.getLiteralClassOrder() ,row4); //文6班次
                    ExcelUtils.sheetAddCell(16, "" ,row4);
                    ExcelUtils.sheetAddCell(17, examSience.getNineTotalOrder() ,row4);// 9可名次
                } else { // 非高考学科
                    int index = rowIndex + i*2;
                    // 成绩写入
                    Row row = ExcelUtils.getRow(sheet, index);


                    ExcelUtils.sheetAddCell(0,  "非高考学科" ,row);
                    sheet.addMergedRegion(new CellRangeAddress(index, index,0,1));

                    ExcelUtils.sheetAddCell(1, "",row);
                    ExcelUtils.sheetAddCell(2,  "体育" ,row);
                    ExcelUtils.sheetAddCell(3,  examSience.getSports() ,row);
                    ExcelUtils.sheetAddCell(4,  "美术",row);
                    ExcelUtils.sheetAddCell(5,  examSience.getArt(),row);
                    ExcelUtils.sheetAddCell(6, "音乐",row);
                    ExcelUtils.sheetAddCell(7,  examSience.getMusic(),row);
                    ExcelUtils.sheetAddCell(8,  "信息技术" ,row);
                    sheet.addMergedRegion(new CellRangeAddress(index, index,8,9));
                    ExcelUtils.sheetAddCell(9, "",row);
                    ExcelUtils.sheetAddCell(10,  examSience.getInfomation(),row);
                    ExcelUtils.sheetAddCell(11, "心理健康" ,row);
                    sheet.addMergedRegion(new CellRangeAddress(index, index,11,12));
                    ExcelUtils.sheetAddCell(12, "",row);
                    ExcelUtils.sheetAddCell(13, examSience.getPsychology(),row);
                    ExcelUtils.sheetAddCell(14, "通用技术" ,row);
                    sheet.addMergedRegion(new CellRangeAddress(index, index,14,15));
                    ExcelUtils.sheetAddCell(15, "",row);
                    ExcelUtils.sheetAddCell(16, examSience.getGeneral(),row);
                    ExcelUtils.sheetAddCell(17, "",row);


                }
            }
        }

        // 修正
        if(len < 4 ){
            len = 4;
        }
        int index = rowIndex + (len ) * 2;


        Row row6 = ExcelUtils.getRow(sheet, index);
        sheet.addMergedRegion(new CellRangeAddress(index, index,13,17));
        Cell cell = ExcelUtils.sheetAddCell(13, "成都市树德中学教务处", row6, false);
        ExcelUtils.clearBorder(wb,cell);

        index = index + 1;
        Row row7 = ExcelUtils.getRow(sheet, index);
        sheet.addMergedRegion(new CellRangeAddress(index, index,13,17));
        cell = ExcelUtils.sheetAddCell(13, new SimpleDateFormat("yyyy年MM月dd日").format(new Date()), row7, false);
        ExcelUtils.clearBorder(wb, cell);








        String outPath = excelOut +"/通知单/"  + new SimpleDateFormat("yyyyMMddd").format(new Date()) + "/";

        new File(outPath).mkdirs();

        String outfilePath = outPath + UUID.randomUUID() + ".xlsx";


        OutputStream outputStream = new FileOutputStream(outfilePath);
        wb.write(outputStream);
        outputStream.flush();
        outputStream.close();


        return outfilePath;
    }


    /**
     * 打印
     * @param filepath 文件路径
     */
    public  static void print(String filepath){

        //初始化COM线程
        ComThread.InitSTA();
        //新建Excel对象
        ActiveXComponent xl=new ActiveXComponent("Excel.Application");
        try {
            System.out.println("Version=" + xl.getProperty("Version"));
            //设置是否显示打开Excel
            Dispatch.put(xl, "Visible", new Variant(false));
            //打开具体的工作簿
            Dispatch workbooks = xl.getProperty("Workbooks").toDispatch();
            Dispatch excel=Dispatch.call(workbooks,"Open", filepath).toDispatch();

            //设置打印属性并打印
            Dispatch.callN(excel,"PrintOut",new Object[]{Variant.VT_MISSING, Variant.VT_MISSING, new Integer(1),//new Integer(1)，设置打印的份数
                    new Boolean(false),/*PRINT_NAME*/Variant.VT_MISSING, new Boolean(true),Variant.VT_MISSING, ""});

            //关闭文档
            Dispatch.call(excel, "Close", new Variant(false));
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            xl.invoke("Quit",new Variant[0]);//关闭进程
            //始终释放资源
            ComThread.Release();
        }
    }


    /**
     * 汇总单输出
     * @param one
     * @return
     */
    public static String generateCollectExcel(PrinterQueue one) throws IOException {
        String excelTpl = SystemConfig.getInstance().getExcelCollectPath();
        String excelOut = SystemConfig.getInstance().getExcelOutPath();

        String outPath = excelOut +"/汇总/" + one.getGradeName() + "/" +
                one.getClassName() + "/";

        new File(outPath).mkdirs();

        String outfilePath = outPath + one.getStudentNo() + ".xlsx";
        logger.debug("创建文件：{}", outfilePath);
        Path path = Paths.get(outfilePath);
        if (Files.exists(path)) {
            logger.debug(outfilePath + " -- 已存在");
            return path.toAbsolutePath().toString();
        }

        Workbook wb = ExcelUtils.createWorkBook(excelTpl);

        Sheet sheet = wb.getSheetAt(0);



        Font font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 22);// 设置字体大小
        font.setBold(true);

        Font font18 = wb.createFont();
        font18.setFontName("宋体");
        font18.setFontHeightInPoints((short) 18);// 设置字体大小
        font18.setBold(true);


        // 修改年级
        Row row1 = sheet.getRow(0);
        String title = ExcelUtils.getValue(row1.getCell(0));
        title = title.replaceAll("\\{time\\}", one.getGradeName());
        Cell cell = ExcelUtils.sheetAddCell(0, title ,row1, false);
//        ExcelUtils.clearBorder(wb, cell);
        cell.getCellStyle().setFont(font);

        // 姓名
        row1 = sheet.getRow(1);
        cell = ExcelUtils.sheetAddCell(2, one.getStudentName() , row1, false);
//        ExcelUtils.clearBorder(wb, cell);
        cell.getCellStyle().setFont(font18);

        // 班级
        cell =  ExcelUtils.sheetAddCell(9, one.getClassName() + " 班",row1, false);
//        ExcelUtils.clearBorder(wb, cell);
        cell.getCellStyle().setFont(font18);

        // 学号
        cell =  ExcelUtils.sheetAddCell(20, one.getStudentNo() + " ",row1, false);
//        ExcelUtils.clearBorder(wb, cell);
        cell.getCellStyle().setFont(font18);


        // 处理成绩
        List<ExamSience> list = one.getExamSiences();
        int rowIndex = 3 ;
        int len = list.size();


        Font font1 = wb.createFont();
        font1.setFontName("宋体");
        font1.setFontHeightInPoints((short) 9);// 设置字体大小



        if(list != null){
            for(int i=0; i < len; i++){
                ExamSience examSience = list.get(i);
                String name = examSience.getName();// 考试名称

                if(!"非高考学科".equals(name)){
                    // 成绩写入
                    Row row = ExcelUtils.getRow(sheet, rowIndex + i);

                    Cell cell1 = ExcelUtils.sheetAddCell(0,  examSience.getName() ,row);
                    cell1.getCellStyle().setFont(font1);
                    ExcelUtils.sheetAddCell(1,  examSience.getCount() == 0 ? "" : examSience.getCount(),row).getCellStyle().setFont(font1);// 人数
                    ExcelUtils.sheetAddCell(2,  examSience.getLanguage() == 0 ? "" : examSience.getLanguage(),row).getCellStyle().setFont(font1);// 语文
                    ExcelUtils.sheetAddCell(3,  examSience.getLanguageOrder() ,row).getCellStyle().setFont(font1);
                    ExcelUtils.sheetAddCell(4,  examSience.getMathematics() == 0 ? "" : examSience.getMathematics(),row).getCellStyle().setFont(font1);// 数学
                    ExcelUtils.sheetAddCell(5,  examSience.getMathematicsOrder() ,row).getCellStyle().setFont(font1);
                    ExcelUtils.sheetAddCell(6,  examSience.getEnglish() == 0 ? "" : examSience.getEnglish() ,row).getCellStyle().setFont(font1);// 英语
                    ExcelUtils.sheetAddCell(7,  examSience.getEnglishOrder() ,row).getCellStyle().setFont(font1);// 英语
                    ExcelUtils.sheetAddCell(8,  examSience.getPhysics() == 0 ? "" : examSience.getPhysics(),row).getCellStyle().setFont(font1);// 物理
                    ExcelUtils.sheetAddCell(9,  examSience.getPhysicsOrder() ,row).getCellStyle().setFont(font1);// 物理
                    ExcelUtils.sheetAddCell(10,  examSience.getChemistry() == 0 ? "" : examSience.getChemistry() ,row).getCellStyle().setFont(font1);// 化学
                    ExcelUtils.sheetAddCell(11,  examSience.getChemistryOrder() ,row).getCellStyle().setFont(font1);// 化学
                    ExcelUtils.sheetAddCell(12,  examSience.getBiology() == 0 ? "" : examSience.getBiology() ,row).getCellStyle().setFont(font1);// 生物
                    ExcelUtils.sheetAddCell(13,  examSience.getBiologyOrder() ,row).getCellStyle().setFont(font1);// 生物

                    ExcelUtils.sheetAddCell(14, examSience.getScienceTotalAchievement() == 0 ? "" : examSience.getScienceTotalAchievement(),row).getCellStyle().setFont(font1); // 理6科
                    ExcelUtils.sheetAddCell(15, examSience.getScienceTotalAchievementOrder() ,row).getCellStyle().setFont(font1);
                    ExcelUtils.sheetAddCell(16, examSience.getScienceClassOrder() ,row).getCellStyle().setFont(font1); //理6班次

                    ExcelUtils.sheetAddCell(17,  examSience.getPolitics() == 0 ? "" : examSience.getPolitics() ,row).getCellStyle().setFont(font1);// 政治
                    ExcelUtils.sheetAddCell(18,  examSience.getPoliticsOrder() ,row).getCellStyle().setFont(font1);// 政治
                    ExcelUtils.sheetAddCell(19,  examSience.getHistory() == 0 ? "" : examSience.getHistory() ,row).getCellStyle().setFont(font1);// 历史
                    ExcelUtils.sheetAddCell(20,  examSience.getHistoryOrder() ,row).getCellStyle().setFont(font1);// 历史
                    ExcelUtils.sheetAddCell(21, examSience.getGeography() == 0 ? "" : examSience.getGeography() ,row).getCellStyle().setFont(font1);// 地理
                    ExcelUtils.sheetAddCell(22, examSience.getGeographyOrder() ,row).getCellStyle().setFont(font1);
                    ExcelUtils.sheetAddCell(23, examSience.getLiteralTotalAchievement() == 0 ? "" : examSience.getLiteralTotalAchievement() ,row).getCellStyle().setFont(font1); //文6科
                    ExcelUtils.sheetAddCell(24, examSience.getLiteralTotalAchievementOrder() ,row).getCellStyle().setFont(font1);
                    ExcelUtils.sheetAddCell(25, examSience.getLiteralClassOrder() ,row).getCellStyle().setFont(font1); //文6班次
                    ExcelUtils.sheetAddCell(26, examSience.getNineTotal() == 0 ? "" : examSience.getNineTotal(),row).getCellStyle().setFont(font1); //文6班次
                    ExcelUtils.sheetAddCell(27, examSience.getNineTotalOrder()  ,row).getCellStyle().setFont(font1); //文6班次
                    ExcelUtils.sheetAddCell(28, "" ,row).getCellStyle().setFont(font1); //班次
                    ExcelUtils.sheetAddCell(29, examSience.getInfomation() == 0 ? "" : examSience.getInfomation() ,row).getCellStyle().setFont(font1); //文6班次
                    ExcelUtils.sheetAddCell(30, examSience.getInfomationOrder() ,row).getCellStyle().setFont(font1); //文6班次
                    ExcelUtils.sheetAddCell(31, examSience.getGeneral() == 0 ? "" : examSience.getGeneral(),row).getCellStyle().setFont(font1); //文6班次
                    ExcelUtils.sheetAddCell(32, examSience.getGeneralOrder() ,row).getCellStyle().setFont(font1); //文6班次
                    ExcelUtils.sheetAddCell(33, examSience.getSports() == 0 ? "" : examSience.getSports(),row).getCellStyle().setFont(font1); //文6班次
                    ExcelUtils.sheetAddCell(34, examSience.getArt() == 0 ? "" : examSience.getArt() ,row).getCellStyle().setFont(font1); //文6班次
                    ExcelUtils.sheetAddCell(35, examSience.getMusic() == 0 ? "" : examSience.getMusic(),row).getCellStyle().setFont(font1); //文6班次
                }
            }
        }
        int index = rowIndex + len -1;
        Row row = ExcelUtils.getRow(sheet, index);
        CellStyle cellStyle = ExcelUtils.clearBorder(wb, row);
        row.setRowStyle(cellStyle);



        // TODO 时间写入
        index++;
        Row row6 = ExcelUtils.getRow(sheet, index);
        sheet.addMergedRegion(new CellRangeAddress(index, index,0,22));
        cell = ExcelUtils.sheetAddCell(0, "备注：学考成绩的说明：学考成绩分为分数和等级，等级填写在名次一栏。", row6, false);
        ExcelUtils.clearBorder(wb,cell);

        row6 = ExcelUtils.getRow(sheet, index);
        sheet.addMergedRegion(new CellRangeAddress(index, index,23,30));
        cell = ExcelUtils.sheetAddCell(23, "树德中学教务处", row6, false);
        ExcelUtils.clearBorder(wb,cell);

        index = index + 1;

        row6 = ExcelUtils.getRow(sheet, index);
        sheet.addMergedRegion(new CellRangeAddress(index, index,23,30));
        cell = ExcelUtils.sheetAddCell(23, new SimpleDateFormat("yyyy年MM月dd日").format(new Date()), row6, false);
        ExcelUtils.clearBorder(wb,cell);


        index = index + 1;
        row6 = ExcelUtils.getRow(sheet, index);
        sheet.addMergedRegion(new CellRangeAddress(index, index,0,30));
        cell = ExcelUtils.sheetAddCell(0, "请注意保存好这张成绩单，以后需要教务处在学生自己的模版盖章时请带上这张成绩表，谢谢。", row6, false);
        ExcelUtils.clearBorder(wb,cell);
        font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 14);// 设置字体大小
        font.setBold(true);
        cell.getCellStyle().setFont(font);

        OutputStream outputStream = new FileOutputStream(outfilePath);
        wb.write(outputStream);
        outputStream.flush();
        outputStream.close();


        return path.toAbsolutePath().toString();
    }
}
