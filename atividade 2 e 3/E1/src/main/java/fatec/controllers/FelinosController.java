package fatec.controllers;

import java.io.IOException;
import java.util.List;

import fatec.classes.Felino;
import fatec.dao.FelinoDAO;
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

public class FelinosController {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtIdade;

    @FXML
    private TextField txtPeso;

    @FXML
    private TextField txtTamanhoGarras;

    @FXML
    private Button btnCriar;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnSom;

    @FXML
    private Button btnSubirArvore;

    @FXML
    private Button btnCacar;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnListar;

    @FXML
    private TableView<Felino> tblFelinos;

    @FXML
    private TableColumn<Felino, Integer> colId;

    @FXML
    private TableColumn<Felino, String> colNome;

    @FXML
    private TableColumn<Felino, Integer> colIdade;

    @FXML
    private TableColumn<Felino, Double> colPeso;

    @FXML
    private TableColumn<Felino, Double> colTamanhoGarras;

    private FelinoDAO felinoDAO;
    private Felino felino;
    private ObservableList<Felino> felinosList;

    @FXML
    public void initialize() {
        felinoDAO = new FelinoDAO();
        felinosList = FXCollections.observableArrayList();
        
        // Configurar as colunas da tabela
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
        colPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        colTamanhoGarras.setCellValueFactory(new PropertyValueFactory<>("tamanhoGarras"));

        // Adicionar listener para seleção na tabela
        tblFelinos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                felino = newSelection;
                preencherCampos(felino);
            }
        });

        // Carregar dados iniciais
        atualizarTabela();
    }

    private void preencherCampos(Felino felino) {
        txtNome.setText(felino.getNome());
        txtIdade.setText(String.valueOf(felino.getIdade()));
        txtPeso.setText(String.valueOf(felino.getPeso()));
        txtTamanhoGarras.setText(String.valueOf(felino.getTamanhoGarras()));
    }

    private void atualizarTabela() {
        try {
            List<Felino> felinos = felinoDAO.listarTodos();
            felinosList.clear();
            felinosList.addAll(felinos);
            tblFelinos.setItems(felinosList);
        } catch (Exception e) {
            mbox.ShowMessageBox("Erro", "Erro ao carregar felinos: " + e.getMessage());
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

            double tamanhoGarras;
            try {
                tamanhoGarras = Double.parseDouble(txtTamanhoGarras.getText());
            } catch (NumberFormatException e) {
                mbox.ShowMessageBox("Erro", "Tamanho das garras inválido!");
                return;
            }

            felino = new Felino(nome, idade, peso, tamanhoGarras);
            felinoDAO.inserir(felino);

            mbox.ShowMessageBox("Sucesso", "Felino criado e salvo no banco de dados!");
            limparCampos();
            atualizarTabela();
        } catch (Exception e) {
            mbox.ShowMessageBox("Erro", "Erro ao salvar felino: " + e.getMessage());
        }
    }

    @FXML
    public void AtualizarOnAction() {
        try {
            if (felino == null) {
                mbox.ShowMessageBox("Erro", "Por favor, selecione um felino na tabela.");
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

            double tamanhoGarras;
            try {
                tamanhoGarras = Double.parseDouble(txtTamanhoGarras.getText());
            } catch (NumberFormatException e) {
                mbox.ShowMessageBox("Erro", "Tamanho das garras inválido!");
                return;
            }

            felino.setNome(nome);
            felino.setIdade(idade);
            felino.setPeso(peso);
            felino.setTamanhoGarras(tamanhoGarras);

            felinoDAO.atualizar(felino);

            mbox.ShowMessageBox("Sucesso", "Felino atualizado com sucesso!");
            limparCampos();
            atualizarTabela();
        } catch (Exception e) {
            mbox.ShowMessageBox("Erro", "Erro ao atualizar felino: " + e.getMessage());
        }
    }

    @FXML
    public void ExcluirOnAction() {
        try {
            if (felino == null) {
                mbox.ShowMessageBox("Erro", "Por favor, selecione um felino na tabela.");
                return;
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Exclusão");
            alert.setHeaderText("Excluir Felino");
            alert.setContentText("Tem certeza que deseja excluir o felino " + felino.getNome() + "?");

            if (alert.showAndWait().get() == ButtonType.OK) {
                felinoDAO.excluir(felino.getId());
                mbox.ShowMessageBox("Sucesso", "Felino excluído com sucesso!");
                limparCampos();
                atualizarTabela();
            }
        } catch (Exception e) {
            mbox.ShowMessageBox("Erro", "Erro ao excluir felino: " + e.getMessage());
        }
    }

    @FXML
    public void SomOnAction() {
        if (Verificar()) {
            String title = felino.FazerSom();
            String gifPath = getClass().getResource("/fatec/gifs/felino-som.gif").toExternalForm();
            String soundPath = getClass().getResource("/fatec/sounds/gatinho-som.mp3").toExternalForm();

            mbox.ReproduzirSom(soundPath);
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void SubirArvoreOnAction() {
        if (Verificar()) {
            String title = felino.SubirArvore();
            String gifPath = getClass().getResource("/fatec/gifs/felino-arvore.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void CacarOnAction() {
        if (Verificar()) {
            String title = felino.Cacar();
            String gifPath = getClass().getResource("/fatec/gifs/felino-cacar.gif").toExternalForm();
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
        if (felino == null) {
            mbox.ShowMessageBox("Erro", "Por favor, selecione um felino na tabela.");
            return false;
        }
        return true;
    }

    private void limparCampos() {
        txtNome.clear();
        txtIdade.clear();
        txtPeso.clear();
        txtTamanhoGarras.clear();
        tblFelinos.getSelectionModel().clearSelection();
        felino = null;
    }
}
