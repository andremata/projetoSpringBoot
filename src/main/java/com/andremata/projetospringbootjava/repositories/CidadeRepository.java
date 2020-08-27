package com.andremata.projetospringbootjava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andremata.projetospringbootjava.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
