package br.com.fiap.unidades.dto.response;

public record UnidadeResponse(
        String nome,
        String sigla,
        String descricao,
        Long id,
        UnidadeResponse macro
) {
}
