package fatec.controllers;

import java.io.IOException;

import fatec.classes.Aquatico;
import fatec.utils.mbox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AquaticosController {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtIdade;

    @FXML
    private TextField txtPeso;

    @FXML
    private CheckBox chkSalgada;

    @FXML
    private Button btnCriar;

    @FXML
    private Button btnSom;

    @FXML
    private Button btnNadar;

    @FXML
    private Button btnMergulhar;
    @FXML
    private Button btnVoltar;

    private Aquatico aquatico;

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

        boolean aguaSalgada = chkSalgada.isSelected();

        aquatico = new Aquatico(nome, idade, peso, aguaSalgada);

        mbox.ShowMessageBox("Sucesso", "Animal aquático criado!");
    }

    @FXML
    private void CadastroOnAction() {
        String message = "Nome: " + aquatico.getNome() +
                "\nIdade: " + aquatico.getIdade() +
                "\nPeso: " + aquatico.getPeso() +
                "\nVive em águas salgadas: " + (aquatico.isViveEmAguasSalgadas() ? "Sim" : "Não");

        mbox.ShowMessageBox("Ser aquático cadastrado", message);
    }

    @FXML
    public void SomOnAction() {
        if (Verificar()) {
            String title = aquatico.FazerSom();
            String gifPath = getClass().getResource("/fatec/gifs/aquatico-som.gif").toExternalForm();
            String soundPath = getClass().getResource("/fatec/sounds/golfinho-som.mp3").toExternalForm();

            mbox.ReproduzirSom(soundPath);
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void NadarOnAction() {
        if (Verificar()) {
            String title = aquatico.Nadar();
            String gifPath = getClass().getResource("/fatec/gifs/aquatico-nadar.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void MergulharOnAction() {
        if (Verificar()) {
            String title = aquatico.Mergulhar();
            String gifPath = getClass().getResource("/fatec/gifs/aquatico-mergulhar.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void VoltarOnAction() {
        try {
            Stage stage = (Stage) btnVoltar.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/fatec/views/inicial-view.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean Verificar() {
        if (aquatico == null) {
            mbox.ShowMessageBox("Erro", "Por favor, crie um animal primeiro.");
            return false;
        }
        return true;
    }
}
