package fatec.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fatec.classes.Bicicleta;
import fatec.dao.BicicletaDAO;
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

public class BicicletasController implements Initializable {

    @FXML
    private TextField txtMarca;

    @FXML
    private TextField txtModelo;

    @FXML
    private TextField txtAno;

    @FXML
    private TextField txtPeso;

    @FXML
    private TextField txtNumMarchas;

    @FXML
    private CheckBox chkTemFreioDisco;

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
    private Button btnTrocarMarcha;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableView<Bicicleta> tblBicicletas;

    @FXML
    private TableColumn<Bicicleta, Integer> colId;

    @FXML
    private TableColumn<Bicicleta, String> colMarca;

    @FXML
    private TableColumn<Bicicleta, String> colModelo;

    @FXML
    private TableColumn<Bicicleta, Integer> colAno;

    @FXML
    private TableColumn<Bicicleta, Double> colPeso;

    @FXML
    private TableColumn<Bicicleta, Integer> colNumMarchas;

    @FXML
    private TableColumn<Bicicleta, Boolean> colTemFreioDisco;

    private BicicletaDAO bicicletaDAO;
    private Bicicleta bicicletaSelecionada;
    private ObservableList<Bicicleta> bicicletasList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bicicletaDAO = new BicicletaDAO();
        bicicletasList = FXCollections.observableArrayList();
        configurarTabela();
        atualizarTabela();
    }

    private void configurarTabela() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
        colPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        colNumMarchas.setCellValueFactory(new PropertyValueFactory<>("numMarchas"));
        colTemFreioDisco.setCellValueFactory(new PropertyValueFactory<>("temFreioDisco"));

        tblBicicletas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                bicicletaSelecionada = newSelection;
                preencherCampos(bicicletaSelecionada);
            }
        });
    }

    private void preencherCampos(Bicicleta bicicleta) {
        txtMarca.setText(bicicleta.getMarca());
        txtModelo.setText(bicicleta.getModelo());
        txtAno.setText(String.valueOf(bicicleta.getAno()));
        txtPeso.setText(String.valueOf(bicicleta.getPeso()));
        txtNumMarchas.setText(String.valueOf(bicicleta.getNumMarchas()));
        chkTemFreioDisco.setSelected(bicicleta.isTemFreioDisco());
    }

    private void atualizarTabela() {
        try {
            List<Bicicleta> bicicletas = bicicletaDAO.listarTodos();
            bicicletasList.clear();
            bicicletasList.addAll(bicicletas);
            tblBicicletas.setItems(bicicletasList);
        } catch (Exception e) {
            mbox.ShowMessageBox("Erro", "Erro ao carregar bicicletas: " + e.getMessage());
        }
    }

    @FXML
    private void CriarOnAction() {
        try {
            String marca = txtMarca.getText();
            String modelo = txtModelo.getText();
            int ano = Integer.parseInt(txtAno.getText());
            double peso = Double.parseDouble(txtPeso.getText());
            int numMarchas = Integer.parseInt(txtNumMarchas.getText());
            boolean temFreioDisco = chkTemFreioDisco.isSelected();

            if (marca.isEmpty() || modelo.isEmpty()) {
                mbox.ShowMessageBox("Erro", "Por favor, preencha todos os campos obrigatórios.");
                return;
            }

            Bicicleta novaBicicleta = new Bicicleta(marca, modelo, ano, peso, numMarchas, temFreioDisco);
            bicicletaDAO.inserir(novaBicicleta);
            limparCampos();
            atualizarTabela();
            mbox.ShowMessageBox("Sucesso", "Bicicleta cadastrada com sucesso!");
        } catch (NumberFormatException e) {
            mbox.ShowMessageBox("Erro", "Por favor, insira valores numéricos válidos para Ano, Peso e Número de Marchas.");
        } catch (Exception e) {
            mbox.ShowMessageBox("Erro", "Erro ao cadastrar bicicleta: " + e.getMessage());
        }
    }

    @FXML
    private void AtualizarOnAction() {
        if (bicicletaSelecionada == null) {
            mbox.ShowMessageBox("Erro", "Por favor, selecione uma bicicleta para atualizar.");
            return;
        }
        try {
            String marca = txtMarca.getText();
            String modelo = txtModelo.getText();
            int ano = Integer.parseInt(txtAno.getText());
            double peso = Double.parseDouble(txtPeso.getText());
            int numMarchas = Integer.parseInt(txtNumMarchas.getText());
            boolean temFreioDisco = chkTemFreioDisco.isSelected();

            if (marca.isEmpty() || modelo.isEmpty()) {
                mbox.ShowMessageBox("Erro", "Por favor, preencha todos os campos obrigatórios.");
                return;
            }

            bicicletaSelecionada.setMarca(marca);
            bicicletaSelecionada.setModelo(modelo);
            bicicletaSelecionada.setAno(ano);
            bicicletaSelecionada.setPeso(peso);
            bicicletaSelecionada.setNumMarchas(numMarchas);
            bicicletaSelecionada.setTemFreioDisco(temFreioDisco);

            bicicletaDAO.atualizar(bicicletaSelecionada);
            limparCampos();
            atualizarTabela();
            mbox.ShowMessageBox("Sucesso", "Bicicleta atualizada com sucesso!");
        } catch (NumberFormatException e) {
            mbox.ShowMessageBox("Erro", "Por favor, insira valores numéricos válidos para Ano, Peso e Número de Marchas.");
        } catch (Exception e) {
            mbox.ShowMessageBox("Erro", "Erro ao atualizar bicicleta: " + e.getMessage());
        }
    }

    @FXML
    private void ExcluirOnAction() {
        if (bicicletaSelecionada == null) {
            mbox.ShowMessageBox("Erro", "Por favor, selecione uma bicicleta para excluir.");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Exclusão");
        alert.setHeaderText("Excluir Bicicleta");
        alert.setContentText("Tem certeza que deseja excluir a bicicleta " + bicicletaSelecionada.getMarca() + " " + bicicletaSelecionada.getModelo() + "?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            try {
                bicicletaDAO.excluir(bicicletaSelecionada.getId());
                limparCampos();
                atualizarTabela();
                mbox.ShowMessageBox("Sucesso", "Bicicleta excluída com sucesso!");
            } catch (Exception e) {
                mbox.ShowMessageBox("Erro", "Erro ao excluir bicicleta: " + e.getMessage());
            }
        }
    }

    @FXML
    private void ListarOnAction() {
        atualizarTabela();
    }

    @FXML
    private void AcelerarOnAction() {
        if (VerificarSelecionada()) {
            String title = bicicletaSelecionada.Acelerar();
            // Recurso de GIF para acelerar bicicleta
            String gifPath = getClass().getResource("/fatec/gifs/bicicleta-acelerar.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    private void FrearOnAction() {
        if (VerificarSelecionada()) {
            String title = bicicletaSelecionada.Frear();
            // Recurso de GIF para frear bicicleta
             String gifPath = getClass().getResource("/fatec/gifs/bicicleta-frear.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

     @FXML
    private void TrocarMarchaOnAction() {
        if (VerificarSelecionada()) {
            String title = bicicletaSelecionada.TrocarMarcha();
            // Recurso de GIF para trocar marcha bicicleta
             String gifPath = getClass().getResource("/fatec/gifs/bicicleta-trocar-marcha.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    private void VoltarOnAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fatec/views/inicial-view.fxml"));
        Parent root = loader.load();

        Stage currentStage = (Stage) btnVoltar.getScene().getWindow();
        Scene newScene = new Scene(root);

        currentStage.setScene(newScene);
        currentStage.setTitle("Menu Principal");
    }

    private boolean VerificarSelecionada() {
        if (bicicletaSelecionada == null) {
            mbox.ShowMessageBox("Erro", "Por favor, selecione uma bicicleta na tabela.");
            return false;
        }
        return true;
    }

    private void limparCampos() {
        txtMarca.clear();
        txtModelo.clear();
        txtAno.clear();
        txtPeso.clear();
        txtNumMarchas.clear();
        chkTemFreioDisco.setSelected(false);
        tblBicicletas.getSelectionModel().clearSelection();
        bicicletaSelecionada = null;
    }
} 