create database banco;
use banco;


create table conta(
id int not null auto_increment,
numero_conta int not null,
saldo FLOAT(7,2),/*armazena sete algarismos, nos quais dois sao decimais*/
status boolean,
primary key(id)
);

create table registro_transacao(
id int not null auto_increment,
numero_conta int not null,
tipo varchar(50),
data_hora_transacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY (id)
);