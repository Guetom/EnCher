package fr.eni.EnCher.bll;

public abstract class CodesResultatBLL {
	
	/**
	 * Le pseudo de l'utilisateur est invalide.
	 */
	public static final int REGLE_UTILISATEUR_PSEUDO_INVALID=20001;

	/**
	 * Le prénom de l'utilisateur est invalide.
	 */
	public static final int REGLE_UTILISATEUR_PRENOM_INVALID=20002;

	/**
	 * Le nom de l'utilisateur est invalide.
	 */
	public static final int REGLE_UTILISATEUR_NOM_INVALID=20003;

	/**
	 * Le numéro de téléphone de l'utilisateur est invalide.
	 */
	public static final int REGLE_UTILISATEUR_TEL_INVALID=20004;

	/**
	 * L'email de l'utilisateur est invalide.
	 */
	public static final int REGLE_UTILISATEUR_EMAIL_INVALID=20005;

	/**
	 * La date de naissance de l'utilisateur est invalide.
	 */
	public static final int REGLE_UTILISATEUR_DATENAISSANCE_INVALID=20006;

	/**
	 * La rue de l'utilisateur est invalide.
	 */
	public static final int REGLE_UTILISATEUR_RUE_INVALID=20007;

	/**
	 * La ville de l'utilisateur est invalide.
	 */
	public static final int REGLE_UTILISATEUR_VILLE_INVALID=20008;

	/**
	 * Le code postal de l'utilisateur est invalide.
	 */
	public static final int REGLE_UTILISATEUR_CP_INVALID=20009;

	/**
	 * Le mot de passe de l'utilisateur est invalide.
	 */
	public static final int REGLE_UTILISATEUR_MDP_INVALID=20010;

	/**
	 * Le crédit de l'utilisateur ne peut pas être inférieur à zé
	 */
	public static final int REGLE_UTILISATEUR_CREDIT_INVALID=20011;

	/**
	 * La date de création de l'utilisateur est invalide.
	 */
	public static final int REGLE_UTILISATEUR_DATECREATION_INVALID=20012;

	/**
	 * La photo de l'utilisateur est invalide.
	 */
	public static final int REGLE_UTILISATEUR_PHOTO_INVALID=20013;
	
	/**
	 * 
	 */
	public static final int REGLE_ARTICLE_NOM_INVALID=20100;
	
	/**
	 * 
	 */
	public static final int REGLE_ARTICLE_DESCRIPTION_INVALID = 20102;
	
	/**
	 * 
	 */
	public static final int REGLE_ARTICLE_PRIX_NEGATIF = 20103;
	
	/**
	 * 
	 */
	public static final int REGLE_ARTICLE_CATEGORIE_NULL = 20104;
	
	/**
	 * 
	 */
	public static final int REGLE_ARTICLE_UTILISATEUR_NULL = 20105;
	
	/**
	 * 
	 */
	public static final int REGLE_ARTICLE_RETRAIT_NULL = 20106;
	
	/**
	 * 
	 */
	public static final int REGLE_ARTICLE_DATE_DEBUT_DANS_LE_PASSE = 20107;
	
	/**
	 * 
	 */
	public static final int REGLE_ARTICLE_DATE_FIN_DANS_LE_PASSE = 20108;
	
	/**
	 * 
	 */
	public static final int REGLE_ARTICLE_DATE_DEBUT_APRES_FIN = 20109;
	
	/**
	 * La date et l'heure de l'enchère sont obligatoires.
	 */
	public static final int REGLE_ENCHERE_DATE_INVALIDE=20300;
	
	/**
	 * Le montant de l'enchère doit être supérieur à zéro.
	 */
	public static final int REGLE_ENCHERE_MONTANT_INVALIDE=20301;
	
	/**
	 * L'enchérisseur est invalide.
	 */
	public static final int REGLE_ENCHERE_ENCHERISSEUR_INVALIDE=20302;
	
	/**
	 * L'article est invalide.
	 */
	public static final int REGLE_ENCHERE_ARTICLE_INVALIDE=20303;
	
	/**
	 * L'article est indisponible.
	 */
	public static final int REGLE_ENCHERE_ARTICLE_INDISPONIBLE=20304;
}
