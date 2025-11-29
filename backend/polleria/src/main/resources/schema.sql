-- Eliminar tablas si existen (para reiniciar limpio)
DROP TABLE IF EXISTS detalle_venta;
DROP TABLE IF EXISTS ventas;
DROP TABLE IF EXISTS plato_insumo;
DROP TABLE IF EXISTS movimiento_insumo;
DROP TABLE IF EXISTS platos;
DROP TABLE IF EXISTS categorias;
DROP TABLE IF EXISTS insumos;
DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS anuncio;

-- Tabla de Usuarios (simplificada, sin tabla roles)
CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    rol VARCHAR(50) NOT NULL
);

-- Tabla de Categorías
CREATE TABLE categorias (
    id_categoria BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

-- Tabla de Platos
CREATE TABLE platos (
    id_plato BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DOUBLE NOT NULL,
    id_categoria BIGINT NOT NULL,
    FOREIGN KEY (id_categoria) REFERENCES categorias(id_categoria)
);

-- Tabla de Insumos
CREATE TABLE insumos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    unidad_medida VARCHAR(50) NOT NULL,
    stock DOUBLE NOT NULL DEFAULT 0
);

-- Tabla de Plato-Insumo (Relación muchos a muchos)
CREATE TABLE plato_insumo (
    id_plato_insumo BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_plato BIGINT NOT NULL,
    id_insumo BIGINT NOT NULL,
    FOREIGN KEY (id_plato) REFERENCES platos(id_plato) ON DELETE CASCADE,
    FOREIGN KEY (id_insumo) REFERENCES insumos(id) ON DELETE CASCADE
);

-- Tabla de Ventas
CREATE TABLE ventas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME NOT NULL,
    total DOUBLE NOT NULL,
    cliente VARCHAR(100),
    metodo_pago VARCHAR(50) NOT NULL
);

-- Tabla de Detalle de Venta
CREATE TABLE detalle_venta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_venta BIGINT NOT NULL,
    id_plato BIGINT NOT NULL,
    cantidad INT NOT NULL,
    precio DOUBLE NOT NULL,
    subtotal DOUBLE NOT NULL,
    FOREIGN KEY (id_venta) REFERENCES ventas(id) ON DELETE CASCADE,
    FOREIGN KEY (id_plato) REFERENCES platos(id_plato)
);

-- Tabla de Movimientos de Insumo
CREATE TABLE movimiento_insumo (
    id_movimiento BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_insumo BIGINT NOT NULL,
    tipo_movimiento VARCHAR(50) NOT NULL,
    cantidad DOUBLE NOT NULL,
    fecha DATETIME NOT NULL,
    descripcion TEXT,
    FOREIGN KEY (id_insumo) REFERENCES insumos(id)
);

-- Tabla de Anuncios
CREATE TABLE anuncio (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    mensaje TEXT NOT NULL,
    activo BOOLEAN DEFAULT TRUE,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL
);