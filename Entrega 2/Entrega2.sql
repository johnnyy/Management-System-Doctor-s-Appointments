-- Entrega 2
-- Trabalho: Sistema de gerenciamentos de consultas médicas
-- Equipe: Caio César Silva Diogenes, Johnny Marcos Silva Soares, José Robertty de Freitas Costa	

﻿-- criacao das tabelas

CREATE TABLE medico (nome VARCHAR(100) NOT NULL, salario DECIMAL(10, 2), carga_horaria INT NOT NULL, cpf CHAR(11) NOT NULL, crm CHAR(11) NOT NULL, logradouro VARCHAR(100) NOT NULL, cep CHAR(8) NOT NULL, cidade VARCHAR(100), PRIMARY KEY(cpf));

CREATE TABLE telefone_med (fk_cpf CHAR(11) NOT NULL, telefone VARCHAR(16) NOT NULL, PRIMARY KEY(fk_cpf, telefone), FOREIGN KEY(fk_cpf) REFERENCES medico(cpf)); 

CREATE TABLE especialidade (fk_cpf CHAR(11) NOT NULL, especialidade VARCHAR(50) NOT NULL, PRIMARY KEY(fk_cpf, especialidade), FOREIGN KEY(fk_cpf) REFERENCES medico(cpf)); 

CREATE TABLE enfermeiro (nome VARCHAR(100) NOT NULL, salario DECIMAL(10, 2), carga_horaria INT NOT NULL, cpf CHAR(11) NOT NULL, coren CHAR(11) NOT NULL, formacao VARCHAR(100), logradouro VARCHAR(100) NOT NULL, cep CHAR(8) NOT NULL, cidade VARCHAR(100), PRIMARY KEY(cpf));

CREATE TABLE telefone_enf (fk_cpf CHAR(11) NOT NULL, telefone VARCHAR(16) NOT NULL, PRIMARY KEY(fk_cpf, telefone), FOREIGN KEY(fk_cpf) REFERENCES enfermeiro(cpf)); 

CREATE TABLE paciente (nome VARCHAR(100) NOT NULL, idade INT, cpf CHAR(11) NOT NULL, logradouro VARCHAR(100) NOT NULL, cep CHAR(8) NOT NULL, cidade VARCHAR(100), Data_nasc DATE, PRIMARY KEY(cpf));

CREATE TABLE telefone_pac (fk_cpf CHAR(11) NOT NULL, telefone VARCHAR(16) NOT NULL, PRIMARY KEY(fk_cpf, telefone), FOREIGN KEY(fk_cpf) REFERENCES paciente(cpf)); 

CREATE TABLE prescricao_medica(id SERIAL, data_atendimento DATE NOT NULL, horario TIME NOT NULL, descricao_diagnostico VARCHAR(10000), cpf_medico CHAR(11) NOT NULL, cpf_paciente CHAR(11) NOT NULL, PRIMARY KEY(id), FOREIGN KEY(cpf_paciente) REFERENCES paciente(cpf), FOREIGN KEY(cpf_medico) REFERENCES medico(cpf)); 

CREATE TABLE medicamento(id_remedio SERIAL, nome VARCHAR(100) NOT NULL, tarja_preta BOOLEAN NOT NULL, tipo_remedio VARCHAR(15), periodicidade INT NOT NULL, quant_dias INT NOT NULL, dosagem INT NOT NULL, cpf_enfermeiro CHAR(11), id_prescricao INT NOT NULL, PRIMARY KEY(id_remedio), FOREIGN KEY(cpf_enfermeiro) REFERENCES enfermeiro(cpf), FOREIGN KEY(id_prescricao) REFERENCES prescricao_medica(id), CONSTRAINT ck_tipo_remedio CHECK (tipo_remedio::text = ANY (ARRAY['USO HOSPITALAR'::character varying, 'USO DOMICILIAR'::character varying]::text[])));


