package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import dbconnecte.Dbase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.Ue;

public class UeStudentSceneController implements Initializable {

    @FXML
    private AnchorPane UeStudentSceneId;

    @FXML
    private Button addId;

    @FXML
    private Button freshId;

    @FXML
    private TableColumn<Ue, GridPane> actionsId;

    @FXML
    private TableColumn<Ue, Integer> codeLibId;

    @FXML
    private TableColumn<Ue, Integer> colcreditId;

    @FXML
    private TextField creditId;

    @FXML
    private ChoiceBox<String> filiereId;

    @FXML
    private ChoiceBox<String> semestreId;

    @FXML
    private TableColumn<Ue, String> intituleId;

    @FXML
    private TableView<Ue> tableId;

    @FXML
    private TextField ueId;

    private int idFiliere = 0;
    private int idSemestre = 0;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        addId.setStyle("-fx-background-color:  #48a5e4; -fx-cursor: hand;");
        freshId.setStyle("-fx-background-color:  #48a5e4; -fx-cursor: hand;");

        ObservableList<Ue> data = FXCollections.observableArrayList();

        Connection db = Dbase.connect();
        PreparedStatement statement;
        ResultSet result;
        String query;

        // Récupération et affichage des semestres et filiere dans le choiceBox

        semestreSelected();
        filiereSelected();
        
        // Récupération de l'id de filliere et semestre

        recupFiliereId();
        recupSemestreId();

        // Action au changement de filière et de semestre

        filiereId.setOnAction(
                (ActionEvent event) -> {
                    recupFiliereId();
                    recupSemestreId();
                    refreshBtnClicked(event);
                });

        semestreId.setOnAction(
            (ActionEvent event) -> {
                recupSemestreId();
                recupFiliereId();
                refreshBtnClicked(event);
            });
    

        // Récupération des UE dans la base de donnée

        try {

            query = "SELECT * FROM UE WHERE ID_FILIERE = ? AND ID_SEMESTRE = ?";
            statement = db.prepareStatement(query);
            statement.setInt(1, idFiliere);
            statement.setInt(2, idSemestre);

            result = statement.executeQuery();

            while (result.next()) {

                Ue ue = new Ue(
                        result.getInt(1),
                        result.getString(2),
                        result.getInt(3),
                        result.getInt(4),
                        result.getInt(5));
                data.add(ue);
            }
            db.close();

        } catch (Exception e) {
            // TODO: handle exception
        }

        codeLibId.setCellValueFactory(new PropertyValueFactory<>("id"));
        intituleId.setCellValueFactory(new PropertyValueFactory<>("intitule"));
        colcreditId.setCellValueFactory(new PropertyValueFactory<>("credit"));
        actionsId.setCellValueFactory(new PropertyValueFactory<>("actions"));

        tableId.setItems(data);

        tableId.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    @FXML
    void AddBtnClicked(ActionEvent event) {

        Ue ue = new Ue(
            1,
            ueId.getText(),
            Integer.parseInt(creditId.getText()),
            idFiliere,
            idSemestre);

        if (ue.insertToDataBase()) {
            System.out.println("Donnée insérer !");
        } else {
            System.out.println("Donnée non insérer !");
        }
        refreshBtnClicked(event);

        ueId.setText("");
        creditId.setText("");

    }

    @FXML
    void refreshBtnClicked(ActionEvent event) {

        ObservableList<Ue> data = FXCollections.observableArrayList();

        Connection db = Dbase.connect();
        PreparedStatement statement;
        ResultSet result;
        String query;

        try {

            query = "SELECT * FROM UE WHERE ID_FILIERE = ? AND ID_SEMESTRE = ?";
            statement = db.prepareStatement(query);
            statement.setInt(1, idFiliere);
            statement.setInt(2, idSemestre);

            result = statement.executeQuery();

            while (result.next()) {

                Ue ue = new Ue(
                        result.getInt(1),
                        result.getString(2),
                        result.getInt(3),
                        result.getInt(4),
                        result.getInt(5));
                data.add(ue);
            }
            db.close();

        } catch (Exception e) {
            // TODO: handle exception
        }

        codeLibId.setCellValueFactory(new PropertyValueFactory<>("id"));
        intituleId.setCellValueFactory(new PropertyValueFactory<>("intitule"));
        colcreditId.setCellValueFactory(new PropertyValueFactory<>("credit"));
        actionsId.setCellValueFactory(new PropertyValueFactory<>("actions"));

        tableId.setItems(data);

        tableId.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }


    void recupFiliereId(){

        Connection db = Dbase.connect();
        PreparedStatement statement;
        ResultSet result;
        String query;

        try {
            String intituleFiliere = filiereId.getValue().toString();

            query = "SELECT * FROM FILIERE WHERE intitule = ?";
            statement = db.prepareStatement(query);
            statement.setString(1, intituleFiliere);

            result = statement.executeQuery();
            result.next();
            idFiliere = result.getInt(1);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void recupSemestreId(){

        Connection db = Dbase.connect();
        PreparedStatement statement;
        ResultSet result;
        String query;

        try {
            String intituleSemestre = semestreId.getValue().toString();

            query = "SELECT * FROM SEMESTRE WHERE intitule = ?";
            statement = db.prepareStatement(query);
            statement.setString(1, intituleSemestre);

            result = statement.executeQuery();
            result.next();
            idSemestre = result.getInt(1);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    void semestreSelected(){
        Connection db = Dbase.connect();
        PreparedStatement statement;
        ResultSet result;
        String query;
        int compte = 1;

        // Récupération et affichage des filière dans le choiceBox

        try {

            query = "SELECT * FROM SEMESTRE";
            statement = db.prepareStatement(query);
            result = statement.executeQuery();

            while (result.next()) {

                semestreId.getItems().add(result.getString(2));

                if(compte == 1){
                    semestreId.setValue(result.getString(2));
                }
                compte++;

            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void filiereSelected(){

        Connection db = Dbase.connect();
        PreparedStatement statement;
        ResultSet result;
        String query;
        int compte = 1;

        // Récupération et affichage des filières dans le choiceBox

        try {

            query = "SELECT * FROM FILIERE";
            statement = db.prepareStatement(query);
            result = statement.executeQuery();

            while (result.next()) {

                filiereId.getItems().add(result.getString(2));
                if(compte == 1){
                    filiereId.setValue(result.getString(2));
                }
                compte++;

            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }


}
