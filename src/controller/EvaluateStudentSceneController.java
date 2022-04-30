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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.Student;


public class EvaluateStudentSceneController implements Initializable {

    @FXML
    private AnchorPane EvaluateStudentSceneId;

    @FXML
    private BorderPane preloadId;

    @FXML
    private ChoiceBox<String> anneeId;

    @FXML
    private ChoiceBox<String> filiereId;

    @FXML
    private ChoiceBox<String> sessionId;

    @FXML
    private ChoiceBox<String> semestreId;

    @FXML
    private ComboBox<String> ueId;

    @FXML
    private TableColumn<Student, GridPane> actionId;

    @FXML
    private TableColumn<Student, Integer> matriculeId;

    @FXML
    private TableColumn<Student, String> nomId;

    @FXML
    private TableColumn<Student, String> noteId;

    @FXML
    private TableColumn<Student, String> prenomId;

    @FXML
    private TableView<Student> tableId;

    @FXML
    private Button freshId;

    private int idUe;
    private int idFiliere;
    private int idSession;
    private int idAnnee;
    private int idSemestre;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        freshId.setStyle("-fx-background-color:  #48a5e4; -fx-cursor: hand;");

        ObservableList<Student> data = FXCollections.observableArrayList();

        Connection db = Dbase.connect();
        PreparedStatement statement;
        ResultSet result;
        String query;

        filiereSelected();
        sessionSelected();
        anneeSelected();
        semestreSelected();
        ueSelected();

        // Récupération des UE dans la base de donnée

        
        recupFiliereId();
        recupSessionId();
        recupAnneeId();
        recupSemestreId();
        recupUeId();

        // Action au changement des choiceBox

        filiereId.setOnAction(
            (ActionEvent event) -> {
                ueSelected();
                refreshBtnClicked(event);
            });

        semestreId.setOnAction(
            (ActionEvent event) -> {
                ueSelected();
                refreshBtnClicked(event);
            });
    
        sessionId.setOnAction(
            (ActionEvent event) -> {
                refreshBtnClicked(event);
            });

        anneeId.setOnAction(
            (ActionEvent event) -> {
                refreshBtnClicked(event);
            });

        ueId.setOnAction(
            (ActionEvent event) -> {
                refreshBtnClicked(event);
            });


