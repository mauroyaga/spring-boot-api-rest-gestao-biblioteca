-- Cria a tabela de usuários
CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY, -- Gera automaticamente um valor único para cada registro
    nome VARCHAR(100) NOT NULL, -- Nome do usuário (máximo 100 caracteres)
    email VARCHAR(100) UNIQUE NOT NULL, -- E-mail do usuário (máximo 100 caracteres, deve ser único)
    data_cadastro DATE NOT NULL DEFAULT CURRENT_DATE, -- Data de cadastro do usuário (data atual por padrão)
    telefone VARCHAR(20) NOT NULL -- Número de telefone do usuário (máximo 20 caracteres)
);

-- Cria a tabela de livros
CREATE TABLE livros (
    id SERIAL PRIMARY KEY, -- Gera automaticamente um valor único para cada registro
    titulo VARCHAR(255) NOT NULL, -- Título do livro (máximo 255 caracteres)
    autor VARCHAR(100) NOT NULL, -- Autor do livro (máximo 100 caracteres)
    isbn VARCHAR(20) UNIQUE NOT NULL, -- Número ISBN do livro (máximo 20 caracteres, deve ser único)
    data_publicacao DATE NOT NULL, -- Data de publicação do livro
    categoria VARCHAR(50) NOT NULL -- Categoria do livro (máximo 50 caracteres)
);

-- Cria a tabela de empréstimos
CREATE TABLE emprestimos (
    id SERIAL PRIMARY KEY, -- Gera automaticamente um valor único para cada registro
    usuario_id INTEGER REFERENCES usuarios(id), -- Referência à tabela de usuários (chave estrangeira)
    livro_id INTEGER REFERENCES livros(id), -- Referência à tabela de livros (chave estrangeira)
    data_emprestimo DATE NOT NULL DEFAULT CURRENT_DATE, -- Data do empréstimo
    data_devolucao DATE, -- Data de devolução (pode ser nulo se o livro ainda não foi devolvido)
    status VARCHAR(20) CHECK (status IN ('EMPRESTADO', 'DEVOLVIDO', 'RESERVADO')) NOT NULL DEFAULT 'EMPRESTADO'
); -- Status do empréstimo (pode ser "EMPRESTADO", "DEVOLVIDO" ou "RESERVADO")