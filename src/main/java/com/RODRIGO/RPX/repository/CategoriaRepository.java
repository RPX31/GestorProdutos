package com.RODRIGO.RPX.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.RODRIGO.RPX.entity.Categoria;
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
         List<Categoria> findByNomeContainingIgnoreCase(String nome);
}
