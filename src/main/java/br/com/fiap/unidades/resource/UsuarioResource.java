package br.com.fiap.unidades.resource;

import br.com.fiap.unidades.dto.request.UsuarioRequest;
import br.com.fiap.unidades.dto.response.UsuarioResponse;
import br.com.fiap.unidades.entity.Chefe;
import br.com.fiap.unidades.entity.Pessoa;
import br.com.fiap.unidades.entity.Tipo;
import br.com.fiap.unidades.entity.Usuario;
import br.com.fiap.unidades.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/*
*TODO:
* - Fazer UK que garanta que não se tenha mais de um usuário para a mesma pessoa
* - Fazer UK que garanta que não se possa ter mais de um usuário com o mesmo username
* - Possibilitar consultas pelos campos: username, pessoa.id, pessoa.nome, pessoa.sobrenome, pessoa.nascimento, pessoa.tipo, pessoa.email
* */

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResource implements ResourceDTO<UsuarioRequest, UsuarioResponse> {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> findAll(
            @RequestParam(name = "username", required = false) String username,
            @RequestParam(name = "pessoaId", required = false) Long idPessoa,
            @RequestParam(name = "pessoaNome", required = false) String nomePessoa,
            @RequestParam(name = "pessoaSobrenome", required = false) String sobrenomePessoa,
            @RequestParam(name = "pessoaNascimento", required = false) LocalDate pessoaNascimento,
            @RequestParam(name = "pessoaTipo", required = false) Tipo tipo,
            @RequestParam(name = "pessoaEmail", required = false) String email
    ) {
        var pessoa = Pessoa.builder()
                .id(idPessoa)
                .nome(nomePessoa)
                .sobrenome(sobrenomePessoa)
                .nascimento(pessoaNascimento)
                .tipo(tipo)
                .email(email)
                .build();

        var usuario = Usuario.builder()
                .username(username)
                .pessoa(pessoa)
                .build();

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Usuario> example = Example.of( usuario, matcher);

        var encontrados = service.findAll( example );
        var resposta = encontrados.stream()
                .map( service::toResponse )
                .collect(Collectors.toList());

        return ResponseEntity.ok( resposta );
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<UsuarioResponse> findById(@PathVariable(name = "id") Long id) {
        var encontrado = service.findById(id);
        var resposta = service.toResponse(encontrado);
        return ResponseEntity.ok(resposta);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioResponse> save(@RequestBody @Valid UsuarioRequest r) {
        var saved = service.save( r );
        var resposta = service.toResponse(saved);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(resposta);
    }
}