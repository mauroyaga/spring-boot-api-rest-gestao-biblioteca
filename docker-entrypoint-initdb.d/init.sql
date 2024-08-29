-- Cria a tabela de usuários
CREATE TABLE usuario (
    usuario_id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    data_cadastro DATE NOT NULL DEFAULT CURRENT_DATE,
    telefone VARCHAR(20) NOT NULL
);

-- Cria a tabela de livros
CREATE TABLE livro (
    livro_id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    isbn VARCHAR(20) UNIQUE NOT NULL,
    data_publicacao DATE NOT NULL,
    categoria VARCHAR(50) NOT NULL
);

-- Cria a tabela de empréstimos
CREATE TABLE emprestimo (
    emprestimo_id SERIAL PRIMARY KEY,
    usuario_id INTEGER REFERENCES usuario(usuario_id),
    livro_id INTEGER REFERENCES livro(livro_id),
    data_emprestimo DATE NOT NULL DEFAULT CURRENT_DATE,
    data_devolucao DATE,
    status VARCHAR(20) CHECK (status IN ('EMPRESTADO', 'DEVOLVIDO', 'RESERVADO')) NOT NULL DEFAULT 'EMPRESTADO'
);