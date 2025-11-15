-- Datos iniciales para la base de datos
-- Se ejecuta automáticamente al iniciar Spring Boot

-- Categorías
INSERT INTO categoria (nombre) VALUES ('Pollo');
INSERT INTO categoria (nombre) VALUES ('Guarniciones');
INSERT INTO categoria (nombre) VALUES ('Bebidas');

-- Insumos
INSERT INTO insumo (nombre, unidad_medida, stock) VALUES ('Pollo entero', 'kg', 25.5);
INSERT INTO insumo (nombre, unidad_medida, stock) VALUES ('Papas', 'kg', 15.0);
INSERT INTO insumo (nombre, unidad_medida, stock) VALUES ('Aceite vegetal', 'l', 8.5);
INSERT INTO insumo (nombre, unidad_medida, stock) VALUES ('Sal', 'kg', 2.0);
INSERT INTO insumo (nombre, unidad_medida, stock) VALUES ('Limón', 'unidad', 50);
INSERT INTO insumo (nombre, unidad_medida, stock) VALUES ('Ají', 'kg', 3.0);

-- Platos (necesitas verificar el ID de categoría correcta)
INSERT INTO plato (nombre, precio, id_categoria) VALUES ('Pollo a la brasa - 1/4', 15.00, 1);
INSERT INTO plato (nombre, precio, id_categoria) VALUES ('Pollo a la brasa - 1/2', 28.00, 1);
INSERT INTO plato (nombre, precio, id_categoria) VALUES ('Pollo a la brasa - Entero', 50.00, 1);
INSERT INTO plato (nombre, precio, id_categoria) VALUES ('Papas fritas', 8.00, 2);
INSERT INTO plato (nombre, precio, id_categoria) VALUES ('Ensalada', 5.00, 2);
INSERT INTO plato (nombre, precio, id_categoria) VALUES ('Inca Kola 500ml', 3.00, 3);
INSERT INTO plato (nombre, precio, id_categoria) VALUES ('Chicha morada', 4.00, 3);
