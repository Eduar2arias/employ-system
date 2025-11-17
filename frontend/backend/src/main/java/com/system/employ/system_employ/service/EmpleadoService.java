package com.system.employ.system_employ.service;

import java.util.List;
import com.system.employ.system_employ.model.Empleado;

public interface EmpleadoService {
    List<Empleado> listarTodos();
    Empleado guardar(Empleado empleado);
    Empleado buscarPorId(Long id);
    Empleado actualizar(Empleado empleado);
    void eliminar(Long id);
}
