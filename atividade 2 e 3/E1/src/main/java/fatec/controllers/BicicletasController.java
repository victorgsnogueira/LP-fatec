package fatec.controllers;

import java.io.IOException;

import fatec.classes.Bicicleta;
import fatec.utils.mbox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BicicletasController {

    @FXML
    private TextField txtMarca;

    @FXML
    private TextField txtModelo;

    @FXML
    private TextField txtAno;

    @FXML
    private TextField txtPeso;

    @FXML
    private TextField txtMarchas;

    @FXML
    private CheckBox chkFreioDisco;

    @FXML
    private Button btnCriar;

    @FXML
    private Button btnAcelerar;

    @FXML
    private Button btnFrear;

    @FXML
    private Button btnTrocarMarcha;

    @FXML
    private Button btnVoltar;

    private Bicicleta bicicleta;

    @FXML
    public void CriarOnAction() {
        String marca = txtMarca.getText();
        if (marca.isEmpty()) {
            mbox.ShowMessageBox("Erro", "Marca não pode ser vazia!");
            return;
        }

        String modelo = txtModelo.getText();
        if (modelo.isEmpty()) {
            mbox.ShowMessageBox("Erro", "Modelo não pode ser vazio!");
            return;
        }

        int ano;
        try {
            ano = Integer.parseInt(txtAno.getText());
        } catch (NumberFormatException e) {
            mbox.ShowMessageBox("Erro", "Ano inválido!");
            return;
        }

        double peso;
        try {
            peso = Double.parseDouble(txtPeso.getText());
        } catch (NumberFormatException e) {
            mbox.ShowMessageBox("Erro", "Peso inválido!");
            return;
        }

        int marchas;
        try {
            marchas = Integer.parseInt(txtMarchas.getText());
        } catch (NumberFormatException e) {
            mbox.ShowMessageBox("Erro", "Número de marchas inválido!");
            return;
        }

        boolean temFreioDisco = chkFreioDisco.isSelected();

        bicicleta = new Bicicleta(marca, modelo, ano, peso, marchas, temFreioDisco);

        mbox.ShowMessageBox("Sucesso", "Bicicleta criada!");
    }

    @FXML
    private void CadastroOnAction() {
        String message = "Marca: " + bicicleta.getMarca() +
                "\nModelo: " + bicicleta.getModelo() +
                "\nAno: " + bicicleta.getAno() +
                "\nPeso: " + bicicleta.getPeso() +
                "\nNúmero de marchas: " + bicicleta.getNumeroMarchas() +
                "\nTem freio a disco: " + (bicicleta.isTemFreioDisco() ? "Sim" : "Não");

        mbox.ShowMessageBox("Bicicleta cadastrada", message);
    }

    @FXML
    public void AcelerarOnAction() {
        if (Verificar()) {
            String title = bicicleta.Acelerar();
            String gifPath = getClass().getResource("/fatec/gifs/bicicleta-acelerar.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void FrearOnAction() {
        if (Verificar()) {
            String title = bicicleta.Frear();
            String gifPath = getClass().getResource("/fatec/gifs/bicicleta-frear.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void TrocarMarchaOnAction() {
        if (Verificar()) {
            String title = bicicleta.TrocarMarcha();
            String gifPath = getClass().getResource("/fatec/gifs/bicicleta-trocar-marcha.gif").toExternalForm();
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
        if (bicicleta == null) {
            mbox.ShowMessageBox("Erro", "Por favor, crie uma bicicleta primeiro.");
            return false;
        }
        return true;
    }
} 