package br.com.fiap.unidades.dto.request;

import br.com.fiap.unidades.entity.Unidade;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AbstractRequest(
        @Min(0)
        @NotNull(message = "Por favor insira o ID.")
        Long id
) {
}
