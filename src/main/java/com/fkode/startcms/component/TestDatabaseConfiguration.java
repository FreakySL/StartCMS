package com.fkode.startcms.component;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.fkode.startcms.repository.CategoriaRepository;
import com.fkode.startcms.repository.ComentarioRepository;
import com.fkode.startcms.repository.ContenidoRepository;
import com.fkode.startcms.repository.GrupoPermisoRepository;
import com.fkode.startcms.repository.GrupoRepository;
import com.fkode.startcms.repository.PermisoRepository;
import com.fkode.startcms.repository.PostMetadataRepository;
import com.fkode.startcms.repository.PostRepository;
import com.fkode.startcms.repository.UsuarioMetadataRepository;
import com.fkode.startcms.repository.UsuarioRepository;

@Configuration
public class TestDatabaseConfiguration {

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/test_blog");
		dataSource.setUsername("freaky");
		dataSource.setPassword("admin");
		
		return dataSource;
	}
	
	@Bean
	public CategoriaRepository categoriaRepository() {
		return new CategoriaRepository();
	}
	
	@Bean
	public ComentarioRepository comentarioRepository() {
		return new ComentarioRepository();
	}
	
	@Bean
	public ContenidoRepository contenidoRepository() {
		return new ContenidoRepository();
	}
	
	@Bean
	public GrupoPermisoRepository grupoPermisoRepository() {
		return new GrupoPermisoRepository();
	}
	
	@Bean
	public GrupoRepository grupoRepository() {
		return new GrupoRepository();
	}
	
	@Bean
	public PermisoRepository permisoRepository() {
		return new PermisoRepository();
	}
	
	@Bean
	public PostMetadataRepository postMetadataRepository() {
		return new PostMetadataRepository();
	}
	
	@Bean
	public PostRepository postRepository() {
		return new PostRepository();
	}
	
	@Bean
	public UsuarioMetadataRepository usuarioMetadataRepository() {
		return new UsuarioMetadataRepository();
	}
	
	@Bean
	public UsuarioRepository usuarioRepository() {
		return new UsuarioRepository();
	}
	
}
