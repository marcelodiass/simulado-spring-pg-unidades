package br.com.fiap.unidades.service;

import org.springframework.data.domain.Example;

import java.util.List;

/**
 * @param <Entity>
 * @param <Request>
 * @param <Response>
 */

public interface ServiceDTO<Entity, Request, Response> {

    Entity toEntity(Request r);

    Response toResponse(Entity e);

    List<Entity> findAll();

    List<Entity> findAll(Example<Entity> example);

    Entity findById(Long id);

    Entity save(Request r);
}