package com.atoudeft.controleur;

import com.atoudeft.client.Client;
import com.atoudeft.vue.PanneauConfigServeur;
import com.atoudeft.vue.PanneauPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2024-11-01
 */
public class EcouteurMenuPrincipal implements ActionListener {
    private Client client;
    private JFrame fenetre;
    private PanneauPrincipal panneauPrincipal;

    public EcouteurMenuPrincipal(Client client, JFrame fenetre, PanneauPrincipal panneauPrincipal) {
        this.client = client;
        this.fenetre = fenetre;
        this.panneauPrincipal = panneauPrincipal;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();
        String action;
        String alias;
        int res;

        if (source instanceof JMenuItem) {
            action = ((JMenuItem)source).getActionCommand();
            switch (action) {
                case "CONNECTER":
                    if (!client.isConnecte()) {
                        if (!client.connecter()) {
                            JOptionPane.showMessageDialog(fenetre, "Le serveur ne répond pas");
                            break;
                        }
                    }

                    break;
                case "DECONNECTER":
                    if (!client.isConnecte())
                        break;
                    res = JOptionPane.showConfirmDialog(fenetre, "Vous allez vous déconnecter",
                            "Confirmation Déconnecter",
                            JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                    if (res == JOptionPane.OK_OPTION){
                        client.deconnecter();
                    }
                    break;
                case "CONFIGURER":
                    String adresseServ = client.getAdrServeur();
                    int portServ = client.getPortServeur();
                    boolean configurationValide = false;

                    while (!configurationValide) {
                        //Créer un panneau de configuration avec les valeurs actuelles
                        PanneauConfigServeur panneauConfig = new PanneauConfigServeur(adresseServ, portServ);

                        //Afficher le panneau dans une boîte de dialogue
                        int resultat = JOptionPane.showConfirmDialog(
                                fenetre,
                                panneauConfig,
                                "Configuration serveur",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.PLAIN_MESSAGE
                        );

                        //Verifier si l'utilisateur a cliquer sur OK
                        if (resultat == JOptionPane.OK_OPTION) {
                            try {
                                //Recuperer les nouvelles valeurs saisies
                                String nouvelleAdresse = panneauConfig.getAdresseServeur();
                                int nouveauPort = Integer.parseInt(panneauConfig.getPortServeur());

                                //Fournir les nouvelles donnees au client
                                client.setAdrServeur(nouvelleAdresse);
                                client.setPortServeur(nouveauPort);

                                configurationValide = true; //Pour sortir de la boucle
                            } catch (NumberFormatException e) {
                                //Afficher un message d'erreur si le port n'est pas un entier
                                JOptionPane.showMessageDialog(
                                        fenetre,
                                        "Le numéro de port doit être un entier valide.",
                                        "Erreur",
                                        JOptionPane.ERROR_MESSAGE
                                );
                            }
                        } else {
                            // L'utilisateur a cliquer sur annuler, donc on sort de la boucle
                            configurationValide = true;
                        }
                    }
                    break;
                case "QUITTER":
                    if (client.isConnecte()) {
                        res = JOptionPane.showConfirmDialog(fenetre, "Vous allez vous déconnecter",
                                "Confirmation Quitter",
                                JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                        if (res == JOptionPane.OK_OPTION){
                            client.deconnecter();
                            System.exit(0);
                        }
                    }
                    else
                        System.exit(0);
                    break;
            }
        }
    }
}