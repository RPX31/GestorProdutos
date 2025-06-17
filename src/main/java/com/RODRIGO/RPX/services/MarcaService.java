package com.RODRIGO.RPX.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void deletar(Long id){
        marcaRepository.deleteById(id);
    }
}
