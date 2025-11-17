package com.system.employ.system_employ.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.system.employ.system_employ.model.Empleado;
import com.system.employ.system_employ.repository.EmpleadoRepository;

import java.sql.Connection;
import java.sql.DriverManager;

@Service("empleadoService")
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> listarTodos() {
        List<Empleado> lista = empleadoRepository.findAll();
        System.out.println("Empleados cargados: " + lista.size());
        return lista;
    }

    @Override
    public Empleado guardar(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    public Empleado buscarPorId(Long id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    public Empleado actualizar(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    public void eliminar(Long id) {
        empleadoRepository.deleteById(id);
    }

    public void probarConexion() {
    try (Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/proyecto_integracioncontinua?useSSL=false&serverTimezone=UTC",
        "root",
        "root")) {
        System.out.println("✅ Conexión exitosa a la base de datos!");
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}

