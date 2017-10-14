package upmc.game;

import jdk.nashorn.internal.scripts.JO;

import java.util.ArrayList;
import java.util.Scanner;

public class LectureConsole implements LecturePseudo {

    private Scanner scChoix;
    private Scanner sc1;
    private Scanner sc2;

    public LectureConsole() {
        this.scChoix = new Scanner(System.in);
        this.sc1 = new Scanner(System.in);
        this.sc2 = new Scanner(System.in);
    }

    public ArrayList<String> lirePseudo() {
        ArrayList<String> pseudo = new ArrayList<String>();
        System.out.println("Joueur 1, saisissez votre nom :");
        pseudo.add(sc1.nextLine());

        int choix = 0;
        while(choix!=1 && choix!=2) {
            System.out.println("*****************************************************");
            System.out.println("Jouer avec un deuxième joueur (1) ou avec l'IA ? (2)");
            System.out.println("*****************************************************");
            choix = this.scChoix.nextInt();
            if(choix==1) {
                System.out.println("Joueur 2, saisissez votre nom :");
                pseudo.add(this.sc2.nextLine());
            } else if(choix==2) {
                pseudo.add("IA");
            } else {
                System.out.print("Erreur : ");
            }
        }
        System.out.println(pseudo);

        return pseudo;
    }
}
