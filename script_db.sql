CREATE TABLE articulos(
	id_articulo VARCHAR(10) NOT NULL PRIMARY KEY,
    nombre VARCHAR(20) NOT NULL,
    descripcion VARCHAR(200),                     
    precio DECIMAL(10, 2) NOT NULL,
    modelo VARCHAR(10)  
);

INSERT INTO articulos (id_articulo, nombre, descripcion, precio, modelo)
VALUES ('A123456789', 'Laptop Pro', 'Laptop Pro Gaming marca X', 1200.99, 'LTP2024');

INSERT INTO articulos (id_articulo, nombre, descripcion, precio, modelo)
VALUES ('B987654321', 'Smartphone Y', 'Teléfono con pantalla OLED', 899.99, 'SMY2024');

INSERT INTO articulos (id_articulo, nombre, descripcion, precio, modelo)
VALUES ('C543210987', 'Tablet Z', 'Tablet con 128GB de almacenamiento', 499.99, 'TBZ2024');