create database banco;
use banco;



create table cliente(
id int not null auto_increment,
cpf varchar(30),
primary key(id)
);

create table registro_conta(
id int not null auto_increment,
cpf varchar(30),
numero_conta varchar(50) not null,
saldo FLOAT(7,2),/*armazena sete algarismos, nos quais dois sao decimais*/
tipo varchar(20),
status boolean,
data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
primary key(id)
);

create table conta_corrente(
id int not null,
numero_conta VARCHAR(50) not NULL,
saldo FLOAT(7,2),
status boolean,
data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
primary key(id)
);

create table conta_poupanca(
id int not null,
numero_conta VARCHAR(50) not NULL,
saldo FLOAT(7,2),
status boolean,
data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
primary key(id)
);

CREATE TABLE registro_transacao(
id int not null,
valor float,
tipo_conta varchar(20),
tipo_transacao varchar(20),
data TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
primary key(id)
);


