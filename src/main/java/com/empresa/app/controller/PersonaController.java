package com.empresa.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.empresa.app.model.bean.PersonaBean;
import com.empresa.app.service.PersonaService;

@RestController
public class PersonaController {

	@Autowired
	PersonaService personaService;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/listaPersona", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<List<PersonaBean>> listPersona() throws Exception{ 
		//LOGGER.info(" *** INGRESO *** [" + "/api/loginWeb" + "] ");
		return personaService.listarPersona();
	}
}
