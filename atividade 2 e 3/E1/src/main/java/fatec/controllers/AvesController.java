package fatec.controllers;

import java.io.IOException;

import fatec.classes.Ave;
import fatec.utils.mbox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AvesController {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtIdade;

    @FXML
    private TextField txtPeso;

    @FXML
    private CheckBox chkPodeVoar;

    @FXML
    private TextField txtEnvergadura;

    @FXML
    private Button btnCriar;

    @FXML
    private Button btnSom;

    @FXML
    private Button btnVoar;

    @FXML
    private Button btnNinho;

    @FXML
    private Button btnVoltar;

    private Ave ave;

    @FXML
    public void CriarOnAction() {
        String nome = txtNome.getText();
        if (nome.isEmpty()) {
            mbox.ShowMessageBox("Erro", "Nome não pode ser vazio!");
            return;
        }

        int idade;
        try {
            idade = Integer.parseInt(txtIdade.getText());
        } catch (NumberFormatException e) {
            mbox.ShowMessageBox("Erro", "Idade inválida!");
            return;
        }

        double peso;
        try {
            peso = Double.parseDouble(txtPeso.getText());
        } catch (NumberFormatException e) {
            mbox.ShowMessageBox("Erro", "Peso inválido!");
            return;
        }

        double envergadura;
        try {
            envergadura = Double.parseDouble(txtEnvergadura.getText());
        } catch (NumberFormatException e) {
            mbox.ShowMessageBox("Erro", "Envergadura inválida!");
            return;
        }

        boolean podeVoar = chkPodeVoar.isSelected();

        ave = new Ave(nome, idade, peso, podeVoar, envergadura);

        mbox.ShowMessageBox("Sucesso", "Ave criada!");
    }

    @FXML
    private void CadastroOnAction() {
        String message = "Nome: " + ave.getNome() +
                "\nIdade: " + ave.getIdade() +
                "\nPeso: " + ave.getPeso() +
                "\nPode voar: " + (ave.isPodeVoar() ? "Sim" : "Não") +
                "\nEnvergadura: " + ave.getEnvergadura();

        mbox.ShowMessageBox("Ave cadastrada", message);
    }

    @FXML
    public void SomOnAction() {
        if (Verificar()) {
            String title = ave.FazerSom();
            String gifPath = getClass().getResource("/fatec/gifs/ave-som.gif").toExternalForm();
            String soundPath = getClass().getResource("/fatec/sounds/passaro-som.mp3").toExternalForm();

            mbox.ReproduzirSom(soundPath);
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void VoarOnAction() {
        if (Verificar()) {
            String title = ave.Voar();
            String gifPath = getClass().getResource("/fatec/gifs/ave-voar.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void NinhoOnAction() {
        if (Verificar()) {
            String title = ave.ConstruirNinho();
            String gifPath = getClass().getResource("/fatec/gifs/ave-ninho.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void VoltarOnAction() throws IOException {
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fatec/views/inicial-view.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    private boolean Verificar() {
        if (ave == null) {
            mbox.ShowMessageBox("Erro", "Por favor, crie uma ave primeiro.");
            return false;
        }
        return true;
    }
} 