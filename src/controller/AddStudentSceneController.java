package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import dbconnecte.Dbase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import model.Student;

public class AddStudentSceneController implements Initializable {

    @FXML
    private AnchorPane AddStudentSceneId;

    @FXML
    private Button addId;

    @FXML
    private DatePicker dateNaissId;

    @FXML
    private RadioButton femininId;

    @FXML
    private RadioButton masculinId;

    @FXML
    private TextField matriculeId;

    @FXML
    private TextField nomId;

    @FXML
    private TextField prenomId;

    @FXML
    private TextField telephoneId;

    @FXML
    private ChoiceBox<String> filiereId;

    private String sexeId = "";
    private String dateNaiss = "";
    private int idFiliere;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        final ToggleGroup sexeGroup = new ToggleGroup();

        masculinId.setToggleGroup(sexeGroup);
        masculinId.setSelected(true);
        femininId.setToggleGroup(sexeGroup);

        filiereSelected();
        recupFiliereId();

    }

    @FXML
    void AddBtnClicked(ActionEvent event) {

        if(masculinId.isSelected()){
            sexeId = "Masculin";
        }else{
            sexeId = "Feminin";
        }
        recupFiliereId();

        dateNaiss = dateNaissId.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Student student = new Student(
            1,
            Integer.parseInt(matriculeId.getText()),
            nomId.getText(),
            prenomId.getText(),
            telephoneId.getText(),
            sexeId,
            dateNaiss,"-",
            idFiliere,
            0,0,0,0
        );

        if(student.insertToDataBase()){
            System.out.println("Donnée insérer !");

            Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Donnée insérer");
                alert.setContentText("Donnée insérer avec succès !");
                alert.showAndWait();

            nomId.setText("");
            prenomId.setText("");
            telephoneId.setText("");
            matriculeId.setText("");

        }else{
            System.out.println("Donnée non insérer !");

            Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Donnée non insérer");
                alert.setContentText("Une erreur s'est produite lors de l'enregistrment des données !");
                alert.showAndWait();
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

}
