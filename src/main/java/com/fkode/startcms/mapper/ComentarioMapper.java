package com.fkode.startcms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fkode.startcms.model.Comentario;

public class ComentarioMapper implements RowMapper<Comentario>{

	@Override
	public Comentario mapRow(ResultSet rs, int rowNum) throws SQLException {

		Comentario comentario = new Comentario();
		
		comentario.setComentario(rs.getString("Comentario"));
		comentario.setIdComentario(rs.getInt("IdComentario"));
		comentario.setIdUsuario(rs.getInt("IdPost"));
		comentario.setIdPost(rs.getInt("IdPost"));
		comentario.setFecha(rs.getDate("Fecha"));
		comentario.setRespuesta(rs.getInt("Respuesta"));
		
		return comentario;
	}
	
}
