package com.empresa.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.empresa.app.dao.PersonaDao;
import com.empresa.app.model.bean.PersonaBean;
import com.empresa.app.service.PersonaService;

@Service
public class PersonaServiceImpl implements PersonaService{

	@Autowired
	PersonaDao personaDao;
	
	@Override
	public ResponseEntity<List<PersonaBean>> listarPersona() {
		return new ResponseEntity<List<PersonaBean>>(personaDao.listarPersona(), HttpStatus.OK);
	}

	
}
