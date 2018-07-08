

CREATE TABLE IF NOT EXISTS empresa (
    id serial NOT NULL,
    razao_social text COLLATE NOT NULL,
    cnpj "char"[] NOT NULL,
    data_criacao timestamp(4) NOT NULL,
    CONSTRAINT empresa_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS funcionario (
  id serial NOT NULL,
  cpf text NOT NULL,

  nome text NOT NULL,
  email text NOT NULL,
  senha text NOT NULL,
  perfil text NOT NULL,
  quantidade_horas_almoco numeric DEFAULT NULL,
  quantidade_horas_dia numeric DEFAULT NULL,

  valor_hora numeric(19,2) DEFAULT NULL,
  data_atualizacao timestamp(4) NOT NULL,
  data_criacao timestamp(4) NOT NULL,
  empresa_id serial DEFAULT NULL,

  CONSTRAINT funcionario_pk PRIMARY KEY (id),
  CONSTRAINT empresa_fk FOREIGN KEY (empresa_id)
    REFERENCES empresa (id) MATCH SIMPLE
);

CREATE TABLE IF NOT EXISTS lancamento (
  id serial NOT NULL,
  data timestamp(4) NOT NULL,
  data_atualizacao timestamp(4) NOT NULL,
  data_criacao timestamp(4) NOT NULL,
  descricao text DEFAULT NULL,
  localizacao text DEFAULT NULL,
  tipo text NOT NULL,
  funcionario_id serial DEFAULT NULL,

  CONSTRAINT lancamento_pk PRIMARY KEY (id),
  CONSTRAINT funcionario_fk FOREIGN KEY (funcionario_id)
    REFERENCES funcionario (id) MATCH SIMPLE
);

