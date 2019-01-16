package com.empresa.app.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

public interface ReporteService {
	byte[] ReportePlanilla(String tipo) throws Exception;
	
	void reporte(HttpServletResponse response) throws Exception;
}