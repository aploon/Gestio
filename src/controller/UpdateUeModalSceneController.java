package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Ue;

public class UpdateUeModalSceneController implements Initializable {

    @FXML
    private AnchorPane UpdateStudentModalSceneId;

    @FXML
    private TextField creditId;

    @FXML
    private TextField intituleId;

    @FXML
    private Button updateId;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        updateId.setStyle("-fx-background-color:  #48a5e4; -fx-cursor: hand;");
                
        try {                    

            Platform.runLater(new Runnable() {

                @Override
                public void run() {
                    Stage stage = (Stage) updateId.getScene().getWindow();
                    Ue ue = (Ue) stage.getUserData();

                    intituleId.setText(ue.getIntitule());
                    creditId.setText(String.valueOf(ue.getCredit()));
                }
                
            });
        } catch (Exception e) {

            e.printStackTrace();
        }
        
    }


    @FXML
    void UpdateBtnClicked(ActionEvent event) {

        Stage stage = (Stage) updateId.getScene().getWindow();
        Ue ue = (Ue) stage.getUserData();
        
        int id = ue.getId();
        int idFiliere = ue.getIdFiliere();
        int idSemestre = ue.getIdSemestre();


        Ue ueUpdate = new Ue(
            id,
            intituleId.getText(),
            Integer.parseInt(creditId.getText()),
            idFiliere,
            idSemestre,
            null
        );

        if(ueUpdate.updateDataBase(id)){
            System.out.println("Modification faite !");

            try {

                ue.getUeStudentSceneController().refreshBtnClicked(event);
    
            } catch (Exception e) {
                System.out.println("Erreur : " + e);
            }

            stage.close();
        }else{
            System.out.println("Erreur de modification !");
        }

    }

}
