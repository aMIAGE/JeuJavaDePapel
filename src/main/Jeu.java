package main;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.Timer;

import elmtJeu.Etape;
import elmtJeu.Fenetre;
import elmtJeu.Joueur;
import utils.MsgPerdu;

/**
 * <b>Classe principale de notre jeu.</b>
 * <p>
 * La classe jeu a comme propriétés:
 * <ul>
 * <li>Une image.</li>
 * <li>Un texte.</li>
 * <li>Un compteur.</li>
 * </ul>
 * @see Etape
 */

public class Jeu{
	/**
	 * <p> Le nom du jeu </p>
	 */
	public static final String NAME = "La Java De Papel";
	/**
	 * <p> La durée maximum du jeu en minutes </p>
	 */
	public static final int DUREE = 20;
	/**
	 * <p> Le joueur, propriété de classe du jeu </p>
	 */
	private static Joueur joueur;
	/**
	 * <p> La fenetre, propriété de classe du jeu </p>
	 */
	private static Fenetre fen;
	/**
	 * <p> Un timer, propriété de classe du jeu </p>
	 */
	private static Timer timer;
	
	/**
	 * Constructeur Jeu
	 * Appelle la méthode de classe creationJeu.
	 */
	public static void main(String[] args) throws IOException {
		Jeu.creationJeu();
		
		
	}
	
	/**
	 *<h1> Creation du jeu</h1>
	 * Instanciation du joueur, instanciation de la fenetre et démarrage du timer.
	 */
	public static void creationJeu() throws IOException {

		Jeu.joueur = new Joueur(228,306); 
		
		Jeu.fen = new Fenetre();
		
		
		timer = new Timer(DUREE*1000*60, new ActionListener() {
			  public void actionPerformed(ActionEvent a) {
				 MsgPerdu.createMessage("Le temps est écoulé, vous avez perdu ... Voulez vous recommencer ?");
				 timer.stop();
			  }
		  });
		  timer.start();
	}
	
	public static Joueur getJoueur() {
		return Jeu.joueur;
	}
	
	public static Fenetre getFenetre() {
		return Jeu.fen;
	}
}
