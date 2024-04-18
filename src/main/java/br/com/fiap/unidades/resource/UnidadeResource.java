package br.com.fiap.unidades.resource;

import br.com.fiap.unidades.dto.request.ChefeRequest;
import br.com.fiap.unidades.dto.request.UnidadeRequest;
import br.com.fiap.unidades.dto.response.ChefeResponse;
import br.com.fiap.unidades.dto.response.UnidadeResponse;
import br.com.fiap.unidades.entity.Chefe;
import br.com.fiap.unidades.entity.Unidade;
import br.com.fiap.unidades.service.UnidadeService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


@RestController
@RequestMapping(value = "/unidade")
public class UnidadeResource implements ResourceDTO<UnidadeRequest, UnidadeResponse> {

    @Autowired
    private UnidadeService service;

    @GetMapping
    public ResponseEntity<List<UnidadeResponse>> findAll(
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "sigla", required = false) String sigla,
            @RequestParam(name = "macroId", required = false) Unidade idMacro
    ) {
        var unidade = Unidade.builder()
                .nome(nome)
                .sigla(sigla)
                .macro(idMacro)
                .build();

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreCase()
                .withIgnoreNullValues();

        Example<Unidade> example = Example.of( unidade, matcher);

        var encontrados = service.findAll( example );
        var resposta = encontrados.stream()
                .map(service::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resposta);
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<UnidadeResponse> findById(@PathVariable Long id) {
        var encontrado = service.findById(id);
        var resposta = service.toResponse(encontrado);

        return ResponseEntity.ok(resposta);
    }

    @PostMapping
    @Transactional
    @Override
    public ResponseEntity<UnidadeResponse> save(@RequestBody @Valid UnidadeRequest r) {
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
