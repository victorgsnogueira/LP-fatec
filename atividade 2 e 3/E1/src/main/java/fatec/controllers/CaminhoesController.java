package fatec.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fatec.classes.Caminhao;
import fatec.dao.CaminhaoDAO;
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

public class CaminhoesController implements Initializable {

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
    private Button btnCarregar;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableView<Caminhao> tblCaminhoes;

    @FXML
    private TableColumn<Caminhao, Integer> colId;

    @FXML
    private TableColumn<Caminhao, String> colMarca;

    @FXML
    private TableColumn<Caminhao, String> colModelo;

    @FXML
    private TableColumn<Caminhao, Integer> colAno;

    @FXML
    private TableColumn<Caminhao, Double> colPeso;

    @FXML
    private TableColumn<Caminhao, Double> colCarga;

    private CaminhaoDAO caminhaoDAO;
    private Caminhao caminhaoSelecionado;
    private ObservableList<Caminhao> caminhoesList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        caminhaoDAO = new CaminhaoDAO();
        caminhoesList = FXCollections.observableArrayList();
        configurarTabela();
        atualizarTabela();
    }

    private void configurarTabela() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
        colPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        colCarga.setCellValueFactory(new PropertyValueFactory<>("capacidadeCarga")); // Assuming getter is getCapacidadeCarga

        tblCaminhoes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                caminhaoSelecionado = newSelection;
                preencherCampos(caminhaoSelecionado);
            }
        });
    }

    private void preencherCampos(Caminhao caminhao) {
        txtMarca.setText(caminhao.getMarca());
        txtModelo.setText(caminhao.getModelo());
        txtAno.setText(String.valueOf(caminhao.getAno()));
        txtPeso.setText(String.valueOf(caminhao.getPeso()));
        txtCarga.setText(String.valueOf(caminhao.getCapacidadeCarga()));
    }

    private void atualizarTabela() {
        try {
            List<Caminhao> caminhoes = caminhaoDAO.listarTodos();
            caminhoesList.clear();
            caminhoesList.addAll(caminhoes);
            tblCaminhoes.setItems(caminhoesList);
        } catch (Exception e) {
            mbox.ShowMessageBox("Erro", "Erro ao carregar caminhões: " + e.getMessage());
        }
    }

    @FXML
    private void CriarOnAction() {
        try {
            String marca = txtMarca.getText();
            String modelo = txtModelo.getText();
            int ano = Integer.parseInt(txtAno.getText());
            double peso = Double.parseDouble(txtPeso.getText());
            double carga = Double.parseDouble(txtCarga.getText());

            if (marca.isEmpty() || modelo.isEmpty()) {
                mbox.ShowMessageBox("Erro", "Por favor, preencha todos os campos obrigatórios.");
                return;
            }

            Caminhao novoCaminhao = new Caminhao(marca, modelo, ano, peso, carga);
            caminhaoDAO.inserir(novoCaminhao);
            limparCampos();
            atualizarTabela();
            mbox.ShowMessageBox("Sucesso", "Caminhão cadastrado com sucesso!");
        } catch (NumberFormatException e) {
            mbox.ShowMessageBox("Erro", "Por favor, insira valores numéricos válidos para Ano, Peso e Capacidade de Carga.");
        } catch (Exception e) {
            mbox.ShowMessageBox("Erro", "Erro ao cadastrar caminhão: " + e.getMessage());
        }
    }

    @FXML
    private void AtualizarOnAction() {
        if (caminhaoSelecionado == null) {
            mbox.ShowMessageBox("Erro", "Por favor, selecione um caminhão para atualizar.");
            return;
        }
        try {
            String marca = txtMarca.getText();
            String modelo = txtModelo.getText();
            int ano = Integer.parseInt(txtAno.getText());
            double peso = Double.parseDouble(txtPeso.getText());
            double carga = Double.parseDouble(txtCarga.getText());

            if (marca.isEmpty() || modelo.isEmpty()) {
                mbox.ShowMessageBox("Erro", "Por favor, preencha todos os campos obrigatórios.");
                return;
            }

            caminhaoSelecionado.setMarca(marca);
            caminhaoSelecionado.setModelo(modelo);
            caminhaoSelecionado.setAno(ano);
            caminhaoSelecionado.setPeso(peso);
            caminhaoSelecionado.setCapacidadeCarga(carga);

            caminhaoDAO.atualizar(caminhaoSelecionado);
            limparCampos();
            atualizarTabela();
            mbox.ShowMessageBox("Sucesso", "Caminhão atualizado com sucesso!");
        } catch (NumberFormatException e) {
            mbox.ShowMessageBox("Erro", "Por favor, insira valores numéricos válidos para Ano, Peso e Capacidade de Carga.");
        } catch (Exception e) {
            mbox.ShowMessageBox("Erro", "Erro ao atualizar caminhão: " + e.getMessage());
        }
    }

    @FXML
    private void ExcluirOnAction() {
        if (caminhaoSelecionado == null) {
            mbox.ShowMessageBox("Erro", "Por favor, selecione um caminhão para excluir.");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Exclusão");
        alert.setHeaderText("Excluir Caminhão");
        alert.setContentText("Tem certeza que deseja excluir o caminhão " + caminhaoSelecionado.getMarca() + " " + caminhaoSelecionado.getModelo() + "?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            try {
                caminhaoDAO.excluir(caminhaoSelecionado.getId());
                limparCampos();
                atualizarTabela();
                mbox.ShowMessageBox("Sucesso", "Caminhão excluído com sucesso!");
            } catch (Exception e) {
                mbox.ShowMessageBox("Erro", "Erro ao excluir caminhão: " + e.getMessage());
            }
        }
    }

    @FXML
    private void ListarOnAction() {
        atualizarTabela();
    }

    @FXML
    public void AcelerarOnAction() {
        if (VerificarSelecionado()) {
            String title = caminhaoSelecionado.Acelerar();
            String gifPath = getClass().getResource("/fatec/gifs/caminhao-acelerar.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void FrearOnAction() {
        if (VerificarSelecionado()) {
            String title = caminhaoSelecionado.Frear();
            String gifPath = getClass().getResource("/fatec/gifs/caminhao-frear.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void CarregarOnAction() {
        if (VerificarSelecionado()) {
            String title = caminhaoSelecionado.Carregar();
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

    private boolean VerificarSelecionado() {
        if (caminhaoSelecionado == null) {
            mbox.ShowMessageBox("Erro", "Por favor, selecione um caminhão primeiro na tabela.");
            return false;
        }
        return true;
    }

    private void limparCampos() {
        txtMarca.clear();
        txtModelo.clear();
        txtAno.clear();
        txtPeso.clear();
        txtCarga.clear();
        caminhaoSelecionado = null;
    }
}
