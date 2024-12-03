package com.atoudeft.vue;

import javax.swing.*;
import java.awt.*;

public class PanneauTransfert extends PanneauBase {
    public PanneauTransfert() {
        super();
        initComponents();
    }

    @Override
    protected void initComponents() {
        setLayout(new GridLayout(3, 1, 0, 5));

        txtMontant = new JTextField();
        txtMontant.setBorder(BorderFactory.createTitledBorder("Montant a transferer: "));

        txtTransNum = new JTextField();
        txtTransNum.setBorder((BorderFactory.createTitledBorder("Numero du compte a transferer les fonds: ")));

        bValider = new JButton("Valider");
        bValider.setActionCommand("TRANSFER");

        add(txtMontant);
        add(txtTransNum);
        add(bValider);
    }
}


