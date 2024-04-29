package com.fkode.startcms.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fkode.startcms.model.PostMetadata;

import jakarta.annotation.PostConstruct;

import com.fkode.startcms.mapper.PostMetadataMapper;

@Repository
public class PostMetadataRepository implements PostMetadataRep{

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	@PostConstruct
	public void postConstruct() {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean save(PostMetadata postMeta) {
		
		try {
			String sql = String.format("insert into Post_Metadata (Clave, Valor, Tipo, IdPost) "
					+ "values ('%s', '%s', '%s', '%d')"
					, postMeta.getClave(), postMeta.getValor()
					, postMeta.getTipo(), postMeta.getIdPost());
			
			jdbcTemplate.execute(sql);
			return true;
			
		} catch (Exception e) {
			
			return false;
		}
	}

	@Override
	public boolean update(PostMetadata postMeta) {
		
		if (postMeta.getIdPostMetadata() > 0) {
			String sql = String.format("update Post_Metadata set "
					+ " Clave = '%s',"
					+ " Valor = '%s,"
					+ " Tipo = '%s'"
					+ " where IdPostMetadata = '%d'"
					, postMeta.getClave(), postMeta.getValor()
					, postMeta.getTipo(), postMeta.getIdPostMetadata());
			jdbcTemplate.execute(sql);
			return true;
		}
		
		return false;
	}

	@Override
	public List<PostMetadata> findAll(Pageable pageable) {

		return jdbcTemplate.query("select * from Post_Metadata", new PostMetadataMapper());
		
	}

	@Override
	public PostMetadata findById(int id) {

		Object[] params = new Object[] {id};
		
		return jdbcTemplate.queryForObject("select * from Post_Metadata where IdPostMetadata = ?"
				, params, new PostMetadataMapper());
		
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
}
