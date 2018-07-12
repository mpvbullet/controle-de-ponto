INSERT INTO empresa(razao_social, cnpj, data_criacao)
    values ('Syntax Tech', '19327916000189', current_date );


 INSERT INTO funcionario(cpf, nome, email, senha, perfil, data_admissao, empresa_id)
    VALUES('09088585660', 'Marcos Paulo', 'marcos@admin.com', '$2a$10$qq32.r2ARq07.8g1WVLQG.wivOJMKf5oroAGg07wzvF5LhbMmQLYG', 'ADMIN', current_date, (SELECT id FROM empresa where cnpj = '19327916000189'));