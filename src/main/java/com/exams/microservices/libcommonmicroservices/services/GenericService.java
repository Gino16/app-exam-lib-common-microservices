package com.exams.microservices.libcommonmicroservices.services;

import java.util.Optional;


public interface GenericService<E> {
    public Iterable<E> findAll();

    public Optional<E> findById(Long id);

    public E save(E entity);

    public void deleteById(Long id);
}
