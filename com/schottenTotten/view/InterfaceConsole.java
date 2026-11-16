package com.schottenTotten.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.schottenTotten.model.Borne;
import com.schottenTotten.model.Carte;
import com.schottenTotten.model.CarteClan;

// affichage et saisie dans le terminal sans aucune règle du jeu
public class InterfaceConsole {

    // un seul lecteur pour tout le programme
    private BufferedReader lecteur = new BufferedReader(new InputStreamReader(System.in));

    // ---------- affichage ----------

    public void afficherMessage(String message) {
        System.out.println(message);
    }

    public void afficherTour(int numeroTour, String nomJoueur, int numeroJoueur) {
        System.out.println();
        System.out.println("--- tour " + numeroTour + " : " + nomJoueur + " (joueur " + numeroJoueur + ") ---");
    }

    // une ligne par borne  cartes du joueur 1 [ numero ] cartes du joueur 2
    // * borne gagnée par le joueur 1  + borne gagnée par le joueur 2
    public void afficherBornes(List<Borne> bornes) {
        for (Borne b : bornes) {
            String marque = " ";
            if (b.getGagnant() == 1) {
                marque = "*";
            }
            if (b.getGagnant() == 2) {
                marque = "+";
            }
            System.out.println(texteCote(b, 1) + "[ " + b.getNumero() + " ]" + marque + " " + texteCote(b, 2));
        }
        System.out.println();
    }

    // trois places par côté  une place vide s'affiche --
    private String texteCote(Borne borne, int cote) {
        List<CarteClan> cartes = borne.getCombinaison(cote).getCartes();
        String texte = "";
        for (int i = 0; i < 3; i++) {
            if (i < cartes.size()) {
                texte = texte + cartes.get(i) + " ";
            } else {
                texte = texte + "-- ";
            }
        }
        return texte;
    }

    public void afficherMain(List<Carte> main) {
        String texte = "main :";
        for (int i = 0; i < main.size(); i++) {
            texte = texte + "  " + (i + 1) + ")" + main.get(i);
        }
        System.out.println(texte);
    }

    public void afficherVainqueur(String nom) {
        System.out.println();
        if (nom == null) {
            System.out.println("égalité");
        } else {
            System.out.println(nom + " gagne la partie");
        }
    }

    // ---------- saisie ----------

    // redemande tant que la saisie n'est pas un entier entre min et max
    public int lireEntier(String invite, int min, int max) {
        while (true) {
            System.out.print(invite + " [" + min + "-" + max + "] : ");
            try {
                int n = Integer.parseInt(lireLigne().trim());
                if (n >= min && n <= max) {
                    return n;
                }
            } catch (NumberFormatException e) {
                // pas un nombre  on redemande
            }
            System.out.println("saisie invalide");
        }
    }

    public String lireTexte(String invite) {
        System.out.print(invite + " : ");
        return lireLigne().trim();
    }

    // si l'entrée est fermée on arrête le programme
    private String lireLigne() {
        String ligne = null;
        try {
            ligne = lecteur.readLine();
        } catch (IOException e) {
            ligne = null;
        }
        if (ligne == null) {
            System.out.println("fin de la saisie");
            System.exit(0);
        }
        return ligne;
    }
}