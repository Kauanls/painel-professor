CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL
);

INSERT INTO usuarios (nome, email, senha)
VALUES ('Administrador', 'admin@email.com', '3412');

CREATE TABLE IF NOT EXISTS alunos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    numero_chamada INT NOT NULL UNIQUE,
    faltas INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS configuracoes (
    id INT PRIMARY KEY,
    limite_faltas INT NOT NULL
);

INSERT INTO configuracoes (id, limite_faltas)
VALUES (1, 5);

CREATE TABLE IF NOT EXISTS controle_bimestre (
    id INT PRIMARY KEY,
    bimestre_atual INT NOT NULL,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO controle_bimestre (id, bimestre_atual)
VALUES (1, 1);

CREATE TABLE IF NOT EXISTS historico_bimestres (
    id INT AUTO_INCREMENT PRIMARY KEY,
    aluno_id INT NOT NULL,
    bimestre INT NOT NULL,
    faltas INT NOT NULL,
    data_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_hb_aluno FOREIGN KEY (aluno_id)
        REFERENCES alunos(id) ON DELETE CASCADE
);

CREATE INDEX idx_hb_aluno ON historico_bimestres (aluno_id);
CREATE INDEX idx_hb_bimestre ON historico_bimestres (bimestre);
