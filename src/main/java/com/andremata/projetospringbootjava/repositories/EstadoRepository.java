package com.andremata.projetospringbootjava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andremata.projetospringbootjava.domain.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}
