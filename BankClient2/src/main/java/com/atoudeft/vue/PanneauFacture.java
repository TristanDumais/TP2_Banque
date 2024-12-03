package com.atoudeft.vue;

import javax.swing.*;
import java.awt.*;

public class PanneauFacture extends PanneauBase {

    public PanneauFacture() {
        super();
        initComponents();
    }

    @Override
    protected void initComponents() {
        setLayout(new GridLayout(4, 1, 0, 5));

        txtMontant = new JTextField();
        txtMontant.setBorder(BorderFactory.createTitledBorder("Montant a transferer: "));

        txtFacNum = new JTextField();
        txtFacNum.setBorder(BorderFactory.createTitledBorder("Numero de facture: "));

        txtFacDesc = new JTextField();
        txtFacDesc.setBorder(BorderFactory.createTitledBorder("Description: "));

        bValider = new JButton("Valider");
        bValider.setActionCommand("FACTURE");

        add(txtMontant);
        add(txtFacNum);
        add(txtFacDesc);
        add(bValider);
    }
}


