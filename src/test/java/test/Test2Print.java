package test;/**
 * Created by marker on 2018/2/25.
 */

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author marker
 * @create 2018-02-25 下午10:14
 **/
public class Test2Print {

    public static void main(String[] args) throws PrinterException {
        String filepath = "C:\\Users\\marker\\Documents\\2018年4月1日婚礼\\婚礼请帖名单.xlsx";

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


}
