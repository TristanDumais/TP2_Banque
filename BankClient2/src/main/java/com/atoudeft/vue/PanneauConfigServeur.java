package com.atoudeft.vue;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-11-01
 */
public class PanneauConfigServeur extends JPanel {
    private JTextField txtAdrServeur, txtNumPort;

    public PanneauConfigServeur(String adr, int port) {
        txtAdrServeur = new JTextField(adr);
        txtNumPort = new JTextField(String.valueOf(port));

        //Grid Layout
        setLayout(new GridLayout(2, 2, 20, 10));

        //Ajouts etiquettes et champs de textes
        add(new JLabel("Adresse du serveur :"));
        add(txtAdrServeur);
        add(new JLabel("Port du serveur :"));
        add(txtNumPort);

        setBorder(BorderFactory.createTitledBorder("Configuration du serveur"));
    }
    public String getAdresseServeur() {
        return txtAdrServeur.getText();
    }
    public String getPortServeur() {
        return txtNumPort.getText();
    }
}
