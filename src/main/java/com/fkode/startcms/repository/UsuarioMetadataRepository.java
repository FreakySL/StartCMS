package com.fkode.startcms.repository;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fkode.startcms.model.UsuarioMetadata;
import com.fkode.startcms.mapper.UsuarioMetadataMapper;

@Repository
public class UsuarioMetadataRepository implements UsuarioMetadataRep{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean save(UsuarioMetadata usuarioMeta) {
		try {
			String sql = String.format("insert into Usuario_Metadata (IdUsuario, Clave, Valor, Tipo) "
					+ "values ('%d', '%s', '%s', '%s')"
					, usuarioMeta.getIdUsuario(), usuarioMeta.getClave()
					, usuarioMeta.getValor(), usuarioMeta.getTipo());
			
			jdbcTemplate.execute(sql);
			return true;
			
		} catch (Exception e) {
			
			return false;
		}
	}

	@Override
	public boolean update(UsuarioMetadata usuarioMeta) {
		
		if (usuarioMeta.getIdUsuarioMetadata() > 0) {
			String sql = String.format("update Post_Metadata set "
					+ " Clave = '%s',"
					+ " Valor = '%s,"
					+ " Tipo = '%s'"
					+ " where IdUsuarioMetadata = '%d'"
					, usuarioMeta.getClave(), usuarioMeta.getValor()
					, usuarioMeta.getTipo(), usuarioMeta.getIdUsuarioMetadata());
			jdbcTemplate.execute(sql);
			return true;
		}
		
		return false;
	}

	@Override
	public List<UsuarioMetadata> findAll(Pageable pageable) {
		
		return jdbcTemplate.query("select * from Usuario_Metadata", new UsuarioMetadataMapper());
		
	}

	@Override
	public UsuarioMetadata findById(int id) {
		
		Object[] params = new Object[] {id};
		
		return jdbcTemplate.queryForObject("select * from Usuario_Metadata where IdUsuarioMetadata = ?"
				, params, new UsuarioMetadataMapper());
		
	}
	
}
