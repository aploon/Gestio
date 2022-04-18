package controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Student;

public class UpdateStudentModalSceneController implements Initializable {

    @FXML
    private AnchorPane AddStudentSceneId;

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
    private Button updateId;

    private String sexe;

    @FXML
    void UpdateBtnClicked(ActionEvent event) {

        Stage stage = (Stage) updateId.getScene().getWindow();
        Student student = (Student) stage.getUserData();
        
        String sexe = "";
        String dateNaiss = "";
        int id = student.getId();

        if(masculinId.isSelected()){
            sexe = "Masculin";
        }else{
            sexe = "Feminin";
        }

        dateNaiss = dateNaissId.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Student studentUpdate = new Student(
            id,
            Integer.parseInt(matriculeId.getText()),
            nomId.getText(),
            prenomId.getText(),
            telephoneId.getText(),
            sexe,
            dateNaiss,"-",0,0,0,0,0, null, null
        );

        if(studentUpdate.updateDataBase(id)){
            System.out.println("Modification faite !");

            try {

                // FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/MainScene.fxml"));
                // Parent root = loader.load();
    
                // MainSceneController MainSceneController = loader.getController();

                student.getAllStudentSceneController().freshBtnClicked(event);
    
            } catch (Exception e) {
                System.out.println("Erreur : " + e);
            }

            stage.close();
        }else{
            System.out.println("Erreur de modification !");
        }
        
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        femininId.setStyle("-fx-cursor: hand;");
        masculinId.setStyle("-fx-cursor: hand;");
        updateId.setStyle("-fx-background-color:  #48a5e4; -fx-cursor: hand;");

        final ToggleGroup sexeGroup = new ToggleGroup();
        masculinId.setToggleGroup(sexeGroup);
        femininId.setToggleGroup(sexeGroup);
                
        try {                    

            Platform.runLater(new Runnable() {

                @Override
                public void run() {
                    Stage stage = (Stage) updateId.getScene().getWindow();

                    Student student = (Student) stage.getUserData();

                    nomId.setText(student.getNom());
                    prenomId.setText(student.getPrenom());
                    matriculeId.setText(String.valueOf(student.getMatricule()));
                    telephoneId.setText(student.getTelephone());
                    
                    sexe = student.getSexe();
                    if(sexe.equals("Masculin")){
                        masculinId.setSelected(true);
                    }else{
                        femininId.setSelected(true);
                    }

                    dateNaissId.setValue(LocalDate.parse(student.getDate_naiss()));
                }
                
            });
        } catch (Exception e) {

            e.printStackTrace();
        }
                
        
    }

    // private final ObjectProperty<Stage> currentStage = new SimpleObjectProperty<>(null);
    // public final ObjectProperty<Stage> currentStageProperty() {
    //     return this.currentStage;
    // }
    // public final javafx.stage.Stage getCurrentStage() {
    //     return this.currentStageProperty().get();
    // }

}
