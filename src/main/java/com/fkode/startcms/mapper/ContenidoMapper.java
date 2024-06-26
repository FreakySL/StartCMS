package com.fkode.startcms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fkode.startcms.model.Contenido;

public class ContenidoMapper implements RowMapper<Contenido>{

	@Override
	public Contenido mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Contenido contenido = new Contenido();
		
		contenido.setIdContenido(rs.getInt("IdContenido"));
		contenido.setTipo(rs.getString("Contenido"));
		contenido.setContenido(rs.getString("Contenido"));
		contenido.setIdPost(rs.getInt("IdPost"));
		contenido.setFecha(rs.getDate("Fecha"));
		
		return contenido;
	}

}
