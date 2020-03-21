create schema fetaqui;

use fetaqui;

create table poblacion(
	id_poblacion int not null primary key auto_increment,
    nombre varchar (32) not null
);

create table direccion(
	id_direccion int not null primary key auto_increment,
    direccion varchar(200) not null,
    poblacion int not null,
    foreign key(poblacion) references poblacion(id_poblacion)
		on update cascade
        on delete restrict
);



create table vendedor(
	id_vendedor int not null primary key auto_increment,
    nif varchar(9),/*no es necesaria en caso de que un vendedor no desee vender online, en caso de que lo desee deberá updatear el atributo*/
    email varchar(64) not null,
    password varchar(32) not null,
    foto varchar(100) not null,
    direccion int not null,
    telefono int(9) not null,
    activado tinyint not null default 0,
    fecha_alta timestamp not null,
    venta_online tinyint not null,
	foreign key(direccion) references direccion(id_direccion)
        on update cascade
        on delete restrict
);

create table codigo_act_vendedor(
	id int not null primary key,
    vendedor int not null,
    foreign key (vendedor) references vendedor(id_vendedor)
);


create table post(
	id_post int not null primary key auto_increment,
    texto_post varchar(200) not null,
    autor int not null,
    foreign key(autor) references vendedor(id_vendedor)
        on update cascade
        on delete restrict
);

create table cliente(
	id_cliente int not null primary key auto_increment,
    email varchar(32) not null unique,
    nombre varchar(32) not null,
    apellido varchar(32) not null,
    telefono int (9) unsigned not null , /*unsigned no admite signo, por tanto siempre es positivo*/
    direccion int not null,
    password varchar(32) not null,
    foto varchar(100),
    foreign key(direccion) references direccion(id_direccion)
		on delete cascade
        on update cascade    
);

create table codigo_act_cliente(
	id int not null primary key,
    cliente int not null,
    foreign key (cliente) references cliente(id_cliente)
);

create table valoracion_post(
	id_valoracion_post int not null primary key auto_increment,
    valoracion int not null,
	id_cliente int not null,
    id_post int not null,
    foreign key(id_cliente) references cliente(id_cliente)
		on update cascade,
	foreign key(id_post) references post(id_post)
		on update cascade
);


create table valoracioncv(
	id_valoracioncv int not null primary key auto_increment,
    valoracion int not null,
	id_cliente int not null,
    id_vendedor int not null,    
    foreign key(id_cliente) references cliente(id_cliente)
		on update cascade,
	foreign key(id_vendedor) references vendedor(id_vendedor)
		on update cascade
);

create table valoracionvc(
	id_valoracionvc int not null primary key auto_increment,
    valoracion int not null,
	id_cliente int not null,
    id_vendedor int not null,    
    foreign key(id_cliente) references cliente(id_cliente)
		on update cascade,
	foreign key(id_vendedor) references vendedor(id_vendedor)
		on update cascade
);

create table categoria(
	id_categoria int not null primary key auto_increment,
    nombre varchar(32) not null
);

create table formato(
	id_formato int not null primary key auto_increment,
    nombre varchar(32)
);

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
		on update cascade
);

create table valoracion_producto(
	id_valoracion_producto int not null primary key auto_increment,
    valoracion int not null,
    cliente int not null,
    producto int not null,
    foreign key (cliente) references cliente (id_cliente)
		on update cascade,
	foreign key (producto) references producto(id_producto)
		on update cascade
);

create table pedido(
	id_pedido int not null primary key auto_increment,
    fecha_pedido timestamp not null,
    fecha_entrega timestamp not null,
    cliente int not null,
    destino int not null,
    foreign key (cliente) references cliente(id_cliente)
		on update cascade,
	foreign key (destino) references direccion(id_direccion)
		on update cascade
);

create table detalle_pedido(
	id_pedido int not null,
    id_producto int not null,
    precio_unidad decimal(6,2),
    cantidad int not null,
    dto int,
    primary key(id_pedido,id_producto),
    foreign key (id_pedido) references pedido(id_pedido)
		on update cascade,
	foreign key (id_producto) references producto(id_producto)
		on update cascade
);







