package com.RODRIGO.RPX.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.RODRIGO.RPX.entity.Categoria;
import com.RODRIGO.RPX.entity.Marca;
import com.RODRIGO.RPX.entity.Produto;
import com.RODRIGO.RPX.repository.CategoriaRepository;
import com.RODRIGO.RPX.repository.MarcaRepository;
import com.RODRIGO.RPX.repository.ProdutoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoPageService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final MarcaRepository marcaRepository;

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    public List<Marca> listarMarcas() {
        return marcaRepository.findAll();
    }

    public Produto novoProduto() {
        return new Produto();
    }

    public Categoria novaCategoria() {
        return new Categoria();
    }

    public Marca novaMarca() {
        return new Marca();
    }
}