CREATE TABLE consulta(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    medico_id INT NOT NULL,
    paciente_id INT NOT NULL,
    funcionario_id INT NOT NULL,
    valor float NOT NULL,
    forma_pagamento VARCHAR(30),
    informacoes_adicionais BLOB,
    situacao VARCHAR(30) NOT NULL,
    data_marcacao DATE NOT NULL,
    data_consulta DATE NOT NULL);