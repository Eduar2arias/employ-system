import axios from "axios";

// const API_URL = "/api/empleados"; // proxy en vite.config.js
const API_URL = "http://localhost:5000/api/empleados"; // proxy en vite.config.js
 
export const getEmployees = () => axios.get(API_URL);
export const createEmployee = (employee) => axios.post(API_URL, employee);
export const updateEmployee = (id, employee) => axios.put(`${API_URL}/${id}`, employee);
export const deleteEmployee = (id) => axios.delete(`${API_URL}/${id}`);
