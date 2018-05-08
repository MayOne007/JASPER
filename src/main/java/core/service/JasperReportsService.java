package core.service;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Component
public class JasperReportsService {
	
	private static Logger  logger = Logger.getLogger(JasperReportsService.class);
	
	private Connection connection;
	
	public void setDataSource(DruidDataSource dataSource) throws SQLException {
		this.connection = dataSource.getConnection();
	}
	
	private void setDownloadName(String fileName, String fileType, HttpServletResponse response) {
		try {
			response.setHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes("utf-8"),"iso8859-1")+"."+fileType);
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	/**
	 * 
	 * @param jasperName jasper文件全名
	 * @param isPreview 是否预览
	 * @param response 
	 */
	public void exportPdf(String jasperName, boolean isPreview, HttpServletResponse response){
		jasperName = this.getClass().getClassLoader().getResource("").getPath()+File.separator+"jasperreports"+File.separator+jasperName;
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperName, null,connection);
			if(isPreview) {
				setDownloadName(jasperPrint.getName(),"pdf",response);
			}		
			JasperExportManager.exportReportToPdfStream(jasperPrint,  response.getOutputStream());			
		} catch (Exception e) {
			logger.error(e);
		}
	}
}
