CREATE USER 'administrator'@'localhost' IDENTIFIED BY 'adminpsw';
-- Crear base de dades.

CREATE DATABASE mypizza  DEFAULT CHARACTER SET utf8  DEFAULT COLLATE utf8_general_ci;


GRANT SELECT, INSERT, UPDATE, DELETE ON mypizza.* TO 'administrator'@'localhost';

USE mypizza;


CREATE TABLE `tb_pizza`(
    `id_pizza` INT(4) NOT NULL AUTO_INCREMENT,
    `id_producto` INT(4) NOT NULL,
    PRIMARY KEY (`id_pizza`)
) ENGINE=InnoDB;

CREATE TABLE `tb_pizzaDetalle` (
	`id` INT(4) NOT NULL AUTO_INCREMENT,
	`id_ingrediente` INT(4) NOT NULL,
    `id_pizza` INT(4) NOT NULL,    
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `tb_ingredientes` (
	`id_ingrediente` INT(4) NOT NULL AUTO_INCREMENT,
    `id_producto` INT(4) NOT NULL,    
    PRIMARY KEY (`id_ingrediente`)
) ENGINE=InnoDB;


CREATE TABLE `tb_producto` (
    `id_producto` INT(4) NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(40) DEFAULT NULL,
    `precio` DOUBLE DEFAULT 0.0,
    `imagen` VARCHAR(40),
    `id_tipo` INT(4) NOT NULL,
    PRIMARY KEY (`id_producto`)
) ENGINE=InnoDB;

CREATE TABLE `tb_refresco` (
    `id_refresco` INT(4) NOT NULL AUTO_INCREMENT,
    `id_producto` INT(4) NOT NULL,
    PRIMARY KEY (`id_refresco`)
) ENGINE=InnoDB;




CREATE TABLE `tb_tipo` (
    `id_tipo` INT(4) NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(40),
    PRIMARY KEY (`id_tipo`)
) ENGINE=InnoDB;

CREATE TABLE `tb_pedido_info` (
    `id_pedido_info` INT(4) NOT NULL AUTO_INCREMENT,
    `id_estado` INT(4),
    `direccion` VARCHAR(40),
    `dia_hora` DATETIME,
    PRIMARY KEY (`id_pedido_info`)
) ENGINE=InnoDB;

CREATE TABLE `tb_pedido` (
	`id_pedido` INT(4) NOT NULL AUTO_INCREMENT,
    `id_pedido_info` INT(4),
    `id_producto` INT(4),
    `observaciones` VARCHAR(40),
    `id_cliente` INT(4),
    PRIMARY KEY (`id_pedido`)
) ENGINE=InnoDB;

CREATE TABLE `tb_estado` (
	`id_estado` INT(4) NOT NULL AUTO_INCREMENT,
    `observaciones` VARCHAR(40),
    `id_empleado` INT(4),
    PRIMARY KEY (`id_estado`)
) ENGINE=InnoDB;


CREATE TABLE `tb_detalleFactura` (
	`id_detalleFactura` INT(4) NOT NULL AUTO_INCREMENT,
    `id_pedido` INT(4),
    `id_factura` INT(4),
    `cantidad` INT(4),
    `precio_total` DOUBLE DEFAULT 0.0,
    PRIMARY KEY (`id_detalleFactura`)
) ENGINE=InnoDB;

CREATE TABLE `tb_Factura` (
	`id_factura` INT(4) NOT NULL AUTO_INCREMENT,
    `id_cliente` INT(4),
    `id_metodoPago` INT(4),
    `fecha` DATE,
    `cobrado` BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (`id_factura`)
) ENGINE=InnoDB;

CREATE TABLE `tb_MetodoPago` (
	`id_metodoPago` INT(4) NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(40),
    `otros_detalles` VARCHAR(40),
    PRIMARY KEY (`id_metodoPago`)
) ENGINE=InnoDB;



CREATE TABLE `tb_empleado` (
    `id_empleado` INT(4) NOT NULL AUTO_INCREMENT,
    `id_usuario` INT(4),
    `hora_entrada` DATETIME,
    `hora_salida` DATETIME,
    `horas_semanales` int(4),
    `salario` DOUBLE DEFAULT 0.0,  
    PRIMARY KEY (`id_empleado`)
) ENGINE=InnoDB;

CREATE TABLE `Usuario` (
    `id_usuario` INT(4) NOT NULL AUTO_INCREMENT,
    `dni` VARCHAR(9) DEFAULT NULL UNIQUE,
    `nombre` VARCHAR(40) DEFAULT NULL,
    `apellidos` VARCHAR(40) DEFAULT NULL,
    `contraseña` VARCHAR(40) DEFAULT NULL,
    `imagen` VARCHAR(40) DEFAULT NULL,
    `tipo_usuario` VARCHAR(40) DEFAULT NULL,
    `correo` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB;


CREATE TABLE `tb_cliente` (
    `id_cliente` INT(4) NOT NULL AUTO_INCREMENT,
    `id_usuario` INT(4),
    `direccion1` VARCHAR(40) DEFAULT NULL,
    `direccion2` VARCHAR(40) DEFAULT NULL,   
    PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB;


alter table tb_pizza ADD CONSTRAINT FK_tbPizza_tbProducto
foreign key (id_producto) references tb_producto(id_producto)
on update cascade;

alter table tb_pizzaDetalle ADD CONSTRAINT FK_tbPizzaDetalle_tbPizza
foreign key (id_pizza) references tb_pizza(id_pizza)
on update cascade;

alter table tb_pizzaDetalle ADD CONSTRAINT FK_tbPizzaDetalle_tbIngrediente
foreign key (id_ingrediente) references tb_ingredientes(id_ingrediente)
on update cascade;

alter table tb_producto ADD CONSTRAINT FK_tbProducto_tbTipo
foreign key (id_tipo) references tb_tipo(id_tipo)
on update cascade;


alter table tb_refresco ADD CONSTRAINT FK_tbRefresco_tbProducto
foreign key (id_producto) references tb_producto(id_producto)
on update cascade;

alter table tb_pedido_info ADD CONSTRAINT FK_tbPedidoInfo_tbEstado
foreign key (id_estado) references tb_estado(id_estado)
on update cascade;

alter table tb_pedido ADD CONSTRAINT FK_tbPedido_tbPedidoInfo
foreign key (id_pedido_info) references tb_pedido_info(id_pedido_info)
on update cascade;

alter table tb_pedido ADD CONSTRAINT FK_tbPedido_tbProducto
foreign key (id_producto) references tb_producto(id_producto)
on update cascade;

alter table tb_pedido ADD CONSTRAINT FK_tbPedido_tbCliente
foreign key (id_cliente) references tb_cliente(id_cliente)
on update cascade;

alter table tb_detalleFactura ADD CONSTRAINT FK_tbDetalleFactura_tbPedido
foreign key (id_pedido) references tb_pedido(id_pedido)
on update cascade;

alter table tb_detalleFactura ADD CONSTRAINT FK_tbDetalleFactura_tbFactura
foreign key (id_factura) references tb_Factura(id_factura)
on update cascade;

alter table tb_cliente ADD CONSTRAINT FK_tbCliente_tbUsuario
foreign key (id_usuario) references Usuario(id_usuario)
on update cascade;

alter table tb_empleado ADD CONSTRAINT FK_tbEmpleado_tbUsuario
foreign key (id_usuario) references Usuario(id_usuario)
on update cascade;

alter table tb_estado ADD CONSTRAINT FK_tbEstado_tbEmpleado
foreign key (id_empleado) references tb_empleado(id_empleado)
on update cascade;
