-- Insertar Usuarios (contraseña: 123456)
INSERT INTO usuario (username, password, rol) 
VALUES ('admin', '123456', 'ADMIN');

INSERT INTO usuario (username, password, rol) 
VALUES ('trabajador', '123456', 'TRABAJADOR');

-- Insertar Categorías
INSERT INTO categorias (nombre) VALUES ('Pollos');
INSERT INTO categorias (nombre) VALUES ('Guarniciones');
INSERT INTO categorias (nombre) VALUES ('Bebidas');
INSERT INTO categorias (nombre) VALUES ('Postres');

-- Insertar Platos
INSERT INTO platos (nombre, descripcion, precio, id_categoria) 
VALUES ('Pollo a la Brasa Entero', 'Pollo entero con papas y ensalada', 45.00, 1);

INSERT INTO platos (nombre, descripcion, precio, id_categoria) 
VALUES ('1/2 Pollo a la Brasa', 'Medio pollo con papas fritas', 25.00, 1);

INSERT INTO platos (nombre, descripcion, precio, id_categoria) 
VALUES ('1/4 Pollo a la Brasa', 'Cuarto de pollo con papas', 15.00, 1);

INSERT INTO platos (nombre, descripcion, precio, id_categoria) 
VALUES ('Papas Fritas', 'Porcion grande de papas fritas', 8.00, 2);

INSERT INTO platos (nombre, descripcion, precio, id_categoria) 
VALUES ('Ensalada Mixta', 'Ensalada fresca con vegetales', 7.00, 2);

INSERT INTO platos (nombre, descripcion, precio, id_categoria) 
VALUES ('Inca Kola 1.5L', 'Bebida gaseosa', 6.00, 3);

INSERT INTO platos (nombre, descripcion, precio, id_categoria) 
VALUES ('Chicha Morada', 'Bebida tradicional peruana', 5.00, 3);

INSERT INTO platos (nombre, descripcion, precio, id_categoria) 
VALUES ('Arroz con Leche', 'Postre tradicional', 6.00, 4);

-- Insertar Insumos
INSERT INTO insumos (nombre, unidad_medida, stock) 
VALUES ('Pollo', 'kg', 50.00);

INSERT INTO insumos (nombre, unidad_medida, stock) 
VALUES ('Papas', 'kg', 30.00);

INSERT INTO insumos (nombre, unidad_medida, stock) 
VALUES ('Lechuga', 'unidad', 20.00);

INSERT INTO insumos (nombre, unidad_medida, stock) 
VALUES ('Tomate', 'kg', 15.00);

INSERT INTO insumos (nombre, unidad_medida, stock) 
VALUES ('Aceite', 'litros', 10.00);

INSERT INTO insumos (nombre, unidad_medida, stock) 
VALUES ('Sal', 'kg', 5.00);

-- Insertar Plato-Insumo (Relaciones)
-- Pollo a la Brasa Entero
INSERT INTO plato_insumo (id_plato, id_insumo) VALUES (1, 1);
INSERT INTO plato_insumo (id_plato, id_insumo) VALUES (1, 2);
INSERT INTO plato_insumo (id_plato, id_insumo) VALUES (1, 3);

-- 1/2 Pollo
INSERT INTO plato_insumo (id_plato, id_insumo) VALUES (2, 1);
INSERT INTO plato_insumo (id_plato, id_insumo) VALUES (2, 2);

-- 1/4 Pollo
INSERT INTO plato_insumo (id_plato, id_insumo) VALUES (3, 1);
INSERT INTO plato_insumo (id_plato, id_insumo) VALUES (3, 2);

-- Papas Fritas
INSERT INTO plato_insumo (id_plato, id_insumo) VALUES (4, 2);
INSERT INTO plato_insumo (id_plato, id_insumo) VALUES (4, 5);

-- Ensalada
INSERT INTO plato_insumo (id_plato, id_insumo) VALUES (5, 3);
INSERT INTO plato_insumo (id_plato, id_insumo) VALUES (5, 4);

-- Insertar Anuncios de ejemplo
INSERT INTO anuncio (titulo, mensaje, activo, fecha_inicio, fecha_fin) 
VALUES ('Bienvenidos', '¡Bienvenidos a nuestro sistema de gestión!', TRUE, '2024-01-01', '2025-12-31');

INSERT INTO anuncio (titulo, mensaje, activo, fecha_inicio, fecha_fin) 
VALUES ('Promoción del día', 'Pollo entero + 2 bebidas por solo S/. 50', TRUE, '2024-01-01', '2025-12-31');