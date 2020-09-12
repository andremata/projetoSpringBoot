package com.andremata.projetospringbootjava.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.andremata.projetospringbootjava.domain.Categoria;
import com.andremata.projetospringbootjava.domain.dto.CategoriaDTO;
import com.andremata.projetospringbootjava.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Categoria> consultar(@PathVariable Integer id) {
		
		Categoria categoria = service.consultar(id);
		
		return ResponseEntity.ok().body(categoria);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> consultar() {
		List<Categoria> categorias = service.consultar();
		
		//converte a lista de Categoria e CategoriaDTO
		List<CategoriaDTO> categoriasDto = categorias.stream().map(cat -> new CategoriaDTO(cat)) //converte categoria e categoriadto
											.collect(Collectors.toList()); //retorna a lista de categotiadto
		
		return ResponseEntity.ok().body(categoriasDto);
	}
	
	@RequestMapping(value="/pagina", method=RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> consultarPaginacao(
			@RequestParam(value="pagina", defaultValue = "0") Integer pagina, 
			@RequestParam(value="linhas", defaultValue = "24") Integer linhas, 
			@RequestParam(value="ordenacao", defaultValue = "nome") String ordenacao, 
			@RequestParam(value="direcao", defaultValue = "ASC") String direcao) {
		
		Page<Categoria> categorias = service.paginar(pagina, linhas, ordenacao, direcao);
		
		Page<CategoriaDTO> categoriasDto = categorias.map(cat -> new CategoriaDTO(cat));
		
		return ResponseEntity.ok().body(categoriasDto);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> incluir(@RequestBody Categoria categoria) {
		//@RequestBody faz o json ser convertido para um objeto java
		
		categoria = service.incluir(categoria);
		
		//Pega a URI do novo recurso inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(categoria.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> alterar(@RequestBody Categoria categoria, @PathVariable Integer id) {
		categoria.setId(id);
		
		service.alterar(categoria);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable Integer id) {
		service.deletar(id);
		
		return ResponseEntity.noContent().build();
	}
}
