package test;/**
 * Created by marker on 2018/2/25.
 */

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import javax.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.Sides;
import javax.print.event.PrintServiceAttributeListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author marker
 * @create 2018-02-25 下午10:14
 **/
public class TestPrint {

    public static void main(String[] args) {
        String path = "/Users/marker/Desktop/未命名文件夹/考试/111.docx";


        //1.得到一个文件的输入流
        FileInputStream fiStream = null;
        try {
            fiStream = new FileInputStream(path);
        } catch (FileNotFoundException ffne) {
        }
        if (fiStream == null) {
            return;
        }

//这是要打印文件的格式，如果是PDF文档要设为自动识别
        DocFlavor fileFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
//2.得到要打印的文档类DOC
        Doc myDoc = new SimpleDoc(fiStream, fileFormat, null);
//3.生成一个打印属性设置对象
        PrintRequestAttributeSet aset =
                new HashPrintRequestAttributeSet();
        aset.add(new Copies(1));//Copies-打印份数5份
//        aset.add(MediaSize.);//A4纸张
//        aset.add(Sides.DUPLEX);//双面打印
//4.关键一步，得到当前机器上所有已经安装的打印机
//传入的参数是文档格式跟打印属性，只有支持这个格式与属性的打印机才会被找到


//        PrinterJob pj = PrinterJob.getPrinterJob();//创建一个打印任务
//        PageFormat pf = pj.defaultPage();// PageFormat 类描述要打印的页面大小和方向。
//        Paper paper   = pf.getPaper();// Paper 类描述一张纸的物理特征。
//
//
//        pj.pageDialog(pf);


        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;


        PrintService defaultService = PrintServiceLookup
                .lookupDefaultPrintService(); // 默认的PrintService



        PrintService[] services = new PrintService[]{
                new PrintService() {
                    @Override
                    public String getName() {
                        return null;
                    }

                    @Override
                    public DocPrintJob createPrintJob() {
                        return null;
                    }

                    @Override
                    public void addPrintServiceAttributeListener(PrintServiceAttributeListener listener) {

                    }

                    @Override
                    public void removePrintServiceAttributeListener(PrintServiceAttributeListener listener) {

                    }

                    @Override
                    public PrintServiceAttributeSet getAttributes() {
                        return null;
                    }

                    @Override
                    public <T extends PrintServiceAttribute> T getAttribute(Class<T> category) {
                        return null;
                    }

                    @Override
                    public DocFlavor[] getSupportedDocFlavors() {
                        return new DocFlavor[0];
                    }

                    @Override
                    public boolean isDocFlavorSupported(DocFlavor flavor) {
                        return false;
                    }

                    @Override
                    public Class<?>[] getSupportedAttributeCategories() {
                        return new Class<?>[0];
                    }

                    @Override
                    public boolean isAttributeCategorySupported(Class<? extends Attribute> category) {
                        return false;
                    }

                    @Override
                    public Object getDefaultAttributeValue(Class<? extends Attribute> category) {
                        return null;
                    }

                    @Override
                    public Object getSupportedAttributeValues(Class<? extends Attribute> category, DocFlavor flavor, AttributeSet attributes) {
                        return null;
                    }

                    @Override
                    public boolean isAttributeValueSupported(Attribute attrval, DocFlavor flavor, AttributeSet attributes) {
                        return false;
                    }

                    @Override
                    public AttributeSet getUnsupportedAttributes(DocFlavor flavor, AttributeSet attributes) {
                        return null;
                    }

                    @Override
                    public ServiceUIFactory getServiceUIFactory() {
                        return null;
                    }

                    @Override
                    public boolean equals(Object obj) {
                        return false;
                    }

                    @Override
                    public int hashCode() {
                        return 0;
                    }
                }
        };
                PrintServiceLookup.lookupPrintServices(fileFormat, aset);


        PrintService service = ServiceUI.printDialog(null, 200, 200,
                services, defaultService, flavor, aset);




        if (services.length > 0) {
            //5.用打印服务生成一个文档打印任务，这里用的是第一个服务
            //也可以进行筛选，services[i].getName()可以得到打印机名称，可用名称进行比较得到自己想要的打印机
            DocPrintJob job = services[0].createPrintJob();
            try {

                //6.最后一步，执行打印文档任务，传入的参数是Doc文档类，与属性(5份，双面,A4)
                job.print(myDoc, aset);//成功后电脑会提示已有文档添加到打印队列
            } catch (PrintException pe) {
                pe.printStackTrace();
            }
        }
    }


}
