CREATE TABLE endereco(
    id INT NOT NULL AUTO_INCREMENT,
    cep VARCHAR(9)NOT NULL,
    logradouro VARCHAR(100) NOT NULL,
    numero_residencia INT NOT NULL,
    cidade VARCHAR(50),
    estado VARCHAR(20),
    PRIMARY KEY(id)
);