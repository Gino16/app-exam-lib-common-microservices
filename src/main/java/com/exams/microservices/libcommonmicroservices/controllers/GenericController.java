package com.exams.microservices.libcommonmicroservices.controllers;

import com.exams.microservices.libcommonmicroservices.services.GenericService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class GenericController<S extends GenericService<E>, E> {

  protected S service;

  @GetMapping
  public ResponseEntity<?> list() {
    return ResponseEntity.ok().body(service.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable("id") Long id) {
    Optional<E> o = service.findById(id);

    return o.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(o.get());
  }

  @PostMapping
  public ResponseEntity<?> save(@RequestBody E entity) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    service.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
