package com.andremata.projetospringbootjava.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.andremata.projetospringbootjava.domain.Pedido;
import com.andremata.projetospringbootjava.repositories.PedidoRepository;
import com.andremata.projetospringbootjava.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository repository;
	
	public Pedido consultar(Integer id) {
		Optional<Pedido> pedido = repository.findById(id);
		
		return pedido.orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado! Id: " + id + " Tipo: " + pedido.getClass().getName()));
	}
}
