-- Crear la base de datos y seleccionarla
CREATE DATABASE IF NOT EXISTS clinica_veterinaria_dg;
USE clinica_veterinaria_dg;

-- Creación de la tabla employees
CREATE TABLE IF NOT EXISTS users (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     name VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(255) NOT NULL,
    ci VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
    ) ENGINE=InnoDB;

-- Creación de la tabla owners
CREATE TABLE IF NOT EXISTS owners (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      ci VARCHAR(50) UNIQUE NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    client_code VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(50),
    email VARCHAR(255) UNIQUE NOT NULL,
    address VARCHAR(50)
    ) ENGINE=InnoDB;

-- Creación de la tabla pets
CREATE TABLE IF NOT EXISTS pets (
                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                    name VARCHAR(100) NOT NULL,
    species VARCHAR(50) NOT NULL,
    sex VARCHAR(10) NOT NULL,
    sterilized BOOLEAN NOT NULL,
    age INT,
    owner_id INT NOT NULL,
    weight DECIMAL(5,2),
    color VARCHAR(50),
    breed VARCHAR(100),
    FOREIGN KEY (owner_id) REFERENCES owners(id) ON DELETE CASCADE
    ) ENGINE=InnoDB;

-- Insertar datos de usuarios
INSERT INTO users (name, phone, ci, email, password) VALUES
                                                         ('Rodrigo Machaca', '123456789', '1234567890', 'rodrigo@example.com', 'contraseña123'),
                                                         ('Ana López', '987654321', '2345678901', 'ana@example.com', 'contraseña456'),
                                                         ('Martín González', '555123456', '3456789012', 'martin@example.com', 'contraseña789'),
                                                         ('Sofia Ramírez', '777987654', '4567890123', 'sofia@example.com', 'contraseñaabc'),
                                                         ('Pablo Rodríguez', '666888999', '5678901234', 'pablo@example.com', 'contraseñaxyz');

-- Insertar datos de propietarios
INSERT INTO owners (ci, first_name, last_name, client_code, phone, email, address) VALUES
                                                                                       ('1234567', 'Juan', 'Pérez', 'JP001', '123456789', 'juan@example.com', 'Calle 123, Ciudad'),
                                                                                       ('2345678', 'María', 'Gómez', 'MG002', '987654321', 'maria@example.com', 'Avenida XYZ, Pueblo'),
                                                                                       ('3456789', 'Carlos', 'Martínez', 'CM003', '555123456', 'carlos@example.com', 'Plaza Principal, Villa'),
                                                                                       ('4567890', 'Ana', 'Sánchez', 'AS004', '777987654', 'ana@example.com', 'Carrera 456, Pueblo'),
                                                                                       ('5678901', 'Luis', 'Rodríguez', 'LR005', '666888999', 'luis@example.com', 'Avenida Central, Ciudad');

-- Insertar datos de mascotas
INSERT INTO pets (name, species, sex, sterilized, age, owner_id, weight, color, breed) VALUES
                                                                                           ('Max', 'Perro', 'Macho', TRUE, 3, 1, 7.5, 'Negro', 'Labrador Retriever'),
                                                                                           ('Luna', 'Gato', 'Hembra', FALSE, 2, 1, 4.2, 'Blanco y Negro', 'Siamés'),
                                                                                           ('Toby', 'Perro', 'Macho', TRUE, 5, 2, 10.1, 'Café', 'Golden Retriever'),
                                                                                           ('Pelusa', 'Gato', 'Hembra', TRUE, 4, 3, 3.8, 'Gris', 'Persa'),
                                                                                           ('Rocky', 'Perro', 'Macho', FALSE, 1, 3, 6.7, 'Marrón', 'Bulldog'),
                                                                                           ('Simba', 'Gato', 'Macho', TRUE, 6, 4, 5.5, 'Naranja', 'Egipcio'),
                                                                                           ('Coco', 'Perro', 'Macho', TRUE, 2, 5, 8.3, 'Blanco', 'Bichón Frisé'),
                                                                                           ('Lola', 'Perro', 'Hembra', FALSE, 4, 5, 4.9, 'Dorado', 'Chihuahua');

-- Consulta de propietarios con sus mascotas
SELECT o.first_name, o.last_name, p.name AS pet_name, p.species
FROM owners o
         JOIN pets p ON o.id = p.owner_id;





