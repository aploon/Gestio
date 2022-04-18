package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import dbconnecte.Dbase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class LoginSceneController implements Initializable{

    @FXML
    private TextField loginId;

    @FXML
    private PasswordField passwordId;

    @FXML
    private Button loginBtnId;

    @FXML
    private ImageView loginLoaderId;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        loginBtnId.setStyle("-fx-cursor: hand;");
        
    }

    @FXML
    void OnLoginClicked(ActionEvent event) {
        checkAuthentification();
    }

    @FXML
    void loginKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {

            checkAuthentification();
        }
    }

    void checkAuthentification(){
        Connection db = Dbase.connect();
        PreparedStatement statement;
        ResultSet result;
        String query;

        try {
            query = "SELECT * FROM COMPTE WHERE TYPE = 'Admin' AND (PSEUDO = ? OR EMAIL = ?) AND PASSWORD = ?";
            statement = db.prepareStatement(query);

            statement.setString(1, loginId.getText());
            statement.setString(2, loginId.getText());
            statement.setString(3, passwordId.getText());

            result = statement.executeQuery();

            if (result.next()) {
                System.out.println("Connecter avec succès !");

                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("../fxml/MainScene.fxml"));
                    Scene scene = new Scene(root);

                    Stage mainStage = new Stage();
                    mainStage.getIcons().add(new Image("/assets/img/gestio.png"));

                    Stage currentStage = (Stage)loginId.getScene().getWindow();

                    mainStage.setTitle("Gestio Eneam");
                    mainStage.setScene(scene);
                    mainStage.setMinWidth(500);
                    mainStage.setMinHeight(500);

                    currentStage.close();
                    mainStage.show();

                    
                } catch (IOException e) {

                    // e.printStackTrace();
                }
            } else {
                System.out.println("Non connecter !");

                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur de connexion");
                alert.setContentText("Une erreur s'est produite lors de la connexion à Gestio ! \n" 
                + "Veuillez vérifier vos identifiants de connexion et réessayer");
                alert.showAndWait();
            }

        } catch (Exception e) {
            System.out.println("Erreur lors de la connexion à Gestio : " + e);

            Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur de connexion");
                alert.setContentText("Une erreur s'est produite lors de la connexion à Gestio ! \n" 
                + "Veuillez vérifier vos identifiants de connexion et réessayer \n"
                + "Erreur de connexion : " + e);
                alert.showAndWait();

        }
    }
}
