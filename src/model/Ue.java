package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Optional;

import dbconnecte.Dbase;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Ue {

    private int id;
    private String intitule;
    private int credit;
    private int idFiliere;
    private int idSemestre;
    private GridPane actions;

    public Ue(int id, String intitule, int credit, int idFiliere, int idSemestre) {
        this.id = id;
        this.intitule = intitule;
        this.credit = credit;
        this.idFiliere = idFiliere;
        this.idSemestre = idSemestre;

        this.actions = new GridPane();
        actions.setVgap(1);
        actions.setPadding(new Insets(3, 3, 3, 3));

        Button update = new Button("Modifier");
        Button delete = new Button("Supprimer");
        update.setStyle("-fx-background-color: #48a5e4; -fx-text-fill: white; -fx-cursor: hand;");
        delete.setStyle("-fx-background-color: #d608089d; -fx-text-fill: white; -fx-cursor: hand;");

        actions.add(update, 0, 0);
        actions.add(delete, 1, 0);

        update.setOnAction(
                (ActionEvent event) -> {
                    System.out.println("selectedData: ");

                    Parent root;
                    try {

                        // FXMLLoader loader = new
                        // FXMLLoader(getClass().getResource("../fxml/UpdateStudentModalScene.fxml"));
                        // loader.setController(new UpdateStudentModalSceneController());

                        // root = loader.load();
                        // Scene scene = new Scene(root);

                        root = FXMLLoader.load(getClass().getResource("../fxml/UpdateUeModalScene.fxml"));
                        Scene scene = new Scene(root);

                        Stage mainStage = new Stage();
                        Stage currentStage = (Stage) update.getScene().getWindow();

                        mainStage.setUserData(this);
                        mainStage.initModality(Modality.APPLICATION_MODAL);
                        mainStage.initOwner(currentStage);
                        mainStage.setResizable(false);

                        mainStage.setTitle("Gestio Eneam");
                        mainStage.setScene(scene);

                        mainStage.show();

                    } catch (IOException e) {

                        System.out.println(e);
                    }
                });

        delete.setOnAction(
                (ActionEvent event) -> {
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Supprimer");
                    alert.setHeaderText("Suppression de l'UE " + this.intitule);
                    alert.setContentText("Voulez-vous vraiment supprimer cet UE ?");
                    Optional<ButtonType> option = alert.showAndWait();

                    if (option.get() == null) {
                        // rien
                    } else if (option.get() == ButtonType.OK) {
                        deleteToDatabase(this.id);
                    } else if (option.get() == ButtonType.CANCEL) {
                        // rien
                    }

                });
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

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getIdFiliere() {
        return idFiliere;
    }

    public void setIdFiliere(int idFiliere) {
        this.idFiliere = idFiliere;
    }

    public int getIdSemestre() {
        return idSemestre;
    }

    public void setIdSemestre(int idSemestre) {
        this.idSemestre = idSemestre;
    }

    public GridPane getActions() {
        return actions;
    }

    public void setActions(GridPane actions) {
        this.actions = actions;
    }

    public boolean insertToDataBase() {

        Connection db = Dbase.connect();
        PreparedStatement statement;
        int result = 0;
        String query;

        try {

            query = "INSERT INTO UE VALUES (NULL, ?, ?, ?, ?)";
            statement = db.prepareStatement(query);

            statement.setString(1, this.intitule);
            statement.setInt(2, this.credit);
            statement.setInt(3, this.idFiliere);
            statement.setInt(4, this.idSemestre);

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

    public boolean updateDataBase(int id) {

        Connection db = Dbase.connect();
        PreparedStatement statement;
        int result = 0;
        String query;

        try {

            query = "UPDATE UE SET " +

                    "intitule = ?, " +
                    "credit = ?, " +
                    "id_filiere = ?, " +
                    "id_semestre = ? " +
                    "WHERE id = ?;";

            statement = db.prepareStatement(query);

            statement.setString(1, this.intitule);
            statement.setInt(2, this.credit);
            statement.setInt(3, this.idFiliere);
            statement.setInt(4, this.idSemestre);
            statement.setInt(5, id);

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
