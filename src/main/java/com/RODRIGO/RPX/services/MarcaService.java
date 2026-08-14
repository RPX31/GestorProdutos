package com.RODRIGO.RPX.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.RODRIGO.RPX.entity.Marca;
import com.RODRIGO.RPX.exception.BusinessException;
import com.RODRIGO.RPX.exception.ResourceInUseException;
import com.RODRIGO.RPX.exception.ResourceNotFoundException;
import com.RODRIGO.RPX.repository.MarcaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MarcaService {

    private final MarcaRepository marcaRepository;

    @Transactional(readOnly = true)
    public List<Marca> listarTodos() {
        return marcaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Marca> buscarPorNome(String nome) {
        return marcaRepository.findByNomeContainingIgnoreCase(nome);
    }

    @Transactional(readOnly = true)
    public Marca buscarPorId(Long id) {

        return marcaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Marca não encontrada com o ID: " + id
                        )
                );
    }

    @Transactional
    public Marca salvar(Marca marca) {

        validarNomeDuplicado(marca);

        return marcaRepository.save(marca);
    }

    @Transactional
    public void deletar(Long id) {

        Marca marca = buscarPorId(id);

        if (!marca.getProdutos().isEmpty()) {

            throw new ResourceInUseException(
                    "Não é possível excluir a marca '" +
                            marca.getNome() +
                            "' porque existem produtos associados a ela."
            );
        }

        marcaRepository.delete(marca);
    }

    private void validarNomeDuplicado(Marca marca) {

        boolean existe = marcaRepository
                .existsByNomeIgnoreCase(marca.getNome());

        if (existe) {

            throw new BusinessException(
                    "Já existe uma marca com esse nome."
            );
        }
    }
}