--inserir
INSERT INTO medico values('Francisco Caio',10000.00,160,'00000000000','12345632341','Rua dos Monólitos','63940000','Quixadá'),('José Robson',7000.00,160,'00000000001','12345634567','Rua da Galinha Choca','63940000','Quixadá'),
 ('João das Neves',15000.00,176,'00000000002','45246435213','Avenida Beira Mar','60125030','Fortaleza'),
('Danielle Targerian',15000.00,176,'00000000003','34678534895','Avenida Santos Dumont','60125030','Fortaleza'),
('Marcos Junior',9000.00,160,'00000000004','98976598451','Rua da Praça do Leão','63940000','Quixadá'),
('Diemisson Lanister',11000.00,160,'00000000005','87324534565','Rua do Duarte','63940000','Quixadá'),
('Cecilia Lenister',12000.00,160,'00000000006','87324534546','Rua do Duarte','63940000','Quixadá'),
('Roberto Almeida',9000.00,176,'00000000007','34324576543','Rua Rodrigues Junior','63940000','Quixadá'),
('Mateus Lopes',12000.00,176,'00000000008','34324545457','Avenida Pláscido Castelo','63940000','Quixadá'),
('Ronaldo Adriano',17000.00,176,'00000000009','34678578765','Avenida Santos Dumont','60125030','Fortaleza'),
('Francisco Helder',12000.00,140,'00000000010','48598445643','Rua da UFC','63940000','Quixada'),
('Elvis Miguel',13000.00,160,'00000000011','78634512345','Rua Basilio Pinto','63940000','Quixada'),
('Livia Almada',20000.00,150,'00000000012','45623443565','Rua do Pinheiro','63940000','Quixada'),
('Joel Ramiro',12000.00,150,'00000000013','34523448975','Rua Raul Nogueira','62940000','Morada Nova'),
('Robertinha Dutra',12000.00,148,'00000000014','87634567543','Rua das Oliveiras','60940000','Fortaleza'),
('Ticiana Coelho',15000.00,160,'00000000015','84623498094','Rua das Oliveiras','60434333','Fortaleza'),
('Arthur Callado',8000.00,150,'00000000016','34564331256','Avenida da Faculdade','78493456','Fortaleza'),
('Régis Pires', 20000.00, 150,'00000000017','45645656543','Rua do Povo','72903000','Quixeramobim'),
('Andi Store',15000.00, 130,'00000000018','12346587945','Rua do Banabs','62904444','Banabuiu'),
('Jose Ronaldo',13000,120,'00000000019','87434543565','Rua Francisco Duarte','62944999','Quixada');


INSERT INTO telefone_med values('00000000000','8745654345'),('00000000000','2345434543'),('00000000001','9343456765'),('00000000002','9834546765'),('00000000003','8394545656'),('00000000004','4345434543'),('00000000005','8945439095'),('00000000006','9832394839'),('00000000007','8349679654'),('00000000008','3454545654'),('00000000009','3454598495'),('00000000010','3493945493'),('00000000011','9394934857'),('00000000012','9483849594'),('00000000013','9304345459'),('00000000014','8394594859'),('00000000015','9304938456'),('00000000016','3948594859'),('00000000017','9304950695'),('00000000018','9304930493'),('00000000019','3940594854');


INSERT INTO especialidade values('00000000000','Acupuntura'),('00000000001','Anestesiologia'),('00000000002','Angiologia'),('00000000003','Cancerologia'),('00000000004','Cardiologia'),('00000000005','Cirurgia Cardiovascular'),('00000000006','Coloproctologia'),('00000000007','Dermatologia'),('00000000008','Gastroenterologia'),('00000000009','Genetica medica'),('00000000010','Endoscopia'),('00000000011','Geriatria'),('00000000012','Homeopatia'),('00000000013','Infectologia'),('00000000014','Mastologia'),('00000000015','Medicina de Emergência'),('00000000016','Nefrologia'),('00000000017','Neurologia'),('00000000018','Patologia'),('00000000019','Urologia');


