SELECT * FROM clientes;
select * from usuarios;

SELECT * CASE 
WHEN status THEN 'ativo' ELSE 'inativo' END AS status
FROM clientes
ORDER BY status ASC;
		
		
--- Excluir -------------------
drop table audiencia;
drop table processos;
drop table clientes;	
drop table usuarios;




--- Inserir Usuario -------------
INSERT INTO usuarios (nome_completo, email, senha)
VALUES 
('Maria Silva', 'maria@email.com', 'p@ssw0rd'),
('Pedro Fernandes', 'pedro@email.com', 'sPwd123!'),
('Ana Santos', 'ana@email.com', 'anapwd23'),
('Rafaela Lima', 'rafaela@email.com', 'Rafa#pwd'),
('Lucas Oliveira', 'lucas@email.com', 'lucasPwd'),
('Carla Souza', 'carla@email.com', 'cSouza67'),
('Gustavo Pereira', 'gustavo@email.com', 'GusPwd78'),
('Mariana Costa', 'mariana@email.com', 'mariPwd'),
('Fernando Rodrigues', 'fernando@email.com', 'FerPwd12'),
('Juliana Pereira', 'juliana@email.com', 'juPwd#12');


--- Inserir Cliente --------------
INSERT INTO clientes (nome_completo, cpf_ou_cnpj, data_nascimento, rua, numero, bairro, cidade, uf, cep, 
		pais, telefone, email, status, complemento)
VALUES 
('João Oliveira', '98765432109', '1990-04-20', 'Rua das Flores', '50', 'Jardim Botânico', 'Rio de Janeiro', 'RJ', '22041010', 
		'Brasil', '21987654321', 'joao@email.com', true, 'Casa 2'),		
('Ana Santos', '45678901234', '1988-12-10', 'Rua da Praia', '123', 'Beira Mar', 'Fortaleza', 'CE', '60165005', 
		'Brasil', '8587654321', 'ana@email.com', true, 'Bloco B'), 	
('Pedro Pereira', '23456789012', '1975-09-28', 'Avenida Central', '300', 'Centro', 'Recife', 'PE', '50070400', 
		'Brasil', '81987654321', 'pedro@email.com', false, 'Sala 501'),	
('Luiza Souza', '34567890123', '1995-03-18', 'Rua dos Artistas', '75', 'Boêmia', 'Salvador', 'BA', '40070320', 
		'Brasil', '71987654321', 'luiza@email.com', true, 'Apto 3A'),
('Rafaela Ferreira', '56789012345', '1982-06-25', 'Alameda das Palmeiras', '200', 'Parque das Árvores', 'Brasília', 'DF', '70070900', 
		'Brasil', '61987654321', 'rafaela@email.com', true, 'Lote 15'),	
('Carlos Oliveira', '67890123456', '1978-11-30', 'Avenida do Sol', '40', 'Solar', 'Natal', 'RN', '59064100', 
		'Brasil', '84987654321', 'carlos@email.com', false, 'Apartamento 101'),	
('Juliana Costa', '89012345678', '1998-08-05', 'Rua das Águas', '15', 'Litorânea', 'Florianópolis', 'SC', '88015100', 
		'Brasil', '48987654321', 'juliana@email.com', true, 'Casa 7'),	
('Miguel Fernandes', '01234567890', '1989-02-14', 'Alameda dos Ventos', '500', 'Vento Bom', 'Porto Alegre', 'RS', '90040120', 
		'Brasil', '51987654321', 'miguel@email.com', true, 'Bloco C'),
('Amanda Cardoso', '78901234567', '1980-07-03', 'Rua da Montanha', '30', 'Monte Verde', 'Curitiba', 'PR', '80010000', 
		'Brasil', '41987654321', 'amanda@email.com', false, 'Casa 12'),	
('José Rodrigues', '90123456789', '1973-10-22', 'Avenida das Estrelas', '1000', 'Estelar', 'Manaus', 'AM', '69050030', 
		'Brasil', '92987654321', 'jose@email.com', true, 'Apartamento 202'),
('Fernanda Lima', '12345098765', '1992-12-07', 'Rua das Margaridas', '80', 'Jardim Primavera', 'Goiânia', 'GO', '74000000', 
		'Brasil', '62987654321', 'fernanda@email.com', true, 'Casa 5'),
('Gustavo Ribeiro', '23456777012', '1987-05-19', 'Alameda dos Girassóis', '25', 'Floral', 'João Pessoa', 'PB', '58035000', 
		'Brasil', '83987654321', 'gustavo@email.com', false, 'Bloco D'),
('Larissa Carvalho', '34568890123', '1984-09-11', 'Avenida do Mar', '700', 'Marítimo', 'Vitória', 'ES', '29050330', 
		'Brasil', '27987654321', 'larissa@email.com', true, 'Casa 20'), 	
('Roberto Silva', '45678901244', '1996-06-28', 'Rua das Pedras', '10', 'Pérola Negra', 'Belém', 'PA', '66000000', 
		'Brasil', '91987654321', 'roberto@email.com', false, 'Apartamento 505'),	
('Camila Oliveira', '56780012345', '1977-03-15', 'Alameda das Flores', '150', 'Floral', 'Maceió', 'AL', '57000000', 
		'Brasil', '82987654321', 'camila@email.com', true, 'Casa 3'),	
('Diego Almeida', '67890123455', '1991-11-02', 'Rua do Bosque', '20', 'Bosque Feliz', 'Campinas', 'SP', '13000000', 
		'Brasil', '19987654321', 'diego@email.com', false, 'Apto 102'),
('Isabela Pereira', '89012245678', '1986-04-25', 'Avenida do Céu', '300', 'Celestial', 'São Luís', 'MA', '65000000', 
		'Brasil', '98987654321', 'isabela@email.com', true, 'Casa 8'),	
('Lucas Fernandes', '90023456789', '1974-08-12', 'Rua das Estrelas', '50', 'Estelar', 'Cuiabá', 'MT', '78000000', 
		'Brasil', '65987654321', 'lucas@email.com', true, 'Bloco A'),	
('Tatiana Costa', '12245678901', '1993-02-09', 'Alameda das Árvores', '400', 'Arbórea', 'Teresina', 'PI', '64000000', 
		'Brasil', '86987654321', 'tatiana@email.com', false, 'Casa 10'),
('Paulo Santos', '23456789912', '1983-05-14', 'Avenida da Praia', '250', 'Beira Mar', 'Natal', 'RN', '59000000', 
		'Brasil', '84987654321', 'paulo@email.com', true, 'Apartamento 303');



