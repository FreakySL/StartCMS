package com.fkode.startcms;

import java.util.Date;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fkode.startcms.model.Categoria;
import com.fkode.startcms.repository.CategoriaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class StartCmsApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private CategoriaRepository categoriaRepository; 

}
