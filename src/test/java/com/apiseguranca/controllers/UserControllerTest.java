package com.apiseguranca.controllers;

import com.apiseguranca.dtos.UserDTO;
import com.apiseguranca.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testFindAll() {
        Pageable pageable = Pageable.unpaged();
        UserDTO userDTO = new UserDTO();
        List<UserDTO> userDTOList = Collections.singletonList(userDTO);
        Page<UserDTO> expectedPage = new PageImpl<>(userDTOList);
        when(userService.findAllPaged(pageable)).thenReturn(expectedPage);

        ResponseEntity<Page<UserDTO>> response = userController.findAll(pageable);

        assertEquals(expectedPage, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(userService, times(1)).findAllPaged(pageable);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void testFindById() {
        Long userId = 1L;
        UserDTO userDTO = new UserDTO();
        when(userService.findById(userId)).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.findById(userId);

        assertEquals(userDTO, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        verify(userService, times(1)).findById(userId);
        verifyNoMoreInteractions(userService);
    }

    // Implementar testes para os demais métodos do controller

}
