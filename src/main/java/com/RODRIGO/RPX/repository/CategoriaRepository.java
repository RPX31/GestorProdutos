package com.RODRIGO.RPX.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.RODRIGO.RPX.entity.Categoria;
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
         List<Categoria> findByNomeContainingIgnoreCase(String nome);
         boolean existsByNomeIgnoreCase(String nome);
         Optional<Categoria> findByNomeIgnoreCase(String nome);
}
