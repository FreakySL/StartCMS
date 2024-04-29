package com.fkode.startcms.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fkode.startcms.model.GrupoPermiso;

import jakarta.annotation.PostConstruct;

import com.fkode.startcms.mapper.GrupoPermisoMapper;

@Repository
public class GrupoPermisoRepository implements GrupoPermisoRep{

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	@PostConstruct
	public void postConstruct() {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean save(GrupoPermiso grupoPermiso) {
		try {
			String sql = String.format("insert into Grupo_Permiso (IdGrupo, IdPermiso) "
					+ "values ('%d', '%d')"
					, grupoPermiso.getIdGrupo(), grupoPermiso.getIdPermiso());
			
			jdbcTemplate.execute(sql);
			return true;
			
		} catch (Exception e) {
			
			return false;
		}
	}

	@Override
	public boolean update(GrupoPermiso object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<GrupoPermiso> findAll(Pageable pageable) {

		return jdbcTemplate.query("select * from Grupo_Permiso", new GrupoPermisoMapper());
		
	}

	@Override
	public GrupoPermiso findById(int id) {
		
		Object[] params = new Object[] {id};
		
		return jdbcTemplate.queryForObject("select * from Grupo_Permiso where IdGrupoPermiso = ?"
				, params, new GrupoPermisoMapper());
		
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
}
