package fatec.controllers;

import java.io.IOException;

import fatec.classes.Caminhao;
import fatec.utils.mbox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CaminhoesController {

    @FXML
    private TextField txtMarca;

    @FXML
    private TextField txtModelo;

    @FXML
    private TextField txtAno;

    @FXML
    private TextField txtPeso;

    @FXML
    private TextField txtCarga;

    @FXML
    private Button btnCriar;

    @FXML
    private Button btnAcelerar;

    @FXML
    private Button btnFreiar;

    @FXML
    private Button btnCarregar;

    @FXML
    private Button btnVoltar;

    private Caminhao caminhao;

    @FXML
    public void CriarOnAction() {
        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();

        if (marca.isEmpty() || modelo.isEmpty()) {
            mbox.ShowMessageBox("Erro", "Preencha todos os campos!");
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

        double carga;
        try {
            carga = Double.parseDouble(txtCarga.getText());
        } catch (NumberFormatException e) {
            mbox.ShowMessageBox("Erro", "Capacidade de carga inválida!");
            return;
        }

        caminhao = new Caminhao(marca, modelo, ano, peso, carga);
        mbox.ShowMessageBox("Sucesso", "Caminhão criado com sucesso!");
    }

    @FXML
private void CadastroOnAction() {
    String message = "Marca: " + caminhao.getMarca() +
                     "\nModelo: " + caminhao.getModelo() +
                     "\nAno: " + caminhao.getAno() +
                     "\nPeso: " + caminhao.getPeso() +
                     "\nCapacidade de Carga: " + caminhao.getCapacidadeCarga();

    mbox.ShowMessageBox("Caminhão cadastrado", message);
}


    @FXML
    public void AcelerarOnAction() {
        if (Verificar()) {
            String title = caminhao.Acelerar();
            String gifPath = getClass().getResource("/fatec/gifs/caminhao-acelerar.gif").toExternalForm();

            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void FrearOnAction() {
        if (Verificar()) {
            String title = caminhao.Frear();
            String gifPath = getClass().getResource("/fatec/gifs/caminhao-frear.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void CarregarOnAction() {
        if (Verificar()) {
            String title = caminhao.Carregar();
            String gifPath = getClass().getResource("/fatec/gifs/caminhao-carregar.gif").toExternalForm();
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
        if (caminhao == null) {
            mbox.ShowMessageBox("Erro", "Por favor, crie um caminhão primeiro.");
            return false;
        }
        return true;
    }
}
