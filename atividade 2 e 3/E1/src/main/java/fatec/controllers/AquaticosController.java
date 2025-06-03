package fatec.controllers;

import java.io.IOException;
import java.util.List;

import fatec.classes.Aquatico;
import fatec.dao.AquaticoDAO;
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

public class AquaticosController {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtIdade;

    @FXML
    private TextField txtPeso;

    @FXML
    private TextField txtProfundidadeMaxima;

    @FXML
    private Button btnCriar;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnSom;

    @FXML
    private Button btnNadar;

    @FXML
    private Button btnMergulhar;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnListar;

    @FXML
    private TableView<Aquatico> tblAquaticos;

    @FXML
    private TableColumn<Aquatico, Integer> colId;

    @FXML
    private TableColumn<Aquatico, String> colNome;

    @FXML
    private TableColumn<Aquatico, Integer> colIdade;

    @FXML
    private TableColumn<Aquatico, Double> colPeso;

    @FXML
    private TableColumn<Aquatico, Double> colProfundidadeMaxima;

    private AquaticoDAO aquaticoDAO;
    private Aquatico aquatico;
    private ObservableList<Aquatico> aquaticosList;

    @FXML
    public void initialize() {
        aquaticoDAO = new AquaticoDAO();
        aquaticosList = FXCollections.observableArrayList();
        
        // Configurar as colunas da tabela
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
        colPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        colProfundidadeMaxima.setCellValueFactory(new PropertyValueFactory<>("profundidadeMaxima"));

        // Adicionar listener para seleção na tabela
        tblAquaticos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                aquatico = newSelection;
                preencherCampos(aquatico);
            }
        });

        // Carregar dados iniciais
        atualizarTabela();
    }

    private void preencherCampos(Aquatico aquatico) {
        txtNome.setText(aquatico.getNome());
        txtIdade.setText(String.valueOf(aquatico.getIdade()));
        txtPeso.setText(String.valueOf(aquatico.getPeso()));
        txtProfundidadeMaxima.setText(String.valueOf(aquatico.getProfundidadeMaxima()));
    }

    private void atualizarTabela() {
        try {
            List<Aquatico> aquaticos = aquaticoDAO.listarTodos();
            aquaticosList.clear();
            aquaticosList.addAll(aquaticos);
            tblAquaticos.setItems(aquaticosList);
        } catch (Exception e) {
            mbox.ShowMessageBox("Erro", "Erro ao carregar animais aquáticos: " + e.getMessage());
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

            double profundidadeMaxima;
            try {
                profundidadeMaxima = Double.parseDouble(txtProfundidadeMaxima.getText());
            } catch (NumberFormatException e) {
                mbox.ShowMessageBox("Erro", "Profundidade máxima inválida!");
                return;
            }

            aquatico = new Aquatico(nome, idade, peso, profundidadeMaxima);
            aquaticoDAO.inserir(aquatico);

            mbox.ShowMessageBox("Sucesso", "Animal aquático criado e salvo no banco de dados!");
            limparCampos();
            atualizarTabela();
        } catch (Exception e) {
            mbox.ShowMessageBox("Erro", "Erro ao salvar animal aquático: " + e.getMessage());
        }
    }

    @FXML
    public void AtualizarOnAction() {
        try {
            if (aquatico == null) {
                mbox.ShowMessageBox("Erro", "Por favor, selecione um animal aquático na tabela.");
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

            double profundidadeMaxima;
            try {
                profundidadeMaxima = Double.parseDouble(txtProfundidadeMaxima.getText());
            } catch (NumberFormatException e) {
                mbox.ShowMessageBox("Erro", "Profundidade máxima inválida!");
                return;
            }

            aquatico.setNome(nome);
            aquatico.setIdade(idade);
            aquatico.setPeso(peso);
            aquatico.setProfundidadeMaxima(profundidadeMaxima);

            aquaticoDAO.atualizar(aquatico);

            mbox.ShowMessageBox("Sucesso", "Animal aquático atualizado com sucesso!");
            limparCampos();
            atualizarTabela();
        } catch (Exception e) {
            mbox.ShowMessageBox("Erro", "Erro ao atualizar animal aquático: " + e.getMessage());
        }
    }

    @FXML
    public void ExcluirOnAction() {
        try {
            if (aquatico == null) {
                mbox.ShowMessageBox("Erro", "Por favor, selecione um animal aquático na tabela.");
                return;
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Exclusão");
            alert.setHeaderText("Excluir Animal Aquático");
            alert.setContentText("Tem certeza que deseja excluir o animal " + aquatico.getNome() + "?");

            if (alert.showAndWait().get() == ButtonType.OK) {
                aquaticoDAO.excluir(aquatico.getId());
                mbox.ShowMessageBox("Sucesso", "Animal aquático excluído com sucesso!");
                limparCampos();
                atualizarTabela();
            }
        } catch (Exception e) {
            mbox.ShowMessageBox("Erro", "Erro ao excluir animal aquático: " + e.getMessage());
        }
    }

    @FXML
    public void SomOnAction() {
        if (Verificar()) {
            String title = aquatico.FazerSom();
            String gifPath = getClass().getResource("/fatec/gifs/aquatico-som.gif").toExternalForm();
            String soundPath = getClass().getResource("/fatec/sounds/golfinho-som.mp3").toExternalForm();

            mbox.ReproduzirSom(soundPath);
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void NadarOnAction() {
        if (Verificar()) {
            String title = aquatico.Nadar();
            String gifPath = getClass().getResource("/fatec/gifs/aquatico-nadar.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void MergulharOnAction() {
        if (Verificar()) {
            String title = aquatico.Mergulhar();
            String gifPath = getClass().getResource("/fatec/gifs/aquatico-mergulhar.gif").toExternalForm();
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
        if (aquatico == null) {
            mbox.ShowMessageBox("Erro", "Por favor, selecione um animal aquático na tabela.");
            return false;
        }
        return true;
    }

    private void limparCampos() {
        txtNome.clear();
        txtIdade.clear();
        txtPeso.clear();
        txtProfundidadeMaxima.clear();
        tblAquaticos.getSelectionModel().clearSelection();
        aquatico = null;
    }
}
