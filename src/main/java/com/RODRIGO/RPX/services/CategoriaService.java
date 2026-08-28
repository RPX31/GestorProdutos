package com.RODRIGO.RPX.services;

import java.util.List;

import com.RODRIGO.RPX.infrastructure.restclient.ProdutoRestClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.RODRIGO.RPX.entity.Categoria;
import com.RODRIGO.RPX.exception.BusinessException;
import com.RODRIGO.RPX.exception.ResourceInUseException;
import com.RODRIGO.RPX.exception.ResourceNotFoundException;
import com.RODRIGO.RPX.repository.CategoriaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ProdutoRestClient produtoRestClient;

    @Transactional(readOnly = true)
    public List<Categoria> listarTodos() {
        return categoriaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Categoria> buscarPorNome(String nome) {
        return categoriaRepository.findByNomeContainingIgnoreCase(nome);
    }

    @Transactional(readOnly = true)
    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Categoria não encontrada com o ID: " + id
                        )
                );
    }

    @Transactional
    public Categoria salvar(Categoria categoria) {

        validarNomeDuplicado(categoria.getNome(), null);

        return categoriaRepository.save(categoria);
    }

    @Transactional
    public Categoria atualizar(Long id, Categoria categoriaAtualizada) {

        Categoria categoriaExistente = buscarPorId(id);

        validarNomeDuplicado(
                categoriaAtualizada.getNome(),
                id
        );

        categoriaExistente.setNome(categoriaAtualizada.getNome());

        return categoriaRepository.save(categoriaExistente);
    }

    @Transactional
    public void deletar(Long id) {

        Categoria categoria = buscarPorId(id);

        if (produtoRestClient.existeProdutoPorCategoria(id)) {

            throw new ResourceInUseException(
                    "Não é possível excluir a categoria '" +
                            categoria.getNome() +
                            "' porque existem produtos associados a ela."
            );
        }

        categoriaRepository.delete(categoria);
    }

    private void validarNomeDuplicado(String nome, Long id) {

        categoriaRepository
                .findByNomeIgnoreCase(nome)
                .ifPresent(categoria -> {

                    if (id == null || !categoria.getId().equals(id)) {

                        throw new BusinessException(
                                "Já existe uma categoria com esse nome."
                        );
                    }
                });
    }
}