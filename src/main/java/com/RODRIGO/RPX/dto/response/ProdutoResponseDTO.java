package com.RODRIGO.RPX.dto.response;

public record ProdutoResponseDTO(
        Long id,
        String nome,
        Double preco,
        String descricao,
        Integer quantidade,
        Long categoriaId,
        Long marcaId
) {
}
