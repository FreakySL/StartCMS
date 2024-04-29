package com.fkode.startcms.component;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.fkode.startcms.repository.CategoriaRepository;
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
	public UsuarioRepository usuarioRepository() {
		return new UsuarioRepository();
	}
	
}
