package fatec.controllers;

import java.io.IOException;

import fatec.classes.Mamifero;
import fatec.utils.mbox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MamiferosController {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtIdade;

    @FXML
    private TextField txtPeso;

    @FXML
    private CheckBox chkPelos;

    @FXML
    private TextField txtAlimentacao;

    @FXML
    private Button btnCriar;

    @FXML
    private Button btnSom;

    @FXML
    private Button btnAmamentar;

    @FXML
    private Button btnDormir;

    @FXML
    private Button btnVoltar;

    private Mamifero mamifero;

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

        String alimentacao = txtAlimentacao.getText();
        if (alimentacao.isEmpty()) {
            mbox.ShowMessageBox("Erro", "Tipo de alimentação não pode ser vazio!");
            return;
        }

        boolean temPelos = chkPelos.isSelected();

        mamifero = new Mamifero(nome, idade, peso, temPelos, alimentacao);

        mbox.ShowMessageBox("Sucesso", "Mamífero criado!");
    }

    @FXML
    private void CadastroOnAction() {
        String message = "Nome: " + mamifero.getNome() +
                "\nIdade: " + mamifero.getIdade() +
                "\nPeso: " + mamifero.getPeso() +
                "\nTem pelos: " + (mamifero.isTemPelos() ? "Sim" : "Não") +
                "\nTipo de alimentação: " + mamifero.getTipoAlimentacao();

        mbox.ShowMessageBox("Mamífero cadastrado", message);
    }

    @FXML
    public void SomOnAction() {
        if (Verificar()) {
            String title = mamifero.FazerSom();
            String gifPath = getClass().getResource("/fatec/gifs/mamifero-som.gif").toExternalForm();
            String soundPath = getClass().getResource("/fatec/sounds/mamifero-som.mp3").toExternalForm();

            mbox.ReproduzirSom(soundPath);
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void AmamentarOnAction() {
        if (Verificar()) {
            String title = mamifero.Amamentar();
            String gifPath = getClass().getResource("/fatec/gifs/mamifero-amamentar.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void DormirOnAction() {
        if (Verificar()) {
            String title = mamifero.Dormir();
            String gifPath = getClass().getResource("/fatec/gifs/mamifero-dormir.gif").toExternalForm();
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
        if (mamifero == null) {
            mbox.ShowMessageBox("Erro", "Por favor, crie um mamífero primeiro.");
            return false;
        }
        return true;
    }
} 