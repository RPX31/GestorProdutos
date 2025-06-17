package com.RODRIGO.RPX.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void deletar(Long id){
        categoriaRepository.deleteById(id);
    }

}



