package com.exams.microservices.libcommonmicroservices.services.impl;

import com.exams.microservices.libcommonmicroservices.services.GenericService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
public class GenericServiceImpl<E, R extends PagingAndSortingRepository<E, Long>> implements
    GenericService<E> {

  protected R repository;


  @Transactional(readOnly = true)
  @Override
  public Iterable<E> findAll() {
    return repository.findAll();
  }

  @Transactional(readOnly = true)
  @Override
  public Page<E> findAll(Pageable pageable) {
    return repository.findAll(pageable);
  }

  @Transactional(readOnly = true)

  @Override
  public Optional<E> findById(Long id) {
    return repository.findById(id);
  }

  @Transactional
  @Override
  public E save(E entity) {
    return repository.save(entity);
  }

  @Override
  public void deleteById(Long id) {
    repository.deleteById(id);
  }
}
