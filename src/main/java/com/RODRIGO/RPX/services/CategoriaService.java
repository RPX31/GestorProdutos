package com.RODRIGO.RPX.services;

import java.util.List;

import com.RODRIGO.RPX.dto.request.CategoriaRequestDTO;
import com.RODRIGO.RPX.dto.response.CategoriaResponseDTO;
import com.RODRIGO.RPX.entity.Categoria;
import com.RODRIGO.RPX.exception.BusinessException;
import com.RODRIGO.RPX.exception.ResourceInUseException;
import com.RODRIGO.RPX.exception.ResourceNotFoundException;
import com.RODRIGO.RPX.infrastructure.restclient.ProdutoRestClient;
import com.RODRIGO.RPX.repository.CategoriaRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ProdutoRestClient produtoRestClient;

    @Transactional(readOnly = true)
    public List<CategoriaResponseDTO> listarTodos() {

        return categoriaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<CategoriaResponseDTO> buscarPorNome(String nome) {

        return categoriaRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public CategoriaResponseDTO buscarPorId(Long id) {

        Categoria categoria = buscarEntidadePorId(id);

        return toResponseDTO(categoria);
    }

    @Transactional
    public CategoriaResponseDTO salvar(CategoriaRequestDTO request) {

        validarNomeDuplicado(request.nome(), null);

        Categoria categoria = new Categoria();
        categoria.setNome(request.nome());

        Categoria categoriaSalva = categoriaRepository.save(categoria);

        return toResponseDTO(categoriaSalva);
    }

    @Transactional
    public CategoriaResponseDTO atualizar(
            Long id,
            CategoriaRequestDTO request) {

        Categoria categoriaExistente = buscarEntidadePorId(id);

        validarNomeDuplicado(
                request.nome(),
                id
        );

        categoriaExistente.setNome(request.nome());

        Categoria categoriaAtualizada =
                categoriaRepository.save(categoriaExistente);

        return toResponseDTO(categoriaAtualizada);
    }

    @Transactional
    public void deletar(Long id) {

        Categoria categoria = buscarEntidadePorId(id);

        if (produtoRestClient.existeProdutoPorCategoria(id)) {

            throw new ResourceInUseException(
                    "Não é possível excluir a categoria '" +
                            categoria.getNome() +
                            "' porque existem produtos associados a ela."
            );
        }

        categoriaRepository.delete(categoria);
    }

    private Categoria buscarEntidadePorId(Long id) {

        return categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Categoria não encontrada com o ID: " + id
                        )
                );
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

    private CategoriaResponseDTO toResponseDTO(Categoria categoria) {

        return new CategoriaResponseDTO(
                categoria.getId(),
                categoria.getNome()
        );
    }
}