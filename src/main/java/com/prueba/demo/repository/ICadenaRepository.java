package com.prueba.demo.repository;

import com.prueba.demo.model.Cadena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICadenaRepository extends JpaRepository<Cadena,Long> {
}
