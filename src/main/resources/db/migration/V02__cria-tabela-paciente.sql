CREATE TABLE paciente(
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    data_nascimento DATE NOT NULL,
    endereco_id INT NOT NULL,
    convenio_id INT NOT NULL,
    PRIMARY KEY(id)
);