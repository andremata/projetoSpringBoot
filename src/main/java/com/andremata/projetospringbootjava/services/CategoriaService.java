package com.andremata.projetospringbootjava.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andremata.projetospringbootjava.domain.Categoria;
import com.andremata.projetospringbootjava.repositories.CategoriaRepository;
import com.andremata.projetospringbootjava.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	public Categoria consultar(Integer id) {
		Optional<Categoria> categoria = repository.findById(id);
		
		return categoria.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada! Id: " + id + " Tipo: " + categoria.getClass().getName()));
	}
	
	public Categoria incluir(Categoria categoria) {
		categoria.setId(null);
		
		return repository.save(categoria);
	}
}
