package com.empresa.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.app.service.ReporteService;

@RestController
public class ReporteController {

	@Autowired
	ReporteService reporteService;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/reportePlanillaPdf", method = RequestMethod.POST)
	public @ResponseBody HttpEntity<byte[]> imprimirReportePlanilla(@RequestBody String tipo) throws Exception {
		byte[] reporte;
		HttpEntity<byte[]> aux = null;
		try {
			reporte = reporteService.ReportePlanilla(tipo);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "pdf"));
			headers.set("Content-Disposition", "attachment; filename=" + "planillas.pdf");
			headers.setContentLength(reporte.length);
			aux = new HttpEntity<>(reporte, headers);

		} catch (Exception e) {
			throw e;
		}
		return aux;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/reportePlanillaXlsx", method = RequestMethod.POST)
	public @ResponseBody HttpEntity<byte[]> imprimirReportePlanillaXlsx(@RequestBody String tipo) throws Exception {
		byte[] reporte;
		HttpEntity<byte[]> aux = null;
		try {
			reporte = reporteService.ReportePlanilla(tipo);

			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Description", "File Transfer");
			headers.add("Content-Disposition", "attachment; filename=planillas.xlsx");
			headers.add("Content-Transfer-Encoding", "binary");
			headers.add("Connection", "Keep-Alive");
			headers.setContentType(
					MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
			aux = new HttpEntity<>(reporte, headers);

		} catch (Exception e) {
			throw e;
		}
		return aux;
	}
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/reportePlanillaHtml", method = RequestMethod.POST)
	public @ResponseBody HttpEntity<byte[]> imprimirreportePlanillaHtml(@RequestBody String tipo) throws Exception {
		byte[] reporte;
		HttpEntity<byte[]> aux = null;
		try {
			reporte = reporteService.ReportePlanilla(tipo);

			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Description", "File Transfer");
			headers.add("Content-Disposition", "attachment; filename=planillas.html");
			headers.add("Content-Transfer-Encoding", "binary");
			headers.add("Connection", "Keep-Alive");
			headers.setContentType(MediaType.parseMediaType("text/html"));
			aux = new HttpEntity<>(reporte, headers);

		} catch (Exception e) {
			throw e;
		}
		return aux;
	}
}
