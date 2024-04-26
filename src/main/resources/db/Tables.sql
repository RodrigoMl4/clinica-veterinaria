-- Crear la base de datos y seleccionarla
CREATE DATABASE IF NOT EXISTS clinica_veterinaria_dg_6;
USE clinica_veterinaria_dg_6;

-- Creación de la tabla employees
CREATE TABLE IF NOT EXISTS employees (
                                         employee_id INT AUTO_INCREMENT PRIMARY KEY,
                                         name VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    ci VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL
    ) ENGINE=InnoDB;

-- Creación de la tabla owners
CREATE TABLE IF NOT EXISTS owners (
                                      owner_id INT AUTO_INCREMENT PRIMARY KEY,
                                      ci VARCHAR(50) UNIQUE NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    client_code VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(50),
    email VARCHAR(255) UNIQUE NOT NULL,
    address VARCHAR(255)
    ) ENGINE=InnoDB;

-- Creación de la tabla pets
CREATE TABLE IF NOT EXISTS pets (
                                    pet_id INT AUTO_INCREMENT PRIMARY KEY,
                                    name VARCHAR(100) NOT NULL,
    species VARCHAR(50) NOT NULL,
    sex VARCHAR(50) NOT NULL,
    sterilized BOOLEAN NOT NULL,
    age INT,
    owner_id INT NOT NULL,
    weight DECIMAL(5,2),
    color VARCHAR(50),
    breed VARCHAR(100),
    FOREIGN KEY (owner_id) REFERENCES owners(owner_id) ON DELETE CASCADE
    ) ENGINE=InnoDB;

-- Creación de la tabla Service_Type
CREATE TABLE IF NOT EXISTS service_type (
                                            typeID INT AUTO_INCREMENT PRIMARY KEY,
                                            service_name VARCHAR(255) UNIQUE NOT NULL
    );

-- Creación de la tabla service_provided
CREATE TABLE IF NOT EXISTS service_provided (
                                                serviceProvidedID INT AUTO_INCREMENT PRIMARY KEY,
                                                typeID INT,
                                                price DECIMAL(10, 2) NOT NULL,
    image_video VARCHAR(255),
    reason VARCHAR(255),
    FOREIGN KEY (typeID) REFERENCES Service_Type(typeID)
    );

-- Creación de la tabla services
CREATE TABLE IF NOT EXISTS services (
                                        serviceID INT AUTO_INCREMENT PRIMARY KEY,
                                        pet_id INT,
                                        serviceProvidedID INT,
                                        behavior VARCHAR(255),
    description_case VARCHAR(255),
    date_service DATE,
    temperature DECIMAL(5, 2),
    heartRate DECIMAL(5, 2),
    respiratoryRate DECIMAL(5, 2),
    systolicBloodPressure DECIMAL(5, 2),
    diastolicBloodPressure DECIMAL(5, 2),
    meanBloodPressure DECIMAL(5, 2),
    mucousMembrane VARCHAR(255),
    pulse DECIMAL(5, 2),
    FOREIGN KEY (pet_id) REFERENCES pets(pet_id),
    FOREIGN KEY (serviceProvidedID) REFERENCES service_provided(serviceProvidedID)
    );

INSERT INTO employees (name, phone, ci, email, password_hash) VALUES
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

INSERT INTO pets (name, species, sex, sterilized, age, owner_id, weight, color, breed) VALUES
                                                                                           ('Max', 'Perro', 'Macho', 1, 3, 1, 7.5, 'Negro', 'Labrador Retriever'),
                                                                                           ('Luna', 'Gato', 'Hembra', 0, 2, 1, 4.2, 'Blanco y Negro', 'Siamés'),
                                                                                           ('Toby', 'Perro', 'Macho', 1, 5, 2, 10.1, 'Café', 'Golden Retriever'),
                                                                                           ('Pelusa', 'Gato', 'Hembra', 1, 4, 3, 3.8, 'Gris', 'Persa'),
                                                                                           ('Rocky', 'Perro', 'Macho', 0, 1, 3, 6.7, 'Marrón', 'Bulldog'),
                                                                                           ('Simba', 'Gato', 'Macho', 1, 6, 4, 5.5, 'Naranja', 'Egipcio'),
                                                                                           ('Coco', 'Perro', 'Macho', 1, 2, 5, 8.3, 'Blanco', 'Bichón Frisé'),
                                                                                           ('Lola', 'Perro', 'Hembra', 0, 4, 5, 4.9, 'Dorado', 'Chihuahua');

INSERT INTO service_type (service_name) VALUES
                                            ('Rayos X'),
                                            ('Tomografía'),
                                            ('Vacunación'),
                                            ('Esterilización'),
                                            ('Consulta General'),
                                            ('Análisis de Sangre'),
                                            ('Cirugía de Emergencia'),
                                            ('Baño y Corte de Pelo');


