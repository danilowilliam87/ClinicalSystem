CREATE TABLE medico(
    id INT  AUTO_INCREMENT,
    nome VARCHAR(100)NOT NULL,
    crm VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    senha VARCHAR(100) NOT NULL,
    especialidade_id INT NOT NULL,
    PRIMARY KEY(id)
);