INSERT INTO enfermeiro values('Rubens Ferreira',5000.00,160,'00000000020','13485942392','Saude da Mulher','Rua dos Monólitos','63940000','Quixadá'),
('Jardel Oliveira',7000.00,160,'00000000021','49304958245','Saúde da Mulher','Rua da Galinha Choca','63940000','Quixadá'),
('Marcos Antonio',4000.00,176,'00000000022','4928394855','Saude da Familia','Avenida Beira Mar','60125030','Fortaleza'),
('Tania Maria',5500.00,176,'00000000023','49304920394','Saude da Familia','Avenida Santos Dumont','60125030','Fortaleza'),
('Jose Antonio',3200.00,100,'00000000024','98974342312','Saude do Homem','Rua da Praça do Leão','63940000','Quixadá'), 
('Paulo de Tarso',8000.00,160,'00000000025','87324431565','Saude do Homem','Rua do Duarte','63940000','Quixadá'),
('Vivianne Menezes',8000.00,160,'00000000026','87324545231','Neurologia','Rua do Duarte','63940000','Quixadá'),
('Raphaella Fernandes',3000.00,176,'00000000027','94952349502','Neurologia','Rua Rodrigues Junior','63940000','Quixadá'),
('Leticia Saraiva',7000.00,176,'00000000028','34939492034','Neurologia','Avenida Pláscido Castelo','63940000','Quixadá'),
('Emanuel Coutinho',7000.00,176,'00000000029','34592950324','Neurologia','Avenida Santos Dumont','60125030','Fortaleza'),
('Camila Stefany',8000.00,140,'00000000030','41348445643',' Saude Publica','Rua da UFC','63940000','Quixada'),
('Beatriz Silva',5000.00,160,'00000000031','34059695041','Sexologia Humana','Rua Basilio Pinto','63940000','Quixada'),
('João Mateus',4000.00,150,'00000000032','34590095465','Sexologia Humana','Rua do Pinheiro','63940000','Quixada'),
('Rodrigo Silva',2000.00,150,'00000000033','34523448975','Terapia Intensiva','Rua Raul Nogueira','62940000','Morada Nova'),
('Jordão Rodrigues',12000.00,148,'00000000034','87634567543','Saude do Idoso','Rua das Oliveiras','60940000','Fortaleza'),
('Ticiana Coelho',15000.00,160,'00000000035','84623498094','Saude do Idoso','Rua das Oliveiras','60434333','Fortaleza'),
('Arthur Araruna',3000.00,150,'00000000036','34564331256','Transplantes','Avenida da Faculdade','78493456','Fortaleza'),
('Paulo Antonio', 4000.00, 150,'00000000037','45645656543','Transplantes','Rua do Povo','72903000','Quixeramobim'),
('Anderson Silva',5000.00, 130,'00000000038','12346587945','Saude da Crianca e do Adolescente','Rua do Banabs','62904444','Banabuiu'),
('Francisco Assis',5400,120,'00000000039','87434543565','Saude da Crianca e do Adolescente','Rua Francisco Duarte','62944999','Quixada');

INSERT INTO telefone_enf values('00000000020','8742344345'),
('00000000021','2345424543'),('00000000021','9342456765'),
('00000000022','9834546765'),('00000000023','8394523456'),
('00000000024','4344214543'),('00000000025','8945439095'),
('00000000026','9832353239'),('00000000027','8349549654'),
('00000000028','3454545654'),('00000000029','3442398495'),
('00000000030','3497655493'),('00000000031','9394934857'),
('00000000032','9442349594'),('00000000033','9304255459'),
('00000000034','8394594859'),('00000000035','9342338456'),
('00000000036','3956748989'),('00000000037','9304120695'),
('00000000038','9306630493'),('00000000039','3455948531');

