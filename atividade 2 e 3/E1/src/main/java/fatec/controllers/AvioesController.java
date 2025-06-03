package fatec.controllers;

import java.io.IOException;

import fatec.classes.Aviao;
import fatec.utils.mbox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AvioesController {

    @FXML
    private TextField txtMarca;

    @FXML
    private TextField txtModelo;

    @FXML
    private TextField txtAno;

    @FXML
    private TextField txtPeso;

    @FXML
    private TextField txtMotores;

    @FXML
    private TextField txtEnvergadura;

    @FXML
    private Button btnCriar;

    @FXML
    private Button btnAcelerar;

    @FXML
    private Button btnFrear;

    @FXML
    private Button btnDecolar;

    @FXML
    private Button btnPousar;

    @FXML
    private Button btnVoltar;

    private Aviao aviao;

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

        int motores;
        try {
            motores = Integer.parseInt(txtMotores.getText());
        } catch (NumberFormatException e) {
            mbox.ShowMessageBox("Erro", "Número de motores inválido!");
            return;
        }

        double envergadura;
        try {
            envergadura = Double.parseDouble(txtEnvergadura.getText());
        } catch (NumberFormatException e) {
            mbox.ShowMessageBox("Erro", "Envergadura inválida!");
            return;
        }

        aviao = new Aviao(marca, modelo, ano, peso, motores, envergadura);

        mbox.ShowMessageBox("Sucesso", "Avião criado!");
    }

    @FXML
    private void CadastroOnAction() {
        String message = "Marca: " + aviao.getMarca() +
                "\nModelo: " + aviao.getModelo() +
                "\nAno: " + aviao.getAno() +
                "\nPeso: " + aviao.getPeso() +
                "\nNúmero de motores: " + aviao.getNumeroMotores() +
                "\nEnvergadura: " + aviao.getEnvergadura();

        mbox.ShowMessageBox("Avião cadastrado", message);
    }

    @FXML
    public void AcelerarOnAction() {
        if (Verificar()) {
            String title = aviao.Acelerar();
            String gifPath = getClass().getResource("/fatec/gifs/aviao-acelerar.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void FrearOnAction() {
        if (Verificar()) {
            String title = aviao.Frear();
            String gifPath = getClass().getResource("/fatec/gifs/aviao-frear.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void DecolarOnAction() {
        if (Verificar()) {
            String title = aviao.Decolar();
            String gifPath = getClass().getResource("/fatec/gifs/aviao-decolar.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void PousarOnAction() {
        if (Verificar()) {
            String title = aviao.Pousar();
            String gifPath = getClass().getResource("/fatec/gifs/aviao-pousar.gif").toExternalForm();
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
        if (aviao == null) {
            mbox.ShowMessageBox("Erro", "Por favor, crie um avião primeiro.");
            return false;
        }
        return true;
    }
} 