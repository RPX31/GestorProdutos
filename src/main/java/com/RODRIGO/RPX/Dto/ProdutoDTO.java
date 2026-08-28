package com.RODRIGO.RPX.Dto;

public record ProdutoDTO(
        Long id,
        String nome,
        Double preco,
        Long marcaId,
        Long categoriaId
) {
}