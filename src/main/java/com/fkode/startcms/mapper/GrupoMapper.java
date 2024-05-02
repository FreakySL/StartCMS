package com.fkode.startcms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fkode.startcms.model.Grupo;

public class GrupoMapper implements RowMapper<Grupo>{

	@Override
	public Grupo mapRow(ResultSet rs, int rowNum) throws SQLException {

		Grupo grupo = new Grupo();
		
		grupo.setIdGrupo(rs.getInt("IdGrupo"));
		grupo.setNombre("Nombre");
		grupo.setFecha(rs.getDate("Fecha"));
		
		return grupo;
	}

}
