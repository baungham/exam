package org.marker.certificate.printer;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.Iterator;
import java.util.List;

/**
 * 单页打印
 * */
public class QSPrintable implements Printable{

	/** 存放当前证书要打印的文字相关信息 */
	private List<PrintTextObject> printData;
	
	@Override
	public int print(Graphics g, PageFormat pf, int pageIndex)
			throws PrinterException {
        if (pageIndex > 0) {
            return Printable.NO_SUCH_PAGE;
        }
        Graphics2D g2d = (Graphics2D) g;
        Font font = new Font("新宋体", Font.PLAIN, 11); 
        g2d.setFont(font); // 
        
        Iterator<PrintTextObject> it = printData.iterator();
        while(it.hasNext()){
        	PrintTextObject pto = it.next();
            g2d.drawString(pto.getText(), pto.getX(), pto.getY());
        }
        return Printable.PAGE_EXISTS;
	}

	
	
	
	
	public List<PrintTextObject> getPrintData() {
		return printData;
	}

	public void setPrintData(List<PrintTextObject> printData) {
		this.printData = printData;
	}
 
	
}
