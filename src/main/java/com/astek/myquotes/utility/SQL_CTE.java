package com.astek.myquotes.utility;

public class SQL_CTE {

	public static final String concatSearch = "CONCAT(q.value, q.contexte, q.lieu, q.titre, CONCAT(q.dtEvenement, ''), CONCAT(q.dtCreation, ''), q.auteur.prenom, q.auteur.nom, q.auteur.nickname )";
	public static final String selectQuoteLike = "SELECT q FROM Quote q WHERE "+ concatSearch +" LIKE";

	public static final String selectQuotePublic = "SELECT q FROM Quote q WHERE q.privee= 'false'";
}
