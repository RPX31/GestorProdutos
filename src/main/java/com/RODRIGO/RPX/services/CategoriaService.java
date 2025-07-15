package com.RODRIGO.RPX.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.RODRIGO.RPX.entity.Categoria;
import com.RODRIGO.RPX.repository.CategoriaRepository;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;
    public Categoria buscarPorId(Long id)
    {
        return categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }
    @Transactional
    public void deletar(Long id){
         Categoria categoria = categoriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        if (!categoria.getProdutos().isEmpty()) {
            throw new RuntimeException("Não é possível deletar: existem produtos associados a esta categoria.");
        }

        categoriaRepository.deleteById(id);
    }

}



