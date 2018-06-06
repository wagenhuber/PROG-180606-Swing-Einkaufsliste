package de.gbsschulen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Fenster extends JFrame {


    //Tabelle darf nicht auf Panel gelegt werden!
    private JPanel jpNorth, jpSouth;
    private JComboBox<String> jComboBox;
    private JTextField jtxtAnzahl;
    private JButton jbtnEintragen;
    private JLabel jlGesamtpreis;
    private JMenuBar jMenuBar; //Menüleiste => Balken als Basis
    private JMenu jmenuDatei; //Eintrag in der Menüleiste
    private JMenuItem jmiNeu, jmiSpeichern, jmiBeenden; //Unterpunkt von Menüpunkt


    private JTable jTable;
    private MeinTableModel meinTableModel;
    private JScrollPane jScrollPane;


    public Fenster() throws HeadlessException {
        super("Einkaufslsite");
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.initMenu();
        initComponents();
        initEvents();
        this.setSize(800, 600);
        this.setVisible(true);
    }

    private void initEvents() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                beenden();
            }
        });

        jmiBeenden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                beenden();
            }
        });

    }

    private void beenden() {
        int result = JOptionPane.showConfirmDialog(this, "Wollen Sie wirklich beeenden?", "Beenden?", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            System.exit(NORMAL);
        }

    }


    private void initMenu() {
        jMenuBar = new JMenuBar();
        jmenuDatei = new JMenu("Datei");
        jmiNeu = new JMenuItem("Neu");
        jmiSpeichern = new JMenuItem("Speichern");
        jmiBeenden = new JMenuItem("Beenden");

        jmenuDatei.add(jmiNeu);
        jmenuDatei.add(jmiSpeichern);
        jmenuDatei.add(jmiBeenden);

        jMenuBar.add(jmenuDatei);

        this.setJMenuBar(jMenuBar);
    }

    private void initComponents() {
        jpNorth = new JPanel();
        jComboBox = new JComboBox<>();
        jComboBox.addItem("Bitte auswählen..."); //erster Eintrag
        jtxtAnzahl = new JTextField(3);//Breite des Feldes
        jbtnEintragen = new JButton("Eintragen");
        jpNorth.add(jComboBox);
        jpNorth.add(new JLabel("Anzahl:"));
        jpNorth.add(jtxtAnzahl);
        jpNorth.add(jbtnEintragen);


        meinTableModel = new MeinTableModel();
        jTable = new JTable(meinTableModel);
        jScrollPane = new JScrollPane(jTable);


        jpSouth = new JPanel();
        jpSouth.add(new JLabel("Gesamtpreis: "));
        jpSouth.setLayout(new FlowLayout(FlowLayout.RIGHT));//Layout ist im standard zentriert, wir wollen aber rechtsbündig!
        jlGesamtpreis = new JLabel("0.00");
        jpSouth.add(jlGesamtpreis);


        this.add(jpNorth, BorderLayout.NORTH);
        this.add(jScrollPane);
        this.add(jpSouth, BorderLayout.SOUTH);

    }

    public static void main(String[] args) {
        new Fenster();
    }

}
