package model;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import dbconnecte.Dbase;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Student {

    private int id;
    private int matricule;
    private String nom;
    private String prenom;
    private String telephone;
    private String sexe;
    private String date_naiss;
    private String note;

    private int id_filiere;
    private int id_semestre;
    private int id_session;
    private int id_annee_academique;
    private int id_ue;

    private GridPane actions;
    private GridPane actionsNote;

    // GridPane grid = new GridPane();
    // grid.setVgap(4);
    // grid.setPadding(new Insets(5, 5, 5, 5));
    // grid.add(new Label("First Name: "), 0, 0);
    // grid.add(new TextField(), 1, 0);
    // grid.add(new Label("Last Name: "), 0, 1);
    // grid.add(new TextField(), 1, 1);
    // grid.add(new Label("Email: "), 0, 2);
    // grid.add(new TextField(), 1, 2);

    

    public Student(int id, int matricule, String nom, String prenom, String telephone, String sexe, String date_naiss,
            String note, int id_filiere, int id_semestre, int id_session, int id_annee_academique, int id_ue) {

        super();

        this.id = id;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.sexe = sexe;
        this.date_naiss = date_naiss;

        this.note = note;
        
        this.id_filiere = id_filiere;
        this.id_semestre = id_semestre;
        this.id_session = id_session;
        this.id_annee_academique = id_annee_academique;
        this.id_ue = id_ue;

        noteSetup();

        this.actions = new GridPane();
        actions.setVgap(1);
        actions.setPadding(new Insets(3, 3, 3, 3));

        Button update = new Button("Modifier");
        Button delete = new Button("Supprimer");
        update.setStyle("-fx-background-color: #48a5e4; -fx-text-fill: white;");
        delete.setStyle("-fx-background-color: #d608089d; -fx-text-fill: white;");

        actions.add(update, 0, 0);
        actions.add(delete, 1, 0);

        update.setOnAction(
                (ActionEvent event) -> {
                    System.out.println("selectedData: ");

                    Parent root;
                    try {

                        // FXMLLoader loader = new
                        // FXMLLoader(getClass().getResource("../fxml/UpdateStudentModalScene.fxml"));
                        // loader.setController(new UpdateStudentModalSceneController());

                        // root = loader.load();
                        // Scene scene = new Scene(root);

                        root = FXMLLoader.load(getClass().getResource("../fxml/UpdateStudentModalScene.fxml"));
                        Scene scene = new Scene(root);

                        Stage mainStage = new Stage();
                        Stage currentStage = (Stage) update.getScene().getWindow();

                        mainStage.setUserData(this);
                        mainStage.initModality(Modality.APPLICATION_MODAL);
                        mainStage.initOwner(currentStage);
                        mainStage.setResizable(false);

                        mainStage.setTitle("Gestio Eneam");
                        mainStage.setScene(scene);

                        mainStage.show();

                    } catch (IOException e) {

                        System.out.println(e);
                    }
                });

        delete.setOnAction(
            (ActionEvent event) -> {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Supprimer");
                alert.setHeaderText("Suppression de l'étudiant " + this.nom + " " + this.prenom);
                alert.setContentText("Voulez-vous vraiment supprimer cet étudiant ?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get() == null) {
                    //rien
                } else if (option.get() == ButtonType.OK) {
                    deleteToDatabase(this.id);
                } else if (option.get() == ButtonType.CANCEL) {
                    //rien
                }

            });
    
    
        this.actionsNote = new GridPane();
        actionsNote.setVgap(0);
        actionsNote.setPadding(new Insets(3, 3, 3, 3));

        Button addNote = new Button("Noter");
        addNote.setStyle("-fx-background-color: #48a5e4; -fx-text-fill: white;");
        addNote.setPadding(new Insets(5, 15, 5, 15));
        actionsNote.add(addNote, 1, 1);

        addNote.setOnAction(
            (ActionEvent event) -> {
                System.out.println("selectedData: ");

                Parent root;
                try {

                    // FXMLLoader loader = new
                    // FXMLLoader(getClass().getResource("../fxml/UpdateStudentModalScene.fxml"));
                    // loader.setController(new UpdateStudentModalSceneController());

                    // root = loader.load();
                    // Scene scene = new Scene(root);

                    root = FXMLLoader.load(getClass().getResource("../fxml/AddNoteModalScene.fxml"));
                    Scene scene = new Scene(root);

                    Stage mainStage = new Stage();
                    Stage currentStage = (Stage) addNote.getScene().getWindow();

                    mainStage.setUserData(this);
                    mainStage.initModality(Modality.APPLICATION_MODAL);
                    mainStage.initOwner(currentStage);
                    mainStage.setResizable(false);

                    mainStage.setTitle("Gestio Eneam");
                    mainStage.setScene(scene);

                    mainStage.show();

                } catch (IOException e) {

                    System.out.println(e);
                }
            });

    
    }

    // public Student(int id, int matricule, String nom, String prenom, String telephone, String sexe, String date_naiss,
    //         String note, int id_filiere, int id_semestre, int id_session, int id_annee_academique, int id_ue,
    //         GridPane actions) {
    //     this.id = id;
    //     this.matricule = matricule;
    //     this.nom = nom;
    //     this.prenom = prenom;
    //     this.telephone = telephone;
    //     this.sexe = sexe;
    //     this.date_naiss = date_naiss;
    //     this.note = note;
    //     this.id_filiere = id_filiere;
    //     this.id_semestre = id_semestre;
    //     this.id_session = id_session;
    //     this.id_annee_academique = id_annee_academique;
    //     this.id_ue = id_ue;
    //     this.actions = actions;
    // }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getDate_naiss() {
        return date_naiss;
    }

    public void setDate_naiss(String date_naiss) {
        this.date_naiss = date_naiss;
    }

    

    public int getId_filiere() {
        return id_filiere;
    }

    public void setId_filiere(int id_filiere) {
        this.id_filiere = id_filiere;
    }

    public int getId_semestre() {
        return id_semestre;
    }

    public void setId_semestre(int id_semestre) {
        this.id_semestre = id_semestre;
    }

    public int getId_session() {
        return id_session;
    }

    public void setId_session(int id_session) {
        this.id_session = id_session;
    }

    public int getId_annee_academique() {
        return id_annee_academique;
    }

    public void setId_annee_academique(int id_annee_academique) {
        this.id_annee_academique = id_annee_academique;
    }

    public int getId_ue() {
        return id_ue;
    }

    public void setId_ue(int id_ue) {
        this.id_ue = id_ue;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public GridPane getActions() {
        return actions;
    }

    public void setActions(GridPane actions) {
        this.actions = actions;
    }   

    public GridPane getActionsNote() {
        return actionsNote;
    }

    public void setActionsNote(GridPane actionsNote) {
        this.actionsNote = actionsNote;
    }

    public boolean insertToDataBase() {

        Connection db = Dbase.connect();
        PreparedStatement statement;
        int result = 0;
        String query;

        try {

            query = "INSERT INTO ETUDIANT VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)";
            statement = db.prepareStatement(query);

            statement.setInt(1, this.matricule);
            statement.setString(2, this.nom);
            statement.setString(3, this.prenom);
            statement.setString(4, this.telephone);
            statement.setString(5, this.sexe);
            statement.setString(6, this.date_naiss);
            statement.setInt(7, this.id_filiere);


            result = statement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);

            return false;
        }

        if (result == 1) {
            return true;
        } else {
            return false;
        }

    }

    public boolean updateDataBase(int id) {

        Connection db = Dbase.connect();
        PreparedStatement statement;
        int result = 0;
        String query;

        try {

            query = "UPDATE ETUDIANT SET " +

                    "matricule = ?, " +
                    "nom = ?, " +
                    "prenom = ?, " +
                    "telephone = ?, " +
                    "sexe = ?, " +
                    "date_naiss = ? " +

                    "WHERE id = ?;";

            statement = db.prepareStatement(query);

            statement.setInt(1, this.matricule);
            statement.setString(2, this.nom);
            statement.setString(3, this.prenom);
            statement.setString(4, this.telephone);
            statement.setString(5, this.sexe);
            statement.setString(6, this.date_naiss);
            statement.setInt(7, id);

            result = statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Erreur : " + e);
        }

        if (result != 0) {
            return true;
        } else {
            return false;
        }

    }

    public boolean deleteToDatabase(int id) {

        Connection db = Dbase.connect();
        PreparedStatement statement;
        int result = 0;
        String query;

        try {

            query = "DELETE FROM etudiant WHERE id = ? ";

            statement = db.prepareStatement(query);

            statement.setInt(1, id);

            result = statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("Erreur : " + e);
        }

        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean insertNoteToDatabase(){
        
        Connection db = Dbase.connect();
        PreparedStatement statement;
        int result = 0;
        String query;

        try {

            query = "INSERT INTO EVALUATION VALUES (?, ?, ?, ?, ?, ?, ?)";
            statement = db.prepareStatement(query);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            dtf.format(LocalDateTime.now());

            statement.setInt(1, this.id);
            statement.setInt(2, this.id_ue);
            statement.setInt(3, this.id_annee_academique);
            statement.setInt(4, this.id_session);
            statement.setInt(5, this.id_semestre);
            statement.setString(6, dtf.format(LocalDateTime.now()));
            statement.setInt(7, Integer.parseInt(this.note));



            result = statement.executeUpdate();

        } catch (Exception e) {
                System.out.println(e);
                return false;
        }

        if (result == 1) {
            return true;
        } else {
            return false;
        }

    }
    
    public boolean updateNoteToDatabase(int id, int note){

        Connection db = Dbase.connect();
        PreparedStatement statement;
        int result = 0;
        String query;

        try {

            query = "UPDATE EVALUATION SET " +
                    "note = ? " +

                    "WHERE id_etudiant = ? AND id_session = ? AND id_ue = ? AND id_semestre = ? AND id_annee_academique = ?";
            
            statement = db.prepareStatement(query);

            statement.setInt(1, note);
            statement.setInt(2, this.id);
            statement.setInt(3, this.id_session);
            statement.setInt(4, this.id_ue);
            statement.setInt(5, this.id_semestre);
            statement.setInt(6, this.id_annee_academique);

            result = statement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }

        if (result != 0) {
            return true;
        } else {
            return false;
        }

    }

    public void noteSetup(){
        
        Connection db = Dbase.connect();
        PreparedStatement statement;
        ResultSet result;
        String query;

        if(id_filiere != 0 || id_semestre != 0 || id_session != 0 || id_annee_academique != 0 || id_ue != 0){

            try {

                query = "SELECT note FROM evaluation, etudiant, filiere, semestre, session, annee_academique, ue WHERE " +
                "evaluation.id_etudiant = etudiant.id AND "+ 
                "evaluation.id_ue = ue.id AND "+ 
                "evaluation.id_annee_academique = annee_academique.id AND "+ 
                "evaluation.id_session = session.id AND "+ 
                "evaluation.id_semestre = semestre.id AND "+ 
                "etudiant.id_filiere = filiere.id AND "+ 
                "etudiant.id = ? AND "+ 
                "filiere.id = ? AND "+ 
                "ue.id = ? AND "+ 
                "annee_academique.id = ? AND "+ 
                "session.id = ? AND "+ 
                "semestre.id = ?";

                statement = db.prepareStatement(query);
                statement.setInt(1, id);
                statement.setInt(2, id_filiere);
                statement.setInt(3, id_ue);
                statement.setInt(4, id_annee_academique);
                statement.setInt(5, id_session);
                statement.setInt(6, id_semestre);

                result = statement.executeQuery();
                // System.out.println(result.next());

                // System.out.println(query);
                // System.out.println(id+" "+
                // id_filiere+" "+
                // id_ue+" "+
                // id_annee_academique+" "+
                // id_session+" "+
                // id_semestre);
                

                while (result.next()) {
                    
                    this.note = String.valueOf(result.getInt(1));
                    
                }
            } catch (SQLException e) {
                System.out.println(e);
            }

        }
        
    }

}
