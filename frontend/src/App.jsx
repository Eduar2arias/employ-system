import React, { useState, useEffect } from "react";
import Navbar from "./components/Navbar";
import EmployeeTable from "./components/EmployeeTable";
import EmployeeForm from "./components/EmployeeForm";
import { getEmployees, createEmployee, updateEmployee, deleteEmployee } from "./api/employees";

function App() {
  const [employees, setEmployees] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [editingEmployee, setEditingEmployee] = useState(null);

  const fetchEmployees = async () => {
    try {
      console.log("inicio pet !! ");
      
      const res = await getEmployees();
      console.log(res);
      
      setEmployees(res.data);
    } catch (error) {
      console.error("Error cargando empleados:", error);
    }
  };

  useEffect(() => {
    fetchEmployees();
  }, []);

  const handleAddEmployee = async (employee) => {
    await createEmployee(employee);
    fetchEmployees();
    setShowForm(false);
  };

  const handleEditEmployee = (employee) => {
    setEditingEmployee(employee);
    setShowForm(true);
  };

  const handleUpdateEmployee = async (employee) => {
    await updateEmployee(employee.id, employee);
    fetchEmployees();
    setShowForm(false);
    setEditingEmployee(null);
  };

  const handleDeleteEmployee = async (id) => {
    if (!window.confirm("¿Seguro que quieres eliminar este empleado?")) return;
    await deleteEmployee(id);
    fetchEmployees();
  };

  return (
    <div>
      <Navbar />
      <div className="container py-5">
        <h1 className="text-center mb-4">Gestión de Empleados</h1>
        <button className="btn btn-primary mb-3" onClick={() => { setEditingEmployee(null); setShowForm(true); }}>
          Nuevo Empleado
        </button>
        {showForm && (
          <EmployeeForm
            onSubmit={editingEmployee ? handleUpdateEmployee : handleAddEmployee}
            employee={editingEmployee}
            onCancel={() => { setShowForm(false); setEditingEmployee(null); }}
          />
        )}
        <EmployeeTable
          employees={employees}
          onEdit={handleEditEmployee}
          onDelete={handleDeleteEmployee}
        />
      </div>
    </div>
  );
}

export default App;
