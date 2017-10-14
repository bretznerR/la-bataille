package upmc.game;

public class Carte {

    private Couleur couleur;
    private int valeur;

    public Carte(Couleur c, int v) {
        if (v >= 1 && v <= 13) {
            this.valeur = v;
        }
        this.couleur = c;
    }

    public String getNomFromValue() {
        if (valeur > 9) {
            if (valeur == 10) return "valet";
            if (valeur == 11) return "dame";
            if (valeur == 12) return "roi";
            if (valeur == 13 ) return "AS";
        }
        return null;
    }

    public boolean superieureA(Carte c) {
        return this.valeur > c.getValeur();
    }

    public String toString() {
        if (this.valeur > 9) {
            return this.getNomFromValue() + " de " + this.couleur;
        }
        return this.valeur + " de " + this.couleur;
    }

    public int getValeur() {
        return valeur;
    }



}
