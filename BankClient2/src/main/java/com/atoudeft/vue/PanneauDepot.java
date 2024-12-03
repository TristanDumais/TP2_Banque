package com.atoudeft.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanneauDepot extends PanneauBase {

    public PanneauDepot() {
        super();
        initComponents();
    }

    @Override
    protected void initComponents() {
        setLayout(new GridLayout(2, 1, 0, 5));

        txtMontant = new JTextField();
        txtMontant.setBorder(BorderFactory.createTitledBorder("Montant a deposer: "));

        bValider = new JButton("Valider");
        bValider.setActionCommand("DEPOT");

        add(txtMontant);
        add(bValider);
    }
}
