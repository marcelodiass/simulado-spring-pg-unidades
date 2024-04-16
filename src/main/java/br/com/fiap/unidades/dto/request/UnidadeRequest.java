package br.com.fiap.unidades.dto.request;

public record UnidadeRequest(
        String nome,
        String sigla,
        String descricao,
        AbstractRequest macro
) {
}
