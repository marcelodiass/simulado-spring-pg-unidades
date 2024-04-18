package br.com.fiap.unidades.resource;

import br.com.fiap.unidades.dto.request.ChefeRequest;
import br.com.fiap.unidades.dto.response.ChefeResponse;
import br.com.fiap.unidades.entity.Chefe;
import br.com.fiap.unidades.entity.Unidade;
import br.com.fiap.unidades.service.ChefeService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/chefe")
public class ChefeResource implements ResourceDTO<ChefeRequest, ChefeResponse> {

    @Autowired
    private ChefeService service;

    @GetMapping
    public ResponseEntity<Collection<ChefeResponse>> findAll(
            @RequestParam(name = "usuario", required = false) Long idUsuario,
            @RequestParam(name = "substituto", required = false) Boolean substituto,
            @RequestParam(name = "unidade", required = false) Long idUnidade
    ) {

        var unidade = Unidade.builder().id(idUnidade).build();

        var chefe = Chefe.builder()
                .id( idUsuario )
                .substituto( substituto )
                .unidade( unidade )
                .build();

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Chefe> example = Example.of( chefe, matcher);

        var encontrados = service.findAll( example );
        var resposta = encontrados.stream()
                .map( service::toResponse )
                .collect(Collectors.toList());

        return ResponseEntity.ok( resposta );
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<ChefeResponse> findById(@PathVariable Long id) {
        var encontrado = service.findById(id);
        var resposta = service.toResponse(encontrado);
        return ResponseEntity.ok(resposta);
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<ChefeResponse> save(@RequestBody @Valid ChefeRequest r ) {
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
