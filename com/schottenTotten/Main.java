package com.schottenTotten;

import java.util.Random;

import com.schottenTotten.ai.IA;
import com.schottenTotten.controller.GestionTours;
import com.schottenTotten.controller.Humain;
import com.schottenTotten.controller.Jeu;
import com.schottenTotten.controller.VarianteBase;
import com.schottenTotten.model.Joueur;
import com.schottenTotten.view.InterfaceConsole;

// lancement : java com.schottenTotten.Main [graine]
// seule la variante de base est jouable pour l'instant
// avec une graine on rejoue exactement la même partie
public class Main {

    public static void main(String[] args) {
        InterfaceConsole console = new InterfaceConsole();

        Random alea = new Random();
        if (args.length > 0) {
            try {
                alea = new Random(Long.parseLong(args[0]));
            } catch (NumberFormatException e) {
                console.afficherMessage("graine ignorée : " + args[0] + " n'est pas un nombre");
            }
        }

        console.afficherMessage("=== Schotten-Totten (variante de base) ===");
        console.afficherMessage("cartes : valeur + couleur (R B V M J O)  * borne du joueur 1  + borne du joueur 2");
        int mode = console.lireEntier("1 humain vs humain  2 humain vs IA  3 IA vs IA", 1, 3);

        Joueur j1;
        Joueur j2;
        if (mode == 1) {
            j1 = new Humain(demanderNom(console, "nom du joueur 1", "Joueur 1"), 1, console);
            j2 = new Humain(demanderNom(console, "nom du joueur 2", "Joueur 2"), 2, console);
        } else if (mode == 2) {
            j1 = new Humain(demanderNom(console, "ton nom", "Joueur 1"), 1, console);
            j2 = new IA("IA", 2, alea);
        } else {
            j1 = new IA("IA 1", 1, alea);
            j2 = new IA("IA 2", 2, alea);
        }

        Jeu jeu = new VarianteBase(j1, j2, console, new GestionTours(), alea);
        jeu.lancer();
    }

    // si on ne tape rien on garde le nom par défaut
    private static String demanderNom(InterfaceConsole console, String invite, String parDefaut) {
        String nom = console.lireTexte(invite);
        if (nom.isEmpty()) {
            return parDefaut;
        }
        return nom;
    }
}