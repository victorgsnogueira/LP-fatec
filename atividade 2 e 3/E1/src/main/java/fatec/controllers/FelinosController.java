package fatec.controllers;

import java.io.IOException;

import fatec.classes.Felino;
import fatec.utils.mbox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FelinosController {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtIdade;

    @FXML
    private TextField txtPeso;

    @FXML
    private CheckBox chkDormir;

    @FXML
    private Button btnCriar;

    @FXML
    private Button btnSom;

    @FXML
    private Button btnArvore;

    @FXML
    private Button btnCacar;

    @FXML
    private Button btnVoltar;

    private Felino felino;

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

    boolean gostaDormir = chkDormir.isSelected();

    felino = new Felino(nome, idade, peso, gostaDormir);

    mbox.ShowMessageBox("Sucesso", "Felino criado!");
}


    @FXML
    private void CadastroOnAction() {
        String message = "Nome: " + felino.getNome() +
                "\nIdade: " + felino.getIdade() +
                "\nPeso: " + felino.getPeso() +
                "\nGosta de dormir: " + (felino.isGostaDeDormir() ? "Sim" : "Não");

        mbox.ShowMessageBox("Felino cadastrado", message);
    }

    @FXML
    public void SomOnAction() {
        if (Verificar()) {
            String title = felino.FazerSom();
            String gifPath = getClass().getResource("/fatec/gifs/felino-som.gif").toExternalForm();
            String soundPath = getClass().getResource("/fatec/sounds/gatinho-som.mp3").toExternalForm();

            mbox.ReproduzirSom(soundPath);
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void ArvoreOnAction() {
        if (Verificar()) {
            String title = felino.EscalarArvore();
            String gifPath = getClass().getResource("/fatec/gifs/felino-arvore.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void CacarOnAction() {
        if (Verificar()) {
            String title = felino.CacaPresas();
            String gifPath = getClass().getResource("/fatec/gifs/felino-cacar.gif").toExternalForm();
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
        if (felino == null) {
            mbox.ShowMessageBox("Erro", "Por favor, crie um felino primeiro.");
            return false;
        }
        return true;
    }
}
