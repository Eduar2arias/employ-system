import { useEffect, useState } from "react";
import { getEmployees, deleteEmployee } from "../api/employees";
import EmployeeForm from "../components/EmployeeForm";
import EmployeeTable from "../components/EmployeeTable";

export default function EmployeesPage() {
  const [employees, setEmployees] = useState([]);

  useEffect(() => {
    loadEmployees();
  }, []);

  const loadEmployees = async () => {
    const res = await getEmployees();
    setEmployees(res.data);
  };

  const handleDelete = async (id) => {
    await deleteEmployee(id);
    loadEmployees();
  };

  return (
    <div style={{ padding: "20px" }}>
      <h1>Gestión de Empleados</h1>

      <EmployeeForm onEmployeeCreated={loadEmployees} />

      <EmployeeTable employees={employees} onDelete={handleDelete} />
    </div>
  );
}
