package com.atoudeft.controleur;

import com.atoudeft.client.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurOperationsCompte implements ActionListener {
    private Client client;

    public EcouteurOperationsCompte(Client client) {
        this.client = client;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Verifier si le bouton cliqué est celui pour creer un compte epargne
        String action = e.getActionCommand();
        if ("EPARGNE".equals(action)) {
            client.envoyer("EPARGNE");
        }
    }
}



