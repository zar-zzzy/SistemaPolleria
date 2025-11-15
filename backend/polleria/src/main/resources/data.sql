-- Datos iniciales para la base de datos
-- Se ejecuta automáticamente al iniciar Spring Boot

-- Usuarios (contraseñas sin encriptar para propósitos académicos)
-- Usuarios (contraseñas sin encriptar para propósitos académicos)
INSERT INTO usuario (username, password, rol)
SELECT 'admin', 'admin', 'ADMIN'
WHERE NOT EXISTS (SELECT 1 FROM usuario WHERE username = 'admin');

INSERT INTO usuario (username, password, rol)
SELECT 'trabajador', '123', 'TRABAJADOR'
WHERE NOT EXISTS (SELECT 1 FROM usuario WHERE username = 'trabajador');

-- Categorías
INSERT INTO categorias (nombre) VALUES ('Pollo');
INSERT INTO categorias (nombre) VALUES ('Guarniciones');
INSERT INTO categorias (nombre) VALUES ('Bebidas');

-- Insumos
INSERT INTO insumo (nombre, unidad_medida, stock) VALUES ('Pollo entero', 'kg', 25);
INSERT INTO insumo (nombre, unidad_medida, stock) VALUES ('Papas', 'kg', 15);
INSERT INTO insumo (nombre, unidad_medida, stock) VALUES ('Aceite vegetal', 'l', 8);
INSERT INTO insumo (nombre, unidad_medida, stock) VALUES ('Sal', 'kg', 2);
INSERT INTO insumo (nombre, unidad_medida, stock) VALUES ('Limón', 'unidad', 50);
INSERT INTO insumo (nombre, unidad_medida, stock) VALUES ('Ají', 'kg', 3);

-- Platos (id_categoria: 1=Pollo, 2=Guarniciones, 3=Bebidas)
INSERT INTO platos (nombre, precio, id_categoria) VALUES ('Pollo a la brasa - 1/4', 15.00, 1);
INSERT INTO platos (nombre, precio, id_categoria) VALUES ('Pollo a la brasa - 1/2', 28.00, 1);
INSERT INTO platos (nombre, precio, id_categoria) VALUES ('Pollo a la brasa - Entero', 50.00, 1);
INSERT INTO platos (nombre, precio, id_categoria) VALUES ('Papas fritas', 8.00, 2);
INSERT INTO platos (nombre, precio, id_categoria) VALUES ('Ensalada', 5.00, 2);
INSERT INTO platos (nombre, precio, id_categoria) VALUES ('Inca Kola 500ml', 3.00, 3);
INSERT INTO platos (nombre, precio, id_categoria) VALUES ('Chicha morada', 4.00, 3);
