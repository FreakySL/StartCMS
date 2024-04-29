package com.fkode.startcms.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fkode.startcms.model.Grupo;

import jakarta.annotation.PostConstruct;

import com.fkode.startcms.mapper.GrupoMapper;

@Repository
public class GrupoRepository implements GrupoRep{

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	@PostConstruct
	public void postConstruct() {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean save(Grupo grupo) {
		
		try {
			String sql = String.format("insert into Grupo (Nombre) "
					+ "values ('%s')"
					, grupo.getNombre());
			
			jdbcTemplate.execute(sql);
			return true;
			
		} catch (Exception e) {
			
			return false;
		}
	}

	@Override
	public boolean update(Grupo grupo) {

		if (grupo.getIdGrupo() > 0) {
			String sql = String.format("update Grupo set Nombre = '%s'"
					+ " where IdGrupo = '%d'",  grupo.getNombre(), grupo.getIdGrupo());
			jdbcTemplate.execute(sql);
			return true;
		}
		
		return false;
	}

	@Override
	public List<Grupo> findAll(Pageable pageable) {

		return jdbcTemplate.query("select * from Grupo", new GrupoMapper());
		
	}

	@Override
	public Grupo findById(int id) {
		
		Object[] params = new Object[] {id};
		
		return jdbcTemplate.queryForObject("select * from Grupo where IdGrupo = ?", params, new GrupoMapper());
		
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
}