INSERT INTO paciente values
('Sasha Gray', 29, '00000000040', 'Placido Castelo', '63900000', 'Quixada', '1989-07-05'),
('Vim Disel',51, '00000000041', 'Padre Assis Monteiro', '62940000', 'Morada Nova', '1967-06-18'),
('Naruto Uzumaki', 31,'00000000042', 'Rua do Folha', '62940000', 'Morada Nova', '1987-08-07'),
('Harry Potter', 38, '00000000043', 'Beco Diagonal', '63900000', 'Quixada', '1980-06-30'),
('Bjorn Ragnason', 41, '00000000044', 'Centro', '62880000', 'Horizonte', '1978-05-27'),
('Jair Messias Bolsonaro',63, '00000000045','Rua do Palacio', '70040000', 'Brasilia','1955-03-21'),
('Barry Alen', 33, '00000000046', 'Avenida Central','63900000', 'Quixada', '1986-02-23'),
('Leonel Messi', 31, '00000000047', 'Campo Novo', '63900000', 'Quixada', '1987-06-23'),
('Francisco Everardo Oliveira Silva', 54, '00000000048', 'Florentina', '62540000', 'Itapipoca', '1954-06-23'),
('Luis Incio', 73, '00000000049', 'Santa Candida', '82640040', 'Curitiba', '1945-10-27'),
('Ricardo Millos', 41, '00000000050', 'Travessa Recreio', '63900000', 'Quixada', '1978-10-27'),
('Charlie Brown', 42, '00000000051', 'Ceu', '63900000', 'Quixada', '1975-09-04'),
('Charlie Brown Junior', 21, '00000000052', 'Centro', '63900000', 'Quixada','1991-02-04'),
('Walter White', 50, '00000000053', 'Centro', '62940000', 'Morada Nova', '1968-11-07'),
('Hortelino Trocaletras', 52, '00000000054', 'Centlo', '62955000', 'Ibicuitinga', '1965-04-04'),
('José Alexandre da Silva Filho', 37, '00000000055', 'Conjunto Ceara', '60533000', 'Fortaleza', '1982-03-24'),
('Mia Khalifa', 26, '00000000056', 'Centro', '62880000', 'Horizonte', '1993-02-10'),
('Jhonny Sins', 40, '00000000057', 'Parangaba', '60740000', 'Fortaleza','1978-12-31'),
('Ash Ketchum', 10, '00000000058', 'Palet', '63950000', 'Choro', '2009-01-04'),
('Pablo Emilio Escobar', 44, '00000000059', 'Campo Novo', '63900000', 'Quixada', '1974-02-01');


INSERT INTO telefone_pac values('00000000040','8742344345'),
('00000000041','9283482384'),('00000000042','9234556765'),
('00000000042','9482344765'),('00000000043','83234234456'),
('00000000044','4234514543'),('00000000045','2345439095'),
('00000000046','9823423239'),('00000000047','8349523654'),
('00000000048','4234545654'),('00000000049','3423423495'),
('00000000050','3523655493'),('00000000051','9542934857'),
('00000000052','9233423594'),('00000000053','9452255459'),
('00000000054','8525394859'),('00000000055','9342338456'),
('00000000056','3956748989'),('00000000057','9523120695'),
('00000000058','9306630493'),('00000000059','3455948531');


