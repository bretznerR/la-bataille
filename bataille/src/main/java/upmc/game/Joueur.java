package upmc.game;

import java.util.ArrayList;

public class Joueur {
    private ArrayList<Carte> uneMain;
    private String nom;
    private int score;

    public Joueur(ArrayList uneMain, String nom) {
        this.uneMain = new ArrayList<Carte>(uneMain);
        this.nom = nom;
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getNom() {
        return nom;
    }

    public Carte getCarte() {
        return this.uneMain.get(this.uneMain.size()-1);
    }

    public ArrayList<Carte> getCartes() {
        return this.uneMain;
    }

    public void retirerCarte() {
        this.uneMain.remove(this.uneMain.size()-1);
    }

    public void melangeJeu() {
        // On commence par construire une liste pour recevoir les cartes
        // mélangées
        ArrayList<Carte> jeuMelange = new ArrayList<Carte>();
        int n;
        while (!this.uneMain.isEmpty()) {
            // Calcul d'un indice aléatoire dans le jeu de cartes
            n = (int) (Math.random() * this.uneMain.size());
            // Déplacement d'une carte du jeu vers la liste mélangées
            jeuMelange.add(this.uneMain.remove(n));
        }
        // Remplacement du jeu par le nouveau jeu mélangé
        this.uneMain = jeuMelange;
    }
}
