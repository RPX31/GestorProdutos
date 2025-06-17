package com.RODRIGO.RPX.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.RODRIGO.RPX.entity.Produto;
@Repository
public interface ProdutoRepository extends JpaRepository <Produto, Long> {
}
