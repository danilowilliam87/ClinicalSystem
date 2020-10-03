CREATE TABLE dependente(
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    telefone VARCHAR(50),
    cpf VARCHAR(14),
    endereco_id INT NOT NULL,
    paciente_id INT NOT NULL,
    PRIMARY KEY(id)
);