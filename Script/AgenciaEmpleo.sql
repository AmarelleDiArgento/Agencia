 CREATE USER IF NOT EXISTS 'AgenciaBd'@'localhost' IDENTIFIED BY 'AgenciaBd';
 GRANT ALL PRIVILEGES ON * . * TO 'AgenciaBd'@'localhost';
 FLUSH PRIVILEGES;

create database Agencia;
use Agencia;
/*-------------------------------*/
create table TbProfesion(
IdProfesion int primary key auto_increment,
NomProf varchar(50)
);

insert into TbProfesion(NomProf) values('Abogado');
insert into TbProfesion(NomProf) values('Ingeniero de Sistemas');
insert into TbProfesion(NomProf) values('Administracion de Empresas');
insert into TbProfesion(NomProf) values('Psicologia');

/*Ver profesiones registradas disponible para combo*/
create procedure VerProfesion() select * from TbProfesion;
/*------------------------*/
create table TbAgencia(
Nit int primary key,
NombreAg varchar (50),
TelefonoAg varchar(16),
Direccion varchar(50)
);
 /*Ver agencias registradas disponible para combo*/
create procedure VerAgencias() select * from TbAgencia;
/*Insertar una Agencia*/
create procedure InsAgencia(in NitNew int,in NombreAgNew varchar(50),in TelefonoNew varchar(16),in DireccionNew varchar(50)) insert into TbAgencia(Nit,NombreAg,TelefonoAg,Direccion) values(NitNew,NombreAgNew,TelefonoNew,DireccionNew);
/*---------------------------*/
create table TbAspirante(
NumCedula varchar(12) primary key,
NombreAsp varchar(50),
Edad int,
Genero enum('Masculino','Femenino'),
CodigoProfesion int,
CodigoAgencia int,
constraint TbAspirante_TbProfesion Foreign Key (CodigoProfesion) references TbProfesion (IdProfesion) on delete cascade on update cascade,
constraint TbAspirante_TbAgencia Foreign Key (CodigoAgencia) references TbAgencia (Nit) on delete cascade on update cascade
);
/**Ver Aspirantes Registrados**/
create procedure VerAsp() select NumCedula,NombreAsp,Edad,Genero,TbProfesion.NomProf,TbAgencia.NombreAg from TbAspirante inner join TbProfesion on TbAspirante.CodigoProfesion=TbProfesion.IdProfesion inner join TbAgencia on TbAspirante.CodigoAgencia=TbAgencia.Nit;
/**Insertar nuevo Aspirante**/
create procedure InsAspNew(in NumCedulaNew varchar(12),in NombreAspNew varchar(50),in EdadNew int ,in GeneroNew enum('Masculino','Femenino'),in CodigoProfesionNew int,in CodigoAgenciaNew int) insert into TbAspirante(NumCedula,NombreAsp,Edad,Genero,CodigoProfesion,CodigoAgencia) values(NumCedulaNew,NombreAspNew,EdadNew,GeneroNew,CodigoProfesionNew,CodigoAgenciaNew);

/*--------------------------*/
create table TbOFertas(
IdOfer int auto_increment primary key,
NombreOfer varchar(50),
Descripcion text,
fechaInicio date,
FechaFin date
);
/**Ver ofertas**/
create procedure VerOfertas() select * from TbOFertas;
/**Insertar ofertas**/
create procedure InsOfertaNew(in NombreOferNew varchar(50), in DescripcionNew text, in fechaInicioNew date, in FechaFinNew date)insert into TbOFertas(NombreOfer,Descripcion,fechaInicio,FechaFin) values(NombreOferNew,DescripcionNew,fechaInicioNew,FechaFinNew);

/*------------------------------*/
create table TbEmpleabilidad(
Id int auto_increment primary key,
IdAsp varchar(12),
IdOferta int,
Fecha date,
constraint TbEmpleabilidad_TbAspirante Foreign Key (IdAsp) references TbAspirante (NumCedula) on delete cascade on update cascade,
constraint TbEmpleabilidad_TbOFertas Foreign Key (IdOferta) references TbOFertas (IdOfer) on delete cascade on update cascade
);
/***Ver Empleabilidad*/
create procedure ListadoEmplea()Select Id,IdAsp,NombreAsp,IdOferta,Fecha from TbEmpleabilidad inner join TbAspirante on TbEmpleabilidad.IdAsp=TbAspirante.NumCedula;
/**insertar empleabilidad*/
create procedure InsEmplea(in IdAspNew varchar(12),in IdOfertaNew int, in FechaNew date)
insert into TbEmpleabilidad(IdAsp,IdOferta,Fecha) values(IdAspNew,IdOfertaNew,FechaNew);

/*-----------------------------------*/
create table Sesion(
ID int auto_increment primary key,
Usuario varchar (20),
Contraseña varchar(20),
ContraseñaRes varchar(20),
TipoUsuario enum('Agencia','Aspirante')
);
/**Creacion usuaior**/
create trigger CreaUsuarioAsp after insert on TbAspirante
for each row
insert into Sesion(Usuario,Contraseña,ContraseñaRes,TipoUsuario)
values(new.NumCedula,new.NombreAsp,new.NombreAsp,'Aspirante');
/******/
create trigger CreaUsuarioAge after insert on TbAgencia
for each row
insert into Sesion(Usuario,Contraseña,ContraseñaRes,TipoUsuario)
values(new.Nit,new.NombreAg,new.NombreAg,'Agencia');
/**Verificacion Sesion**/
create procedure InicioSesion(in DocumentoIns varchar(20),in ContraseñaIns varchar(20)) select * from Sesion where Usuario=DocumentoIns and Contraseña=ContraseñaIns;
create procedure verificacion(in DocumentoPS varchar(20)) select TipoUsuario from Sesion where Usuario=DocumentoPS;
/*------------------------------------------------------------------------------------*/
/**Mostrar listado de Aspirantes por letra inicial del nombre**/
create procedure VerAspSegunLetra(in letra char) select * from TbAspirante where NombreAsp regexp letra;

/****/
create procedure ReporGenero(in GeneroNew enum('Masculino','Femenino')) select NumCedula,NombreAsp,Edad,Genero,TbProfesion.NomProf,TbAgencia.NombreAg from TbAspirante inner join TbProfesion on TbAspirante.CodigoProfesion=TbProfesion.IdProfesion inner join TbAgencia on TbAspirante.CodigoAgencia=TbAgencia.Nit where Genero=GeneroNew; 

create procedure ReporProfesion(in  ProfesionNew int) select NumCedula,NombreAsp,Edad,Genero,TbProfesion.NomProf,TbAgencia.NombreAg from TbAspirante inner join TbProfesion on TbAspirante.CodigoProfesion=TbProfesion.IdProfesion inner join TbAgencia on TbAspirante.CodigoAgencia=TbAgencia.Nit where CodigoProfesion=ProfesionNew; 

create procedure AspMayor() select NumCedula,NombreAsp,Edad,Genero,TbProfesion.NomProf,TbAgencia.NombreAg from TbAspirante inner join TbProfesion on TbAspirante.CodigoProfesion=TbProfesion.IdProfesion inner join TbAgencia on TbAspirante.CodigoAgencia=TbAgencia.Nit where Edad=(select max(Edad) from TbAspirante);

