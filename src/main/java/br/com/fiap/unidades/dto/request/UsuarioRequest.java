package br.com.fiap.unidades.dto.request;

public record UsuarioRequest(
        String username,
        String password,
        PessoaRequest pessoa
) {
}