INSERT INTO prescricao_medica values(DEFAULT,'2019-05-23','10:00:00','cancer','00000000000','00000000041'),
(DEFAULT,'2019-03-29','13:30:00','dor de dente','00000000000','00000000042'),
(DEFAULT,'2019-02-12','01:25:00','dor de cabeca','00000000001','00000000041'),
(DEFAULT,'2019-03-03','12:00:00','facada na barriga','00000000002','00000000045'),
(DEFAULT,'2019-04-04','15:40:00','tiro','00000000003','00000000040'), 
(DEFAULT,'2019-05-14','08:15:00','facada','00000000004','00000000043'),
(DEFAULT,'2019-03-04','09:00:00','mao quebrada','00000000005','00000000044'),
(DEFAULT,'2019-04-21','14:00:00','vomito','00000000006','00000000045'),
(DEFAULT,'2019-04-19','13:50:00','colica menstrual','00000000004','00000000046'),
(DEFAULT,'2019-05-23','12:20:00','diarreia','00000000007','00000000047'),
(DEFAULT,'2019-05-23','03:30:00','dor de ouvido','00000000009','00000000050'),
(DEFAULT,'2019-05-23','21:20:00','dor de cabeca','00000000010','00000000048'),
(DEFAULT,'2019-05-23','12:45:00','overdose','00000000010','00000000049'),
(DEFAULT,'2019-05-23','18:30:00','perna quebrada','00000000010','00000000050'),
(DEFAULT,'2019-05-23','23:00:00','corte no braco','00000000011','00000000051'),
(DEFAULT,'2019-05-23','00:30:00','dengue tipo 1','00000000012','00000000052'),
(DEFAULT,'2019-05-23','12:20:00','zika','00000000013','00000000053'),
(DEFAULT,'2019-05-23','17:40:00','machucado no rosto','00000000014','00000000054'),
(DEFAULT,'2019-05-23','13:30:00','dor de barriga','00000000015','00000000055'),
(DEFAULT,'2019-05-23','19:12:00','rinite','00000000017','00000000056'),
(DEFAULT,'2019-05-23','16:45:00','dor abdominal','00000000019','00000000057'),
(DEFAULT,'2019-05-23','04:52:00','falta de ar','00000000019','00000000058'),
(DEFAULT,'2019-05-23','12:10:00','chikungunya','00000000012','00000000059');

INSERT INTO medicamento values(DEFAULT,'tylenol',FALSE,'USO HOSPITALAR',2,10,15,'00000000021',1),
(DEFAULT,'reapro',FALSE,'USO DOMICILIAR',3,15,10,'00000000020',2),
(DEFAULT,'sectral',FALSE,'USO HOSPITALAR',5,3,20,'00000000022',3),
(DEFAULT,'lithostat',TRUE,'USO DOMICILIAR',4,20,15,'00000000023',4),
(DEFAULT,'humira',FALSE,'USO HOSPITALAR',3,25,12,'00000000024',5),
(DEFAULT,'hepsera',TRUE,'USO DOMICILIAR',4,12,12,'00000000025',6),
(DEFAULT,'adenocard',FALSE,'USO HOSPITALAR',5,2,10,'00000000026',7),
(DEFAULT,'fabrazyme',TRUE,'USO HOSPITALAR',4,10,50,'00000000027',8),
(DEFAULT,'albenza',FALSE,'USO DOMICILIAR',3,7,5,'00000000028',9),
(DEFAULT,'tanzeum',TRUE,'USO DOMICILIAR',2,3,10,'00000000029',10),
(DEFAULT,'zyloprim',TRUE,'USO HOSPITALAR',4,5,10,'00000000030',11),
(DEFAULT,'nesina',FALSE,'USO DOMICILIAR',2,12,15,'00000000031',12),
(DEFAULT,'tylenol',TRUE,'USO DOMICILIAR',4,17,20,'00000000032',13),
(DEFAULT,'reapro',TRUE,'USO HOSPITALAR',2,19,20,'00000000033',14),
(DEFAULT,'sectral',FALSE,'USO DOMICILIAR',4,12,25,'00000000034',15),
(DEFAULT,'diamox',TRUE,'USO HOSPITALAR',3,2,15,'00000000035',16),
(DEFAULT,'lithostat',FALSE,'USO DOMICILIAR',1,40,20,'00000000036',17),
(DEFAULT,'humira',FALSE,'USO HOSPITALAR',4,3,12,'00000000037',18),
(DEFAULT,'hepsera',TRUE,'USO DOMICILIAR',3,25,14,'00000000037',19),
(DEFAULT,'adenocard',TRUE,'USO DOMICILIAR',4,13,12,'00000000036',20),
(DEFAULT,'fabrazyme',FALSE,'USO HOSPITALAR',2,14,15,'00000000036',21),
(DEFAULT,'albenza',TRUE,'USO DOMICILIAR',3,35,15,'00000000035',22),
(DEFAULT,'tanzeum',TRUE,'USO HOSPITALAR',4,12,25,'00000000035',23);


