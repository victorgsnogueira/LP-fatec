package fatec.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fatec.classes.Moto;
import fatec.dao.MotoDAO;
import fatec.utils.mbox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MotosController implements Initializable {

    @FXML
    private TextField txtMarca;

    @FXML
    private TextField txtModelo;

    @FXML
    private TextField txtAno;

    @FXML
    private TextField txtPeso;

    @FXML
    private TextField txtCilindrada;

    @FXML
    private Button btnCriar;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnListar;

    @FXML
    private Button btnAcelerar;

    @FXML
    private Button btnFrear;

    @FXML
    private Button btnEmpinar;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableView<Moto> tblMotos;

    @FXML
    private TableColumn<Moto, Integer> colId;

    @FXML
    private TableColumn<Moto, String> colMarca;

    @FXML
    private TableColumn<Moto, String> colModelo;

    @FXML
    private TableColumn<Moto, Integer> colAno;

    @FXML
    private TableColumn<Moto, Double> colPeso;

    @FXML
    private TableColumn<Moto, Integer> colCilindrada;

    private MotoDAO motoDAO;
    private Moto motoSelecionada;
    private ObservableList<Moto> motosList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        motoDAO = new MotoDAO();
        motosList = FXCollections.observableArrayList();
        configurarTabela();
        atualizarTabela();
    }

    private void configurarTabela() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
        colPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        colCilindrada.setCellValueFactory(new PropertyValueFactory<>("cilindrada"));

        tblMotos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                motoSelecionada = newSelection;
                preencherCampos(motoSelecionada);
            }
        });
    }

    private void preencherCampos(Moto moto) {
        txtMarca.setText(moto.getMarca());
        txtModelo.setText(moto.getModelo());
        txtAno.setText(String.valueOf(moto.getAno()));
        txtPeso.setText(String.valueOf(moto.getPeso()));
        txtCilindrada.setText(String.valueOf(moto.getCilindrada()));
    }

    private void atualizarTabela() {
        try {
            List<Moto> motos = motoDAO.listarTodos();
            motosList.clear();
            motosList.addAll(motos);
            tblMotos.setItems(motosList);
        } catch (Exception e) {
            mbox.ShowMessageBox("Erro", "Erro ao carregar motos: " + e.getMessage());
        }
    }

    @FXML
    private void CriarOnAction() {
        try {
            String marca = txtMarca.getText();
            String modelo = txtModelo.getText();
            int ano = Integer.parseInt(txtAno.getText());
            double peso = Double.parseDouble(txtPeso.getText());
            int cilindrada = Integer.parseInt(txtCilindrada.getText());

            if (marca.isEmpty() || modelo.isEmpty()) {
                mbox.ShowMessageBox("Erro", "Por favor, preencha todos os campos obrigatórios.");
                return;
            }

            Moto novaMoto = new Moto(marca, modelo, ano, peso, cilindrada);
            motoDAO.inserir(novaMoto);
            limparCampos();
            atualizarTabela();
            mbox.ShowMessageBox("Sucesso", "Moto cadastrada com sucesso!");
        } catch (NumberFormatException e) {
            mbox.ShowMessageBox("Erro", "Por favor, insira valores numéricos válidos para Ano, Peso e Cilindrada.");
        } catch (Exception e) {
            mbox.ShowMessageBox("Erro", "Erro ao cadastrar moto: " + e.getMessage());
        }
    }

    @FXML
    private void AtualizarOnAction() {
        if (motoSelecionada == null) {
            mbox.ShowMessageBox("Erro", "Por favor, selecione uma moto para atualizar.");
            return;
        }
        try {
            String marca = txtMarca.getText();
            String modelo = txtModelo.getText();
            int ano = Integer.parseInt(txtAno.getText());
            double peso = Double.parseDouble(txtPeso.getText());
            int cilindrada = Integer.parseInt(txtCilindrada.getText());

            if (marca.isEmpty() || modelo.isEmpty()) {
                mbox.ShowMessageBox("Erro", "Por favor, preencha todos os campos obrigatórios.");
                return;
            }

            motoSelecionada.setMarca(marca);
            motoSelecionada.setModelo(modelo);
            motoSelecionada.setAno(ano);
            motoSelecionada.setPeso(peso);
            motoSelecionada.setCilindrada(cilindrada);

            motoDAO.atualizar(motoSelecionada);
            limparCampos();
            atualizarTabela();
            mbox.ShowMessageBox("Sucesso", "Moto atualizada com sucesso!");
        } catch (NumberFormatException e) {
            mbox.ShowMessageBox("Erro", "Por favor, insira valores numéricos válidos para Ano, Peso e Cilindrada.");
        } catch (Exception e) {
            mbox.ShowMessageBox("Erro", "Erro ao atualizar moto: " + e.getMessage());
        }
    }

    @FXML
    private void ExcluirOnAction() {
        if (motoSelecionada == null) {
            mbox.ShowMessageBox("Erro", "Por favor, selecione uma moto para excluir.");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Exclusão");
        alert.setHeaderText("Excluir Moto");
        alert.setContentText("Tem certeza que deseja excluir a moto " + motoSelecionada.getMarca() + " " + motoSelecionada.getModelo() + "?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            try {
                motoDAO.excluir(motoSelecionada.getId());
                limparCampos();
                atualizarTabela();
                mbox.ShowMessageBox("Sucesso", "Moto excluída com sucesso!");
            } catch (Exception e) {
                mbox.ShowMessageBox("Erro", "Erro ao excluir moto: " + e.getMessage());
            }
        }
    }

    @FXML
    private void ListarOnAction() {
        atualizarTabela();
    }

    @FXML
    public void AcelerarOnAction() {
        if (VerificarSelecionada()) {
            String title = motoSelecionada.Acelerar();
            String gifPath = getClass().getResource("/fatec/gifs/moto-acelerar.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void FrearOnAction() {
        if (VerificarSelecionada()) {
            String title = motoSelecionada.Frear();
            String gifPath = getClass().getResource("/fatec/gifs/moto-frear.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void EmpinarOnAction() {
        if (VerificarSelecionada()) {
            String title = motoSelecionada.Empinar();
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

    private boolean VerificarSelecionada() {
        if (motoSelecionada == null) {
            mbox.ShowMessageBox("Erro", "Por favor, selecione uma moto primeiro na tabela.");
            return false;
        }
        return true;
    }

    private void limparCampos() {
        txtMarca.clear();
        txtModelo.clear();
        txtAno.clear();
        txtPeso.clear();
        txtCilindrada.clear();
        motoSelecionada = null;
    }
}
