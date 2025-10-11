-- Script para insertar datos de prueba en PolleriaDB
USE PolleriaDB;
GO

-- Insertar categorías
INSERT INTO Categorias (nombre) VALUES 
('Pollo'),
('Guarnición'),
('Ensalada'),
('Bebida');
GO

-- Insertar insumos
INSERT INTO Insumos (nombre, unidad_medida, cantidad) VALUES 
('Pollo', 'kg', 50.0),
('Papas', 'kg', 30.0),
('Aceite', 'litros', 10.0),
('Sal', 'kg', 5.0),
('Limón', 'unidades', 100.0),
('Arroz', 'kg', 25.0),
('Cebolla', 'kg', 15.0),
('Tomate', 'kg', 20.0);
GO

-- Insertar platos
INSERT INTO Platos (nombre, id_categoria, precio, descripcion) VALUES 
('Pollo a la Brasa', 1, 25.00, 'Pollo entero asado con papas'),
('Pollo Broaster', 1, 20.00, 'Pollo frito crujiente'),
('Papas Fritas', 2, 8.00, 'Papas fritas doradas'),
('Ensalada Mixta', 3, 12.00, 'Ensalada fresca con vegetales'),
('Arroz Chaufa', 2, 15.00, 'Arroz frito con vegetales'),
('Gaseosa', 4, 5.00, 'Bebida gaseosa fría');
GO

-- Insertar relación plato-insumo
INSERT INTO PlatoInsumo (id_plato, id_insumo, cantidad) VALUES 
(1, 1, 1.0),  -- Pollo a la Brasa necesita 1kg de pollo
(1, 2, 0.5),  -- Pollo a la Brasa necesita 0.5kg de papas
(1, 3, 0.1),  -- Pollo a la Brasa necesita 0.1 litros de aceite
(1, 4, 0.05), -- Pollo a la Brasa necesita 0.05kg de sal
(2, 1, 0.8),  -- Pollo Broaster necesita 0.8kg de pollo
(2, 3, 0.2),  -- Pollo Broaster necesita 0.2 litros de aceite
(2, 4, 0.03), -- Pollo Broaster necesita 0.03kg de sal
(3, 2, 0.3),  -- Papas Fritas necesita 0.3kg de papas
(3, 3, 0.15), -- Papas Fritas necesita 0.15 litros de aceite
(3, 4, 0.02), -- Papas Fritas necesita 0.02kg de sal
(4, 6, 0.2),  -- Ensalada Mixta necesita 0.2kg de arroz
(4, 7, 0.1),  -- Ensalada Mixta necesita 0.1kg de cebolla
(4, 8, 0.15), -- Ensalada Mixta necesita 0.15kg de tomate
(5, 6, 0.3),  -- Arroz Chaufa necesita 0.3kg de arroz
(5, 7, 0.08), -- Arroz Chaufa necesita 0.08kg de cebolla
(5, 8, 0.1),  -- Arroz Chaufa necesita 0.1kg de tomate
(5, 3, 0.05); -- Arroz Chaufa necesita 0.05 litros de aceite
GO

-- Insertar ventas de ejemplo
INSERT INTO Ventas (fecha, cliente, metodo_pago) VALUES 
('2025-10-10 12:30:00', 'Juan Pérez', 'Efectivo'),
('2025-10-10 13:15:00', 'María García', 'Tarjeta'),
('2025-10-10 14:00:00', 'Carlos López', 'Efectivo'),
('2025-10-09 11:45:00', 'Ana Martínez', 'Tarjeta'),
('2025-10-09 16:20:00', 'Pedro Rodríguez', 'Efectivo');
GO

-- Insertar detalles de venta
INSERT INTO DetalleVenta (id_venta, id_plato, cantidad, precio_unitario) VALUES 
(1, 1, 1, 25.00),  -- Venta 1: 1 Pollo a la Brasa
(1, 3, 1, 8.00),   -- Venta 1: 1 Papas Fritas
(2, 2, 2, 20.00),  -- Venta 2: 2 Pollo Broaster
(2, 6, 2, 5.00),   -- Venta 2: 2 Gaseosas
(3, 1, 1, 25.00),  -- Venta 3: 1 Pollo a la Brasa
(3, 4, 1, 12.00),  -- Venta 3: 1 Ensalada Mixta
(4, 5, 1, 15.00),  -- Venta 4: 1 Arroz Chaufa
(4, 6, 1, 5.00),   -- Venta 4: 1 Gaseosa
(5, 2, 1, 20.00),  -- Venta 5: 1 Pollo Broaster
(5, 3, 2, 8.00);   -- Venta 5: 2 Papas Fritas
GO

PRINT 'Datos de prueba insertados correctamente';
