package fatec.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fatec.classes.Aviao;
import fatec.dao.AviaoDAO;
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

public class AvioesController implements Initializable {

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
    private Button btnDecolar;

    @FXML
    private Button btnPousar;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableView<Aviao> tblAvioes;

    @FXML
    private TableColumn<Aviao, Integer> colId;

    @FXML
    private TableColumn<Aviao, String> colMarca;

    @FXML
    private TableColumn<Aviao, String> colModelo;

    @FXML
    private TableColumn<Aviao, Integer> colAno;

    @FXML
    private TableColumn<Aviao, Double> colPeso;

    @FXML
    private TableColumn<Aviao, Integer> colNumMotores;

    @FXML
    private TableColumn<Aviao, Double> colEnvergadura;

    private AviaoDAO aviaoDAO;
    private Aviao aviaoSelecionado;
    private ObservableList<Aviao> avioesList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        aviaoDAO = new AviaoDAO();
        avioesList = FXCollections.observableArrayList();
        configurarTabela();
        atualizarTabela();
    }

    private void configurarTabela() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
        colPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        colNumMotores.setCellValueFactory(new PropertyValueFactory<>("numMotores"));
        colEnvergadura.setCellValueFactory(new PropertyValueFactory<>("envergadura"));

        tblAvioes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                aviaoSelecionado = newSelection;
                preencherCampos(aviaoSelecionado);
            }
        });
    }

    private void preencherCampos(Aviao aviao) {
        txtMarca.setText(aviao.getMarca());
        txtModelo.setText(aviao.getModelo());
        txtAno.setText(String.valueOf(aviao.getAno()));
        txtPeso.setText(String.valueOf(aviao.getPeso()));
        txtMotores.setText(String.valueOf(aviao.getNumMotores()));
        txtEnvergadura.setText(String.valueOf(aviao.getEnvergadura()));
    }

    private void atualizarTabela() {
        try {
            List<Aviao> avioes = aviaoDAO.listarTodos();
            avioesList.clear();
            avioesList.addAll(avioes);
            tblAvioes.setItems(avioesList);
        } catch (Exception e) {
            mbox.ShowMessageBox("Erro", "Erro ao carregar aviões: " + e.getMessage());
        }
    }

    @FXML
    private void CriarOnAction() {
        try {
            String marca = txtMarca.getText();
            String modelo = txtModelo.getText();
            int ano = Integer.parseInt(txtAno.getText());
            double peso = Double.parseDouble(txtPeso.getText());
            int numMotores = Integer.parseInt(txtMotores.getText());
            double envergadura = Double.parseDouble(txtEnvergadura.getText());

            if (marca.isEmpty() || modelo.isEmpty()) {
                mbox.ShowMessageBox("Erro", "Por favor, preencha todos os campos obrigatórios.");
                return;
            }

            Aviao novoAviao = new Aviao(marca, modelo, ano, peso, numMotores, envergadura);
            aviaoDAO.inserir(novoAviao);
            limparCampos();
            atualizarTabela();
            mbox.ShowMessageBox("Sucesso", "Avião cadastrado com sucesso!");
        } catch (NumberFormatException e) {
            mbox.ShowMessageBox("Erro", "Por favor, insira valores numéricos válidos para Ano, Peso, Número de Motores e Envergadura.");
        } catch (Exception e) {
            mbox.ShowMessageBox("Erro", "Erro ao cadastrar avião: " + e.getMessage());
        }
    }

    @FXML
    private void AtualizarOnAction() {
        if (aviaoSelecionado == null) {
            mbox.ShowMessageBox("Erro", "Por favor, selecione um avião para atualizar.");
            return;
        }
        try {
            String marca = txtMarca.getText();
            String modelo = txtModelo.getText();
            int ano = Integer.parseInt(txtAno.getText());
            double peso = Double.parseDouble(txtPeso.getText());
            int numMotores = Integer.parseInt(txtMotores.getText());
            double envergadura = Double.parseDouble(txtEnvergadura.getText());

            if (marca.isEmpty() || modelo.isEmpty()) {
                mbox.ShowMessageBox("Erro", "Por favor, preencha todos os campos obrigatórios.");
                return;
            }

            aviaoSelecionado.setMarca(marca);
            aviaoSelecionado.setModelo(modelo);
            aviaoSelecionado.setAno(ano);
            aviaoSelecionado.setPeso(peso);
            aviaoSelecionado.setNumMotores(numMotores);
            aviaoSelecionado.setEnvergadura(envergadura);

            aviaoDAO.atualizar(aviaoSelecionado);
            limparCampos();
            atualizarTabela();
            mbox.ShowMessageBox("Sucesso", "Avião atualizado com sucesso!");
        } catch (NumberFormatException e) {
            mbox.ShowMessageBox("Erro", "Por favor, insira valores numéricos válidos para Ano, Peso, Número de Motores e Envergadura.");
        } catch (Exception e) {
            mbox.ShowMessageBox("Erro", "Erro ao atualizar avião: " + e.getMessage());
        }
    }

    @FXML
    private void ExcluirOnAction() {
        if (aviaoSelecionado == null) {
            mbox.ShowMessageBox("Erro", "Por favor, selecione um avião para excluir.");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Exclusão");
        alert.setHeaderText("Excluir Avião");
        alert.setContentText("Tem certeza que deseja excluir o avião " + aviaoSelecionado.getMarca() + " " + aviaoSelecionado.getModelo() + "?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            try {
                aviaoDAO.excluir(aviaoSelecionado.getId());
                limparCampos();
                atualizarTabela();
                mbox.ShowMessageBox("Sucesso", "Avião excluído com sucesso!");
            } catch (Exception e) {
                mbox.ShowMessageBox("Erro", "Erro ao excluir avião: " + e.getMessage());
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
            String title = aviaoSelecionado.Acelerar();
            String gifPath = getClass().getResource("/fatec/gifs/aviao-acelerar.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void FrearOnAction() {
        if (VerificarSelecionado()) {
            String title = aviaoSelecionado.Frear();
            String gifPath = getClass().getResource("/fatec/gifs/aviao-frear.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void DecolarOnAction() {
        if (VerificarSelecionado()) {
            String title = aviaoSelecionado.Decolar();
            String gifPath = getClass().getResource("/fatec/gifs/aviao-decolar.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void PousarOnAction() {
        if (VerificarSelecionado()) {
            String title = aviaoSelecionado.Pousar();
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

    private boolean VerificarSelecionado() {
        if (aviaoSelecionado == null) {
            mbox.ShowMessageBox("Erro", "Por favor, selecione um avião primeiro na tabela.");
            return false;
        }
        return true;
    }

    private void limparCampos() {
        txtMarca.clear();
        txtModelo.clear();
        txtAno.clear();
        txtPeso.clear();
        txtMotores.clear();
        txtEnvergadura.clear();
        aviaoSelecionado = null;
    }
} 