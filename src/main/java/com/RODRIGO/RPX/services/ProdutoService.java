package com.RODRIGO.RPX.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.RODRIGO.RPX.entity.Produto;
import com.RODRIGO.RPX.exception.ResourceNotFoundException;
import com.RODRIGO.RPX.repository.ProdutoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Transactional(readOnly = true)
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Produto buscarPorId(Long id) {

        return produtoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Produto não encontrado com o ID: " + id
                        )
                );
    }

    @Transactional
    public Produto salvar(Produto produto) {

        return produtoRepository.save(produto);
    }

    @Transactional
    public Produto atualizar(Long id, Produto produto) {

        Produto produtoExistente = buscarPorId(id);

        produtoExistente.setNome(produto.getNome());
        produtoExistente.setPreco(produto.getPreco());
        produtoExistente.setDescricao(produto.getDescricao());
        produtoExistente.setQuantidade(produto.getQuantidade());
        produtoExistente.setCategoria(produto.getCategoria());
        produtoExistente.setMarca(produto.getMarca());

        return produtoRepository.save(produtoExistente);
    }

    @Transactional
    public void deletar(Long id) {

        Produto produto = buscarPorId(id);

        produtoRepository.delete(produto);
    }
}