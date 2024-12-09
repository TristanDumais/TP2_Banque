package com.atoudeft.vue;

import javax.swing.*;

/**
 * Classe responsable de l'affichage de l'historique dans une boîte de dialogue.
 */
public class AfficheurHistorique {

    /**
     * Affiche l'historique dans une boîte de dialogue.
     *
     * @param historique La chaîne contenant l'historique des opérations.
     * @param titre      Le titre de la boîte de dialogue.
     */
    public void afficher(String historique, String titre) {
        JTextArea textArea = new JTextArea(historique);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        //Afficher dans une boite de dialogue
        JOptionPane.showMessageDialog(null, scrollPane, titre, JOptionPane.INFORMATION_MESSAGE);
    }
}
