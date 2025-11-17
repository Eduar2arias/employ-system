CREATE TABLE empleados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    cargo VARCHAR(100),
    salario DECIMAL(15,2)
);

INSERT INTO empleados (id, nombre, apellido, email, cargo, salario) VALUES
(1, 'camila', 'dueña', 'cami@gmail.com', 'senior developer', 50000000),
(2, 'juan', 'perez', 'juan@example.com', 'junior developer', 3500000),
(3, 'laura', 'gomez', 'laura@example.com', 'tech lead', 9000000);