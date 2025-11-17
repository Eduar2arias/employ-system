package com.system.employ.system_employ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.system.employ.system_employ.model.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
}
