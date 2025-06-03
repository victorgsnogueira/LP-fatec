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
    private Button btnAves;
    @FXML
    private Button btnRepteis;
    @FXML
    private Button btnMamiferos;
    @FXML
    private Button btnBicicletas;
    @FXML
    private Button btnAvioes;
    @FXML
    private Button btnSair;

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
    private void AquaticosOnAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fatec/views/aquaticos-view.fxml"));
            Parent root = loader.load();

            Stage currentStage = (Stage) btnAquaticos.getScene().getWindow();
            Scene newScene = new Scene(root);

            currentStage.setScene(newScene);
            currentStage.setTitle("Animais Aquaticos");
    }

    @FXML
    private void CarrosOnAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fatec/views/carros-view.fxml"));
            Parent root = loader.load();

            Stage currentStage = (Stage) btnCarros.getScene().getWindow();
            Scene newScene = new Scene(root);

            currentStage.setScene(newScene);
            currentStage.setTitle("Carros");
    }

    @FXML
    private void MotosOnAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fatec/views/motos-view.fxml"));
            Parent root = loader.load();

            Stage currentStage = (Stage) btnMotos.getScene().getWindow();
            Scene newScene = new Scene(root);

            currentStage.setScene(newScene);
            currentStage.setTitle("Motos");
    }

    @FXML
    private void CaminhoesOnAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fatec/views/caminhoes-view.fxml"));
            Parent root = loader.load();

            Stage currentStage = (Stage) btnCaminhoes.getScene().getWindow();
            Scene newScene = new Scene(root);

            currentStage.setScene(newScene);
            currentStage.setTitle("Caminhoes");
    }

    @FXML
    private void AvesOnAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fatec/views/aves-view.fxml"));
            Parent root = loader.load();

            Stage currentStage = (Stage) btnAves.getScene().getWindow();
            Scene newScene = new Scene(root);

            currentStage.setScene(newScene);
            currentStage.setTitle("Aves");
    }

    @FXML
    private void RepteisOnAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fatec/views/repteis-view.fxml"));
            Parent root = loader.load();

            Stage currentStage = (Stage) btnRepteis.getScene().getWindow();
            Scene newScene = new Scene(root);

            currentStage.setScene(newScene);
            currentStage.setTitle("Répteis");
    }

    @FXML
    private void MamiferosOnAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fatec/views/mamiferos-view.fxml"));
            Parent root = loader.load();

            Stage currentStage = (Stage) btnMamiferos.getScene().getWindow();
            Scene newScene = new Scene(root);

            currentStage.setScene(newScene);
            currentStage.setTitle("Mamíferos");
    }

    @FXML
    private void BicicletasOnAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fatec/views/bicicletas-view.fxml"));
            Parent root = loader.load();

            Stage currentStage = (Stage) btnBicicletas.getScene().getWindow();
            Scene newScene = new Scene(root);

            currentStage.setScene(newScene);
            currentStage.setTitle("Bicicletas");
    }

    @FXML
    private void AvioesOnAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fatec/views/avioes-view.fxml"));
            Parent root = loader.load();

            Stage currentStage = (Stage) btnAvioes.getScene().getWindow();
            Scene newScene = new Scene(root);

            currentStage.setScene(newScene);
            currentStage.setTitle("Aviões");
    }

    @FXML
    private void SairOnAction() {
        System.exit(0);
    }
}