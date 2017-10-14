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
import java.util.Scanner;

public class Bataille {
    public static void main(String[] args) {

        JeuCartes jeuCartes = new JeuCartes();
        jeuCartes.melangeJeu();
        ArrayList<Carte> main1 = new ArrayList<Carte>();
        ArrayList<Carte> main2 = new ArrayList<Carte>();
        suffle(jeuCartes, main1, main2);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Nom du Joueur 1:");
        String j1 = scanner.nextLine();
        System.out.println("Nom du Joueur 2:");
        String j2 = scanner.nextLine();

        scanner.close();

        Joueur joueur1 = new Joueur(main1, j1);
        Joueur joueur2 = new Joueur(main1, j2);
        joueur1.melangeJeu();
        joueur2.melangeJeu();

        System.out.println("----------------------------------------------------");
        System.out.println("|                                                   |");
        System.out.println("|                                                   |");
        System.out.println("|              Joueur Versus Joueur                 |");
        System.out.println("|                                                   |");
        System.out.println("|                                                   |");
        System.out.println("----------------------------------------------------");

        lancementBataille(joueur1, joueur2);

        System.out.println(joueur1.getCartes().size());
        System.out.println(joueur2.getCartes().size());
    }

    private static void suffle(JeuCartes jeuCartes, ArrayList<Carte> main1, ArrayList<Carte> main2) {
        for (int i = 0; i < jeuCartes.getListeCartes().size(); i++) {
            if (i % 2 == 0) {
                main1.add(jeuCartes.getListeCartes().get(i));
            }
            else {
                main2.add(jeuCartes.getListeCartes().get(i));
            }
        }
    }

    public static void lancementBataille(Joueur joueur1, Joueur joueur2) {
        Scanner sc = new Scanner(System.in);
        int choix = 0;
        int compteur = 0;
        while ((joueur1.getCartes().size() > 0) || (joueur2.getCartes().size() > 0)) {
            System.out.println("----------------------------------------------------");
            System.out.println("----------------------------------------------------");
            System.out.println("|                                                   |");
            System.out.println("|                                                   |");
            System.out.println("|              Tour numéro : " + compteur +  "                      |");
            System.out.println("|                                                   |");
            System.out.println("|                                                   |");
            System.out.println("----------------------------------------------------");
            System.out.println("----------------------------------------------------");
            System.out.println();
            if (compteur%2 == 0) {
                System.out.println(joueur1.getNom());
            }
            else {
                System.out.println(joueur2.getNom());
            }
            if (joueur1.getCarte().superieureA(joueur2.getCarte())) {
                System.out.println(joueur1.getNom() + " a pour carte : " + joueur1.getCarte());
                System.out.println(joueur2.getNom() + " a pour carte : " + joueur2.getCarte());
                System.out.println("\t " + joueur1.getNom() + " gagne ce tour");
                System.out.println(joueur1.getCarte().superieureA(joueur2.getCarte()));

                joueur1.getCartes().add(0, joueur2.getCarte());
                joueur1.getCartes().add(0, joueur1.getCarte());
                joueur1.retirerCarte();
                joueur2.retirerCarte();
                joueur1.setScore(joueur1.getScore()+1);
            }
            else if (joueur1.getCarte().superieureA(joueur2.getCarte())) {
                System.out.println(joueur2.getNom() + " a pour carte : " + joueur2.getCarte());
                System.out.println(joueur1.getNom() + " a pour carte : " + joueur1.getCarte());
                System.out.println("\t " + joueur2.getNom() + " gagne ce tour");
                System.out.println(joueur1.getCarte());
                System.out.println(joueur2.getCarte());

                joueur2.getCartes().add(0, joueur1.getCarte());
                joueur2.getCartes().add(0, joueur2.getCarte());
                joueur1.retirerCarte();
                joueur1.retirerCarte();
                joueur2.setScore(joueur2.getScore()+1);
            }
            else {
                if (joueur1.getCartes().size() > 2 && joueur2.getCartes().size() > 2) {
                    System.out.println(joueur1.getNom() + " a pour carte : " + joueur1.getCarte());
                    System.out.println(joueur2.getNom() + " a pour carte : " + joueur2.getCarte());
                    System.out.println("BATAILLE");
                    batailleCondition(joueur1, joueur2);
                }
                else {
                    break;
                }

            }
            compteur++;
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("=================================================");
        if (joueur1.getScore() > joueur2.getScore()) {
            System.out.println(joueur1.getNom() + " a gagné ! Score de : " + joueur1.getScore());

        }
        else if (joueur2.getScore() > joueur1.getScore()) {
            System.out.println(joueur2.getNom() + " a gagné ! Score de :  " + joueur2.getScore());
        }
        System.out.println("=================================================");
    }
    public static void batailleCondition(Joueur j1, Joueur j2) {
        Carte tmp1 = j1.getCarte();
        Carte tmp2 = j2.getCarte();

        if (j1.getCartes().get(j1.getCartes().size() - 2).superieureA(j2.getCartes().get(j2.getCartes().size() - 2))) {
            j1.getCartes().add(0, tmp2);
            j1.getCartes().add(0, j2.getCartes().get(j2.getCartes().size() - 2));
            j1.getCartes().add(0, j1.getCartes().get(j1.getCartes().size() - 2));
            j1.getCartes().remove(j1.getCartes().get(j1.getCartes().size() - 2));
            j2.getCartes().remove(tmp2);
            j2.getCartes().remove(j2.getCartes().get(j2.getCartes().size() - 2));
            j1.setScore(j1.getScore()+1);
            System.out.println(j1.getNom() + " a pour carte : " + j1.getCarte());
            System.out.println(j2.getNom() + " a pour carte : " + j2.getCarte());
            System.out.println("\t " + j1.getNom() + " gagne ce tour");
        }
        else {
            j2.getCartes().add(0, tmp1);
            j2.getCartes().add(0, j1.getCartes().get(j1.getCartes().size() - 2));
            j2.getCartes().add(0, j2.getCartes().get(j2.getCartes().size() - 2));
            j2.getCartes().remove(j2.getCartes().get(j2.getCartes().size() - 2));
            j1.getCartes().remove(tmp1);
            j1.getCartes().remove(j1.getCartes().get(j1.getCartes().size() - 2));
            j2.setScore(j2.getScore()+1);
            System.out.println(j1.getNom() + " a pour carte : " + j1.getCarte());
            System.out.println(j2.getNom() + " a pour carte : " + j2.getCarte());
            System.out.println("\t " + j2.getNom() + " gagne ce tour");
        }
    }
}

