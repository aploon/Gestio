package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import dbconnecte.Dbase;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Student;

public class AddNoteModalSceneController implements Initializable{

    @FXML
    private AnchorPane UpdateStudentModalSceneId;

    @FXML
    private TextField intituleId;

    @FXML
    private TextField noteId;

    @FXML
    private Button noterId;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        noterId.setStyle("-fx-background-color:  #48a5e4; -fx-cursor: hand;");

        try {                    

            Platform.runLater(new Runnable() {

                @Override
                public void run() {

                    Stage stage = (Stage) noterId.getScene().getWindow();
                    Student student = (Student) stage.getUserData();

                    Connection db = Dbase.connect();
                    PreparedStatement statement;
                    ResultSet result;
                    String query;
                    String intituleUe = "";
                    String noteUe = "";

                    if(student.getNote().equals("-")){
                        noteUe = "";
                    }else{
                        noteUe = student.getNote();
                    }

                    try {

                        query = "SELECT * FROM UE WHERE ID = ?";
                        statement = db.prepareStatement(query);
                        statement.setInt(1, student.getId_ue());

                        result = statement.executeQuery();

                        while (result.next()) {
                            intituleUe = result.getString(2);
                        }
                        db.close();

                    } catch (Exception e) {
                        System.out.println(e);
                    }


                    intituleId.setText(intituleUe);
                    noteId.setText(noteUe);
                }
                
            });
        } catch (Exception e) {

            e.printStackTrace();
        }
        
    }

    @FXML
    void AddNoteBtnClicked(ActionEvent event) {

        Stage stage = (Stage) noterId.getScene().getWindow();
        Student student = (Student) stage.getUserData();
        
        int id_filiere = student.getId_filiere();
        int id_etudiant = student.getId();
        int id_semestre = student.getId_semestre();
        int id_session = student.getId_session();
        int id_annee_academique = student.getId_annee_academique();
        int id_ue = student.getId_ue();


        Student studentNote = new Student(
            id_etudiant,
            123456789,
            "Arnaud",
            "Précieux",
            "68980983",
            "Masculin",
            "02/02/2002",
            noteId.getText(),
            id_filiere,
            id_semestre,
            id_session,
            id_annee_academique,
            id_ue,
            null,
            null
        );

        if(student.getNote().equals("-")){

            if(studentNote.insertNoteToDatabase()){

                student.getEvaluateStudentSceneController().preload(true);
                System.out.println("Note attribué !");

                try {

                    new Thread(new Runnable() {

                        @Override
                        public void run() {
            
                            try {
            
                                Thread.sleep(2000);
                                Platform.runLater(new Runnable() {
            
                                    @Override
                                    public void run() {
            
                                        student.getEvaluateStudentSceneController().refreshBtnClicked(event);
                                        student.getEvaluateStudentSceneController().preload(false);
            
                                    }
            
                                });
            
                            } catch (Exception e) {
            
                            }
                        }
                    }).start();
        
                } catch (Exception e) {
                    System.out.println("Erreur : " + e);
                }

                stage.close();
            }else{
                System.out.println("Erreur de modification !");
            }
        }else{
            if(studentNote.updateNoteToDatabase(student.getId(), Integer.parseInt(noteId.getText()))){
                System.out.println("Note modifier !");

                try {

                    student.getEvaluateStudentSceneController().refreshBtnClicked(event);
        
                } catch (Exception e) {
                    System.out.println("Erreur : " + e);
                }

                stage.close();
            }else{
                System.out.println("Erreur de modification !");
            }
        }

        

    }


}
