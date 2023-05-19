USE EnCher_BDD
GO

/*
DELETE FROM ARTICLES_TAGS;
DELETE FROM ARTICLES_PHOTOS;
DELETE FROM ENCHERES;
DELETE FROM ARTICLES;
DELETE FROM RETRAITS;
DELETE FROM UTILISATEURS;
DELETE FROM PHOTOS;
DELETE FROM CATEGORIES;
DELETE FROM TAGS;
*/

-- Insertions dans la table TAGS: IDENTITY: idTag,
    --libelle
INSERT INTO TAGS (libelle) VALUES ('souris');
INSERT INTO TAGS (libelle) VALUES ('tableau');
INSERT INTO TAGS (libelle) VALUES ('art');

-- Insertions dans la table CATEGORIES: IDENTITY: idCategorie,
    --libelle
INSERT INTO CATEGORIES (libelle) VALUES ('Informatique');
INSERT INTO CATEGORIES (libelle) VALUES ('Ameublement');
INSERT INTO CATEGORIES (libelle) VALUES ('Vêtement');
INSERT INTO CATEGORIES (libelle) VALUES ('Sport&Loisirs');

-- Insertions dans la table PHOTOS: IDENTITY: idPhoto,
    --url
INSERT INTO PHOTOS (url) VALUES ('https://i.la-croix.com/1400x933/smart/2018/02/23/1200916100/jeune-fille-perle-Vermeer-Musee-Mauritshuis-Haye_0.jpg');
INSERT INTO PHOTOS (url) VALUES ('https://image.jeuxvideo.com/medias/166254/1662538644-9887-card.png');
INSERT INTO PHOTOS (url) VALUES ('https://i.la-croix.com/1400x933/smart/2018/02/23/1200916100/jeune-fille-perle-Vermeer-Musee-Mauritshuis-Haye_0.jpg');

--UTILISATEURS: IDENTITY: idUtilisateur,
    --pseudo, prenom, nom, NULL idPhoto, NULL tel, email, rue, code_postal, ville, mot_de_passe, isAdmin, credit, dateCreation, dateNaissance
INSERT INTO UTILISATEURS (pseudo, prenom, nom, idPhoto, email, rue, code_postal, ville, mot_de_passe, credit, isAdmin, dateNaissance)
VALUES ('leRiche1', 'Prénom1', 'Nom1', 1, 'utilisateur1@example.com', '1 Rue du Poireaux', '56860', 'Vannes', 'mdp123', 100, 0, '2000-01-01');

INSERT INTO UTILISATEURS (pseudo, prenom, nom, email, rue, code_postal, ville, mot_de_passe, credit, isAdmin, dateNaissance)
VALUES ('leRiche2', 'Prénom2', 'Nom2', 'utilisateur2@example.com', '2 Impasse Brocolis ', '20100', 'Ville2', 'mdp456', 200, 0, '1995-05-10');

INSERT INTO UTILISATEURS (pseudo, prenom, nom, email, rue, code_postal, ville, mot_de_passe, credit, isAdmin, dateNaissance)
VALUES ('artiste', 'Prénom3', 'Nom3', 'utilisateur3@example.com', '3 Rue Harry covert', '93000', 'Ville3', 'mdp789', 150, 0, '1990-08-15');

INSERT INTO UTILISATEURS (pseudo, prenom, nom, tel, email, rue, code_postal, ville, mot_de_passe, credit, isAdmin, dateNaissance)
VALUES ('artiste2', 'Prénom4', 'Nom4', 0666999011,'utilisateur4@example.com', '4 Rue Harry covert', '93000', 'Ville3', 'mdp789', 150, 0, '1991-08-15');

-- Insertions dans la table RETRAITS: IDENTITY: idRetrait,
    --idArticle, rue ,code_postal ,ville
INSERT INTO RETRAITS (rue, code_postal, ville)
VALUES ('Rue du Retrait', '12345', 'Ville du Retrait');

INSERT INTO RETRAITS (rue, code_postal, ville)
VALUES ('3 Rue Harry covert', '93000', 'Ville3');

-- Insertions dans la table ARTICLES: IDENTITY: idArticle,
    --etat, nom, description, NULL prix, idCategorie, idUtilisateur, dateDebut, dateFin

--CREER("CR"),EN_COURS("EC"),VENDU("VD"),RETIRER("RT");
INSERT INTO ARTICLES (etat, nom, description, prix, idCategorie, idUtilisateur, idRetrait, dateDebut, dateFin)
VALUES ('EC', 'Femme à la perle', 'Quel regard', 500000, 4, 3, 1, CURRENT_TIMESTAMP, '2025-05-17T10:30:00');

INSERT INTO ARTICLES (etat, nom, description, prix, idCategorie, idUtilisateur, idRetrait, dateDebut, dateFin)
VALUES ('CR', 'G502X+', 'Clic plus vite que son ombre', 167, 1, 3, 2, CURRENT_TIMESTAMP, '2025-05-17T10:30:00');



-- Insertions dans la table ARTICLES_PHOTOS:
	--idArticle, idPhoto
INSERT INTO ARTICLES_PHOTOS (idArticle, idPhoto) VALUES (1, 1);
INSERT INTO ARTICLES_PHOTOS (idArticle, idPhoto) VALUES (2, 2);
INSERT INTO ARTICLES_PHOTOS (idArticle, idPhoto) VALUES (1, 3);

-- Insertions dans la table ARTICLES_TAGS:
	--idArticle, idTag
INSERT INTO ARTICLES_TAGS (idArticle, idTag) VALUES (1, 1);
INSERT INTO ARTICLES_TAGS (idArticle, idTag) VALUES (2, 2);
INSERT INTO ARTICLES_TAGS (idArticle, idTag) VALUES (1, 3);
-- Insertions dans la table ENCHERES: IDENTITY: idEnchere,
    --dateheure, montant, idUtilisateur, idArticle
INSERT INTO ENCHERES (dateheure, montant, idUtilisateur, idArticle) VALUES ('2023-05-17T10:00:00', 500500, 1, 1);
INSERT INTO ENCHERES (dateheure, montant, idUtilisateur, idArticle) VALUES ('2023-05-17T10:10:00', 520000, 2, 1);