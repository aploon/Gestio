import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {

        // Button btn = new Button();
        // btn.setText("Say 'Hello World'");
        // btn.setOnAction(new EventHandler<ActionEvent>() {

        // @Override
        // public void handle(ActionEvent event) {
        // System.out.println("Hello World!");
        // }
        // });

        // StackPane root = new StackPane();
        // root.getChildren().add(btn);

        // Scene scene = new Scene(root, 300, 250);

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("fxml/LoginScene.fxml"));
            Scene scene = new Scene(root);

            primaryStage.getIcons().add(new Image("gestio.png"));

            primaryStage.setTitle("Connexion à Gestio Eneam");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            // primaryStage.setMinWidth(690);
            // primaryStage.setMinHeight(468);
            // primaryStage.setMaxWidth(690);
            // primaryStage.setMaxHeight(468);
            
            primaryStage.show();
            // Scene scene = new Scene(root);

            // primaryStage.setTitle("Gestio Eneam");
            // primaryStage.setScene(scene);
            // primaryStage.setMinWidth(500);
            // primaryStage.setMinHeight(500);
            // primaryStage.show();
        } catch (IOException e) {

            // e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
