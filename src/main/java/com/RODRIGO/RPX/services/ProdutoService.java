package com.RODRIGO.RPX.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RODRIGO.RPX.entity.Produto;
import com.RODRIGO.RPX.repository.ProdutoRepository;

@Service 
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }
    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }
}

