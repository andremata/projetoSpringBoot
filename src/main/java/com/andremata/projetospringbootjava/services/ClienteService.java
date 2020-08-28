package com.andremata.projetospringbootjava.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andremata.projetospringbootjava.domain.Cliente;
import com.andremata.projetospringbootjava.repositories.ClienteRepository;
import com.andremata.projetospringbootjava.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository repository;
	
	public Cliente consultar(Integer id) {
		Optional<Cliente> cliente = repository.findById(id);
		
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! Id: " + id + " Tipo: " + cliente.getClass().getName()));
	}
}
