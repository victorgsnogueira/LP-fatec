package fatec.controllers;

import java.io.IOException;

import fatec.classes.Carro;
import fatec.utils.mbox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CarrosController {

    @FXML
    private TextField txtMarca;

    @FXML
    private TextField txtModelo;

    @FXML
    private TextField txtAno;

    @FXML
    private TextField txtPeso;

    @FXML
    private TextField txtPortas;

    @FXML
    private Button btnCriar;

    @FXML
    private Button btnAcelerar;

    @FXML
    private Button btnFrear;

    @FXML
    private Button btnPorta;

    @FXML
    private Button btnVoltar;

    private Carro carro;

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

        int portas;
        try {
            portas = Integer.parseInt(txtPortas.getText());
        } catch (NumberFormatException e) {
            mbox.ShowMessageBox("Erro", "Número de portas inválido!");
            return;
        }

        carro = new Carro(marca, modelo, ano, peso, portas);

        mbox.ShowMessageBox("Sucesso", "Carro criado!");
    }

    @FXML
    private void CadastroOnAction() {
        String message = "Marca: " + carro.getMarca() +
                "\nModelo: " + carro.getModelo() +
                "\nAno: " + carro.getAno() +
                "\nPeso: " + carro.getPeso() +
                "\nQuantidade de Portas: " + carro.getQuantidadePortas();

        mbox.ShowMessageBox("Carro cadastrado", message);
    }

    @FXML
    public void AcelerarOnAction() {
        if (Verificar()) {
            String title = carro.Acelerar();
            String gifPath = getClass().getResource("/fatec/gifs/carro-acelerar.gif").toExternalForm();

            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void FrearOnAction() {
        if (Verificar()) {
            String title = carro.Frear();
            String gifPath = getClass().getResource("/fatec/gifs/carro-frear.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void PortaOnAction() {
        if (Verificar()) {
            String title = carro.AbrirPorta();
            String gifPath = getClass().getResource("/fatec/gifs/carro-porta.gif").toExternalForm();
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
        if (carro == null) {
            mbox.ShowMessageBox("Erro", "Por favor, crie um carro primeiro.");
            return false;
        }
        return true;
    }
}
