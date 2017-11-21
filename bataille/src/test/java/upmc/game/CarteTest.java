package upmc.game;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CarteTest {
    private Carte carte;

    @Before
    public void CreationJeu() {
        carte = new Carte(Carte.couleurs[2], 3);
    }

    @Test
    public void testCarteInferieure() {
        Carte carteTest = new Carte(Carte.couleurs[1], 2);

        int comparaison = carte.compareA(carteTest);
        boolean result = comparaison==1;

        assertTrue(result);
    }

    @Test
    public void testCarteSuperieure() {
        Carte carteTest = new Carte(Carte.couleurs[2], 4);

        int comparaison = carte.compareA(carteTest);
        boolean result = comparaison==-1;

        assertTrue(result);
    }

    @Test
    public void testCarteEgale() {
        Carte carteTest = new Carte(Carte.couleurs[2], 1);

        int comparaison = carte.compareA(carteTest);
        boolean result = comparaison==0;

        assertTrue(result);
    }
}