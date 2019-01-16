package com.empresa.app.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.empresa.app.service.ReporteService;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.HtmlExporterConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterConfiguration;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
@Service
public class ReporteServiceImpl implements ReporteService {

	@Autowired
    protected DataSource dataSource;
	
	@Override
	public byte[] ReportePlanilla(String tipo) throws Exception {
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		ByteArrayOutputStream reporte = new ByteArrayOutputStream();

		try {

			String titulo = "";
			String path = "";
			titulo = "PLANILLA PERSONA";
			if (tipo.equals("PDF")) {
				path = "/report/planillas.jasper";
			} else {
				path = "/report/planillas.jasper";
			}

			Map parameters = new HashMap();
			parameters.put("ptitulo", "LIQUIDACIONES : " + titulo);

			
			Resource resource = new ClassPathResource(path);
			File file = resource.getFile();
			
			//File file = new File(path);
			jasperReport = (JasperReport) JRLoader.loadObject(file);
			
			java.sql.Connection con = dataSource.getConnection();
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,con);

			switch (tipo) {
			case "PDF":
				JRPdfExporter jrPdfExporter = new JRPdfExporter();
				jrPdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
				jrPdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(reporte));
				jrPdfExporter.exportReport();
				break;
			case "XLSX":
				JRXlsxExporter jrXlsxExporter = new JRXlsxExporter();
				jrXlsxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
				jrXlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(reporte));
				SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
				configuration.setOnePagePerSheet(false);
				configuration.setDetectCellType(true);
				configuration.setCollapseRowSpan(true);
				jrXlsxExporter.setConfiguration(configuration);
				jrXlsxExporter.exportReport();
				break;
			case "HTML":
				JRHtmlExporter jrHtmlExporter= new JRHtmlExporter();
				jrHtmlExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
				jrHtmlExporter.setExporterOutput(new SimpleHtmlExporterOutput(reporte));
				//jrHtmlExporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, request.getContextPath() + "/image?image=");
				
				jrHtmlExporter.exportReport();
				break;
			default:
				break;
			}

		} catch (Exception e) {
			throw e;
		}
		return reporte.toByteArray();
	}

	@Override
	public void reporte(HttpServletResponse response) throws Exception {
		response.setContentType("text/html");
		Resource resource = new ClassPathResource("/report/planillas.jasper");
		File file = resource.getFile();
		
		Map parameters = new HashMap();
		parameters.put("ptitulo", "LIQUIDACIONES : " + "PLANILLA PERSONAL");
		
		java.sql.Connection con = dataSource.getConnection();
		//File file = new File(path);
		JasperReport jr = (JasperReport) JRLoader.loadObject(file);
		JasperPrint jp = JasperFillManager.fillReport(jr, parameters,con);
		HtmlExporter htmlExporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
		htmlExporter.setExporterInput(new SimpleExporterInput(jp));
		htmlExporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
		htmlExporter.setConfiguration(createHtmlConfiguration());
		htmlExporter.exportReport();
		
	}
	
	
	private HtmlExporterConfiguration createHtmlConfiguration() throws IOException {
	    SimpleHtmlExporterConfiguration shec = new SimpleHtmlExporterConfiguration();

	    shec.setHtmlHeader(getHtmlHeader());
	    //shec.setHtmlFooter(getHtmlFooter());

	    return shec;
	}

	private String getHtmlHeader() {
	    StringBuffer sb = new StringBuffer();
	    sb.append("<html>");
	    sb.append("<head>");
	    sb.append("  <title></title>");
	    sb.append("  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>");
	    sb.append("  <style type=\"text/css\">");
	    sb.append("    a {text-decoration: none}");
	    sb.append("  </style>");
	    sb.append("</head>");
	    sb.append("<body text=\"#000000\" link=\"#000000\" alink=\"#000000\" vlink=\"#000000\">");
	    sb.append("<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">");
	    sb.append("<tr><td align=\"left\">");

	    return sb.toString();
	}

	private String getHtmlFooter() {
	  return "";
	}

}
