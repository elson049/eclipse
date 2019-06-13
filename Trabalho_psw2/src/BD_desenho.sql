/*
create database Painel_desenho;
use Painel_desenho;

create table Desenhos(
ID_Desenho integer(4) not null auto_increment,
nome varchar(20) not null,
primary key(ID_Desenho)
);

create table Ponto(
ID_Ponto integer(11) not null auto_increment,
X int not null,
Y int not null,
ID_desenho integer not null,
primary key(ID_Ponto),
constraint fk_Ponto foreign key(ID_Ponto) references Desenhos(ID_desenho)
);

create table Linha(
ID_Linha integer(11) not null auto_increment,
X_a integer not null,
Y_a integer not null,
X_b integer not null,
Y_b integer not null,
ID_desenho integer not null,
primary key(ID_Linha),
constraint fk_Linha foreign key(ID_Linha) references Desenhos(ID_desenho)
);

create table Circulo(
ID_Circulo integer(11) not null auto_increment,
X_a integer not null,
Y_a integer not null,
X_b integer not null,
Y_b integer not null,
ID_desenho integer not null,
primary key(ID_Circulo),
constraint fk_Circulo foreign key(ID_Circulo) references Desenhos(ID_desenho)
);

create table Retangulo(
ID_Retangulo integer(11) not null auto_increment,
X_a integer not null,
Y_a integer not null,
X_b integer not null,
Y_b integer not null,
ID_desenho integer not null,
primary key(ID_Retangulo),
constraint fk_Retangulo foreign key(ID_Retangulo) references Desenhos(ID_desenho)
);
insert into Desenhos(nome) values ('desenho01');
insert into Ponto (x,y,ID_desenho) values (3,5,1);
insert into Linha (X_a,Y_a,X_b,Y_b,ID_desenho) values (4,1,3,5,1);
insert into Circulo (X_a,Y_a,X_b,Y_b,ID_desenho) values (6,9,9,2,1);
insert into Retangulo (X_a,Y_a,X_b,Y_b,ID_desenho) values (4,2,2,7,1);
*/
#select* from Retangulo;
SELECT ID_Ponto, x, y FROM Ponto WHERE ID_desenho = '2';