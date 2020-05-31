CREATE DATABASE fetaqui CHARACTER SET utf8 COLLATE utf8_general_ci;


use fetaqui;

create table poblacion(
	id_poblacion int not null primary key auto_increment,
    nombre varchar (32) not null
)DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

create table direccion(
	id_direccion int not null primary key auto_increment,
    direccion varchar(200) not null,
    poblacion int not null,
    foreign key(poblacion) references poblacion(id_poblacion)
		on update cascade
)DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;



create table vendedor(
	id_vendedor int not null primary key auto_increment,
    nif varchar(9) ,
    nombre varchar(32) not null,
    email varchar(64) not null,
    password varchar(32) not null,
    foto varchar(100),
    direccion int,
    telefono varchar(9),
    activado tinyint not null default 0,
    fecha_alta timestamp not null,
    venta_online tinyint not null,
	foreign key(direccion) references direccion(id_direccion)
        on update cascade
        on delete restrict
)DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

create table codigo_act_vendedor(
	id int not null primary key,
    vendedor int not null,
    foreign key (vendedor) references vendedor(id_vendedor)
)DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


create table post(
	id_post int not null primary key auto_increment,
    texto_post varchar(200) not null,
    autor int not null,
    foreign key(autor) references vendedor(id_vendedor)
        on delete cascade
)DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

create table cliente(
	id_cliente int not null primary key auto_increment,
    email varchar(32) not null unique,
    nombre varchar(32) not null,
    apellido varchar(32) not null,
    telefono varchar (9) not null , 
    direccion int,
    password varchar(32) not null,
    foto varchar(100),
    activado tinyint not null default 0,
    foreign key(direccion) references direccion(id_direccion)
        on update cascade    
)DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

create table codigo_act_cliente(
	id int not null primary key,
    cliente int not null,
    foreign key (cliente) references cliente(id_cliente)
)DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

create table valoracion_post(
	id_valoracion_post int not null primary key auto_increment,
    valoracion int not null,
	id_cliente int not null,
    id_post int not null,
    foreign key(id_cliente) references cliente(id_cliente)
		on update cascade,
	foreign key(id_post) references post(id_post)
		on delete cascade
)DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


create table valoracioncv(
	id_valoracioncv int not null primary key auto_increment,
    valoracion int not null,
	id_cliente int not null,
    id_vendedor int not null,    
    foreign key(id_cliente) references cliente(id_cliente)
		on update cascade,
	foreign key(id_vendedor) references vendedor(id_vendedor)
		on delete cascade
)DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

create table valoracionvc(
	id_valoracionvc int not null primary key auto_increment,
    valoracion int not null,
	id_cliente int not null,
    id_vendedor int not null,    
    foreign key(id_cliente) references cliente(id_cliente)
		on delete cascade,
	foreign key(id_vendedor) references vendedor(id_vendedor)
		on delete cascade
)DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

create table categoria(
	id_categoria int not null primary key auto_increment,
    nombre varchar(32) not null
)DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

create table formato(
	id_formato int not null primary key auto_increment,
    nombre varchar(32)
)DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

create table producto(
	id_producto int not null primary key auto_increment,
    nombre varchar(64) not null,
    descripcion varchar(200),
    foto varchar(100),
    precio decimal(6,2) unsigned not null,
    vendedor int not null,
    stock int not null,
    vendido int unsigned,
    categoria int not null,
    formato int not null,
    venta_online tinyint not null,
    foreign key(categoria) references categoria(id_categoria)
		on update cascade,
	foreign key (formato) references formato(id_formato)
		on update cascade,
	foreign key (vendedor) references vendedor(id_vendedor)
		on delete cascade
)DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

create table valoracion_producto(
	id_valoracion_producto int not null primary key auto_increment,
    valoracion int not null,
    cliente int not null,
    producto int not null,
    foreign key (cliente) references cliente (id_cliente)
		on delete cascade,
	foreign key (producto) references producto(id_producto)
		on delete cascade
)DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

create table pedido(
	id_pedido int not null primary key auto_increment,
    fecha_pedido timestamp not null,
    fecha_entrega timestamp not null,
    cliente int not null,
    destino int not null,
    estado varchar(32) not null,
    foreign key (cliente) references cliente(id_cliente)
		on delete cascade,
	foreign key (destino) references direccion(id_direccion)
		on update cascade
)DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

create table detalle_pedido(
	id_detalle int not null primary key auto_increment,
	id_pedido int not null,
    id_producto int not null,
    precio_unidad decimal(6,2),
    cantidad int not null,
    dto int,
    foreign key (id_pedido) references pedido(id_pedido)
		on delete cascade,
	foreign key (id_producto) references producto(id_producto)
		on delete cascade
)DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


insert into poblacion (nombre) values ('Alaró'),('Alcudia'),('Algaida'),('Andratx'),('Ariany'),
('Artá'),('Bañalbufar'),('Binissalem'),('Búger'),('Buñola'),('Calviá'),('Campanet'),('Campos'),('Capdepera'),
('Consell'),('Costitx'),('Deiá'),('Escorca'),('Eporlas'),('Estellencs'),('Felanitx'),('Fronalutx'),('Inca'),
('Lloret de Vistalegre'),('Lloseta'),('Llubí'),('Llucmajor'),('Manacor'),('Mnacor de la Vall'),('Maria de la Salut'),('Montuiri'),('Muro'),
('Petra'),('Sa Pobla'),('Palma'),('Pollença'),('Porreres'),('Puigpunyent'),('Ses Salines'),('Sant Joan'),('Sant Llorenç des Cardassar'),('Santa Eugenia'),
('Santa Maria'),('Santa Margalida'),('Santanyí'),('Selva'),('Sencelles'),('Sineu'),('Sóller'),('Son Servera'),('Valldemossa'),('Villafranca de Bonany');

insert into categoria (nombre) values ('Frutas'),('Verduras'),('Carnes'),('Pescados'),('Conservas'),('Pan'),('Leches'),('Cafés'),('Espirituosas'),
										('Aguas'),('Refrescos'),('Embutidos'),('Dulce/Pasteleria'),('Otros');

insert into formato (nombre) values ('100 GR.'),('200 GR.'),('400 GR.'),('500 GR.'),('KG'),('LATA'),('BOTELLA'),('BARRA'),('UNIDAD');

insert into cliente (email, nombre, apellido, telefono, password, foto, activado) 
	values ('cliente@gmail.com', 'Ramon', 'Horrach', 630513222,'Cliente!1986','FotoPorDefecto',1);

