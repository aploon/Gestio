package controller;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import dbconnecte.Dbase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.Student;

public class AllStudentSceneController implements Initializable{

    @FXML
    private Button freshId;

    @FXML
    private AnchorPane AllStudentSceneId;

    @FXML
    private TableColumn<Student, GridPane> actionId;

    @FXML
    private TableView<Student> tableId;

    @FXML
    private TableColumn<Student, String> dateNaissId;

    @FXML
    private TableColumn<Student, Integer> matriculeId;

    @FXML
    private TableColumn<Student, String> nomId;

    @FXML
    private TableColumn<Student, String> prenomId;

    @FXML
    private TableColumn<Student, String> sexeId;

    @FXML
    private TableColumn<Student, String> telephoneId;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        /*
            Comment régler le problème du chargement automatique

            Vidéo youtube qui explique comment un modal ouvert peut envoyer des informations à sa fenêtre parent
        */

        freshId.setStyle("-fx-background-color:  #48a5e4; -fx-cursor: hand;");

        ObservableList<Student> data = FXCollections.observableArrayList();
        
        Connection db = Dbase.connect();
        PreparedStatement statement;
        ResultSet result;
        String query;

        try {

            query = "SELECT * FROM ETUDIANT";
            statement = db.prepareStatement(query);
            result = statement.executeQuery();

            while (result.next()) {

                Student stud = new Student(
                    result.getInt(1),
                    result.getInt(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getString(6),
                    result.getString(7),"-",0,0,0,0,0
                );
                data.add(stud);
            }
            db.close();

        } catch (Exception e) {
            //TODO: handle exception
        }

        matriculeId.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        nomId.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomId.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        telephoneId.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        sexeId.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        dateNaissId.setCellValueFactory(new PropertyValueFactory<>("date_naiss"));
        actionId.setCellValueFactory(new PropertyValueFactory<>("actions"));
        tableId.setItems(data);

        tableId.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
    }


    @FXML
    void freshBtnClicked(ActionEvent event) {

        ObservableList<Student> data = FXCollections.observableArrayList();

        Connection db = Dbase.connect();
        PreparedStatement statement;
        ResultSet result;
        String query;

        try {

            query = "SELECT * FROM ETUDIANT";
            statement = db.prepareStatement(query);
            result = statement.executeQuery();

            while (result.next()) {
                Student stud = new Student(
                    result.getInt(1),
                    result.getInt(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getString(6),
                    result.getString(7),"-",0,0,0,0,0
                );
                data.add(stud);
            }
            db.close();

        } catch (Exception e) {
            //TODO: handle exception
        }

        matriculeId.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        nomId.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomId.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        telephoneId.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        sexeId.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        dateNaissId.setCellValueFactory(new PropertyValueFactory<>("date_naiss"));
        actionId.setCellValueFactory(new PropertyValueFactory<>("actions"));
        tableId.setItems(data);

        tableId.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

}