        try {

            query = "SELECT * FROM ETUDIANT WHERE ID_FILIERE = ?";
            statement = db.prepareStatement(query);
            statement.setInt(1, idFiliere);

            result = statement.executeQuery();

            while (result.next()) {

                Student student = new Student(
                    result.getInt(1),
                    result.getInt(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getString(6),
                    result.getString(7),
                    "-",
                    idFiliere,
                    idSemestre,
                    idSession,
                    idAnnee,
                    idUe,
                    null,
                    this
                );
                data.add(student);
            }
            db.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        matriculeId.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        nomId.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomId.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        noteId.setCellValueFactory(new PropertyValueFactory<>("note"));
        actionId.setCellValueFactory(new PropertyValueFactory<>("actionsNote"));

        tableId.setItems(data);

        tableId.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
    }

    @FXML
    void refreshBtnClicked(ActionEvent event) {

        ObservableList<Student> data = FXCollections.observableArrayList();

        Connection db = Dbase.connect();
        PreparedStatement statement;
        ResultSet result;
        String query;

        // Récupération des UE dans la base de donnée

        recupFiliereId();
        recupSessionId();
        recupAnneeId();
        recupSemestreId();

        if(ueId.getValue() != null){
            recupUeId();
        }

        try {

            query = "SELECT * FROM ETUDIANT WHERE ID_FILIERE = ?";
            statement = db.prepareStatement(query);
            statement.setInt(1, idFiliere);

            result = statement.executeQuery();

            while (result.next()) {

                Student student = new Student(
                    result.getInt(1),
                    result.getInt(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getString(6),
                    result.getString(7),
                    "-",
                    idFiliere,
                    idSemestre,
                    idSession,
                    idAnnee,
                    idUe,
                    null,
                    this
                );
                data.add(student);
            }
            db.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        matriculeId.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        nomId.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomId.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        noteId.setCellValueFactory(new PropertyValueFactory<>("note"));
        actionId.setCellValueFactory(new PropertyValueFactory<>("actionsNote"));

        tableId.setItems(data);

        tableId.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    void preload(boolean visible){
        preloadId.setVisible(visible);
    }

    void ueSelected(){
        
        Connection db = Dbase.connect();
        PreparedStatement statement;
        ResultSet result;
        String query;
        int compte = 1;

        // Récupération des id filiere et semestre

        recupFiliereId();
        recupSemestreId();

        // Récupération et affichage des filière dans le choiceBox

        try {

            query = "SELECT * FROM UE WHERE " + 
                    "id_filiere = ? AND " +
                    "id_semestre = ?";
            statement = db.prepareStatement(query);
            statement.setInt(1, idFiliere);
            statement.setInt(2, idSemestre);

            result = statement.executeQuery();

            ueId.getItems().clear();
            while (result.next()) {

                ueId.getItems().add(result.getString(2));

                if(compte == 1){
                    ueId.setValue(result.getString(2));
                }
                compte++;
            }

            recupUeId();

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

        // Récupération et affichage des filière dans le choiceBox 

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
            System.out.println(e);
        }

    }

    void sessionSelected(){

        Connection db = Dbase.connect();
        PreparedStatement statement;
        ResultSet result;
        String query;
        int compte = 1;

        // Récupération et affichage des session dans le choiceBox

        try {

            query = "SELECT * FROM SESSION";
            statement = db.prepareStatement(query);
            result = statement.executeQuery();

            while (result.next()) {

                sessionId.getItems().add(result.getString(2));

                if(compte == 1){
                    sessionId.setValue(result.getString(2));
                }
                compte++;

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        
    }

    void anneeSelected(){
        Connection db = Dbase.connect();
        PreparedStatement statement;
        ResultSet result;
        String query;

        // Récupération et affichage des filière dans le choiceBox

        ueId.setEditable(true); 

        try {

            query = "SELECT * FROM ANNEE_ACADEMIQUE";
            statement = db.prepareStatement(query);
            result = statement.executeQuery();

            while (result.next()) {

                anneeId.getItems().add(result.getString(2));
                anneeId.setValue(result.getString(2));

            }

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

        semestreId.setValue("Semestre 1");

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


    void recupUeId(){

        Connection db = Dbase.connect();
        PreparedStatement statement;
        ResultSet result;
        String query;

        try {
            String intituleUe = ueId.getValue().toString();

            query = "SELECT * FROM UE WHERE intitule = ?";
            statement = db.prepareStatement(query);
            statement.setString(1, intituleUe);

            result = statement.executeQuery();
            result.next();
            idUe = result.getInt(1);

        } catch (Exception e) {
            System.out.println(e + "ue");
        }
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
            System.out.println(e + "filiere");
        }
    }

    void recupSessionId(){

        Connection db = Dbase.connect();
        PreparedStatement statement;
        ResultSet result;
        String query;

        try {
            String intituleSession = sessionId.getValue().toString();

            query = "SELECT * FROM SESSION WHERE intitule = ?";
            statement = db.prepareStatement(query);
            statement.setString(1, intituleSession);

            result = statement.executeQuery();
            result.next();
            idSession = result.getInt(1);

        } catch (Exception e) {
            System.out.println(e + "session");
        }
    }

    void recupAnneeId(){

        Connection db = Dbase.connect();
        PreparedStatement statement;
        ResultSet result;
        String query;

        try {
            String intituleAnnee = anneeId.getValue().toString();

            query = "SELECT * FROM ANNEE_ACADEMIQUE WHERE annee = ?";
            statement = db.prepareStatement(query);
            statement.setString(1, intituleAnnee);

            result = statement.executeQuery();
            result.next();
            idAnnee = result.getInt(1);

        } catch (Exception e) {
            System.out.println(e + "annee");
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
            System.out.println(e + "semestre");
        }
    }



}