package com.atoudeft.vue;

import com.atoudeft.client.Client;
import com.atoudeft.controleur.EcouteurConnexion;
import com.atoudeft.controleur.EcouteurListeComptes;
import com.atoudeft.controleur.EcouteurOperationsCompte;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Abdelmoumène Toudeft
 * @version 1.0
 * @since 2024-11-01
 */
public class PanneauPrincipal extends JPanel {
    private Client client;
    private PanneauConnexion panneauConnexion;
    private JPanel panneauCompteClient; // Panneau contenant la liste et les boutons d'opérations
    private PanneauOperationsCompte panneauOperationsCompte;
    private JPanel panneauDynamique; // Zone dynamique pour PanneauDepot ou d'autres panneaux
    private DefaultListModel<String> numerosComptes;
    private JList<String> jlNumerosComptes;
    private CardLayout cardLayout; // Pour gérer les panneaux dynamiques
    private Map<String, PanneauBase> panneauxDynamiques; // Map pour gérer les panneaux dynamiques

    public PanneauPrincipal(Client client) {
        this.client = client;

        // Initialisation des panneaux fixes
        panneauConnexion = new PanneauConnexion();
        panneauConnexion.setEcouteur(new EcouteurConnexion(client, panneauConnexion));
        panneauOperationsCompte = new PanneauOperationsCompte();
        panneauOperationsCompte.setEcouteur(new EcouteurOperationsCompte(client, this));

        // Panneau pour la liste des comptes
        panneauCompteClient = new JPanel(new BorderLayout());
        panneauCompteClient.setBackground(Color.WHITE);
        panneauCompteClient.add(panneauOperationsCompte, BorderLayout.NORTH);

        // Initialiser la liste des comptes bancaires
        numerosComptes = new DefaultListModel<>();
        jlNumerosComptes = new JList<>(numerosComptes);
        jlNumerosComptes.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        jlNumerosComptes.setBorder(BorderFactory.createTitledBorder("Comptes bancaires"));
        jlNumerosComptes.setPreferredSize(new Dimension(250, 500));
        panneauCompteClient.add(jlNumerosComptes, BorderLayout.WEST);

        // Enregistrer l'écouteur de souris sur la liste
        jlNumerosComptes.addMouseListener(new EcouteurListeComptes(client));

        // Initialisation du panneau dynamique (CardLayout)
        cardLayout = new CardLayout();
        panneauDynamique = new JPanel(cardLayout);
        panneauxDynamiques = new HashMap<>();

        // Ajouter les panneaux dynamiques au CardLayout
        ajouterPanneauDynamique("DEPOT", new PanneauDepot());
        ajouterPanneauDynamique("RETRAIT", new PanneauRetrait());
        ajouterPanneauDynamique("FACTURE", new PanneauFacture());
        ajouterPanneauDynamique("TRANSFER", new PanneauTransfert());

        // Panneau par défaut
        panneauDynamique.add(new JPanel(), "VIDE");

        // Structure globale avec BorderLayout
        this.setLayout(new BorderLayout());
        this.add(panneauConnexion, BorderLayout.NORTH); // Panneau connexion en haut
        this.add(panneauCompteClient, BorderLayout.WEST); // Liste des comptes à gauche
        this.add(panneauDynamique, BorderLayout.CENTER); // Zone dynamique au centre

        // Par défaut, afficher le panneau vide
        cardLayout.show(panneauDynamique, "VIDE");
    }

    /**
     * Ajoute un panneau dynamique et l'enregistre dans la map.
     *
     * @param nom     Le nom du panneau (clé)
     * @param panneau Une instance de PanneauBase
     */
    public void ajouterPanneauDynamique(String nom, PanneauBase panneau) {
        panneauxDynamiques.put(nom, panneau);
        panneauDynamique.add(panneau, nom);
        panneau.setEcouteur(new EcouteurOperationsCompte(client, this));

    }

    /**
     * Affiche le panneau dynamique correspondant à la clé.
     *
     * @param nom Le nom du panneau à afficher
     */
    public void afficherPanneauDynamique(String nom) {
        if (panneauxDynamiques.containsKey(nom)) {
            cardLayout.show(panneauDynamique, nom);
        } else {
            System.err.println("Erreur : panneau '" + nom + "' non trouvé.");
        }
    }

    /**
     * Retourne une instance d'un panneau dynamique.
     *
     * @param nom Le nom du panneau
     * @return L'instance de PanneauBase ou null si non trouvée
     */
    public PanneauBase getPanneauDynamique(String nom) {
        return panneauxDynamiques.get(nom);
    }

    /**
     * Retourne au panneau par défaut.
     */
    public void cacherPanneauDynamique() {
        cardLayout.show(panneauDynamique, "VIDE");
    }

    /**
     * Vide les éléments d'interface du panneau principal.
     */
    public void vider() {
        this.numerosComptes.clear();
    }

    /**
     * Masque le panneau de connexion.
     */
    public void cacherPanneauConnexion() {
        panneauConnexion.effacer();
        panneauConnexion.setVisible(false);
    }

    /**
     * Affiche le panneau de connexion.
     */
    public void montrerPanneauConnexion() {
        panneauConnexion.setVisible(true);
    }

    /**
     * Masque la liste des comptes.
     */
    public void cacherPanneauCompteClient() {
        panneauCompteClient.setVisible(false);
        this.numerosComptes.clear();
    }

    /**
     * Affiche la liste des comptes.
     */
    public void montrerPanneauCompteClient() {
        panneauCompteClient.setVisible(true);
    }

    /**
     * Ajoute un compte bancaire à la liste des comptes.
     *
     * @param str chaîne contenant le numéro du compte
     */
    public void ajouterCompte(String str) {
        numerosComptes.addElement(str);
    }

    public PanneauOperationsCompte getPanneauOperationsCompte() {
        return panneauOperationsCompte;
    }
}
