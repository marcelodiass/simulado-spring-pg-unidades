package br.com.fiap.unidades.resource;

import br.com.fiap.unidades.dto.request.UnidadeRequest;
import br.com.fiap.unidades.dto.response.UnidadeResponse;
import br.com.fiap.unidades.service.UnidadeService;
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
* - Fazer UK que garanta que não haja mais de uma unidade com a mesma sigla na mesma unidade macro
* - Possibilitar consultas pelos campos: nome, sigla, macro.id
* */

@RestController
@RequestMapping(value = "/unidade")
public class UnidadeResource {

    @Autowired
    private UnidadeService service;

    @GetMapping
    public List<UnidadeResponse> findAll() {
        return service.findAll().stream().map(service::toResponse).collect(Collectors.toList()); //?
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UnidadeResponse> findById(@PathVariable(name = "id") Long id) {
        UnidadeResponse response = service.toResponse(service.findById(id));
        if (Objects.isNull( response )) return ResponseEntity.notFound().build();
        return ResponseEntity.ok( response );
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UnidadeResponse> save(@RequestBody UnidadeRequest request) {
        UnidadeResponse response = service.toResponse(service.save(request));
        if (Objects.isNull(response)) return ResponseEntity.badRequest().build();

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created( uri ).body( response );
    }
}
