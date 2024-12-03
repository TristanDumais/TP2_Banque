package com.atoudeft.vue;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class PanneauBase extends JPanel {
    protected JTextField txtMontant;
    protected JTextField txtFacDesc;
    protected JTextField txtFacNum;
    protected JTextField txtTransNum;
    protected JButton bValider;

    public void cleartxt() {
        if (txtMontant != null) txtMontant.setText("");
        if (txtFacDesc != null) txtFacDesc.setText("");
        if (txtFacNum != null) txtFacNum.setText("");
        if (txtTransNum != null) txtTransNum.setText("");
    }
    public String getMontant(){
        return txtMontant.getText();
    }
    public String getFacDesc(){
        return txtFacDesc.getText();
    }
    public String getFacNum(){
        return txtFacDesc.getText();
    }
    public String getTransNum(){
        return txtTransNum.getText();
    }
    public void setEcouteur(ActionListener ecouteur) {
        if (bValider != null) {
            bValider.addActionListener(ecouteur);
        } else {
            throw new IllegalStateException("bValider doit etre initialiser");
        }
    }

    protected abstract void initComponents();
}