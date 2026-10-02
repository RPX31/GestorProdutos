package com.RODRIGO.RPX.dto.request;

public record ProdutoRequestDTO(
        String nome,
        Double preco,
        String descricao,
        Integer quantidade,
        Long categoriaId,
        Long marcaId
) {
}