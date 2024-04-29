package com.fkode.startcms.repository;

import java.util.Date;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.fkode.startcms.component.TestDatabaseConfiguration;
import com.fkode.startcms.model.Categoria;
import com.fkode.startcms.repository.CategoriaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestDatabaseConfiguration.class})
public class TestingGeneral {
	
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
	
}
