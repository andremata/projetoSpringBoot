package com.andremata.projetospringbootjava;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.andremata.projetospringbootjava.domain.Categoria;
import com.andremata.projetospringbootjava.domain.Cidade;
import com.andremata.projetospringbootjava.domain.Estado;
import com.andremata.projetospringbootjava.domain.Produto;
import com.andremata.projetospringbootjava.repositories.CategoriaRepository;
import com.andremata.projetospringbootjava.repositories.CidadeRepository;
import com.andremata.projetospringbootjava.repositories.EstadoRepository;
import com.andremata.projetospringbootjava.repositories.ProdutoRepository;

@SpringBootApplication
public class ProjetoSpringBootJavaApplication implements CommandLineRunner 	{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	EstadoRepository estadoRepository;
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetoSpringBootJavaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria c1 = new Categoria(null, "Informática");
		Categoria c2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
				
		c1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		c2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(c1));
		p2.getCategorias().addAll(Arrays.asList(c1, c2));
		p3.getCategorias().addAll(Arrays.asList(c1));
		
		categoriaRepository.saveAll(Arrays.asList(c1, c2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado e1 = new Estado(null, "Minas Gerais");
		Estado e2 = new Estado(null, "São Paulo");
		
		Cidade cd1 = new Cidade(null, "Uberlândia", e1);
		Cidade cd2 = new Cidade(null, "São Paulo", e2);
		Cidade cd3 = new Cidade(null, "Campinas", e2);
				
		e1.getCidades().addAll(Arrays.asList(cd1));
		e2.getCidades().addAll(Arrays.asList(cd2, cd3));
		
		estadoRepository.saveAll(Arrays.asList(e1, e2));
		cidadeRepository.saveAll(Arrays.asList(cd1,cd2,cd3));
	}
}
