package com.atoudeft.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanneauRetrait extends PanneauBase {
    public PanneauRetrait() {
        super();
        initComponents();
    }
    @Override
    protected void initComponents(){
        setLayout(new GridLayout(2, 1, 0, 5));

        txtMontant = new JTextField();
        txtMontant.setBorder(BorderFactory.createTitledBorder("Montant a retirer: "));

        bValider = new JButton("Valider");
        bValider.setActionCommand("RETRAIT");

        add(txtMontant);
        add(bValider);
    }
}

