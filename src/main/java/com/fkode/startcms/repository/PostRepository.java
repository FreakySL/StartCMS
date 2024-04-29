package com.fkode.startcms.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fkode.startcms.mapper.PostMapper;
import com.fkode.startcms.model.Post;

import jakarta.annotation.PostConstruct;

@Repository
public class PostRepository implements PostRep{

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	@PostConstruct
	public void postConstruct() {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean save(Post post) {
		
		try {
			String sql = String.format("insert into Post "
					+ "(Titulo, Slug, Extracto, IdUsuario, Categoria, ImagenDestacada, Tipo) "
					+ "values ('%s', '%s', '%s', '%d', '%d', '%s', '%s')"
					, post.getTitulo(), post.getSlug(), post.getExtracto(), post.getIdUsuario()
					, post.getCategoria(), post.getImagenDestacada(), post.getTipo());
			
			jdbcTemplate.execute(sql);
			return true;
			
		} catch (Exception e) {
			
			return false;
		}
	}

	@Override
	public boolean update(Post post) {
		
		if (post.getIdPost() > 0) {
			String sql = String.format("update Post set "
					+ " Titulo = '%s',"
					+ " Slug = '%s"
					+ " Extracto = '%s'"
					+ " Categoria = '%d'"
					+ " ImagenDestacada = '%s'"
					+ " Tipo = '%s'"
					+ " where IdPost = '%d'"
					, post.getTitulo(), post.getSlug(), post.getExtracto()
					, post.getCategoria(), post.getImagenDestacada()
					, post.getTipo(), post.getIdPost());
			jdbcTemplate.execute(sql);
			return true;
		}
		
		return false;
	}

	@Override
	public List<Post> findAll(Pageable pageable) {
		
		return jdbcTemplate.query("select * from Post", new PostMapper());
	}

	@Override
	public Post findById(int id) {

		Object[] params = new Object[] {id};
		
		return jdbcTemplate.queryForObject("select * from Post where IdPost = ?", params, new PostMapper());
		
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
}
