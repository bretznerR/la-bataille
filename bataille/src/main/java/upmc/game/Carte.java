package upmc.game;

/**
 * Created by Raphaël Bretzner on 12/09/2017.
 */
public class Carte {

    static String[] couleurs = {"pique","trefle","coeur","carreau"};
    private String couleur;
    private int nombre;

    public Carte(String couleur, int nombre) {
        this.couleur = couleur;
        this.nombre = nombre;
    }

    public int compareA(Carte c) {
        return this.nombre - c.getNombre();
    }

    private String nomValeur() {
        String nomValeur;
        if(this.nombre == 11) nomValeur="valet";
        else if(this.nombre == 12) nomValeur="reine";
        else if(this.nombre ==13) nomValeur="roi";
        else nomValeur = String.valueOf(this.nombre);
        return nomValeur;
    }

    public int getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return this.nomValeur()+" de "+this.couleur;
    }
}
