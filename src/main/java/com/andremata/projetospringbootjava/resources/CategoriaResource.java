package com.andremata.projetospringbootjava.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.andremata.projetospringbootjava.domain.Categoria;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Categoria> consultar() {
		
		List<Categoria> categorias = Arrays.asList(new Categoria(1, "Informática"), new Categoria(2, "Escritório"));
		
		return categorias;
	}
}
