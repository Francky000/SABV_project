

-- Supprimer toutes les tables

DROP TABLE tuto_memo;
DROP TABLE tuto_telephone;
DROP TABLE tuto_personne;
DROP TABLE tuto_categorie;
DROP TABLE tuto_role;
DROP TABLE tuto_compte;


-- Crée les Tables

CREATE TABLE tuto_compte (
	IdCompte		INT			 	NOT NULL,
	Pseudo			VARCHAR(25)		NOT NULL,
	MotDePasse		VARCHAR(25) 	NOT NULL,
	Email			VARCHAR(100)	NOT NULL,
	PRIMARY KEY (IdCompte),
	UNIQUE (Pseudo)
);

CREATE TABLE tuto_role (
	IdCompte		INT	 		NOT NULL,
	Role			VARCHAR(20) NOT NULL,
	CHECK( Role IN ('ADMINISTRATEUR','UTILISATEUR') ),	
	PRIMARY KEY (IdCompte, Role),
	FOREIGN KEY (IdCompte) REFERENCES tuto_compte (IdCompte)
);

CREATE TABLE tuto_categorie (
	IdCategorie		INT				NOT NULL,
	Libelle			VARCHAR(25)		NOT NULL,
	PRIMARY KEY (IdCategorie)
);

CREATE TABLE tuto_personne (
	IdPersonne		INT				NOT NULL,
	IdCategorie		INT				NOT NULL,
	Nom				VARCHAR(25)  	NOT NULL,
	Prenom			VARCHAR(25) 	NOT NULL,
	PRIMARY KEY (IdPersonne),
 	FOREIGN KEY (IdCategorie) REFERENCES tuto_categorie (IdCategorie)
);

CREATE TABLE tuto_telephone (
	IdTelephone		INT				NOT NULL,
	IdPersonne		INT	 			NOT NULL,
	Libelle			VARCHAR(25)		NOT NULL,
	Numero			VARCHAR(25)		NOT NULL,
	PRIMARY KEY (IdTelephone),
	FOREIGN KEY (IdPersonne) REFERENCES tuto_personne (IdPersonne)
);


CREATE TABLE tuto_memo (
	IdMemo			INT				NOT NULL,
	Titre			VARCHAR(50)		NOT NULL,
	Description		VARCHAR(1000),
	FlagUrgent		NUMERIC(1)		DEFAULT 0	NOT NULL,
	Statut			NUMERIC(1)		DEFAULT 0	NOT NULL, 
	Effectif 		INT, 
	Budget			NUMERIC(9,2), 
	Echeance		DATE,
	Image1			BLOB,
	CheminImage2	VARCHAR(255),
	IdPersonne		INT				NULL,
	CHECK( Statut BETWEEN 0 AND 2 ),	
	PRIMARY KEY (IdMemo),
	FOREIGN KEY (IdPersonne) REFERENCES tuto_personne (IdPersonne)
);

