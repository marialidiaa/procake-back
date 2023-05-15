CREATE TABLE TB_ROLES (
	ID UUID NOT NULL,
	DESCRICAO VARCHAR(255) NOT NULL,
	NOME VARCHAR(255) NOT NULL,
	CONSTRAINT TB_ROLES_PK PRIMARY KEY (ID),
	CONSTRAINT TB_ROLES_CONSTRAINT_NOME_UNIQUE UNIQUE (NOME)
);

CREATE TABLE TB_GRUPO_ACESSO (
	ID UUID NOT NULL,
	NOME VARCHAR(255) NOT NULL,
	CONSTRAINT TB_GRUPO_ACESSO_PK PRIMARY KEY (ID),
	CONSTRAINT TB_GRUPO_ACESSO_CONSTRAINT_NOME_UNIQUE UNIQUE (NOME)
);

CREATE TABLE TB_ROLES_GRUPOS (
	ROLE_ID UUID NOT NULL,
	GRUPO_ID UUID NOT NULL,
	CONSTRAINT TB_ROLES_TB_GRUPO_ACESSO_FK FOREIGN KEY (GRUPO_ID) REFERENCES TB_GRUPO_ACESSO(ID),
	CONSTRAINT TB_GRUPO_ACESSO_TB_ROLES_FK FOREIGN KEY (ROLE_ID) REFERENCES TB_ROLES(ID)
);