-- criacao de views

-- DROP VIEW PACIENTE_MEDICO_DATA;

CREATE VIEW PACIENTE_MEDICO_DATA as (SELECT DISTINCT p.nome as NOME_PACIENTE, m.nome as NOME_MEDICO, pm.data_atendimento as DATA_ATENDIMENTO FROM paciente p, medico m, prescricao_medica pm WHERE pm.cpf_medico = m.cpf AND pm.cpf_paciente = p.cpf);

-- SELECT * FROM PACIENTE_MEDICO_DATA;

CREATE MATERIALIZED VIEW QTD_ATENDIMENTO_PACIENTE as (SELECT atendimento.nome as NOME_PACIENTE, COUNT(*) as QTD_ATENDIMENTOS FROM (paciente as p JOIN prescricao_medica as pm ON (pm.cpf_paciente = p.cpf)) as atendimento GROUP BY atendimento.nome, atendimento.cpf_paciente);

-- SELECT * FROM QTD_ATENDIMENTO_PACIENTE;

-- DROP MATERIALIZED VIEW QTD_ATENDIMENTO_PACIENTE;


-- criacao de consultas

-- lista pacientes que foram atendidos mais de uma vez

SELECT nome_paciente as NOME_PACIENTE FROM QTD_ATENDIMENTO_PACIENTE WHERE qtd_atendimentos > 1;

-- lista pacientes que foram atendidos mais de uma vez por mes

SELECT EXTRACT(MONTH FROM data_atendimento) as MES, nome_paciente as NOME_PACIENTE FROM PACIENTE_MEDICO_DATA GROUP BY nome_paciente, EXTRACT(MONTH FROM data_atendimento) HAVING COUNT(*) > 1 ORDER BY EXTRACT(MONTH FROM data_atendimento), nome_paciente;

-- lista os nomes dos enferemeiros e quantidade de procedimentos feitos em um determinado mes

SELECT e.nome as NOME_ENFERMEIRO, EXTRACT(MONTH FROM pm.data_atendimento) as MES, COUNT(m.cpf_enfermeiro) as QTD_PROCEDIMENTOS FROM enfermeiro e LEFT JOIN medicamento m ON m.cpf_enfermeiro = e.cpf, prescricao_medica pm WHERE (pm.id = m.id_prescricao) GROUP BY e.nome, m.cpf_enfermeiro, EXTRACT(MONTH FROM pm.data_atendimento) ORDER BY e.nome, EXTRACT(MONTH FROM pm.data_atendimento);

-- lista os nomes dos pacientes, nomes dos medicamentos aplicados neste paciente e qtd de vez que foi aplicado

SELECT p.nome as NOME_PACIENTE, m.nome as NOME_MEDICAMENTO, COUNT(*) as QTD_UTILIZADA  FROM paciente p, medicamento m, prescricao_medica pm WHERE pm.id = m.id_prescricao AND pm.cpf_paciente = p.cpf  GROUP BY p.nome, m.nome ORDER BY p.nome, m.nome;

-- lista os nomes dos pacientes, nomes dos medicamentos aplicados e a data que foi aplicado

SELECT DISTINCT p.nome as NOME_PACIENTE, m.nome as NOME_MEDICAMENTO, pm.data_atendimento as DATA_ATENDIMENTO FROM paciente p, medicamento m, prescricao_medica pm WHERE pm.id = m.id_prescricao AND pm.cpf_paciente = p.cpf ORDER BY p.nome, m.nome, pm.data_atendimento;

