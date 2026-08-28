package com.RODRIGO.RPX.dto;

public record ProdutoDTO(
        Long id,
        String nome,
        Double preco,
        Long marcaId,
        Long categoriaId
) {
}