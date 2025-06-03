package fatec.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fatec.classes.Carro;
import fatec.dao.CarroDAO;
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

public class CarrosController implements Initializable {

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
    private Button btnPorta;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableView<Carro> tblCarros;

    @FXML
    private TableColumn<Carro, Integer> colId;

    @FXML
    private TableColumn<Carro, String> colMarca;

    @FXML
    private TableColumn<Carro, String> colModelo;

    @FXML
    private TableColumn<Carro, Integer> colAno;

    @FXML
    private TableColumn<Carro, Double> colPeso;

    @FXML
    private TableColumn<Carro, Integer> colPortas;

    private CarroDAO carroDAO;
    private Carro carroSelecionado;
    private ObservableList<Carro> carrosList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carroDAO = new CarroDAO();
        carrosList = FXCollections.observableArrayList();
        configurarTabela();
        atualizarTabela();
    }

    private void configurarTabela() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
        colPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        colPortas.setCellValueFactory(new PropertyValueFactory<>("numPortas"));

        tblCarros.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                carroSelecionado = newSelection;
                preencherCampos(carroSelecionado);
            }
        });
    }

    private void preencherCampos(Carro carro) {
        txtMarca.setText(carro.getMarca());
        txtModelo.setText(carro.getModelo());
        txtAno.setText(String.valueOf(carro.getAno()));
        txtPeso.setText(String.valueOf(carro.getPeso()));
        txtPortas.setText(String.valueOf(carro.getNumPortas()));
    }

    private void atualizarTabela() {
        try {
            List<Carro> carros = carroDAO.listarTodos();
            carrosList.clear();
            carrosList.addAll(carros);
            tblCarros.setItems(carrosList);
        } catch (Exception e) {
            mbox.ShowMessageBox("Erro", "Erro ao carregar carros: " + e.getMessage());
        }
    }

    @FXML
    private void CriarOnAction() {
        try {
            String marca = txtMarca.getText();
            String modelo = txtModelo.getText();
            int ano = Integer.parseInt(txtAno.getText());
            double peso = Double.parseDouble(txtPeso.getText());
            int portas = Integer.parseInt(txtPortas.getText());

            if (marca.isEmpty() || modelo.isEmpty()) {
                mbox.ShowMessageBox("Erro", "Por favor, preencha todos os campos obrigatórios.");
                return;
            }

            Carro novoCarro = new Carro(marca, modelo, ano, peso, portas);
            carroDAO.inserir(novoCarro);
            limparCampos();
            atualizarTabela();
            mbox.ShowMessageBox("Sucesso", "Carro cadastrado com sucesso!");
        } catch (NumberFormatException e) {
            mbox.ShowMessageBox("Erro", "Por favor, insira valores numéricos válidos para Ano, Peso e Quantidade de Portas.");
        } catch (Exception e) {
            mbox.ShowMessageBox("Erro", "Erro ao cadastrar carro: " + e.getMessage());
        }
    }

    @FXML
    private void AtualizarOnAction() {
        if (carroSelecionado == null) {
            mbox.ShowMessageBox("Erro", "Por favor, selecione um carro para atualizar.");
            return;
        }
        try {
            String marca = txtMarca.getText();
            String modelo = txtModelo.getText();
            int ano = Integer.parseInt(txtAno.getText());
            double peso = Double.parseDouble(txtPeso.getText());
            int portas = Integer.parseInt(txtPortas.getText());

            if (marca.isEmpty() || modelo.isEmpty()) {
                mbox.ShowMessageBox("Erro", "Por favor, preencha todos os campos obrigatórios.");
                return;
            }

            carroSelecionado.setMarca(marca);
            carroSelecionado.setModelo(modelo);
            carroSelecionado.setAno(ano);
            carroSelecionado.setPeso(peso);
            carroSelecionado.setNumPortas(portas);

            carroDAO.atualizar(carroSelecionado);
            limparCampos();
            atualizarTabela();
            mbox.ShowMessageBox("Sucesso", "Carro atualizado com sucesso!");
        } catch (NumberFormatException e) {
            mbox.ShowMessageBox("Erro", "Por favor, insira valores numéricos válidos para Ano, Peso e Quantidade de Portas.");
        } catch (Exception e) {
            mbox.ShowMessageBox("Erro", "Erro ao atualizar carro: " + e.getMessage());
        }
    }

    @FXML
    private void ExcluirOnAction() {
        if (carroSelecionado == null) {
            mbox.ShowMessageBox("Erro", "Por favor, selecione um carro para excluir.");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Exclusão");
        alert.setHeaderText("Excluir Carro");
        alert.setContentText("Tem certeza que deseja excluir o carro " + carroSelecionado.getMarca() + " " + carroSelecionado.getModelo() + "?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            try {
                carroDAO.excluir(carroSelecionado.getId());
                limparCampos();
                atualizarTabela();
                mbox.ShowMessageBox("Sucesso", "Carro excluído com sucesso!");
            } catch (Exception e) {
                mbox.ShowMessageBox("Erro", "Erro ao excluir carro: " + e.getMessage());
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
            String title = carroSelecionado.Acelerar();
            String gifPath = getClass().getResource("/fatec/gifs/carro-acelerar.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void FrearOnAction() {
        if (VerificarSelecionado()) {
            String title = carroSelecionado.Frear();
            String gifPath = getClass().getResource("/fatec/gifs/carro-frear.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void PortaOnAction() {
        if (VerificarSelecionado()) {
            String title = carroSelecionado.AbrirPorta();
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

    private boolean VerificarSelecionado() {
        if (carroSelecionado == null) {
            mbox.ShowMessageBox("Erro", "Por favor, selecione um carro primeiro na tabela.");
            return false;
        }
        return true;
    }

    private void limparCampos() {
        txtMarca.clear();
        txtModelo.clear();
        txtAno.clear();
        txtPeso.clear();
        txtPortas.clear();
        carroSelecionado = null;
    }
}
