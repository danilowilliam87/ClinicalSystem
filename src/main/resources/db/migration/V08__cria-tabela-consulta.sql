CREATE TABLE consulta(
    id INT NOT NULL AUTO_INCREMENT,
    medico_id INT NOT NULL,
    paciente_id INT,
    dependente_id INT,
    funcionario_id INT NOT NULL,
    valor DOUBLE(10,2),
    forma_pagamento VARCHAR(30),
    informacoes_adicionais BLOB,
    situacao VARCHAR(30) NOT NULL,
    data_marcacao DATE NOT NULL,
    data_consulta DATE NOT NULL,
    PRIMARY KEY(id)
    );