package de.gbsschulen;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private List<Gegenstand> gegenstaende;

    public DAO() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gegenstaende", "root", "mysql"); //Connection seht mit in der AP Angabe!!!
        this.gegenstaende = new ArrayList<>();
        this.preparedStatement = connection.prepareStatement("select bezeichnung, preis from gegenstand where bezeichnung like ? ORDER  BY bezeichnung");//? ist Platzhalter des Prep.STatements

    }

    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public List<Gegenstand> getGegenstaende() {
        return gegenstaende;
    }

    public void findeArtikel(String bezeichnung) throws SQLException {
        preparedStatement.setString(1, bezeichnung);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Gegenstand gegenstand = new Gegenstand();
            gegenstand.setBezeichnung(resultSet.getString(1));
            gegenstand.setEinzelpreis(resultSet.getDouble(2));
            gegenstaende.add(gegenstand);
        }



    }

}
