ALTER TABLE consulta ADD CONSTRAINT fk_consulta_paciente FOREIGN KEY(paciente_id) REFERENCES paciente(id);