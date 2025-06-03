package fatec.controllers;

import java.io.IOException;
import java.util.List;

import fatec.classes.Ave;
import fatec.dao.AveDAO;
import fatec.utils.mbox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AvesController {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtIdade;

    @FXML
    private TextField txtPeso;

    @FXML
    private TextField txtEnvergadura;

    @FXML
    private CheckBox chkVoa;

    @FXML
    private Button btnCriar;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnSom;

    @FXML
    private Button btnVoar;

    @FXML
    private Button btnFazerNinho;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnListar;

    @FXML
    private TableView<Ave> tblAves;

    @FXML
    private TableColumn<Ave, Integer> colId;

    @FXML
    private TableColumn<Ave, String> colNome;

    @FXML
    private TableColumn<Ave, Integer> colIdade;

    @FXML
    private TableColumn<Ave, Double> colPeso;

    @FXML
    private TableColumn<Ave, Double> colEnvergadura;

    @FXML
    private TableColumn<Ave, Boolean> colVoa;

    private AveDAO aveDAO;
    private Ave ave;
    private ObservableList<Ave> avesList;

    @FXML
    public void initialize() {
        aveDAO = new AveDAO();
        avesList = FXCollections.observableArrayList();
        
        // Configurar as colunas da tabela
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
        colPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        colEnvergadura.setCellValueFactory(new PropertyValueFactory<>("envergadura"));
        colVoa.setCellValueFactory(new PropertyValueFactory<>("voa"));

        // Adicionar listener para seleção na tabela
        tblAves.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                ave = newSelection;
                preencherCampos(ave);
            }
        });

        // Carregar dados iniciais
        atualizarTabela();
    }

    private void preencherCampos(Ave ave) {
        txtNome.setText(ave.getNome());
        txtIdade.setText(String.valueOf(ave.getIdade()));
        txtPeso.setText(String.valueOf(ave.getPeso()));
        txtEnvergadura.setText(String.valueOf(ave.getEnvergadura()));
        chkVoa.setSelected(ave.isVoa());
    }

    private void atualizarTabela() {
        try {
            List<Ave> aves = aveDAO.listarTodos();
            avesList.clear();
            avesList.addAll(aves);
            tblAves.setItems(avesList);
        } catch (Exception e) {
            mbox.ShowMessageBox("Erro", "Erro ao carregar aves: " + e.getMessage());
        }
    }

    @FXML
    public void CriarOnAction() {
        try {
            String nome = txtNome.getText();
            if (nome.isEmpty()) {
                mbox.ShowMessageBox("Erro", "Nome não pode ser vazio!");
                return;
            }

            int idade;
            try {
                idade = Integer.parseInt(txtIdade.getText());
            } catch (NumberFormatException e) {
                mbox.ShowMessageBox("Erro", "Idade inválida!");
                return;
            }

            double peso;
            try {
                peso = Double.parseDouble(txtPeso.getText());
            } catch (NumberFormatException e) {
                mbox.ShowMessageBox("Erro", "Peso inválido!");
                return;
            }

            double envergadura;
            try {
                envergadura = Double.parseDouble(txtEnvergadura.getText());
            } catch (NumberFormatException e) {
                mbox.ShowMessageBox("Erro", "Envergadura inválida!");
                return;
            }

            ave = new Ave(nome, idade, peso, envergadura, chkVoa.isSelected());
            aveDAO.inserir(ave);

            mbox.ShowMessageBox("Sucesso", "Ave criada e salva no banco de dados!");
            limparCampos();
            atualizarTabela();
        } catch (Exception e) {
            mbox.ShowMessageBox("Erro", "Erro ao salvar ave: " + e.getMessage());
        }
    }

    @FXML
    public void AtualizarOnAction() {
        try {
            if (ave == null) {
                mbox.ShowMessageBox("Erro", "Por favor, selecione uma ave na tabela.");
                return;
            }

            String nome = txtNome.getText();
            if (nome.isEmpty()) {
                mbox.ShowMessageBox("Erro", "Nome não pode ser vazio!");
                return;
            }

            int idade;
            try {
                idade = Integer.parseInt(txtIdade.getText());
            } catch (NumberFormatException e) {
                mbox.ShowMessageBox("Erro", "Idade inválida!");
                return;
            }

            double peso;
            try {
                peso = Double.parseDouble(txtPeso.getText());
            } catch (NumberFormatException e) {
                mbox.ShowMessageBox("Erro", "Peso inválido!");
                return;
            }

            double envergadura;
            try {
                envergadura = Double.parseDouble(txtEnvergadura.getText());
            } catch (NumberFormatException e) {
                mbox.ShowMessageBox("Erro", "Envergadura inválida!");
                return;
            }

            ave.setNome(nome);
            ave.setIdade(idade);
            ave.setPeso(peso);
            ave.setEnvergadura(envergadura);
            ave.setVoa(chkVoa.isSelected());

            aveDAO.atualizar(ave);

            mbox.ShowMessageBox("Sucesso", "Ave atualizada com sucesso!");
            limparCampos();
            atualizarTabela();
        } catch (Exception e) {
            mbox.ShowMessageBox("Erro", "Erro ao atualizar ave: " + e.getMessage());
        }
    }

    @FXML
    public void ExcluirOnAction() {
        try {
            if (ave == null) {
                mbox.ShowMessageBox("Erro", "Por favor, selecione uma ave na tabela.");
                return;
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Exclusão");
            alert.setHeaderText("Excluir Ave");
            alert.setContentText("Tem certeza que deseja excluir a ave " + ave.getNome() + "?");

            if (alert.showAndWait().get() == ButtonType.OK) {
                aveDAO.excluir(ave.getId());
                mbox.ShowMessageBox("Sucesso", "Ave excluída com sucesso!");
                limparCampos();
                atualizarTabela();
            }
        } catch (Exception e) {
            mbox.ShowMessageBox("Erro", "Erro ao excluir ave: " + e.getMessage());
        }
    }

    @FXML
    public void SomOnAction() {
        if (Verificar()) {
            String title = ave.FazerSom();
            String gifPath = getClass().getResource("/fatec/gifs/ave-som.gif").toExternalForm();
            String soundPath = getClass().getResource("/fatec/sounds/passaro-som.mp3").toExternalForm();

            mbox.ReproduzirSom(soundPath);
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void VoarOnAction() {
        if (Verificar()) {
            String title = ave.Voar();
            String gifPath = getClass().getResource("/fatec/gifs/ave-voar.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void FazerNinhoOnAction() {
        if (Verificar()) {
            String title = ave.FazerNinho();
            String gifPath = getClass().getResource("/fatec/gifs/ave-ninho.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void ListarOnAction() {
        atualizarTabela();
    }

    @FXML
    public void VoltarOnAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fatec/views/inicial-view.fxml"));
        Parent root = loader.load();

        Stage currentStage = (Stage) btnVoltar.getScene().getWindow();
        Scene newScene = new Scene(root);

        currentStage.setScene(newScene);
        currentStage.setTitle("Menu Principal");
    }

    private boolean Verificar() {
        if (ave == null) {
            mbox.ShowMessageBox("Erro", "Por favor, selecione uma ave na tabela.");
            return false;
        }
        return true;
    }

    private void limparCampos() {
        txtNome.clear();
        txtIdade.clear();
        txtPeso.clear();
        txtEnvergadura.clear();
        chkVoa.setSelected(false);
        tblAves.getSelectionModel().clearSelection();
        ave = null;
    }
} 