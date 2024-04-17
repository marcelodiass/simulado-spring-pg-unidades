package br.com.fiap.unidades.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UnidadeRequest(
        @Valid
        AbstractRequest macro,

        @Size(min = 3, max = 100)
        String nome,

        @NotNull(message = "Por favor insira a sigla.")
        String sigla,

        @Size(min = 3)
        String descricao

) {
}
