package com.empresa.app.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.empresa.app.model.bean.PersonaBean;

public interface PersonaService {
	public ResponseEntity<List<PersonaBean>> listarPersona();
}
