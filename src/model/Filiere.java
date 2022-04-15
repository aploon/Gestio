package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import dbconnecte.Dbase;

public class Filiere {

    private int id;
    private String intitule;
    
    public Filiere(int id, String intitule) {
        this.id = id;
        this.intitule = intitule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    
    public boolean insertToDataBase() {

        Connection db = Dbase.connect();
        PreparedStatement statement;
        int result = 0;
        String query;

        try {

            query = "INSERT INTO FILIERE VALUES (NULL, ?)";
            statement = db.prepareStatement(query);

            statement.setString(1, this.intitule);

            result = statement.executeUpdate();

        } catch (Exception e) {
            // TODO: handle exception
        }

        if (result == 1) {
            return true;
        } else {
            return false;
        }

    }

    public boolean updateDataBase(int id) {

        Connection db = Dbase.connect();
        PreparedStatement statement;
        int result = 0;
        String query;

        try {

            query = "UPDATE UE SET " +

                    "intitule = ?, " +
                    "WHERE id = ?;";

            statement = db.prepareStatement(query);

            statement.setString(1, this.intitule);
            statement.setInt(2, id);

            result = statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Erreur : " + e);
        }

        if (result == 1) {
            return true;
        } else {
            return false;
        }

    }

    public boolean deleteToDatabase(int id) {

        Connection db = Dbase.connect();
        PreparedStatement statement;
        int result = 0;
        String query;

        try {

            query = "DELETE FROM UE WHERE id = ? ";

            statement = db.prepareStatement(query);

            statement.setInt(1, id);

            result = statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Erreur : " + e);
        }

        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

}
