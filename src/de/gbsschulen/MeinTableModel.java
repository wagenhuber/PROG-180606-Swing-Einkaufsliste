package de.gbsschulen;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class MeinTableModel extends AbstractTableModel{

    private List<Gegenstand> gegenstaende;
    private String[] columns = {"Anzahl", "Gegenstand", "Einzelpreis", "Preis"};


    public MeinTableModel() {
        gegenstaende = new ArrayList<>();
        gegenstaende.add(new Gegenstand("Apfel", 0.45, 2));
        gegenstaende.add(new Gegenstand("Birne", 0.55, 5));
        gegenstaende.add(new Gegenstand("Banane", 0.65, 7));

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
}
