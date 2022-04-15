package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class MainSceneController implements Initializable {

    @FXML
    private Label headerLabelId;

    @FXML
    private Label pathLabelId;

    @FXML
    private Button addStudentId;

    @FXML
    private Button allStudentId;

    @FXML
    private Button aproposId;

    @FXML
    private Button evaluateStudentId;

    @FXML
    private Button ueStudentId;

    @FXML
    private ImageView facebookId;

    @FXML
    private ImageView linkedinId;

    @FXML
    private ImageView twiterId;

    @FXML
    protected AnchorPane AllStudentSceneId;

    @FXML
    protected AnchorPane AddStudentSceneId;

    @FXML
    protected AnchorPane EvaluateStudentSceneId;

    @FXML
    protected AnchorPane UeStudentSceneId;

    @FXML
    protected AnchorPane AproposSceneId;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        addStudentId.setStyle("-fx-cursor: hand;");
        allStudentId.setStyle("-fx-cursor: hand;");
        aproposId.setStyle("-fx-cursor: hand;");
        evaluateStudentId.setStyle("-fx-cursor: hand;");
        ueStudentId.setStyle("-fx-cursor: hand;");

        facebookId.setStyle("-fx-cursor: hand;");
        linkedinId.setStyle("-fx-cursor: hand;");
        twiterId.setStyle("-fx-cursor: hand;");
        
    }

    @FXML
    void AddStudentClicked(ActionEvent event) {

        if (!AddStudentSceneId.isVisible()) {

            headerLabelId.setText("Ajouter un étudiants");
            pathLabelId.setText("/home/Ajouter un étudiant");

            AllStudentSceneId.setVisible(false);
            AproposSceneId.setVisible(false);
            EvaluateStudentSceneId.setVisible(false);
            UeStudentSceneId.setVisible(false);

            AddStudentSceneId.setVisible(true);

            allStudentId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30; -fx-cursor: hand;");
            aproposId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30; -fx-cursor: hand;");
            evaluateStudentId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30; -fx-cursor: hand;");
            ueStudentId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30; -fx-cursor: hand;");

            addStudentId.setStyle("-fx-background-color: #323233; -fx-background-radius: 30 0 0 30; -fx-cursor: hand;");
        }

    }

    @FXML
    void AllStudentClicked(ActionEvent event) {

        if (!AllStudentSceneId.isVisible()) {

            headerLabelId.setText("Tous les étudiants");
            pathLabelId.setText("/home/tous les etudiants");

            AproposSceneId.setVisible(false);
            EvaluateStudentSceneId.setVisible(false);
            UeStudentSceneId.setVisible(false);
            AddStudentSceneId.setVisible(false);

            AllStudentSceneId.setVisible(true);

            aproposId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30; -fx-cursor: hand;");
            evaluateStudentId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30; -fx-cursor: hand;");
            ueStudentId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30; -fx-cursor: hand;");
            addStudentId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30; -fx-cursor: hand;");

            allStudentId.setStyle("-fx-background-color: #323233; -fx-background-radius: 30 0 0 30; -fx-cursor: hand;");

        }
    }

    @FXML
    void AproposClicked(ActionEvent event) {

        if (!AproposSceneId.isVisible()) {

            headerLabelId.setText("A propos de Gestio");
            pathLabelId.setText("/home/a propos de Gestio");

            AllStudentSceneId.setVisible(false);
            EvaluateStudentSceneId.setVisible(false);
            UeStudentSceneId.setVisible(false);
            AddStudentSceneId.setVisible(false);

            AproposSceneId.setVisible(true);

            allStudentId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30; -fx-cursor: hand;");
            evaluateStudentId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30; -fx-cursor: hand;");
            ueStudentId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30; -fx-cursor: hand;");
            addStudentId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30; -fx-cursor: hand;");

            aproposId.setStyle("-fx-background-color: #323233; -fx-background-radius: 30 0 0 30; -fx-cursor: hand;");
        }
    }

    @FXML
    void EvaluateStudentClicked(ActionEvent event) {

        if (!EvaluateStudentSceneId.isVisible()) {

            headerLabelId.setText("Evaluer un étudiant");
            pathLabelId.setText("/home/evaluer un etudiant");

            AllStudentSceneId.setVisible(false);
            AproposSceneId.setVisible(false);
            UeStudentSceneId.setVisible(false);
            AddStudentSceneId.setVisible(false);

            EvaluateStudentSceneId.setVisible(true);

            allStudentId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30; -fx-cursor: hand;");
            ueStudentId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30; -fx-cursor: hand;");
            addStudentId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30; -fx-cursor: hand;");           
            aproposId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30; -fx-cursor: hand;");

            evaluateStudentId.setStyle("-fx-background-color: #323233; -fx-background-radius: 30 0 0 30; -fx-cursor: hand;");
        }
    }

    @FXML
    void UeStudentClicked(ActionEvent event) {

        if (!UeStudentSceneId.isVisible()) {

            headerLabelId.setText("Unité d'enseignement");
            pathLabelId.setText("/home/unite denseignement");

            AllStudentSceneId.setVisible(false);
            AproposSceneId.setVisible(false);
            EvaluateStudentSceneId.setVisible(false);
            AddStudentSceneId.setVisible(false);

            UeStudentSceneId.setVisible(true);

            allStudentId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30; -fx-cursor: hand;");
            addStudentId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30; -fx-cursor: hand;");           
            aproposId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30; -fx-cursor: hand;");
            evaluateStudentId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30; -fx-cursor: hand;");

            ueStudentId.setStyle("-fx-background-color: #323233; -fx-background-radius: 30 0 0 30; -fx-cursor: hand;");
        }
        
    }

}
