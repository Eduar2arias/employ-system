package com.system.employ.system_employ.controller;

import com.system.employ.system_employ.model.Empleado;
import com.system.employ.system_employ.service.EmpleadoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmpleadoRestController.class)
class EmpleadoRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmpleadoService empleadoService;

    // ==============================
    // Test GET /api/empleados
    // ==============================
    @Test
    void listar_empleados_OK() throws Exception {
        when(empleadoService.listarTodos()).thenReturn(
                Arrays.asList(
                        new Empleado(1L, "Juan", "Perez", "juan@mail.com", "Dev", 2000.0)
                )
        );

        mockMvc.perform(get("/api/empleados"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Juan"))
                .andExpect(jsonPath("$[0].cargo").value("Dev"));
    }

    // ==============================
    // Test GET /api/empleados/{id}
    // ==============================
    @Test
    void buscar_por_id_OK() throws Exception {
        Empleado empleado = new Empleado(1L, "Ana", "Lopez", "ana@mail.com", "QA", 2500.0);

        when(empleadoService.buscarPorId(1L)).thenReturn(empleado);

        mockMvc.perform(get("/api/empleados/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("ana@mail.com"));
    }

    // ==============================
    // Test POST /api/empleados
    // ==============================
    @Test
    void guardar_empleado_OK() throws Exception {
        Empleado empleado = new Empleado(1L, "Carlos", "Mendez", "carlos@mail.com", "DevOps", 3000.0);

        when(empleadoService.guardar(any(Empleado.class))).thenReturn(empleado);

        String json = """
            {
                "nombre": "Carlos",
                "apellido": "Mendez",
                "email": "carlos@mail.com",
                "cargo": "DevOps",
                "salario": 3000.0
            }
        """;

        mockMvc.perform(post("/api/empleados")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Carlos"));
    }

    // ==============================
    // Test PUT /api/empleados/{id}
    // ==============================
    @Test
    void actualizar_empleado_OK() throws Exception {
        Empleado empleado = new Empleado(1L, "Luis", "Gomez", "luis@mail.com", "Dev", 3500.0);

        when(empleadoService.actualizar(any(Empleado.class))).thenReturn(empleado);

        String json = """
            {
                "nombre": "Luis",
                "apellido": "Gomez",
                "email": "luis@mail.com",
                "cargo": "Dev",
                "salario": 3500.0
            }
        """;

        mockMvc.perform(put("/api/empleados/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salario").value(3500.0));
    }

    // ==============================
    // Test DELETE /api/empleados/{id}
    // ==============================
    @Test
    void eliminar_empleado_OK() throws Exception {
        mockMvc.perform(delete("/api/empleados/1"))
                .andExpect(status().isOk());
    }
}
