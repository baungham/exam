package org.marker.certificate.monitor;

import java.awt.Color;
import org.marker.certificate.bean.PrinterQueue;
import org.marker.certificate.service.IPrinterLogService;
import org.marker.certificate.service.ToolsService;
import org.marker.certificate.service.impl.PrinterLogServiceImpl;
import org.marker.certificate.service.impl.ToolsServiceImpl;
import org.marker.certificate.util.ExcelTools;
import org.marker.certificate.view.PrinterQueuePanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 打印监控线程
 * 主要任务是提取打印队列中的数据
 * 
 * 
 * @author marker
 * @version 1.0
 */
public class MonitorThread extends SuperThread {

	private Logger log = LoggerFactory.getLogger(MonitorThread.class);

	private ToolsService toolsService;
	
	// 打印日志
	private IPrinterLogService logService;
	
	public MonitorThread() {
        toolsService = new ToolsServiceImpl();
		logService = new PrinterLogServiceImpl(); 
	}
	
	
	public void goRun() {  
		try{ 
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				log.info("停止监控线程!");
				return;
			}

			/* 打印错误学生
			List<PrinterQueue> queues = toolsService.fetchTmpQueue();
			queues.forEach(e -> {
				try {
					String excelPath = ExcelTools.generateCollectExcel(e);
					log.debug(excelPath);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});*/

			PrinterQueue one = toolsService.fetch();// 抓去一条打印数据
			if(one != null){
//				int logId = logService.save(certificate);// 记录打印日志

				// TODO 生成EXCEL报告文件到data文件夹
                if(1 == one.getType()){
                    String excelPath = ExcelTools.generateExcel(one);
                    System.out.println(excelPath);
                }else{

                    String excelPath = ExcelTools.generateCollectExcel(one);
                    System.out.println(excelPath);
					// TODO 传送给打印机
					try {
//						ExcelTools.print(excelPath);

					} catch (Exception e1) {
						log.error("{}打印失败....", excelPath);
						log.error("打印调用失败....", e1);
//						return;
					} finally {
						// 修改打印状态
						toolsService.updateComplete(one.getId());
					}
                }

			}
		} catch (Exception e){
			// 打印线程停止
			PrinterQueuePanel.label_status.setText("停止"); 
			PrinterQueuePanel.label_status.setText("停止");
			PrinterQueuePanel.btnNewButton.setText("启动");
			PrinterQueuePanel.label_status.setForeground(Color.red);
			log.error("打印线程意外退出....", e);
		}
		
		
		
		
	}
 
}
