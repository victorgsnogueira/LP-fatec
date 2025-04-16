package fatec.controllers;

import java.io.IOException;

import fatec.classes.Moto;
import fatec.utils.mbox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MotosController {

    @FXML
    private TextField txtMarca;

    @FXML
    private TextField txtModelo;

    @FXML
    private TextField txtAno;

    @FXML
    private TextField txtPeso;

    @FXML
    private CheckBox chkCarenagem;

    @FXML
    private Button btnCriar;

    @FXML
    private Button btnAcelerar;

    @FXML
    private Button btnFrear;

    @FXML
    private Button btnEmpinar;

    @FXML
    private Button btnVoltar;

    private Moto moto;

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

        boolean temCarenagem = chkCarenagem.isSelected();

        moto = new Moto(marca, modelo, ano, peso, temCarenagem);

        mbox.ShowMessageBox("Sucesso", "Moto criada!");
    }

    @FXML
    private void CadastroOnAction() {
        String message = "Marca: " + moto.getMarca() +
                "\nModelo: " + moto.getModelo() +
                "\nAno: " + moto.getAno() +
                "\nPeso: " + moto.getPeso() +
                "\nTem carenagem: " + (moto.isTemCarenagem() ? "Sim" : "Não");

        mbox.ShowMessageBox("Moto cadastrada", message);
    }

    @FXML
    public void AcelerarOnAction() {
        if (Verificar()) {
            String title = moto.Acelerar();
            String gifPath = getClass().getResource("/fatec/gifs/moto-acelerar.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void FrearOnAction() {
        if (Verificar()) {
            String title = moto.Frear();
            String gifPath = getClass().getResource("/fatec/gifs/moto-frear.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void EmpinarOnAction() {
        if (Verificar()) {
            String title = moto.Empinar();
            String gifPath = getClass().getResource("/fatec/gifs/moto-empinar.gif").toExternalForm();
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
        if (moto == null) {
            mbox.ShowMessageBox("Erro", "Por favor, crie uma moto primeiro.");
            return false;
        }
        return true;
    }
}
