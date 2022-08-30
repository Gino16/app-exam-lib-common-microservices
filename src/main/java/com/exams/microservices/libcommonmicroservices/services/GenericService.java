package com.exams.microservices.libcommonmicroservices.services;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface GenericService<E> {
    public Iterable<E> findAll();

    public Page<E> findAll(Pageable pageable);
    public Optional<E> findById(Long id);

    public E save(E entity);

    public void deleteById(Long id);
}
