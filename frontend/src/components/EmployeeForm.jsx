import React, { useState, useEffect } from "react";

export default function EmployeeForm({ onSubmit, employee, onCancel }) {
  const [formData, setFormData] = useState({
    nombre: "",
    apellido: "",
    email: "",
    cargo: "",
    salario: "",
  });

  useEffect(() => {
    if (employee) {
      setFormData(employee);
    }
  }, [employee]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit({ ...formData, salario: parseFloat(formData.salario) });
  };

  return (
    <form onSubmit={handleSubmit} className="mb-4">
      <div className="mb-2">
        <input
          type="text"
          name="nombre"
          placeholder="Nombre"
          value={formData.nombre}
          onChange={handleChange}
          required
          className="form-control"
        />
      </div>
      <div className="mb-2">
        <input
          type="text"
          name="apellido"
          placeholder="Apellido"
          value={formData.apellido}
          onChange={handleChange}
          required
          className="form-control"
        />
      </div>
      <div className="mb-2">
        <input
          type="email"
          name="email"
          placeholder="Email"
          value={formData.email}
          onChange={handleChange}
          required
          className="form-control"
        />
      </div>
      <div className="mb-2">
        <input
          type="text"
          name="cargo"
          placeholder="Cargo"
          value={formData.cargo}
          onChange={handleChange}
          required
          className="form-control"
        />
      </div>
      <div className="mb-2">
        <input
          type="number"
          step="0.01"
          name="salario"
          placeholder="Salario"
          value={formData.salario}
          onChange={handleChange}
          required
          className="form-control"
        />
      </div>
      <div>
        <button type="submit" className="btn btn-success me-2">
          {employee ? "Actualizar" : "Agregar"}
        </button>
        <button type="button" className="btn btn-secondary" onClick={onCancel}>
          Cancelar
        </button>
      </div>
    </form>
  );
}
