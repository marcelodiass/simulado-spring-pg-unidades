package br.com.fiap.unidades.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioRequest(
        @Size(min = 3, max = 100)
        @NotNull(message = "Por favor insira")
        String username,

        @Size(min = 3, max = 100)
                @NotNull(message = "Por favor insira a senha.")
        String password,

        @NotNull(message = "Por favor insira a pessoa.")
        PessoaRequest pessoa
) {
}
