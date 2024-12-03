package com.atoudeft.controleur;

import com.atoudeft.client.Client;
import com.atoudeft.vue.PanneauPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurOperationsCompte implements ActionListener {
    private Client client;
    private PanneauPrincipal panneauPrincipal;
    private final String[] actions = {"DEPOT", "RETRAIT", "TRANSFER", "FACTURE"};

    public EcouteurOperationsCompte(Client client, PanneauPrincipal panneauPrincipal) {
        this.client = client;
        this.panneauPrincipal = panneauPrincipal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Verifier si le bouton cliqué est celui pour creer un compte epargne
        String action = e.getActionCommand();
        switch (action) {
            case "EPARGNE":
                client.envoyer("EPARGNE");
                break;

            case "DEPOT":
                //Si la case est vide, sa veut dire qu'on vient de faire apparaitre le panneau
                if (panneauPrincipal.getPanneauDynamique(action).getMontant().isEmpty()) {
                    for (String s : actions) {
                        panneauPrincipal.getPanneauDynamique(s).cleartxt(); //Si on change d'operation alors qu'on avais un nombre deja ecris dans un autre text field, il faut reset pour eviter des bugs
                    }
                    panneauPrincipal.afficherPanneauDynamique(action);
                }
                //Si la case n'est pas vide, alors nous avons mis un montant a deposer, et nous allons l'envoyer au serveur
                else {
                    client.envoyer("DEPOT" + " " + panneauPrincipal.getPanneauDynamique(action).getMontant());
                    panneauPrincipal.getPanneauDynamique(action).cleartxt();  //Reset le montant qui est dans la boite de dialogue
                    panneauPrincipal.cacherPanneauDynamique();

                    panneauPrincipal.getPanneauOperationsCompte()
                            .getLblSolde()
                            .setText("Solde : Mise à jour en attente...");
                }

                break;
            case "RETRAIT":
                //Meme logique
                if (panneauPrincipal.getPanneauDynamique(action).getMontant().isEmpty()) {
                    for (String s : actions) {
                        panneauPrincipal.getPanneauDynamique(s).cleartxt(); //Meme logique
                    }
                        panneauPrincipal.afficherPanneauDynamique(action);
                }
                //Meme logique
                else {
                    client.envoyer("RETRAIT" + " " + panneauPrincipal.getPanneauDynamique(action).getMontant());
                    panneauPrincipal.getPanneauDynamique(action).cleartxt();  //Meme logique
                    panneauPrincipal.cacherPanneauDynamique();

                    panneauPrincipal.getPanneauOperationsCompte()
                            .getLblSolde()
                            .setText("Solde : Mise à jour en attente...");
                }
                break;
            case "TRANSFER":
                if (panneauPrincipal.getPanneauDynamique(action).getMontant().isEmpty()) {
                    for (String s : actions) {
                        panneauPrincipal.getPanneauDynamique(s).cleartxt();
                    }
                    panneauPrincipal.afficherPanneauDynamique(action);
                }

                else {
                    client.envoyer("TRANSFER" + " " + panneauPrincipal.getPanneauDynamique(action).getMontant() + " " + panneauPrincipal.getPanneauDynamique(action).getTransNum());
                    panneauPrincipal.getPanneauDynamique(action).cleartxt();
                    panneauPrincipal.cacherPanneauDynamique();

                    panneauPrincipal.getPanneauOperationsCompte()
                            .getLblSolde()
                            .setText("Solde : Mise à jour en attente...");
                }
                break;
            case "FACTURE":
                if (panneauPrincipal.getPanneauDynamique(action).getMontant().isEmpty()) {
                    for (String s : actions) {
                        panneauPrincipal.getPanneauDynamique(s).cleartxt();                     }
                    panneauPrincipal.afficherPanneauDynamique(action);
                }
                else {
                    client.envoyer("FACTURE" + " " + panneauPrincipal.getPanneauDynamique(action).getMontant() + " " + panneauPrincipal.getPanneauDynamique(action).getFacNum() + " " + panneauPrincipal.getPanneauDynamique(action).getFacDesc());
                    panneauPrincipal.getPanneauDynamique(action).cleartxt();
                    panneauPrincipal.cacherPanneauDynamique();

                    panneauPrincipal.getPanneauOperationsCompte()
                            .getLblSolde()
                            .setText("Solde : Mise à jour en attente...");
                }
                break;
            default:
                System.out.println("Erreur???");
        }

    }
}



