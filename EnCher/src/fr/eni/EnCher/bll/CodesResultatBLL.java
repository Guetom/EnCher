package fr.eni.EnCher.bll;

public abstract class CodesResultatBLL {

	/* UTILISATEUR */

	/**
	 * Le pseudo de l'utilisateur est invalide.
	 */
	public static final int REGLE_UTILISATEUR_PSEUDO_INVALIDE = 20001;

	/**
	 * Le prénom de l'utilisateur est invalide.
	 */
	public static final int REGLE_UTILISATEUR_PRENOM_INVALIDE = 20002;

	/**
	 * Le nom de l'utilisateur est invalide.
	 */
	public static final int REGLE_UTILISATEUR_NOM_INVALIDE = 20003;

	/**
	 * Le numéro de téléphone de l'utilisateur est invalide.
	 */
	public static final int REGLE_UTILISATEUR_TEL_INVALIDE = 20004;

	/**
	 * L'email de l'utilisateur est invalide.
	 */
	public static final int REGLE_UTILISATEUR_EMAIL_INVALIDE = 20005;

	/**
	 * La date de naissance de l'utilisateur est invalide.
	 */
	public static final int REGLE_UTILISATEUR_DATENAISSANCE_INVALIDE = 20006;

	/**
	 * La rue de l'utilisateur est invalide.
	 */
	public static final int REGLE_UTILISATEUR_RUE_INVALIDE = 20007;

	/**
	 * La ville de l'utilisateur est invalide.
	 */
	public static final int REGLE_UTILISATEUR_VILLE_INVALIDE = 20008;

	/**
	 * Le code postal de l'utilisateur est invalide.
	 */
	public static final int REGLE_UTILISATEUR_CP_INVALIDE = 20009;

	/**
	 * Le mot de passe de l'utilisateur est invalide.
	 */
	public static final int REGLE_UTILISATEUR_MDP_INVALIDE = 20010;

	/**
	 * Le crédit de l'utilisateur ne peut pas être inférieur à zé
	 */
	public static final int REGLE_UTILISATEUR_CREDIT_INVALIDE = 20011;

	/**
	 * La date de création de l'utilisateur est invalide.
	 */
	public static final int REGLE_UTILISATEUR_DATECREATION_INVALIDE = 20012;

	/**
	 * La photo de l'utilisateur est invalide.
	 */
	public static final int REGLE_UTILISATEUR_PHOTO_INVALIDE = 20013;
	
	/**
	 * L'identifiant de connexion ou le mot de passe est incorrect.
	 */
	public static final int RELGE_UTILISATEUR_CONNEXION_INVALIDE = 20014;

	/* ARTICLES */

	/**
	 * Le nom de l'article est invalide.
	 */
	public static final int REGLE_ARTICLE_NOM_INVALIDE = 20100;

	/**
	 * La description de l'article est invalide.
	 */
	public static final int REGLE_ARTICLE_DESCRIPTION_INVALIDE = 20101;

	/**
	 * Le prix de l'article ne peut pas �tre n�gatif.
	 */
	public static final int REGLE_ARTICLE_PRIX_NEGATIF = 20102;

	/**
	 * La cat�gorie d'article est invalide.
	 */
	public static final int REGLE_ARTICLE_CATEGORIE_INVALIDE = 20103;

	/**
	 * Aucun utilisateur n'est li� � l'article.
	 */
	public static final int REGLE_ARTICLE_UTILISATEUR_INVALIDE = 20104;

	/**
	 * L'adresse de retrait pour l'article est invalide.
	 */
	public static final int REGLE_ARTICLE_RETRAIT_INVALIDE = 20105;

	/**
	 * Impossible de mettre la date de d�but d'ench�res dans le pass�.
	 */
	public static final int REGLE_ARTICLE_DATE_DEBUT_DANS_LE_PASSE = 20106;

	/**
	 * Impossible de mettre la date de fin d'ench�res dans le pass�.
	 */
	public static final int REGLE_ARTICLE_DATE_FIN_DANS_LE_PASSE = 20107;

	/**
	 * Impossible de mettre la date de d�but d'ench�res apr�s la date de fin
	 * d'ench�res.
	 */
	public static final int REGLE_ARTICLE_DATE_DEBUT_APRES_FIN = 20108;

	/* Categories */

	/**
	 * Le libell� de la cat�gorie est invalide.
	 */
	public static final int REGLE_CATEGORIE_LIBELLE_INVALIDE = 20200;

	/* Enchères */

	/**
	 * La date et l'heure de l'enchère sont obligatoires.
	 */
	public static final int REGLE_ENCHERE_DATE_INVALIDE = 20300;

	/**
	 * Le montant de l'enchère doit être supérieur à zéro.
	 */
	public static final int REGLE_ENCHERE_MONTANT_INVALIDE = 20301;

	/**
	 * L'enchérisseur est invalide.
	 */
	public static final int REGLE_ENCHERE_ENCHERISSEUR_INVALIDE = 20302;

	/**
	 * L'article est invalide.
	 */
	public static final int REGLE_ENCHERE_ARTICLE_INVALIDE = 20303;

	/**
	 * L'article est indisponible.
	 */
	public static final int REGLE_ENCHERE_ARTICLE_INDISPONIBLE = 20304;

	/* Retrait */

	/**
	 * Le nom de rue est invalide.
	 */
	public static final int REGLE_RETRAIT_RUE_INVALIDE = 20400;

	/**
	 * Le nom de ville est invalide.
	 */
	public static final int REGLE_RETRAIT_VILLE_INVALIDE = 20401;

	/**
	 * Le code postal est invalide.
	 */
	public static final int REGLE_RETRAIT_CP_INVALIDE = 20402;

	/* Tags */

	/**
	 * Le libell� du tag est invalide.
	 */
	public static final int REGLE_TAG_LIBELLE_INVALIDE = 20500;

}
