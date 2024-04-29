package com.fkode.startcms.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fkode.startcms.model.Usuario;

import jakarta.annotation.PostConstruct;

import com.fkode.startcms.mapper.UsuarioMapper;

@Repository
public class UsuarioRepository implements UsuarioRep{

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	@PostConstruct
	public void postConstruct() {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean save(Usuario usuario) {
		try {
			String sql = String.format("insert into Usuario (Nombre, Apellido, Contrasena, Correo, IdGrupo) "
					+ "values ('%s', '%s', '%s', '%s', '%d')"
					, usuario.getNombre(), usuario.getApellido(), usuario.getContrasena()
					, usuario.getCorreo(), usuario.getIdGrupo());
			
			jdbcTemplate.execute(sql);
			return true;
			
		} catch (Exception e) {
			
			return false;
		}
	}

	@Override
	public boolean update(Usuario usuario) {
		
		if (usuario.getIdUsuario() > 0) {
			String sql = String.format("update Usuario set "
					+ " Nombre = '%s',"
					+ " Apellido = '%s,"
					+ " Contrasena = '%s',"
					+ " Correo = '%s'"
					+ " where IdUsuario = '%d'"
					, usuario.getNombre(), usuario.getApellido()
					, usuario.getContrasena(), usuario.getCorreo());
			jdbcTemplate.execute(sql);
			return true;
		}
		
		return false;
	}

	@Override
	public List<Usuario> findAll(Pageable pageable) {
		
		return jdbcTemplate.query("select * from Usuario", new UsuarioMapper());
		
	}

	@Override
	public Usuario findById(int id) {
		
		Object[] params = new Object[] {id};
		
		return jdbcTemplate.queryForObject("select * from Usuario where IdUsuario = ?"
				, params, new UsuarioMapper());

	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
}
