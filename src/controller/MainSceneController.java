package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class MainSceneController {

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

    @FXML
    void AddStudentClicked(ActionEvent event) {

        if (!AddStudentSceneId.isVisible()) {

            AllStudentSceneId.setVisible(false);
            AproposSceneId.setVisible(false);
            EvaluateStudentSceneId.setVisible(false);
            UeStudentSceneId.setVisible(false);

            AddStudentSceneId.setVisible(true);

            allStudentId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30;");
            aproposId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30;");
            evaluateStudentId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30;");
            ueStudentId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30;");

            addStudentId.setStyle("-fx-background-color: #323233; -fx-background-radius: 30 0 0 30;");
        }

    }

    @FXML
    void AllStudentClicked(ActionEvent event) {

        if (!AllStudentSceneId.isVisible()) {

            AproposSceneId.setVisible(false);
            EvaluateStudentSceneId.setVisible(false);
            UeStudentSceneId.setVisible(false);
            AddStudentSceneId.setVisible(false);

            AllStudentSceneId.setVisible(true);

            aproposId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30;");
            evaluateStudentId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30;");
            ueStudentId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30;");
            addStudentId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30;");

            allStudentId.setStyle("-fx-background-color: #323233; -fx-background-radius: 30 0 0 30;");

        }
    }

    @FXML
    void AproposClicked(ActionEvent event) {

        if (!AproposSceneId.isVisible()) {

            AllStudentSceneId.setVisible(false);
            EvaluateStudentSceneId.setVisible(false);
            UeStudentSceneId.setVisible(false);
            AddStudentSceneId.setVisible(false);

            AproposSceneId.setVisible(true);

            allStudentId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30;");
            evaluateStudentId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30;");
            ueStudentId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30;");
            addStudentId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30;");

            aproposId.setStyle("-fx-background-color: #323233; -fx-background-radius: 30 0 0 30;");
        }
    }

    @FXML
    void EvaluateStudentClicked(ActionEvent event) {

        if (!EvaluateStudentSceneId.isVisible()) {

            AllStudentSceneId.setVisible(false);
            AproposSceneId.setVisible(false);
            UeStudentSceneId.setVisible(false);
            AddStudentSceneId.setVisible(false);

            EvaluateStudentSceneId.setVisible(true);

            allStudentId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30;");
            ueStudentId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30;");
            addStudentId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30;");           
            aproposId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30;");

            evaluateStudentId.setStyle("-fx-background-color: #323233; -fx-background-radius: 30 0 0 30;");
        }
    }

    @FXML
    void UeStudentClicked(ActionEvent event) {

        if (!UeStudentSceneId.isVisible()) {

            AllStudentSceneId.setVisible(false);
            AproposSceneId.setVisible(false);
            EvaluateStudentSceneId.setVisible(false);
            AddStudentSceneId.setVisible(false);

            UeStudentSceneId.setVisible(true);

            allStudentId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30;");
            addStudentId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30;");           
            aproposId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30;");
            evaluateStudentId.setStyle("-fx-background-color: #434345; -fx-background-radius: 30 0 0 30;");

            ueStudentId.setStyle("-fx-background-color: #323233; -fx-background-radius: 30 0 0 30;");
        }
        
    }

}
