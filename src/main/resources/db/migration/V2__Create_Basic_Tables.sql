CREATE TABLE TB_USUARIOS (
	ID UUID NOT NULL,
	ENABLED BOOL NOT NULL,
	NOME VARCHAR(255) NOT NULL,
	PASSWORD VARCHAR(255) NOT NULL,
	USERNAME VARCHAR(255) NOT NULL,
	GRUPO_ACESSO_ID UUID NULL,
	CPF_CNPJ VARCHAR(14) NOT NULL,
	TELEFONE VARCHAR(255) NOT NULL,
	CONSTRAINT TB_USUARIOS_PK PRIMARY KEY (ID),
	CONSTRAINT TB_USUARIO_CONSTRAINT_USERNAME_UNIQUE UNIQUE (USERNAME),
	CONSTRAINT TB_USUARIO_TB_GRUPO_ACESSO_FK FOREIGN KEY (GRUPO_ACESSO_ID) REFERENCES TB_GRUPO_ACESSO(ID),
	CONSTRAINT TB_USUARIO_CONSTRAINT_CPFCNPJ_UNIQUE UNIQUE (CPF_CNPJ),
	CONSTRAINT TB_USUARIO_CONSTRAINT_TELEFONE_UNIQUE UNIQUE (TELEFONE)
);

CREATE TABLE TB_INSUMOS(
	ID UUID NOT NULL,
	CODIGO SERIAL4 NOT NULL,
	NOME VARCHAR(50) NOT NULL,
	UNIDADE_MEDIDA INT NOT NULL,
	DESCRICAO VARCHAR(255) NOT NULL,
	ENABLED BOOL NOT NULL,
	CONSTRAINT TB_INSUMOS_PK PRIMARY KEY(ID),
	CONSTRAINT TB_INSUMOS_CONSTRAINT_NOME_UNIQUE UNIQUE (NOME)
);

CREATE TABLE TB_ESTOQUE (
	ID UUID NOT NULL,
	DATA_INSERCAO DATE NOT NULL,
	DATA_ALTERACAO DATE,
	DATA_VENCIMENTO DATE NOT NULL,
	MARCA VARCHAR(255),
	NOTA_FISCAL VARCHAR(255),
	QUANTIDADE FLOAT8 NOT NULL,
	VALOR FLOAT8 NOT NULL,
	INSUMO_ID UUID NOT NULL,
	USUARIO_INSERCAO_ID UUID NOT NULL,
	USUARIO_ALTERACAO_ID UUID,
	TIPO INT2 NOT NULL,
	CONSTRAINT TB_ESTOQUE_PK PRIMARY KEY (ID),
	CONSTRAINT TB_ESTOQUE_TB_USUARIOS_INSERCAO_FK FOREIGN KEY (USUARIO_INSERCAO_ID) REFERENCES TB_USUARIOS(ID),
	CONSTRAINT TB_ESTOQUE_TB_USUARIOS_ALTERACAO_FK FOREIGN KEY (USUARIO_ALTERACAO_ID) REFERENCES TB_USUARIOS(ID),
	CONSTRAINT TB_ESTOQUE_TB_INSUMOS_FK FOREIGN KEY (INSUMO_ID) REFERENCES TB_INSUMOS(ID)
);

CREATE TABLE TB_MARCA (
	ID UUID NOT NULL,
	ENABLED BOOL NOT NULL,
	NOME VARCHAR(255) NOT NULL,
	CONSTRAINT TB_MARCA_PK PRIMARY KEY (ID),
	CONSTRAINT TB_MARCA_CONSTRAINT_NOME_UNIQUE UNIQUE (NOME)
);


CREATE TABLE TB_FORNECEDORES (
	ID UUID NOT NULL,
	BAIRRO VARCHAR(255) NOT NULL,
	CEP VARCHAR(255) NOT NULL,
	CIDADE VARCHAR(255) NOT NULL,
	CPF_CNPJ VARCHAR(14) NOT NULL,
	EMAIL VARCHAR(255) NOT NULL,
	ENABLED BOOL NOT NULL,
	ESTADO VARCHAR(255) NOT NULL,
	NOME VARCHAR(255) NOT NULL,
	NUMERO VARCHAR(255) NOT NULL,
	RUA VARCHAR(255) NOT NULL,
	TELEFONE VARCHAR(255) NOT NULL,
	COMPLEMENTO VARCHAR(255) NOT NULL,
	CONSTRAINT TB_FORNECEDORES_PK PRIMARY KEY (ID),
	CONSTRAINT TB_FORNECEDOR_CONSTRAINT_EMAIL_UNIQUE UNIQUE (EMAIL),
	CONSTRAINT TB_FORNECEDOR_CONSTRAINT_CPFCNPJ_UNIQUE UNIQUE (CPF_CNPJ),
	CONSTRAINT TB_FORNECEDOR_CONSTRAINT_TELEFONE_UNIQUE UNIQUE (TELEFONE)
);

CREATE TABLE TB_CLIENTES (
	ID UUID NOT NULL,
	BAIRRO VARCHAR(255) NOT NULL,
	CEP VARCHAR(255) NOT NULL,
	CIDADE VARCHAR(255) NOT NULL,
	CPF_CNPJ VARCHAR(14) NOT NULL,
	EMAIL VARCHAR(255) NOT NULL,
	ENABLED BOOL NOT NULL,
	ESTADO VARCHAR(255) NOT NULL,
	NOME VARCHAR(255) NOT NULL,
	NUMERO VARCHAR(255) NOT NULL,
	RUA VARCHAR(255) NOT NULL,
	TELEFONE VARCHAR(255) NOT NULL,
	COMPLEMENTO VARCHAR(255) NOT NULL,
	CONSTRAINT TB_CLIENTES_PK PRIMARY KEY (ID),
	CONSTRAINT TB_CLIENTE_CONSTRAINT_EMAIL_UNIQUE UNIQUE (EMAIL),
	CONSTRAINT TB_CLIENTE_CONSTRAINT_CPFCNPJ_UNIQUE UNIQUE (CPF_CNPJ),
	CONSTRAINT TB_CLIENTE_CONSTRAINT_TELEFONE_UNIQUE UNIQUE (TELEFONE)
);