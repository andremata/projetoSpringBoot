package com.andremata.projetospringbootjava.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.andremata.projetospringbootjava.domain.Categoria;
import com.andremata.projetospringbootjava.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> consultar(@PathVariable Integer id) {
		
		Categoria categoria = service.consultar(id);
		
		return ResponseEntity.ok().body(categoria);
	}
}
