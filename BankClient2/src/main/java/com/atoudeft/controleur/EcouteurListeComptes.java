package com.atoudeft.controleur;

import com.atoudeft.client.Client;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-11-01
 */
public class EcouteurListeComptes extends MouseAdapter {

    private Client client;

    public EcouteurListeComptes(Client client) {
        this.client = client;
    }

    @Override
    public void mouseClicked(MouseEvent evt) {
        if (evt.getClickCount() == 2) {

            JList<String> list = (JList<String>) evt.getSource();
            int index = list.locationToIndex(evt.getPoint());
            String compte = list.getModel().getElementAt(index); //Recupere le compte selectionne
            System.out.println("Double-clic détecté sur : " + compte);
            //Verifie si le compte est de type cheque, afin de faire la commande SELECT approprier. Sinon, c'est de type epargne
            if (compte.matches(".*\\[CHEQUE\\].*")){
                client.envoyer("SELECT cheque");
            } else {
                client.envoyer("SELECT epargne");
            }
        }
    }
}