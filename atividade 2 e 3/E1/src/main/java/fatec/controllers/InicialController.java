package fatec.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InicialController {

    @FXML
    private Button btnFelinos; 
    @FXML
    private Button btnAquaticos; 
    @FXML
    private Button btnCarros; 
    @FXML
    private Button btnMotos;
    @FXML
    private Button btnCaminhoes; 

    @FXML
    private void FelinosOnAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fatec/views/felinos-view.fxml"));
            Parent root = loader.load();

            Stage currentStage = (Stage) btnFelinos.getScene().getWindow();
            Scene newScene = new Scene(root);

            currentStage.setScene(newScene);
            currentStage.setTitle("Felinos");
    }

    @FXML
    private void AquaticosOnAction(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fatec/views/aquaticos-view.fxml"));
            Parent root = loader.load();

            Stage currentStage = (Stage) btnAquaticos.getScene().getWindow();
            Scene newScene = new Scene(root);

            currentStage.setScene(newScene);
            currentStage.setTitle("Animais Aquaticos");
    }

    @FXML
    private void CarrosOnAction(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fatec/views/carros-view.fxml"));
            Parent root = loader.load();

            Stage currentStage = (Stage) btnCarros.getScene().getWindow();
            Scene newScene = new Scene(root);

            currentStage.setScene(newScene);
            currentStage.setTitle("Carros");
    }

    @FXML
    private void MotosOnAction(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fatec/views/motos-view.fxml"));
            Parent root = loader.load();

            Stage currentStage = (Stage) btnMotos.getScene().getWindow();
            Scene newScene = new Scene(root);

            currentStage.setScene(newScene);
            currentStage.setTitle("Motos");
    }

    @FXML
    private void CaminhoesOnAction(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fatec/views/caminhoes-view.fxml"));
            Parent root = loader.load();

            Stage currentStage = (Stage) btnCaminhoes.getScene().getWindow();
            Scene newScene = new Scene(root);

            currentStage.setScene(newScene);
            currentStage.setTitle("Caminhoes");
    }
}