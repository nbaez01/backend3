package com.empresa.app.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.empresa.app.dao.PersonaDao;
import com.empresa.app.model.bean.PersonaBean;
import com.empresa.app.model.mapper.PersonaMapper;

@Repository
public class PersonaDaoImpl implements PersonaDao {

	@Autowired
	DataSource dataSource;

	@Override
	public List<PersonaBean> listarPersona() {
		Integer op_codigo = -1;
		String op_mensaje = "";
		List<PersonaBean> list = new ArrayList<>();
		try {
			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource).withSchemaName("USERPRUEBA")
					.withCatalogName("PCK_HM").withProcedureName("SP_SEL_PERSONA")
					.returningResultSet("op_objcursor", new PersonaMapper());

			Map<String, Object> out = simpleJdbcCall.execute();
			list = (List<PersonaBean>) out.get("op_objcursor");
			op_codigo = ((BigDecimal) out.get("OP_CODIGO")).intValue();
			if (op_codigo == -1) {
				op_mensaje = (String) out.get("OP_MENSAJE");
			}
		} catch (Exception e) {
			System.out.println("[[" + getClass().getName() + "]] " + e.getMessage());
		}
		return list;
	}

}