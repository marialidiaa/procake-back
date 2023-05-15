CREATE TABLE TB_USERS (
	ID UUID NOT NULL,
	ENABLED BOOL NOT NULL,
	NAME VARCHAR(255) NOT NULL,
	PASSWORD VARCHAR(255) NOT NULL,
	USERNAME VARCHAR(255) NOT NULL,
	ACCES_GROUP_ID UUID NULL,
	CONSTRAINT TB_USERS_PK PRIMARY KEY (ID),
	CONSTRAINT USERS_CONSTRAINT_USERNAME_UNIQUE UNIQUE (USERNAME),
	CONSTRAINT USERS_ACCESS_GROUPS_FK FOREIGN KEY (ACCES_GROUP_ID) REFERENCES TB_ACCESS_GROUPS(ID)
);