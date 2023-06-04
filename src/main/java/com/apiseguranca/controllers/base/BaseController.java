package com.apiseguranca.controllers.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

public interface BaseController<DTO> {
    ResponseEntity<Page<DTO>> findAll(Pageable pageable);
    ResponseEntity<DTO> findById(Long id);
    ResponseEntity<DTO> insert(@Valid DTO dto);
    ResponseEntity<DTO> update(Long id, @Valid DTO dto);
    ResponseEntity<Void> delete(Long id);
}