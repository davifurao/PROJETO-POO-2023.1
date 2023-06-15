create database banco;
use banco;

create table conta_corrente(
numeroConta INT NOT NULL,
saldo decimal not null,
dataAbertura datetime,
status ENUM('true', 'false'),
constraint id_numero_conta_corrente primary key(numeroConta)
);
/*
Eu não criei a coluna de taxa pois a taxa é fixa e não faz sentido
criar uma coluna com um valor fixo. Resumindo, para a regra de negócio
o que é pertinente manter permanente são os dados referentes ao usuário
*/

create table conta_poupanca(
numeroConta INT NOT NULL,
saldo decimal not null,
dataAbertura datetime,
status ENUM("true","false"),
constraint id_numero_conta_poupanca primary key(numeroConta)
);

/*Para fazer a busca de um registro de transação é só fazer um select que
consulte todas as ocorrencias da conta consultada(corrente ou poupança).
Não faz sentido buscar nas duas pois pode existir um caso onde o id seja real*/


/*Exemplo de consulta: SELECT * FROM registro_transacao WHERE conta_poupanca_id = ?  */
create table registro_transacao(
id int not null,
valor decimal not null,
tipo_transacao ENUM("DEPOSITO","SAQUE","TRANSFERENCIA_CREDITO","TRANSFERENCIA_DEBITO"),
conta_poupanca_id int ,
conta_corrente_id int,
tipo_conta ENUM("CONTA_CORRENTE","CONTA_POUPANCA"),
data datetime,
constraint id_registro_transacao primary key(id)
);