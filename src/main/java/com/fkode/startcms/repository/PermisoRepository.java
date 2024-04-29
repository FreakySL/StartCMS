package com.fkode.startcms.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fkode.startcms.model.Permiso;

import jakarta.annotation.PostConstruct;

import com.fkode.startcms.mapper.PermisoMapper;

@Repository
public class PermisoRepository implements PermisoRep{

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	@PostConstruct
	public void postConstruct() {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean save(Permiso permiso) {
		try {
			String sql = String.format("insert into Permiso (Nombre) "
					+ "values ('%s')"
					, permiso.getNombre());
			
			jdbcTemplate.execute(sql);
			return true;
			
		} catch (Exception e) {
			
			return false;
		}
	}

	@Override
	public boolean update(Permiso permiso) {
		
		if (permiso.getIdPermiso() > 0) {
			String sql = String.format("update Permiso set Nombre = '%s'"
					+ " where IdPermiso = '%d'",  permiso.getNombre(), permiso.getIdPermiso());
			jdbcTemplate.execute(sql);
			return true;
		}
		
		return false;
	}

	@Override
	public List<Permiso> findAll(Pageable pageable) {

		return jdbcTemplate.query("select * from Permiso", new PermisoMapper());
		
	}

	@Override
	public Permiso findById(int id) {
		
		Object[] params = new Object[] {id};
		
		return jdbcTemplate.queryForObject("select * from Permiso where IdPermiso = ?"
				, params, new PermisoMapper());
		
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
}
