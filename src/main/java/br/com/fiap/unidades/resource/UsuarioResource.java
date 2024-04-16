package br.com.fiap.unidades.resource;

import br.com.fiap.unidades.dto.request.UsuarioRequest;
import br.com.fiap.unidades.dto.response.UsuarioResponse;
import br.com.fiap.unidades.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
public class UsuarioResource {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<UsuarioResponse> findAll() {
        return service.findAll().stream().map(service::toResponse).collect(Collectors.toList()); //?
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioResponse> findById(@PathVariable(name = "id") Long id) {
        UsuarioResponse response = service.toResponse(service.findById(id));
        if (Objects.isNull( response )) return ResponseEntity.notFound().build();
        return ResponseEntity.ok( response );
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioResponse> save(@RequestBody UsuarioRequest request) {
        UsuarioResponse response = service.toResponse(service.save(request));
        if (Objects.isNull(response)) return ResponseEntity.badRequest().build();

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created( uri ).body( response );
    }
}