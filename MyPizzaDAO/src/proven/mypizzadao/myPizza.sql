DROP USER 'administrator'@'localhost';

DROP DATABASE mypizza;

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

CREATE TABLE `tb_factura` (
	`id_factura` INT(4) NOT NULL AUTO_INCREMENT,
    `id_cliente` INT(4),
    `id_metodoPago` INT(4),
    `fecha` DATE,
    `cobrado` BOOLEAN DEFAULT FALSE,
    PRIMARY KEY (`id_factura`)
) ENGINE=InnoDB;

CREATE TABLE `tb_metodoPago` (
	`id_metodoPago` INT(4) NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(40),
    `otros_detalles` VARCHAR(40),
    PRIMARY KEY (`id_metodoPago`)
) ENGINE=InnoDB;



CREATE TABLE `tb_empleado` (
    `id_empleado` INT(4) NOT NULL AUTO_INCREMENT,
    `id_usuario` INT(4),
    `hora_entrada` TIME,
    `hora_salida` TIME,
    `horas_semanales` int(4),
    `salario` DOUBLE DEFAULT 0.0,  
    PRIMARY KEY (`id_empleado`)
) ENGINE=InnoDB;

CREATE TABLE `tb_usuario` (
    `id_usuario` INT(4) NOT NULL AUTO_INCREMENT,
    `dni` VARCHAR(9) DEFAULT NULL UNIQUE,
    `nombre` VARCHAR(40) DEFAULT NULL,
    `apellidos` VARCHAR(40) DEFAULT NULL,
    `password` VARCHAR(40) DEFAULT NULL,
    `imagen` VARCHAR(40) DEFAULT NULL,
    `tipo_usuario` VARCHAR(40) DEFAULT NULL,
    `correo` VARCHAR(40) DEFAULT NULL,
    `activo` BOOLEAN DEFAULT TRUE,
    PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB;


CREATE TABLE `tb_cliente` (
    `id_cliente` INT(4) NOT NULL AUTO_INCREMENT,
    `id_usuario` INT(4) NOT NULL,
    `telefono` INT (4) DEFAULT NULL,
    `direccion1` VARCHAR(40) DEFAULT NULL,
    `direccion2` VARCHAR(40) DEFAULT NULL,
    `poblacion`  VARCHAR (60) DEFAULT NULL,
    `codigo_postal` INT(5) DEFAULT NULL,
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

alter table tb_detalleFactura ADD CONSTRAINT FK_tbdetalleFactura_tbPedido
foreign key (id_pedido) references tb_pedido(id_pedido)
on update cascade;

alter table tb_detalleFactura ADD CONSTRAINT FK_tbDetalleFactura_tbFactura
foreign key (id_factura) references tb_factura(id_factura)
on update cascade;

alter table tb_cliente ADD CONSTRAINT FK_tbCliente_tbUsuario
foreign key (id_usuario) references tb_usuario(id_usuario)
on update cascade;

alter table tb_empleado ADD CONSTRAINT FK_tbEmpleado_tbUsuario
foreign key (id_usuario) references tb_usuario(id_usuario)
on update cascade;

alter table tb_estado ADD CONSTRAINT FK_tbEstado_tbEmpleado
foreign key (id_empleado) references tb_empleado(id_empleado)
on update cascade;

alter table tb_factura ADD CONSTRAINT FK_tbFactura_tbMetodoPago
foreign key (id_metodoPago) references tb_metodoPago(id_metodoPago)
on update cascade;

alter table tb_ingredientes ADD CONSTRAINT FK_tbingrediente_tbProducto
foreign key (id_producto) references tb_producto(id_producto)
on update cascade;

alter table tb_factura ADD CONSTRAINT FK_tbFactura_tbCliente
foreign key (id_cliente) references tb_cliente(id_cliente)
on update cascade;



INSERT INTO `tb_tipo`(`nombre`) VALUES ("Pizza");
INSERT INTO `tb_tipo`(`nombre`) VALUES ("Bebida");
INSERT INTO `tb_tipo`(`nombre`) VALUES ("Ingrediente");

INSERT INTO tb_producto (nombre,precio,imagen,id_tipo) VALUES ("Jamon York", 0.50, "jamonyork.png",3),
("Salsa barbacoa", 0.50, "salsabarbacoa.png",3), ("Jamon Serrano", 0.50, "jamonserrano.png",3),
("Atún", 0.50, "atun.png",3), ("Salami", 0.50, "salami.png",3),
("Pimiento verde", 0.50, "pimientoverde.png",3), ("Pimiento rojo", 0.50, "pimientorojo.png",3),
("Aceitunas negras", 0.50, "aceitunasnegras.png",3), ("Aceitunas verdes", 0.50, "aceitunasverdes.png",3),
("Gambas", 0.50, "gambas.png",3), ("Queso cheddar", 0.50, "quesocheddar.png",3),
("Queso cabra", 0.50, "quesocabra.png",3), ("Mozzarella", 0.50, "mozzarella.png",3),
("Frankfurt", 0.50, "frankfurt.png",3), ("Tomate natural", 0.50, "tomatenatural.png",3),
("Pepinillo", 0.50, "pepinillo.png",3), ("Champiñones", 0.50, "champiñones.png",3),
("Kebab", 1.50, "kebab.png",3), ("Ternera", 1.50, "ternera.png",3),
("Pollo", 1.50, "pollo.png",3), ("Salsa blanca", 0.50, "salsablanca.png",3),
("Piña", 0.50, "piña.png",3), ("Bacon", 0.50, "bacon.png",3), ("Cebolla", 0.50, "cebolla.png",3), 
("Queso edam", 0.50, "edam.png",3), ("Salsa picante", 0.50, "salsapicante.png",3),
("Hamburguesa", 0.50, "hamburguesa.png",3);

INSERT INTO tb_ingredientes (`id_producto`) VALUES (1), (2), (3), (4),
(5), (6), (7), (8), (9), (10),(11), (12), (13), (14), (15), (16), 
(17), (18), (19), (20), (21), (22), (23), (24), (25), (26), (27);

INSERT INTO tb_producto(`nombre`, `precio`, `imagen`, id_tipo ) VALUES 
('Pizza Barbacoa', 14.95, 'pbarbacoa.png', 1), ('Pizza 4 quesos', 13.55, 'pqueso.png', 1),
('Pizza Champiñones', 13.55, 'pchampiñones.png', 1), ('Pizza Hawaiana', 14.95, 'phawaiana.png', 1),
('Pizza Atun', 13.55, 'patun.png', 1), ('Pizza Burguer', 13.55, 'pburguer.png', 1),
('Pizza Cebolla', 13.55, 'pcebolla.png', 1), ('Pizza Salami', 13.55, 'psalami.png', 1),
('Pizza Kebab', 13.55, 'pkebab.png', 1), ('Pizza doble Carne', 13.55, 'pdcarne.png', 1),
('Pizza de la Terra', 13.55, 'pterra.png', 1),
('Coca-Cola', 1.8, 'beb', 2),
('Aquarius', 1.6, 'beb', 2);

INSERT INTO tb_pizza(`id_producto`) VALUES (28), (29), (30), (31),(32), (33), (34), (35), (36), (37), (38);

INSERT INTO tb_refresco(`id_producto`) VALUES (39), (40);

-- Barbacoa
INSERT INTO tb_pizzaDetalle(`id_ingrediente`, `id_pizza`) VALUES (2, 1), (19, 1), (20, 1), (23, 1);

-- 4 Quesos
INSERT INTO tb_pizzaDetalle(`id_ingrediente`, `id_pizza`) VALUES (11, 2), (13, 2), (12, 2), (13, 2);

-- Champiñones
INSERT INTO tb_pizzaDetalle(`id_ingrediente`, `id_pizza`) VALUES (1, 3), (17, 3);

-- Hawaiana 
INSERT INTO tb_pizzaDetalle(`id_ingrediente`, `id_pizza`) VALUES (1, 4), (22, 4);

-- Atún
INSERT INTO tb_pizzaDetalle(`id_ingrediente`, `id_pizza`) VALUES (1, 5), (17, 5), (24, 5);

-- Burguer
INSERT INTO tb_pizzaDetalle(`id_ingrediente`, `id_pizza`) VALUES (27, 6), (15, 6), (24, 6), (23, 6);

-- Cebolla
INSERT INTO tb_pizzaDetalle(`id_ingrediente`, `id_pizza`) VALUES (24, 7), (1, 7), (19, 7), (11, 7);

-- Salami
INSERT INTO tb_pizzaDetalle(`id_ingrediente`, `id_pizza`) VALUES (1, 8), (11, 8), (5, 8);

-- Kebab
INSERT INTO tb_pizzaDetalle(`id_ingrediente`, `id_pizza`) VALUES (18, 9), (21, 9), (26, 9), (23, 9);

-- Doble Carne
INSERT INTO tb_pizzaDetalle(`id_ingrediente`, `id_pizza`) VALUES (19, 10), (20, 10), (27, 10);

-- De la Terra
INSERT INTO tb_pizzaDetalle(`id_ingrediente`, `id_pizza`) VALUES (6, 11), (7, 11), (19, 11), (23, 11);



-- Empleados

INSERT INTO tb_usuario(`dni`, `nombre`, `apellidos`, `password`, `imagen`, `tipo_usuario`, `correo` ) VALUES 
('46472595Z', 'Javi', 'Delgado', 'a','jdelgado.jpg', 'admin', 'a');
INSERT INTO tb_empleado(id_usuario, hora_entrada, hora_salida, horas_semanales, salario) 
VALUES (1, '10:00:00', '18:00:00', '40', '1200');

INSERT INTO tb_usuario(`dni`, `nombre`, `apellidos`, `password`, `imagen`, `tipo_usuario`, `correo` ) VALUES 
('21343243A', 'Jose', 'Morales', 'b','jmorales.jpg', 'empleado', 'b');
INSERT INTO tb_empleado(id_usuario, hora_entrada, hora_salida, horas_semanales, salario) 
VALUES (2, '9:00:00', '13:00:00', '20', '1100');

INSERT INTO tb_usuario(`dni`, `nombre`, `apellidos`, `password`, `imagen`, `tipo_usuario`, `correo` ) VALUES 
('87542162P', 'Miquel', 'Gomez', 'c','mgomez.jpg', 'empleado', 'c');
INSERT INTO tb_empleado(id_usuario, hora_entrada, hora_salida, horas_semanales, salario) 
VALUES (3, '13:00:00', '17:00:00', '20', '1100');

INSERT INTO tb_usuario(`dni`, `nombre`, `apellidos`, `password`, `imagen`, `tipo_usuario`, `correo` ) VALUES 
('12345678C', 'David', 'Ramirez', 'd','dramirez.jpg', 'admin', 'd');
INSERT INTO tb_empleado(id_usuario, hora_entrada, hora_salida, horas_semanales, salario) 
VALUES (4, '17:00:00', '21:00:00', '20', '1100');
