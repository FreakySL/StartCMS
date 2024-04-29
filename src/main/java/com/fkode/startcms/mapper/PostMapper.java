package com.fkode.startcms.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.fkode.startcms.model.Post;

public class PostMapper implements RowMapper<Post>{

	@Override
	public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Post post = new Post();
		
		post.setIdPost(rs.getInt("IdPost"));
		post.setTitulo(rs.getString("Titulo"));
		post.setSlug(rs.getString("Slug"));
		post.setExtracto(rs.getString("Extracto"));
		post.setIdUsuario(rs.getInt("IdUsuario"));
		post.setImagenDestacada(rs.getString("ImagenDestacada"));
		post.setTipo(rs.getString("Tipo"));
		
		return post;
	}

}
