package com.andremata.projetospringbootjava;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.andremata.projetospringbootjava.domain.Categoria;
import com.andremata.projetospringbootjava.repositories.CategoriaRepository;

@SpringBootApplication
public class ProjetoSpringBootJavaApplication implements CommandLineRunner 	{

	@Autowired
	private CategoriaRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetoSpringBootJavaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {		
		List<Categoria> categorias = Arrays.asList(new Categoria(1, "Informática"), new Categoria(2, "Escritório"));
		
		repository.saveAll(categorias);
	}
}
