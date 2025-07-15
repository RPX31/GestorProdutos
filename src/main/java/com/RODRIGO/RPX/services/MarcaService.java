package com.RODRIGO.RPX.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.RODRIGO.RPX.entity.Marca;
import com.RODRIGO.RPX.repository.MarcaRepository;

@Service
public class MarcaService {
    @Autowired
    private MarcaRepository marcaRepository;
    public Marca buscarPorId(Long id)
    {
        return marcaRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }
    @Transactional
    public void deletar(Long id){
        Marca marca = marcaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Marca não encontrada"));

    if (!marca.getProdutos().isEmpty()) {
        throw new RuntimeException("Não é possível deletar: existem produtos associados a esta marca.");
    }

    marcaRepository.deleteById(id);
    }
}
