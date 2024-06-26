package com.fkode.startcms.repository;

import java.util.Date;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.fkode.startcms.component.TestDatabaseConfiguration;
import com.fkode.startcms.model.Categoria;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestDatabaseConfiguration.class})
public class CategoriaRepositoryTest {

	@Autowired
	private CategoriaRepository categoriaRepository; 
	
	@Test
	public void testInsert() {
		
		Categoria categoria = new Categoria();
		
		categoria.setNombre("Test1");
		categoria.setFecha(new Date());
		categoria.setDescripcion("Este es un ejemplo de categoria superior");
		categoria.setCategoriaSuperior(1);
		
		boolean result = categoriaRepository.save(categoria);
		
		Assert.assertTrue(result);
	}
	
	@Test
	public void testUpdate() {
		
		Categoria categoria = new Categoria();
		
		categoria.setIdCategoria(1);
		categoria.setNombre("Test2");
		categoria.setDescripcion("Este es un ejemplo de categoria superior");
		categoria.setCategoriaSuperior(1);
		
		boolean result = categoriaRepository.update(categoria);
		
		Assert.assertTrue(result);
	}
	
	@Test
	public void testFindById() {
		
		Categoria categoria = categoriaRepository.findById(1);
		
		Assert.assertTrue(categoria != null);
		Assert.assertTrue("Test2".equals(categoria.getNombre()));
		
	}
	
	@Test
	public void testFindAll() {
		
		SpringDataWebProperties.Pageable pageable = new SpringDataWebProperties.Pageable();
		Assert.assertFalse(categoriaRepository.findAll(pageable).isEmpty());
		
	}
	
}
