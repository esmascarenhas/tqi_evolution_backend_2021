CREATE TABLE cliente ( id INT NOT NULL AUTO_INCREMENT,
 nome VARCHAR(120) NOT NULL,
 email VARCHAR(80) NOT NULL,
 cpf VARCHAR(12) NOT NULL,
 rg VARCHAR(14) NOT NULL,
 renda DOUBLE UNSIGNED NULL,
 senha VARCHAR(60) NOT NULL,

 endereco_logradouro VARCHAR(255) NOT NULL,
 endereco_numero INT NOT NULL,
 endereco_complemento VARCHAR(255),
 endereco_bairro VARCHAR(80) NOT NULL,
 endereco_cep CHAR(11) NOT NULL,
 endereco_cidade VARCHAR(80) NOT NULL,
 endereco_estado VARCHAR(80) NOT NULL,
 endereco_pais VARCHAR(80) NOT NULL,

  PRIMARY KEY(id) );




CREATE TABLE emprestimo ( id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
 valor_emprestimo DOUBLE UNSIGNED NULL,
 quant_parcelas INT NOT NULL,
 data_primeira_parcela datetime NULL,
 status varchar(20) not null,
 cliente_id INT NOT NULL,
 PRIMARY KEY(id) );

 alter table emprestimo add constraint fk_emprestimo_cliente
 foreign key (cliente_id) references cliente (id);


