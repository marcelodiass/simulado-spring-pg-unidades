package br.com.fiap.unidades.dto.request;

import br.com.fiap.unidades.entity.Tipo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PessoaRequest(
        @Size(min = 3, max = 100)
        @NotNull(message = "Por favor insira o nome.")
        String nome,

        @Size(min = 3, max = 100)
        @NotNull(message = "Por favor insira o sobrenome.")
        String sobrenome,

        @Email(message = "Insira um email válido!")
        @NotNull(message = "Por favor insira o email.")
        String email,

        @NotNull(message = "Por favor insira o tipo.")
        Tipo tipo,

        @NotNull(message = "Por favor insira a data de nascimento.")
        LocalDate nascimento
) {
}
