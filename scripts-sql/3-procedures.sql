

-- Procédure d'insertion

CREATE OR REPLACE PROCEDURE tuto_compte_insert(
	p_Pseudo		IN	tuto_compte.Pseudo%TYPE, 
	p_MotDePasse	IN	tuto_compte.MotDePasse%TYPE, 
	p_Email			IN	tuto_compte.Email%TYPE,
	p_IdCompte		OUT	tuto_compte.IdCompte%TYPE 
) AS
BEGIN
  INSERT INTO tuto_compte ( Pseudo, MotDePasse, Email, IdCompte ) 
  VALUES ( p_Pseudo, p_MotDePasse, p_Email, seq_tuto_compte.nextval )
  RETURNING IdCompte INTO p_IdCompte;
END;
/

CREATE OR REPLACE PROCEDURE tuto_categorie_insert(
	   p_Libelle 		IN  tuto_categorie.Libelle%TYPE,
	   p_IdCategorie	OUT tuto_categorie.IdCategorie%TYPE )
AS
BEGIN
  INSERT INTO tuto_categorie ( Libelle, IdCategorie  ) 
  VALUES ( p_Libelle, seq_tuto_categorie.nextval )
  RETURNING IdCategorie INTO p_IdCategorie;
END;
/

CREATE OR REPLACE PROCEDURE tuto_personne_insert(
	p_IdCategorie	IN	tuto_personne.IdCategorie%TYPE, 
	p_Nom			IN	tuto_personne.Nom%TYPE, 
	p_Prenom		IN	tuto_personne.Prenom%TYPE,
	p_IdPersonne	OUT	tuto_personne.IdPersonne%TYPE 
) AS
BEGIN
  INSERT INTO tuto_personne ( IdCategorie, Nom, Prenom, IdPersonne ) 
  VALUES ( p_IdCategorie, p_Nom, p_Prenom, seq_tuto_personne.nextval )
  RETURNING IdPersonne INTO p_IdPersonne;
END;
/

CREATE OR REPLACE PROCEDURE tuto_telephone_insert(
	p_IdPersonne	IN	tuto_telephone.IdPersonne%TYPE, 
	p_Libelle		IN	tuto_telephone.Libelle%TYPE, 
	p_Numero		IN	tuto_telephone.Numero%TYPE,
	p_IdTelephone	OUT	tuto_telephone.IdTelephone%TYPE 
) AS
BEGIN
  INSERT INTO tuto_telephone ( IdPersonne, Libelle, Numero, IdTelephone ) 
  VALUES ( p_IdPersonne, p_Libelle, p_Numero, seq_tuto_telephone.nextval )
  RETURNING IdTelephone INTO p_IdTelephone;
END;
/


CREATE OR REPLACE PROCEDURE tuto_memo_insert(
	p_Titre	 		IN  tuto_memo.Titre%TYPE,
	p_Description	IN  tuto_memo.Description%TYPE,
	p_FlagUrgent	IN  tuto_memo.FlagUrgent%TYPE,
	p_Statut		IN	tuto_memo.Statut%TYPE,
	p_Effectif		IN	tuto_memo.Effectif%TYPE,
	p_Budget		IN	tuto_memo.Budget%TYPE,
	p_Echeance		IN	tuto_memo.Echeance%TYPE,
	p_CheminImage2	IN	tuto_memo.CheminImage2%TYPE,
	p_IdPersonne	IN	tuto_memo.IdPersonne%TYPE,
	p_IdMemo		OUT tuto_memo.IdMemo%TYPE )
AS
BEGIN
	INSERT INTO tuto_memo ( Titre, Description, FlagUrgent, Statut, Effectif, Budget, Echeance, CheminImage2, IdPersonne, IdMemo  ) 
	VALUES ( p_Titre, p_Description, p_FlagUrgent, p_Statut, p_Effectif, p_Budget, p_Echeance, p_CheminImage2, p_IdPersonne, seq_tuto_memo.nextval )
	RETURNING IdMemo INTO p_IdMemo;
END;
/
