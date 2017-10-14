package upmc.game;

import java.util.ArrayList;
import java.util.Random;

public class JeuCartes{

    public final int TAILLE_JEU=54;
    public Random generateur= new Random();
    private ArrayList<Carte> listeCartes;

    public JeuCartes() {
        this.listeCartes = new ArrayList<Carte>();
        Couleur [] c = Couleur.values();

        for (int i = 0; i < Couleur.values().length; i++) {
            for (int j = 1; j < 14; j++) {
                this.listeCartes.add(new Carte(c[i], j));
            }
        }
    }
    public void melangeJeu() {
        // On commence par construire une liste pour recevoir les cartes
        // mélangées
        ArrayList<Carte> jeuMelange = new ArrayList<Carte>();
        int n;
        while (!this.listeCartes.isEmpty()) {
            // Calcul d'un indice aléatoire dans le jeu de cartes
            n = (int) (Math.random() * this.listeCartes.size());
            // Déplacement d'une carte du jeu vers la liste mélangées
            jeuMelange.add(this.listeCartes.remove(n));
        }
        // Remplacement du jeu par le nouveau jeu mélangé
        this.listeCartes = jeuMelange;
    }

    public JeuCartes(ArrayList<Carte> c) {
        this.listeCartes = c;
    }

    public JeuCartes(int tmp) {
        this.listeCartes = new ArrayList<Carte>();
    }


    @Override
    public String toString() {
        return "JeuCartes{" +
                "listeCartes=" + listeCartes +
                '}' +
                listeCartes.size();
    }

    public ArrayList<Carte> getListeCartes() {
        return listeCartes;
    }
}