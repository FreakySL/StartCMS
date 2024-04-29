package com.fkode.startcms.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fkode.startcms.model.Contenido;

import jakarta.annotation.PostConstruct;

import com.fkode.startcms.mapper.ContenidoMapper;

@Repository
public class ContenidoRepository implements ContenidoRep{

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	@PostConstruct
	public void postConstruct() {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean save(Contenido contenido) {
		try {
			String sql = String.format("insert into Contenido (Contenido, IdPost, Tipo) "
					+ "values ('%s', '%d', '%s')"
					, contenido.getContenido(), contenido.getIdPost(), contenido.getTipo());
			
			jdbcTemplate.execute(sql);
			return true;
			
		} catch (Exception e) {
			
			return false;
		}
	}

	@Override
	public boolean update(Contenido contenido) {
		
		if (contenido.getIdContenido() > 0) {
			String sql = String.format("update Contenido set "
					+ " Contenido = '%s',"
					+ " Tipo = '%s"
					+ " where IdContenido = '%d'",  contenido.getContenido(), contenido.getTipo()
					, contenido.getIdContenido());
			jdbcTemplate.execute(sql);
			return true;
		}
		
		return false;
	}

	@Override
	public List<Contenido> findAll(Pageable pageable) {

		return jdbcTemplate.query("select * from Contenido", new ContenidoMapper());
		
	}

	@Override
	public Contenido findById(int id) {
		
		Object[] params = new Object[] {id};
		
		return jdbcTemplate.queryForObject("select * from Contenido where IdContenido = ?", params,new ContenidoMapper());
		
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
}
