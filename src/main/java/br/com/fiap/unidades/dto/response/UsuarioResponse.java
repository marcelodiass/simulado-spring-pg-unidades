package br.com.fiap.unidades.dto.response;

public record UsuarioResponse(
        Long id,
        String username,
        PessoaResponse pessoa
) {
}
