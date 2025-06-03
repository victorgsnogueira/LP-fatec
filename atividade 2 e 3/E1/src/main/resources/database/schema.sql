CREATE DATABASE IF NOT EXISTS fatec_e1;
USE fatec_e1;

-- Tabela de Aves
CREATE TABLE IF NOT EXISTS aves (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    idade INT NOT NULL,
    peso DOUBLE NOT NULL,
    envergadura DOUBLE NOT NULL,
    voa BOOLEAN NOT NULL,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de Répteis
CREATE TABLE IF NOT EXISTS repteis (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    idade INT NOT NULL,
    peso DOUBLE NOT NULL,
    tem_escamas BOOLEAN NOT NULL,
    tipo_pele VARCHAR(50) NOT NULL,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de Mamíferos
CREATE TABLE IF NOT EXISTS mamiferos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    idade INT NOT NULL,
    peso DOUBLE NOT NULL,
    gestacao INT NOT NULL,
    amamenta BOOLEAN NOT NULL,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de Aquáticos
CREATE TABLE IF NOT EXISTS aquaticos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    idade INT NOT NULL,
    peso DOUBLE NOT NULL,
    profundidade_maxima DOUBLE NOT NULL,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de Felinos
CREATE TABLE IF NOT EXISTS felinos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    idade INT NOT NULL,
    peso DOUBLE NOT NULL,
    tamanho_garras DOUBLE NOT NULL,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de Bicicletas
CREATE TABLE IF NOT EXISTS bicicletas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(100) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    ano INT NOT NULL,
    peso DOUBLE NOT NULL,
    num_marchas INT NOT NULL,
    tem_freio_disco BOOLEAN NOT NULL,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de Aviões
CREATE TABLE IF NOT EXISTS avioes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(100) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    ano INT NOT NULL,
    peso DOUBLE NOT NULL,
    num_motores INT NOT NULL,
    envergadura DOUBLE NOT NULL,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de Caminhões
CREATE TABLE IF NOT EXISTS caminhoes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(100) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    ano INT NOT NULL,
    peso DOUBLE NOT NULL,
    capacidade_carga DOUBLE NOT NULL,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de Carros
CREATE TABLE IF NOT EXISTS carros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(100) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    ano INT NOT NULL,
    peso DOUBLE NOT NULL,
    num_portas INT NOT NULL,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de Motos
CREATE TABLE IF NOT EXISTS motos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(100) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    ano INT NOT NULL,
    peso DOUBLE NOT NULL,
    cilindrada INT NOT NULL,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
); 