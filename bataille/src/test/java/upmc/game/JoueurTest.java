package upmc.game;

import java.util.Optional;
import static org.junit.Assert.*;

import org.junit.*;

public class JoueurTest {
    private Joueur joueur;

    @Before
    public void CreationJeu() {
        joueur = new Joueur("Joueur test");
    }

    @Test
    public void testDeckVideDebut() {
        boolean result = joueur.deckVide();

        assertTrue(result);
    }


    @Test
    public void testAjoutCarte() {
        joueur.ajouterCarteEnMain(new Carte(Carte.couleurs[2], 10));

        boolean result = joueur.deckVide();

        assertFalse(result);
    }

    @Test
    public void testSupprCarte() {
        joueur.ajouterCarteEnMain(new Carte(Carte.couleurs[2], 10)); //ajout d'une carte pour la supprimer
        joueur.tireCarte(); //Tire une carte

        boolean result = joueur.deckVide();

        assertTrue(result);
    }
}