
CREATE DATABASE SorelleESG_DB;

USE SorelleESG_DB;

CREATE TABLE Usuarios (
    ID INT IDENTIY_INSERT(1,1) PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL,
    Email VARCHAR(100) UNIQUE NOT NULL,
    Senha VARCHAR(255) NOT NULL, -- Armazenará o hash da senha
    DataCadastro DATETIME DEFAULT GETDATE()
);


CREATE TABLE Produtos (
    ID INT identidade(1,1) PRIMARY KEY,
    Nome VARCHAR(100) NOT NULL,
    Descricao VARCHAR(255),
    Preco DECIMAL(10,2) NOT NULL,
    Qtd_Arvores_Impacto INT NOT NULL, 
    Tag_Eco VARCHAR(50)
);


CREATE TABLE Certificados (
    ID INT IDENTITY(1,1) PRIMARY KEY,
    Usuario_ID INT FOREIGN KEY REFERENCES Usuarios(ID),
    Codigo_Diploma VARCHAR(50) UNIQUE NOT NULL,
    Total_Arvores_Plantadas INT NOT NULL,
    Valor_Total_Compra DECIMAL(10,2) NOT NULL,
    Data_Emissao DATETIME DEFAULT GETDATE()
);
GO

INSERT INTO Produtos (Nome, Descricao, Preco, Qtd_Arvores_Impacto, Tag_Eco) VALUES
('Sérum Açaí', 'Revitalização profunda com bioativos puros da floresta Amazônica.', 89.90, 1, 'Vegano'),
('Creme Castanha', 'Nutrição intensa e hidratação prolongada para a pele.', 55.90, 3, 'Reciclado'),
('Óleo Andiroba', 'Puro óleo essencial com alto poder regenerador natural.', 120.00, 1, 'Comunidade'),
('Kit Ritual da Mata', 'A experiência completa de spa sustentável e luxuoso em casa.', 210.50, 3, 'Impacto Triplo');
GO