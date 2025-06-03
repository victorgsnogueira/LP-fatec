package fatec.controllers;

import java.io.IOException;

import fatec.classes.Reptil;
import fatec.utils.mbox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RepteisController {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtIdade;

    @FXML
    private TextField txtPeso;

    @FXML
    private CheckBox chkEscamas;

    @FXML
    private TextField txtTipoPele;

    @FXML
    private Button btnCriar;

    @FXML
    private Button btnSom;

    @FXML
    private Button btnTrocarPele;

    @FXML
    private Button btnTomarSol;

    @FXML
    private Button btnVoltar;

    private Reptil reptil;

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

        String tipoPele = txtTipoPele.getText();
        if (tipoPele.isEmpty()) {
            mbox.ShowMessageBox("Erro", "Tipo de pele não pode ser vazio!");
            return;
        }

        boolean temEscamas = chkEscamas.isSelected();

        reptil = new Reptil(nome, idade, peso, temEscamas, tipoPele);

        mbox.ShowMessageBox("Sucesso", "Réptil criado!");
    }

    @FXML
    private void CadastroOnAction() {
        String message = "Nome: " + reptil.getNome() +
                "\nIdade: " + reptil.getIdade() +
                "\nPeso: " + reptil.getPeso() +
                "\nTem escamas: " + (reptil.isTemEscamas() ? "Sim" : "Não") +
                "\nTipo de pele: " + reptil.getTipoPele();

        mbox.ShowMessageBox("Réptil cadastrado", message);
    }

    @FXML
    public void SomOnAction() {
        if (Verificar()) {
            String title = reptil.FazerSom();
            String gifPath = getClass().getResource("/fatec/gifs/reptil-som.gif").toExternalForm();
            String soundPath = getClass().getResource("/fatec/sounds/reptil-som.mp3").toExternalForm();

            mbox.ReproduzirSom(soundPath);
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void TrocarPeleOnAction() {
        if (Verificar()) {
            String title = reptil.TrocarPele();
            String gifPath = getClass().getResource("/fatec/gifs/reptil-trocar-pele.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void TomarSolOnAction() {
        if (Verificar()) {
            String title = reptil.TomarSol();
            String gifPath = getClass().getResource("/fatec/gifs/reptil-tomar-sol.gif").toExternalForm();
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
        if (reptil == null) {
            mbox.ShowMessageBox("Erro", "Por favor, crie um réptil primeiro.");
            return false;
        }
        return true;
    }
} 