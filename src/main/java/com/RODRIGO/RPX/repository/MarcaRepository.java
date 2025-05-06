package com.RODRIGO.RPX.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RODRIGO.RPX.entity.Marca;

@Repository
public interface MarcaRepository extends JpaRepository <Marca, Long>{

}
