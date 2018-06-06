package de.gbsschulen;

import javax.swing.table.AbstractTableModel;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MeinTableModel extends AbstractTableModel{

    private List<Gegenstand> gegenstaende;
    private String[] columns = {"Anzahl", "Gegenstand", "Einzelpreis", "Preis"};


    public MeinTableModel() {
        gegenstaende = new ArrayList<>();


    }

    @Override
    public int getRowCount() { //legt fest wie viele Spalten automatisch angezeigt werden
        return gegenstaende.size();
    }

    @Override
    public int getColumnCount() {//verglw. wie getRowCount
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Gegenstand gegenstand = gegenstaende.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return gegenstand.getAnzahl();

            case 1:
                return gegenstand.getBezeichnung();

            case 2:
                return gegenstand.getEinzelpreis();

            case 3:
                return gegenstand.getAnzahl() * gegenstand.getEinzelpreis();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) { //Einblenden der Spaltenüberschriften in der Tabelle
        return columns[column];
    }

    public void hinzufuegen(Gegenstand gegenstand) {
        Iterator<Gegenstand> iterator = gegenstaende.iterator();
        while (iterator.hasNext()) {
            Gegenstand next = iterator.next();
            if (next.getBezeichnung().equals(gegenstand.getBezeichnung())) {
                next.setAnzahl(next.getAnzahl() + gegenstand.getAnzahl());
                this.fireTableDataChanged();
                return;
            }
        }

        this.gegenstaende.add(gegenstand);
        this.fireTableDataChanged();//Daten in Tabelle haben sich geändert, Methodenaufruf um Anzeige zu aktualisieren
    }

    public void speichern(File file) throws IOException {
        BufferedWriter bw = null;
        bw = new BufferedWriter(new FileWriter(file));
        for (Gegenstand gegenstand : gegenstaende) {
            bw.write(gegenstand.getBezeichnung() + "," + gegenstand.getAnzahl() + "," + gegenstand.getEinzelpreis() + "," + gegenstand.getAnzahl() * gegenstand.getEinzelpreis());
            bw.newLine();
        }
        if (bw != null) {
            bw.close();
        }
    }

    public double getGesamtPreis() {
        double gesamtpreis = 0.0;
        for (Gegenstand gegenstand : gegenstaende) {
            gesamtpreis += gegenstand.getAnzahl() * gegenstand.getEinzelpreis();
        }
        return gesamtpreis;
    }

    public void loeschen(String bezeichnung) {
        Iterator<Gegenstand> iterator = gegenstaende.iterator();
        while (iterator.hasNext()) {
            Gegenstand gegenstand = iterator.next();
            if (gegenstand.getBezeichnung().equals(bezeichnung)) {
                iterator.remove();
                fireTableDataChanged();
                return;
            }
        }
    }

}
