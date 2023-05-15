INSERT INTO TB_ROLES
	(ID, NAME, DESCRIPTION)
VALUES
	('A7E1595A-CBFD-11ED-AFA1-0242AC120002', 'ROLE_PERSON', 'ROLE FOR FULL ACCESS TO FUNCIONALITY PERSONS'),
	('BB8461FA-CBFD-11ED-AFA1-0242AC120002', 'ROLE_PERSON_READ', 'ROLE FOR READ ONLY PERSONS'),
	('4CCFC14E-CCC2-11ED-AFA1-0242AC120002', 'ROLE_GROUP', 'ROLE FOR FULL ACCESS TO FUNCIONALITY ACCESS GROUPS'),
	('5C372564-CCC2-11ED-AFA1-0242AC120002', 'ROLE_GROUP_READ', 'ROLE FOR READ ONLY ACCESS GROUPS'),
	('630F104A-CCC2-11ED-AFA1-0242AC120002', 'ROLE_ROLES_READ', 'ROLE FOR READ ONLY ROLES');
	
INSERT INTO TB_ACCESS_GROUPS
	(ID, NAME)
VALUES
	('D7E51510-CBFD-11ED-AFA1-0242AC120002', 'ADMIN');
	
	
INSERT INTO TB_ROLES_GROUPS
	(ROLE_ID, GROUP_ID)
VALUES
	('A7E1595A-CBFD-11ED-AFA1-0242AC120002', 'D7E51510-CBFD-11ED-AFA1-0242AC120002'),
	('4CCFC14E-CCC2-11ED-AFA1-0242AC120002', 'D7E51510-CBFD-11ED-AFA1-0242AC120002'),
	('630F104A-CCC2-11ED-AFA1-0242AC120002', 'D7E51510-CBFD-11ED-AFA1-0242AC120002');
	