package upmc.game;

import java.util.Scanner;

public class MenuPseudo {

    public LecturePseudo lirePseudo() {
        Scanner scChoix = new Scanner(System.in);
        int choix = 0;

        while(choix!=1 && choix!=2) {
            System.out.println("----------------------------------------------------");
            System.out.println("|                                                   |");
            System.out.println("|                                                   |");
            System.out.println("|    Saisir noms par console : (1) ou Fichier (2)   |");
            System.out.println("|                                                   |");
            System.out.println("|                                                   |");
            System.out.println("----------------------------------------------------");
            choix = scChoix.nextInt();
            if(choix==1) {
                LectureConsole console = new LectureConsole();
                return console;
            } else if(choix==2) {
                LectureFichier fichier = new LectureFichier();
                return fichier;
            } else {
                System.out.print("Erreur : ");
            }
        }

        return null;
    }
}
