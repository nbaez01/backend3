package com.empresa.app.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.empresa.app.model.bean.PersonaBean;

public class PersonaMapper implements RowMapper<PersonaBean> {

	@Override
	public PersonaBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		PersonaBean bean= new PersonaBean();
		bean.setId(rs.getInt("id"));
		bean.setNombre(rs.getString("nombre"));
		bean.setApepaterno(rs.getString("apepaterno"));
		bean.setApematerno(rs.getString("apematerno"));
		bean.setDni(rs.getString("dni"));
		bean.setIddireccion(rs.getInt("iddireccion"));
		return bean;
	}

}