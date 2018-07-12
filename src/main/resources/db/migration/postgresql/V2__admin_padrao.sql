INSERT INTO empresa(razao_social, cnpj, data_criacao)
    values ('Syntax Tech', '19327916000189', current_date )
ON CONFLICT (key1, key2, key3, key4)
    DO UPDATE SET
        razao_social='Syntax Tech', cnpj='19327916000189', data_criacao=current_date
    WHERE
        id = (SELECT id FROM empresa where cnpj = '19327916000189')
;

INSERT INTO funcionario(cpf, nome, email, senha, perfil, data_admissao, empresa_id)
    VALUES('09088585660', 'Marcos Paulo', 'marcos@admin.com', '$2a$10$qq32.r2ARq07.8g1WVLQG.wivOJMKf5oroAGg07wzvF5LhbMmQLYG', 'ADMIN', current_date, (SELECT id FROM empresa where cnpj = '19327916000189'));