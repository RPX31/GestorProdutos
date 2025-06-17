package com.RODRIGO.RPX.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.RODRIGO.RPX.entity.Marca;
@Repository
public interface MarcaRepository extends JpaRepository <Marca, Long>{
     List<Marca> findByNomeContainingIgnoreCase(String nome);
}
