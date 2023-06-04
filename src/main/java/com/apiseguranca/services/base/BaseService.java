package com.apiseguranca.services.base;

import com.apiseguranca.dtos.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;


public interface BaseService<T> {
    public Page<T> findAllPaged(Pageable pageable);
    public T findById(Long id);
    public T insert(UserDTO dto);
    public T update(Long id, UserDTO dto);
    public void delete(Long id);
}
