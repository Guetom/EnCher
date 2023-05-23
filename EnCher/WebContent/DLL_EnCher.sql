-------------

-- Script de création de la base de données ENCHERES_BDD
--   type :      SQL Server 2012
--

USE EnCher_BDD
GO

/*
DROP TABLE ARTICLES_TAGS;
DROP TABLE TAGS;

DROP TABLE ENCHERES;

DROP TABLE ARTICLES_PHOTOS;

DROP TABLE ARTICLES;
DROP TABLE RETRAITS;
DROP TABLE CATEGORIES;

DROP TABLE UTILISATEURS;
DROP TABLE PHOTOS;
GO
*/

CREATE TABLE TAGS (
    idTag          INTEGER IDENTITY(1,1) NOT NULL,
    libelle        VARCHAR(255) NOT NULL
)
ALTER TABLE TAGS ADD constraint tags_pk PRIMARY KEY (idTag)

CREATE TABLE CATEGORIES (
    idCategorie    INTEGER IDENTITY(1,1) NOT NULL,
    libelle        VARCHAR(255) NOT NULL
)
ALTER TABLE CATEGORIES ADD constraint categories_pk PRIMARY KEY (idCategorie)

CREATE TABLE ENCHERES (
    idEnchere        INTEGER IDENTITY(1,1) NOT NULL,
    dateheure        DATETIME NOT NULL,
    montant          INTEGER NOT NULL,
    idUtilisateur    INTEGER NOT NULL,
    idArticle        INTEGER NOT NULL
)
ALTER TABLE ENCHERES ADD constraint encheres_pk PRIMARY KEY (idEnchere)

CREATE TABLE RETRAITS (
	idRetrait        INTEGER IDENTITY(1,1) NOT NULL,
    rue              VARCHAR(255) NOT NULL,
    code_postal      VARCHAR(5) NOT NULL,
    ville            VARCHAR(255) NOT NULL
)
ALTER TABLE RETRAITS ADD constraint retraits_pk PRIMARY KEY  (idRetrait)

CREATE TABLE UTILISATEURS (
    idUtilisateur    INTEGER IDENTITY(1,1) NOT NULL,
    pseudo           VARCHAR(50) NOT NULL,
    prenom           VARCHAR(255) NOT NULL,
    nom              VARCHAR(255) NOT NULL,
	idPhoto 	     INTEGER NOT NULL DEFAULT 1,
    tel              FLOAT NULL,
    email            VARCHAR(255) NOT NULL,
    rue              VARCHAR(255) NOT NULL,
    code_postal      VARCHAR(5) NOT NULL,
    ville            VARCHAR(255) NOT NULL,
    mot_de_passe     VARCHAR(255) NOT NULL,
    credit           INTEGER NOT NULL,
    isAdmin          BIT NOT NULL,
	dateCreation     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	dateNaissance    DATE NOT NULL
)
ALTER TABLE UTILISATEURS ADD constraint utilisateurs_pk PRIMARY KEY (idUtilisateur)
/*ALTER TABLE UTILISATEURS ADD CONSTRAINT utilisateurs_tel_ck CHECK(tel LIKE ('[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]')
	OR tel LIKE ('[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'))*/
ALTER TABLE UTILISATEURS ADD CONSTRAINT utilisateurs_dateNaissance_ck CHECK(dateNaissance < GETDATE() AND DATEDIFF(YEAR, dateNaissance, GETDATE()) >= 18)
ALTER TABLE UTILISATEURS ADD constraint utilisateurs_pseudo_uq UNIQUE (pseudo)
ALTER TABLE UTILISATEURS ADD constraint utilisateurs_email_uq UNIQUE (email) 


CREATE TABLE ARTICLES (
    idArticle                     INTEGER IDENTITY(1,1) NOT NULL,
	etat    					  VARCHAR(5) NOT NULL DEFAULT 'CR',
    nom                           VARCHAR(255) NOT NULL,
    description                   VARCHAR(255) NOT NULL,
    prix                          INTEGER NOT NULL,
    idCategorie                   INTEGER NOT NULL,
    idUtilisateur                 INTEGER NOT NULL,
	idRetrait                     INTEGER NOT NULL,
	dateDebut                     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    dateFin                       DATETIME NOT NULL
)
ALTER TABLE ARTICLES ADD CONSTRAINT articles_pk PRIMARY KEY (idArticle)
--CREER("CR"),EN_COURS("EC"),VENDU("VD"),RETIRER("RT");
ALTER TABLE ARTICLES ADD CONSTRAINT articles_etat_ck CHECK (etat IN ('CR','EC','VD','RT'))
ALTER TABLE ARTICLES ADD CONSTRAINT articles_prix_ck CHECK (prix >= 0)
ALTER TABLE ARTICLES ADD CONSTRAINT articles_dateDebut_ck CHECK(dateDebut >= CURRENT_TIMESTAMP)
ALTER TABLE ARTICLES ADD CONSTRAINT articles_dateFin_ck CHECK(dateFin > dateDebut)

