// Copyright 2017 Pierre Talbot (IRCAM)

// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at

//     http://www.apache.org/licenses/LICENSE-2.0

// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package upmc.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Bataille {

  private static ArrayList<Carte> pioche;
  private int scoreLimite;
  private Scanner choixJoueur;
  private Scanner choixJoueur2;
  private Scanner choixJeu;
  private Joueur j1;
  private Joueur j2;
  private int nbTour;
  private Carte c1,c2;
  private MenuPseudo menuPseudo;
  private ArrayList<String> pseudos;

  public static void main(String[] args) {
//    LectureFichier fichier = new LectureFichier();
//    fichier.lirePseudo();


    Bataille b = new Bataille();
    //Bataille b = new Bataille(10);
    b.jouer();
  }

  public Bataille() {
    this.scoreLimite = 20;
    this.choixJoueur = new Scanner(System.in);
    this.choixJoueur2 = new Scanner(System.in);
    this.choixJeu = new Scanner(System.in);
    this.j1 = new Joueur();
    this.j2 = new Joueur();
    this.nbTour = 1;
    this.menuPseudo = new MenuPseudo();
  }

  public Bataille(int scoreLimite) {
    this.scoreLimite = scoreLimite;
    this.choixJoueur = new Scanner(System.in);
    this.choixJoueur2 = new Scanner(System.in);
    this.choixJeu = new Scanner(System.in);
    this.j1 = new Joueur();
    this.j2 = new Joueur();
    this.nbTour = 1;
  }

  public void jouer() {
    System.out.println("----------------------------------------------------");
    System.out.println("|                                                   |");
    System.out.println("|                                                   |");
    System.out.println("|                Jeu de la bataille                 |");
    System.out.println("|                                                   |");
    System.out.println("|                                                   |");
    System.out.println("----------------------------------------------------");

    LecturePseudo lecturePseudo = this.menuPseudo.lirePseudo();
    this.pseudos = lecturePseudo.lirePseudo();

    this.j1.setNom(pseudos.get(0));
    this.choixMultijoueur();
    this.creerJeuDeCarte();
    this.distributionDesCartes();

    //La Partie
    boolean partieFini=false;
    while(!joueurAGagnerLaPartie(j1) && !joueurAGagnerLaPartie(j2) && !partieFini) {
      partieFini = this.UnTour();
    }
    this.affichageVainqueurDeLaPartie();
  }

  public boolean UnTour() {
    System.out.println(" ============== Tour "+this.nbTour+" ==============");

    //TOUR JOUEUR 1
    this.c1 = tourJoueur(this.j1);
    if(this.c1 == null) return true;

    //TOUR JOUEUR 2
    this.c2 = tourJoueur(this.j2);
    if(this.c2 == null) return true;

    if(this.c1.compareA(this.c2) > 0) { //Si la carte du joueur 1 est meilleur que celle du joueur 2 alors joueur 1 gagne la manche
      this.j1.ajouterCarteEnMain(this.c1);
      this.j1.ajouterCarteEnMain(this.c2);
      System.out.println("-----------------------------------------");
      System.out.println("\t\t\t" + j1.getNom()+" remporte le tour");
      System.out.println("-----------------------------------------");
      this.j1.gagneUnPoint();
    }
    else if(this.c1.compareA(this.c2) < 0) { //Si la carte du joueur 2 est meilleur que celle du joueur 1 alors joueur 2 gagne la manche
      this.j2.ajouterCarteEnMain(this.c1);
      this.j2.ajouterCarteEnMain(this.c2);
      System.out.println("-----------------------------------------");
      System.out.println("\t\t\t" + j2.getNom()+" remporte le tour");
      System.out.println("-----------------------------------------");
      this.j2.gagneUnPoint();
    } else {
      this.grandeBataille();
    }
    this.affichageDuScore();
    this.nbTour++;
    return false;
  }

  public Carte tourJoueur(Joueur j) {
    Carte c = null;
    if(j.estHumain()) {
      System.out.println("\n\t" + j.getNom()+", à votre tour : \n \t(1 ou autre) pour jouer , (2) pour quitter.");
      String choixJoueur = choixJeu.nextLine();
      if(choixJoueur.equals("2")) {
        return c;
      }
    }
      c = j.jouerCarte();
      System.out.println("\t\t" + j.getNom()+" joue la carte : \n\t\t\t"+c.toString());
    return c;
  }

  public void distributionDesCartes() {
    System.out.println("Distribution des cartes...");
    this.j1.ajouterCarteEnMain(new Carte("Trefle",3)); // A RETIRER
    this.j2.ajouterCarteEnMain(new Carte("Trefle",3)); // A RETIRER
    while(pioche.size()!=0) {
      int pos1 = (int) (Math.random() *(pioche.size()));
      this.j1.ajouterCarteEnMain(pioche.get(pos1));
      pioche.remove(pos1);
      int pos2 = (int) (Math.random() *(pioche.size()));
      this.j2.ajouterCarteEnMain(pioche.get(pos2));
      this.pioche.remove(pos2);
    }
  }

  public void creerJeuDeCarte() {
    this.pioche = new ArrayList<Carte>();
    for(int i=0 ; i<4 ; i++) { //Coeur , Carreau , Trefle , Pique : 4
      for(int j=1 ; j<=13 ; j++) { //1 , 2 , 3 ... Valet , Reine , Roi : 13
        pioche.add(new Carte(Carte.couleurs[i],j));
      }
    }
  }

  public void choixMultijoueur() {
    if (pseudos.get(1).toString().equals("IA")) {
      this.j2.setNom("IA");
      this.j2.setEstHumain(false);
    }
    else {
      this.j2.setNom(pseudos.get(1));
    }
  }

  public void grandeBataille() {
    ArrayList<Carte> tas = new ArrayList<Carte>();
    tas.addAll(Arrays.asList(this.c1,this.c2));
    System.out.println("\"\\t\\t\\t\" + BATAILLE !!!\n");
    do {
      //Joueur 1
      this.c1 = this.tourJoueur(j1);
      tas.add(this.c1);
      //Joueur 2
      this.c2 = this.tourJoueur(j2);
      tas.add(this.c2);

      if (this.c1.compareA(this.c2) > 0) {
        j1.ajouterPlusieursCartesEnMain(tas);
        System.out.println("-------------------------------------------");
        System.out.println("\t\t\t" + this.j1.getNom()+" remporte la bataille");
        System.out.println("-------------------------------------------\n");
        j1.gagneUnPoint();
      }
      else if (this.c1.compareA(this.c2) < 0) {
        this.j2.ajouterPlusieursCartesEnMain(tas);
        System.out.println("-------------------------------------------");
        System.out.println("\t\t\t" + this.j2.getNom()+" \t\tremporte la bataille");
        System.out.println("-------------------------------------------\n");
        j2.gagneUnPoint();
      }
    } while(this.c1.compareA(this.c2) == 0);
  }

  public boolean joueurAGagnerLaPartie(Joueur j) {
    return j.getScore() >= this.scoreLimite;
  }

  public void affichageDuScore() {
    System.out.println("++++++++++++++++++++++++++++++++++++++++++");
    System.out.println("\t\t\tScore de "+j1.getNom()+" : "+j1.getScore());
    System.out.println("\t\t\tScore de "+j2.getNom()+" : "+j2.getScore());
    System.out.println("++++++++++++++++++++++++++++++++++++++++++\n");
  }

  public void affichageVainqueurDeLaPartie() {
    System.out.println("----------------------------------------------------");
    System.out.println("|                                                   |");
    System.out.println("|                                                   |");
    if(this.joueurAGagnerLaPartie(j1))
      System.out.println("|\t\t\t\t" + this.j1.getNom()+" a gagné la partie !    \t\t|");
    else {
      if (j2.getNom().equals("IA")) {
        System.out.println("|\t\t\t\t" + this.j2.getNom()+" a gagné la partie !    \t\t\t|");
      }
      else {
        System.out.println("|\t\t\t\t" + this.j2.getNom()+" a gagné la partie !    \t\t|");
      }
    }
    System.out.println("|                                                   |");
    System.out.println("|                                                   |");
    System.out.println("----------------------------------------------------");
  }
}
