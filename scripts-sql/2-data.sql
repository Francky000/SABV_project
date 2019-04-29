

-- Supprime toutes les données

DELETE FROM tuto_memo;
DELETE FROM tuto_telephone;
DELETE FROM tuto_personne;
DELETE FROM tuto_categorie;
DELETE FROM tuto_role;
DELETE FROM tuto_compte;


-- Insère les données et crée les séquences

-- Compte

INSERT INTO tuto_compte (IdCompte, Pseudo, MotDePasse, Email ) VALUES 
  (1, 'geek', 'geek', 'geek@3il.fr' );
INSERT INTO tuto_compte (IdCompte, Pseudo, MotDePasse, Email ) VALUES 
  (2, 'chef', 'chef', 'chef@3il.fr' );
INSERT INTO tuto_compte (IdCompte, Pseudo, MotDePasse, Email ) VALUES 
  (3, 'job', 'job', 'job@3il.fr' );

DROP SEQUENCE seq_tuto_compte;
CREATE SEQUENCE seq_tuto_compte START WITH 4;


-- Role

INSERT INTO tuto_role (IdCompte, Role) VALUES 
  ( 1, 'ADMINISTRATEUR' );
INSERT INTO tuto_role (IdCompte, Role) VALUES 
  ( 1, 'UTILISATEUR' );
INSERT INTO tuto_role (IdCompte, Role) VALUES 
  ( 2, 'UTILISATEUR' );
INSERT INTO tuto_role (IdCompte, Role) VALUES 
  ( 3, 'UTILISATEUR' );
  

-- Categorie
  
INSERT INTO tuto_categorie (IdCategorie, Libelle ) VALUES 
  (1, 'Employés' );
INSERT INTO tuto_categorie (IdCategorie, Libelle ) VALUES 
  (2, 'Partenaires' );
INSERT INTO tuto_categorie (IdCategorie, Libelle ) VALUES 
  (3, 'Clients' );
INSERT INTO tuto_categorie (IdCategorie, Libelle ) VALUES 
  (4, 'Fournisseurs' );
INSERT INTO tuto_categorie (IdCategorie, Libelle ) VALUES 
  (5, 'Dirigeants' );

DROP SEQUENCE seq_tuto_categorie;
CREATE SEQUENCE seq_tuto_categorie START WITH 6;
  

-- Personne

INSERT INTO tuto_personne (IdPersonne, IdCategorie, Nom, Prenom) VALUES 
  ( 1, 1, 'GRASSET', 'Jérôme' );
INSERT INTO tuto_personne (IdPersonne, IdCategorie, Nom, Prenom) VALUES 
  ( 2, 1, 'BOUBY', 'Claude' );
INSERT INTO tuto_personne (IdPersonne, IdCategorie, Nom, Prenom) VALUES 
  ( 3, 1, 'AMBLARD', 'Emmanuel' );

DROP SEQUENCE seq_tuto_personne;
CREATE SEQUENCE seq_tuto_personne	START WITH 4;


-- Telephone

INSERT INTO tuto_telephone (IdTelephone, IdPersonne, Libelle, Numero ) VALUES 
  ( 11, 1, 'Portable', '06 11 11 11 11' );
INSERT INTO tuto_telephone (IdTelephone, IdPersonne, Libelle, Numero ) VALUES 
  ( 12, 1, 'Fax', '05 55 99 11 11' );
INSERT INTO tuto_telephone (IdTelephone, IdPersonne, Libelle, Numero ) VALUES 
  ( 13, 1, 'Bureau', '05 55 11 11 11' );
INSERT INTO tuto_telephone (IdTelephone, IdPersonne, Libelle, Numero ) VALUES 
  ( 21, 2, 'Portable', '06 22 22 22 22' );
INSERT INTO tuto_telephone (IdTelephone, IdPersonne, Libelle, Numero ) VALUES 
  ( 22, 2, 'Fax', '05 55 99 22 22' );
INSERT INTO tuto_telephone (IdTelephone, IdPersonne, Libelle, Numero ) VALUES 
  ( 23, 2, 'Bureau', '05 55 22 22 22' );
INSERT INTO tuto_telephone (IdTelephone, IdPersonne, Libelle, Numero ) VALUES 
  ( 31, 3, 'Portable', '06 33 33 33 33' );
INSERT INTO tuto_telephone (IdTelephone, IdPersonne, Libelle, Numero ) VALUES 
  ( 32, 3, 'Fax', '05 55 99 33 33' );
INSERT INTO tuto_telephone (IdTelephone, IdPersonne, Libelle, Numero ) VALUES 
  ( 33, 3, 'Bureau', '05 55 33 33 33' );

DROP SEQUENCE seq_tuto_telephone;
CREATE SEQUENCE seq_tuto_telephone START WITH 100;


-- Memo

INSERT INTO tuto_memo (IdMemo, Titre, Description, FlagUrgent, Statut, Effectif, Budget, Echeance ) VALUES 
  ( 1, 'Mémo n°1', 'Texte du mémo n°1', 0, 2, 2, 1234.56, DATE '2019-02-25' );
INSERT INTO tuto_memo (IdMemo, Titre, Description, FlagUrgent, Statut, Effectif, Budget, Echeance ) VALUES 
  ( 2, 'Mémo n°2', 'Texte du mémo n°2', 0, 1, 4, 5000.00, DATE '2019-05-18' );
INSERT INTO tuto_memo (IdMemo, Titre, Description, FlagUrgent, Statut, Effectif, Budget, Echeance ) VALUES 
  ( 3, 'Mémo n°3', 'Texte du mémo n°3', 1, 0, 3, 3333.33, DATE '2019-07-14' );

DROP SEQUENCE seq_tuto_memo;
CREATE SEQUENCE seq_tuto_memo START WITH 4;