CREATE TABLE PHOTOS (
    idPhoto                       INTEGER IDENTITY(1,1) NOT NULL,
	url    					      VARCHAR(255) NOT NULL
)
ALTER TABLE PHOTOS ADD CONSTRAINT photos_pk PRIMARY KEY ( idPhoto )

CREATE TABLE ARTICLES_PHOTOS (
    idArticle     INTEGER NOT NULL,
    idPhoto       INTEGER NOT NULL
);
ALTER TABLE ARTICLES_PHOTOS ADD CONSTRAINT articles_photos_pk PRIMARY KEY ( idArticle, idPhoto )


CREATE TABLE ARTICLES_TAGS (
    idArticle     INTEGER NOT NULL,
    idTag         INTEGER NOT NULL
);
ALTER TABLE ARTICLES_TAGS ADD CONSTRAINT articles_tags_pk PRIMARY KEY ( idArticle, idTag )

--MISE EN PLACE DE L'INTEGRITE REFERENTIELLE
ALTER TABLE UTILISATEURS
	ADD CONSTRAINT utilisateurs_photos_fk FOREIGN KEY (idPhoto)
		REFERENCES PHOTOS ( idPhoto )
	ON DELETE CASCADE
    ON UPDATE NO ACTION

ALTER TABLE ARTICLES
    ADD CONSTRAINT articles_categories_fk FOREIGN KEY ( idCategorie )
        REFERENCES CATEGORIES ( idCategorie )
	ON DELETE NO ACTION 
    ON UPDATE NO ACTION

ALTER TABLE ARTICLES
    ADD CONSTRAINT articles_utilisateurs_fk FOREIGN KEY ( idUtilisateur )
		REFERENCES UTILISATEURS ( idUtilisateur )
	ON DELETE NO ACTION 
    ON UPDATE NO ACTION

ALTER TABLE ARTICLES
    ADD CONSTRAINT articles_retraits_fk FOREIGN KEY ( idRetrait )
		REFERENCES RETRAITS ( idRetrait )
	ON DELETE CASCADE
    ON UPDATE NO ACTION
    
ALTER TABLE ENCHERES
    ADD CONSTRAINT encheres_articles_fk FOREIGN KEY ( idArticle )
        REFERENCES ARTICLES ( idArticle )
	ON DELETE NO ACTION
    ON UPDATE NO ACTION 
    
ALTER TABLE ENCHERES
    ADD CONSTRAINT encheres_utilisateurs_fk FOREIGN KEY ( idUtilisateur )
        REFERENCES UTILISATEURS ( idUtilisateur )
	ON DELETE NO ACTION 
    ON UPDATE NO ACTION 

ALTER TABLE ARTICLES_TAGS
	ADD CONSTRAINT articles_tags_articles_fk FOREIGN KEY (idArticle)
		REFERENCES ARTICLES ( idArticle )
	ON DELETE CASCADE
    ON UPDATE NO ACTION

ALTER TABLE ARTICLES_TAGS
	ADD CONSTRAINT articles_tags_tags_fk FOREIGN KEY (idTag)
		REFERENCES TAGS ( idTag )
	ON DELETE CASCADE
    ON UPDATE NO ACTION

ALTER TABLE ARTICLES_PHOTOS
	ADD CONSTRAINT articles_photos_articles_fk FOREIGN KEY (idArticle)
		REFERENCES ARTICLES ( idArticle )
	ON DELETE CASCADE
    ON UPDATE NO ACTION

ALTER TABLE ARTICLES_PHOTOS
	ADD CONSTRAINT articles_photos_photos_fk FOREIGN KEY (idPhoto)
		REFERENCES PHOTOS ( idPhoto )
	ON DELETE CASCADE
    ON UPDATE NO ACTION
--------------------------------------
/*
 SELECT  a.no_article,nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,
			a.no_utilisateur as no_user,a.no_categorie,etat_vente,image,r.rue as arue,r.code_postal as acp,r.ville as aville, c.libelle, u.*,
			e.no_utilisateur as ec_no_utilisateur, e.date_enchere, e.montant_enchere 
			FROM  (((ARTICLES_VENDUS a LEFT JOIN RETRAITS r ON a.no_article = r.no_article) 
			LEFT JOIN CATEGORIES c ON c.no_categorie = a.no_categorie)
			LEFT JOIN UTILISATEURS u ON u.no_utilisateur = a.no_utilisateur)
			LEFT JOIN ENCHERES e ON (a.no_article = e.no_article AND e.no_utilisateur = (SELECT TOP(1) ec.no_utilisateur FROM ENCHERES ec WHERE ec.no_article = a.no_article ORDER BY date_enchere DESC))
			WHERE (GETDATE() BETWEEN date_debut_encheres AND date_fin_encheres)
*/		
			
-------------------------------------