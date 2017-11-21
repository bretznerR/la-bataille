package upmc.game;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Raphaël Bretzner on 12/09/2017.
 */
public class Joueur {

    private ArrayList<Carte> cartesEnMain;
    private int score;
    private String nom;
    private boolean estHumain;

    public Joueur() {
        this.score = 0;
        this.cartesEnMain = new ArrayList<Carte>();
        this.estHumain = true;
    }

    public Joueur(String nom) {
        this.nom = nom;
        this.score = 0;
        this.cartesEnMain = new ArrayList<Carte>();
        this.estHumain = true;
    }

    public Joueur(String nom, boolean estHumain) {
        this.nom = nom;
        this.score = 0;
        this.cartesEnMain = new ArrayList<Carte>();
        this.estHumain = estHumain;
    }

    public Carte jouerCarte() {
        Carte c = cartesEnMain.get(0);
        cartesEnMain.remove(cartesEnMain.get(0));
        return c;
    }

    public void ajouterCarteEnMain(Carte c) {
        this.cartesEnMain.add(c);
    }

    public void ajouterPlusieursCartesEnMain(ArrayList<Carte> c) {
        this.cartesEnMain.addAll(c);
    }

    public void gagneUnPoint() {
        this.score++;
    }

    public String getNom() {
        return nom;
    }

    public int getScore() {
        return score;
    }

    public boolean estHumain() {
        return estHumain;
    }

    public void setEstHumain(boolean estHumain) {
        this.estHumain = estHumain;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Carte> getCartesEnMain() {
        return cartesEnMain;
    }

    public boolean tireCarte() {
        if (this.cartesEnMain.size() > 0) {
            this.cartesEnMain.remove(this.cartesEnMain.size() - 1);
            return true;
        }
        return false;
    }

    public boolean deckVide() {
        return this.cartesEnMain.isEmpty();
    }
}
