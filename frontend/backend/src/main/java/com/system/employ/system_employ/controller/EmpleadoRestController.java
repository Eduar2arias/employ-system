package com.system.employ.system_employ.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.system.employ.system_employ.model.Empleado;
import com.system.employ.system_employ.service.EmpleadoService;

@RestController
@RequestMapping("/api/empleados") // ✅ ruta distinta para evitar conflicto
public class EmpleadoRestController {

    @Autowired
    private EmpleadoService empleadoService;

    // Obtener todos los empleados
    @GetMapping
    public List<Empleado> listar() {
        return empleadoService.listarTodos();
    }

    // Obtener un empleado por ID
    @GetMapping("/{id}")
    public Empleado buscarPorId(@PathVariable Long id) {
        return empleadoService.buscarPorId(id);
    }

    // Crear un nuevo empleado
    @PostMapping
    public Empleado guardar(@RequestBody Empleado empleado) {
        return empleadoService.guardar(empleado);
    }

    // Actualizar un empleado existente
    @PutMapping("/{id}")
    public Empleado actualizar(@PathVariable Long id, @RequestBody Empleado empleado) {
        empleado.setId(id);
        return empleadoService.actualizar(empleado);
    }

    // Eliminar un empleado
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        empleadoService.eliminar(id);
    }
}
