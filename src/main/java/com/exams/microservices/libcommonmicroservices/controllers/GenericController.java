package com.exams.microservices.libcommonmicroservices.controllers;

import com.exams.microservices.libcommonmicroservices.services.GenericService;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

  @GetMapping("/pageable")
  public ResponseEntity<?> list(Pageable pageable) {
    return ResponseEntity.ok().body(service.findAll(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable("id") Long id) {
    Optional<E> o = service.findById(id);

    return o.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(o.get());
  }

  @PostMapping
  public ResponseEntity<?> save(@Valid @RequestBody E entity, BindingResult result) {
    if (result.hasErrors()) {
      return validate(result);
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));
  }

  protected ResponseEntity<?> validate(BindingResult result) {
    Map<String, Object> errors = new HashMap<>();
    result.getFieldErrors()
        .forEach(error -> errors.put(error.getField(),
            "El campo" + error.getField() + " " + error.getDefaultMessage()));
    return ResponseEntity.badRequest().body(errors);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    service.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
