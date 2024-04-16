package br.com.fiap.unidades.resource;

import br.com.fiap.unidades.dto.request.ChefeRequest;
import br.com.fiap.unidades.dto.response.ChefeResponse;
import br.com.fiap.unidades.entity.Chefe;
import br.com.fiap.unidades.entity.Unidade;
import br.com.fiap.unidades.service.ChefeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @Override
    public ResponseEntity<ChefeResponse> findById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<ChefeResponse> save(ChefeRequest r) {
        return null;
    }
}
