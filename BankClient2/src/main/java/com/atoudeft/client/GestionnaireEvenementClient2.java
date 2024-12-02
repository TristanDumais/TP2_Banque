package com.atoudeft.client;

import com.atoudeft.commun.evenement.Evenement;
import com.atoudeft.commun.evenement.GestionnaireEvenement;
import com.atoudeft.commun.net.Connexion;
import com.atoudeft.vue.PanneauPrincipal;
import com.programmes.MainFrame;

import javax.swing.*;

public class GestionnaireEvenementClient2 implements GestionnaireEvenement {
    private Client client;
    private PanneauPrincipal panneauPrincipal;


    /**
     * Construit un gestionnaire d'événements pour un client.
     *
     * @param client Client Le client pour lequel ce gestionnaire gère des événements
     */
    public GestionnaireEvenementClient2(Client client, PanneauPrincipal panneauPrincipal) {

        this.client = client;
        this.panneauPrincipal = panneauPrincipal;
        this.client.setGestionnaireEvenement(this);
    }
    @Override
    public void traiter(Evenement evenement) {
        Object source = evenement.getSource();
        //Connexion cnx;
        String typeEvenement, arg, str;
        int i;
        String[] t;
        MainFrame fenetre;

        if (source instanceof Connexion) {
            //cnx = (Connexion) source;
            typeEvenement = evenement.getType();
            switch (typeEvenement) {
                /******************* COMMANDES GÉNÉRALES *******************/
                case "END": //Le serveur demande de fermer la connexion
                    client.deconnecter(); //On ferme la connexion
                    break;
                /******************* CREATION et CONNEXION *******************/
//                case "HIST": //Le serveur a renvoyé
//                    panneauPrincipal.setVisible(true);
//                    JOptionPane.showMessageDialog(null,"Panneau visible");
//                    cnx.envoyer("LIST");
//                    arg = evenement.getArgument();
//                    break;
                case "OK":
                    panneauPrincipal.setVisible(true);
                    fenetre = (MainFrame)panneauPrincipal.getTopLevelAncestor();
                    fenetre.setTitle(MainFrame.TITRE);//+" - Connecté"
                    break;
                case "NOUVEAU":
                    arg = evenement.getArgument();
                    if (arg.trim().startsWith("NO")) {
                        JOptionPane.showMessageDialog(panneauPrincipal,"Nouveau refusé");
                    }
                    else {
                        panneauPrincipal.cacherPanneauConnexion();
                        panneauPrincipal.montrerPanneauCompteClient();
                        str = arg.substring(arg.indexOf("OK")+2).trim();
                        panneauPrincipal.ajouterCompte(str);
                    }
                    break;
                case "CONNECT":
                    arg = evenement.getArgument();
                    if (arg.trim().startsWith("NO")) {
                        JOptionPane.showMessageDialog(panneauPrincipal,"Connexion refusée");
                    }
                    else {
                        panneauPrincipal.cacherPanneauConnexion();
                        panneauPrincipal.montrerPanneauCompteClient();
                        str = arg.substring(arg.indexOf("OK")+2).trim();
                        t = str.split(":");
                        for (String s:t) {
                            panneauPrincipal.ajouterCompte(s.substring(0,s.indexOf("]")+1));
                        }
                    }
                    break;
                /******************* SÉLECTION DE COMPTES *******************/
                case "EPARGNE":
                    arg = evenement.getArgument();
                    if (arg.trim().startsWith("NO")) {
                        //Afficher un message d'erreur si l'operation échoue
                        JOptionPane.showMessageDialog(panneauPrincipal, "Erreur : Impossible de créer le compte épargne.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    } else {
                        //Recuperer le numero de compte et l'ajouter à la liste des comptes
                        String numeroCompte = arg.substring(arg.indexOf("OK") + 2).trim();
                        panneauPrincipal.ajouterCompte(numeroCompte);
                        //Message succes
                        JOptionPane.showMessageDialog(panneauPrincipal, "Compte épargne créé avec succès : " + numeroCompte, "Succès", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                case "SELECT":
                    arg = evenement.getArgument();
                    if (arg.trim().startsWith("NO")) {
                        JOptionPane.showMessageDialog(panneauPrincipal, "Erreur : Impossible de sélectionner le compte.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    } else {
                        try {
                            //Separe la reponse du serveur en plusieurs parties (Status, Numero de compte, Solde)
                            String[] parts = arg.split("\\s+"); // Divise par espaces

                            if (parts.length == 3 && "OK".equals(parts[0])) {
                                String numeroCompte = parts[1]; //Le numero de compte
                                double solde = Double.parseDouble(parts[2]); //Le solde du compte

                                // Mettre à jour le panneau des operations avec le nouveau solde
                                panneauPrincipal.getPanneauOperationsCompte().getLblSolde().setText("Solde : "+ String.valueOf(parts[2]));

                                // Afficher un message de confirmation
                                JOptionPane.showMessageDialog(
                                        panneauPrincipal,
                                        "Compte sélectionné avec succès : " + numeroCompte + ". Solde : " + solde + " $",
                                        "Succès",
                                        JOptionPane.INFORMATION_MESSAGE
                                );
                            } else {
                                JOptionPane.showMessageDialog(
                                        panneauPrincipal,
                                        "Réponse inattendue du serveur : " + arg,
                                        "Erreur",
                                        JOptionPane.ERROR_MESSAGE
                                );
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(
                                    panneauPrincipal,
                                    "Erreur lors de la récupération du solde : " + arg,
                                    "Erreur",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                    break;

                /******************* OPÉRATIONS BANCAIRES *******************/
                case "DEPOT" :
                    arg = evenement.getArgument();
                    JOptionPane.showMessageDialog(panneauPrincipal,"DEPOT "+arg);
                    break;
                case "RETRAIT" :
                    arg = evenement.getArgument();
                    JOptionPane.showMessageDialog(panneauPrincipal,"RETRAIT "+arg);
                    break;
                case "FACTURE" :
                    arg = evenement.getArgument();
                    JOptionPane.showMessageDialog(panneauPrincipal,"FACTURE" + arg);
                    break;
                case "TRANSFER" :
                    arg = evenement.getArgument();
                    JOptionPane.showMessageDialog(panneauPrincipal,"TRANSFER " + arg);
                    break;
                /******************* TRAITEMENT PAR DÉFAUT *******************/
                default:
                    System.out.println("RECU : "+evenement.getType()+" "+evenement.getArgument());
            }
        }
    }
}