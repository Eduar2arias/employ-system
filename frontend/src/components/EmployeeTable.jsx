import React from "react";

export default function EmployeeTable({ employees, onEdit, onDelete }) {
  return (
    <table className="table table-striped table-hover">
      <thead className="table-dark">
        <tr>
          <th>ID</th>
          <th>Nombre</th>
          <th>Apellido</th>
          <th>Email</th>
          <th>Cargo</th>
          <th>Salario</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        {employees.map((emp) => (
          <tr key={emp.id}>
            <td>{emp.id}</td>
            <td>{emp.nombre}</td>
            <td>{emp.apellido}</td>
            <td>{emp.email}</td>
            <td>{emp.cargo}</td>
            <td>{emp.salario}</td>
            <td>
              <button
                className="btn btn-warning btn-sm me-1"
                onClick={() => onEdit(emp)}
              >
                ✏ Editar
              </button>
              <button
                className="btn btn-danger btn-sm"
                onClick={() => onDelete(emp.id)}
              >
                🗑 Eliminar
              </button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}
