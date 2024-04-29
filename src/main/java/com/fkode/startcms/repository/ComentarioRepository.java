package com.fkode.startcms.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fkode.startcms.model.Comentario;
import com.fkode.startcms.mapper.ComentarioMapper;

@Repository
public class ComentarioRepository implements ComentarioRep{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean save(Comentario comentario) {
		try {
			String sql = String.format("insert into Comentario (Comentario, IdPost, IdUsuario, Respuesta) "
					+ "values ('%s', '%d', '%d', '%d')"
					, comentario.getComentario(), comentario.getIdPost(), comentario.getIdUsuario(), comentario.getRespuesta());
			
			jdbcTemplate.execute(sql);
			return true;
			
		} catch (Exception e) {
			
			return false;
		}
	}

	@Override
	public boolean update(Comentario comentario) {
		
		if (comentario.getIdComentario() > 0) {
			String sql = String.format("update Comentario set "
					+ " Comentario = '%s',"
					+ " Respuesta = '%d"
					+ " where IdComentario = '%d'",  comentario.getComentario(), comentario.getRespuesta()
					, comentario.getIdComentario());
			jdbcTemplate.execute(sql);
			return true;
		}
		
		return false;
	}

	@Override
	public List<Comentario> findAll(Pageable pageable) {

		return jdbcTemplate.query("select * from Comentario", new ComentarioMapper());
		
	}

	@Override
	public Comentario findById(int id) {
		
		Object[] params = new Object[] {id};
		
		return jdbcTemplate.queryForObject("select * from Comentario where IdComentario = ?", params, new ComentarioMapper());
	}
	
}