-- lista os pacientes, medicos, data de atendimento e horario de atendimento

SELECT p.nome as NOME_PACIENTE, m.nome as NOME_MEDICO, pm.data_atendimento as DATA_ATENDIMENTO, pm.horario as HORARIO FROM medico m, paciente p, prescricao_medica pm WHERE pm.cpf_paciente = p.cpf AND pm.cpf_medico = m.cpf ORDER BY p.nome, pm.data_atendimento, m.nome, pm.horario;

-- lista a quantidade de consultas feitas pelo medico em um determinado mes

SELECT atendimento.nome as NOME_MEDICO, EXTRACT(MONTH FROM atendimento.data_atendimento) as MES, COUNT(*) as QTD_ATENDIMENTOS FROM (medico JOIN prescricao_medica ON (cpf = cpf_medico)) as atendimento GROUP BY atendimento.nome, atendimento.cpf, EXTRACT(MONTH FROM atendimento.data_atendimento) ORDER BY atendimento.nome, EXTRACT(MONTH FROM atendimento.data_atendimento);

-- lista a quantidade de pacientes por cidade

SELECT cidade as CIDADE, COUNT(*) as QTD_PACIENTES FROM paciente GROUP BY cidade ORDER BY cidade;

-- lista a quantidade de pacientes por cep e cidade

SELECT cep as CEP, cidade as CIDADE, COUNT(*) as QTD_PACIENTES FROM paciente GROUP BY cidade, cep ORDER BY cidade, cep;

-- lista os nomes dos pacientes e quantas vezes ele já foi atendido

SELECT atendimento.nome as NOME_PACIENTE, COUNT(atendimento.cpf_paciente) as QTD_ATENDIMENTOS FROM (paciente p LEFT JOIN prescricao_medica ON (cpf = cpf_paciente)) as atendimento GROUP BY atendimento.nome ORDER BY atendimento.nome;

-- lista os nomes dos pacientes que foram atendidos pelo menos uma vez

SELECT p.nome as NOME_PACIENTE FROM paciente p  WHERE EXISTS(SELECT * FROM prescricao_medica pm WHERE pm.cpf_paciente = p.cpf) ORDER BY p.nome;

-- lista os nomes dos medicos que realizaram consulta pelo menos uma vez

SELECT m.nome as NOME_MEDICO FROM medico m WHERE m.cpf IN (SELECT pm.cpf_medico FROM prescricao_medica pm) ORDER BY m.nome;

-- lista os medicos que mais realizaram atendimentos

SELECT m.nome as NOME_MEDICO FROM medico m, prescricao_medica pm WHERE m.cpf = pm.cpf_medico GROUP BY m.cpf, m.nome HAVING COUNT(*) >= all(SELECT COUNT(*) FROM prescricao_medica pm GROUP BY pm.cpf_medico) ORDER BY m.nome;

-- lista os nomes dos enfermeiros e a quantidade de procedimentos realizados pelo mesmo

SELECT e.nome as NOME_ENFERMEIRO, COUNT(m.cpf_enfermeiro) as QTD_PROCEDIMENTOS FROM enfermeiro e LEFT JOIN medicamento m ON e.cpf = m.cpf_enfermeiro GROUP BY e.nome, e.cpf ORDER BY e.nome;

-- deletar views

DROP MATERIALIZED VIEW QTD_ATENDIMENTO_PACIENTE;

DROP VIEW PACIENTE_MEDICO_DATA;

-- deletar tabelas
DROP TABLE medicamento;

DROP TABLE prescricao_medica;

DROP TABLE telefone_pac;

DROP TABLE telefone_enf;

DROP TABLE especialidade;

DROP TABLE telefone_med;

DROP TABLE medico;

DROP TABLE enfermeiro;

DROP TABLE paciente;
