package fatec.controllers;

import java.io.IOException;
import java.util.List;

import fatec.classes.Mamifero;
import fatec.dao.MamiferoDAO;
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

public class MamiferosController {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtIdade;

    @FXML
    private TextField txtPeso;

    @FXML
    private TextField txtGestacao;

    @FXML
    private CheckBox chkAmamenta;

    @FXML
    private Button btnCriar;

    @FXML
    private Button btnAtualizar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnSom;

    @FXML
    private Button btnAmamentar;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnListar;

    @FXML
    private TableView<Mamifero> tblMamiferos;

    @FXML
    private TableColumn<Mamifero, Integer> colId;

    @FXML
    private TableColumn<Mamifero, String> colNome;

    @FXML
    private TableColumn<Mamifero, Integer> colIdade;

    @FXML
    private TableColumn<Mamifero, Double> colPeso;

    @FXML
    private TableColumn<Mamifero, Integer> colGestacao;

    @FXML
    private TableColumn<Mamifero, Boolean> colAmamenta;

    @FXML
    private CheckBox chkTemPelos;

    @FXML
    private TextField txtTipoAlimentacao;

    @FXML
    private TableColumn<Mamifero, Boolean> colTemPelos;

    @FXML
    private TableColumn<Mamifero, String> colTipoAlimentacao;

    private MamiferoDAO mamiferoDAO;
    private Mamifero mamiferoSelecionado;

    @FXML
    public void initialize() {
        mamiferoDAO = new MamiferoDAO();
        configurarTabela();
        ListarOnAction();
    }

    private void configurarTabela() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
        colPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        colTemPelos.setCellValueFactory(new PropertyValueFactory<>("temPelos"));
        colTipoAlimentacao.setCellValueFactory(new PropertyValueFactory<>("tipoAlimentacao"));

        tblMamiferos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                mamiferoSelecionado = newSelection;
                preencherCampos(mamiferoSelecionado);
            }
        });
    }

    private void preencherCampos(Mamifero mamifero) {
        txtNome.setText(mamifero.getNome());
        txtIdade.setText(String.valueOf(mamifero.getIdade()));
        txtPeso.setText(String.valueOf(mamifero.getPeso()));
        chkTemPelos.setSelected(mamifero.isTemPelos());
        txtTipoAlimentacao.setText(mamifero.getTipoAlimentacao());
    }

    @FXML
    private void CriarOnAction() {
        try {
            String nome = txtNome.getText();
            int idade = Integer.parseInt(txtIdade.getText());
            double peso = Double.parseDouble(txtPeso.getText());
            boolean temPelos = chkTemPelos.isSelected();
            String tipoAlimentacao = txtTipoAlimentacao.getText();

            if (nome.isEmpty() || tipoAlimentacao.isEmpty()) {
                mbox.ShowMessageBox("Erro", "Por favor, preencha todos os campos.");
                return;
            }

            Mamifero mamifero = new Mamifero(nome, idade, peso, temPelos, tipoAlimentacao);
            mamiferoDAO.inserir(mamifero);
            limparCampos();
            ListarOnAction();
            mbox.ShowMessageBox("Sucesso", "Mamífero cadastrado com sucesso!");
        } catch (NumberFormatException e) {
            mbox.ShowMessageBox("Erro", "Por favor, insira valores numéricos válidos para idade e peso.");
        } catch (Exception e) {
            mbox.ShowMessageBox("Erro", "Erro ao cadastrar mamífero: " + e.getMessage());
        }
    }

    @FXML
    private void AtualizarOnAction() {
        if (mamiferoSelecionado == null) {
            mbox.ShowMessageBox("Erro", "Por favor, selecione um mamífero para atualizar.");
            return;
        }

        try {
            String nome = txtNome.getText();
            int idade = Integer.parseInt(txtIdade.getText());
            double peso = Double.parseDouble(txtPeso.getText());
            boolean temPelos = chkTemPelos.isSelected();
            String tipoAlimentacao = txtTipoAlimentacao.getText();

            if (nome.isEmpty() || tipoAlimentacao.isEmpty()) {
                mbox.ShowMessageBox("Erro", "Por favor, preencha todos os campos.");
                return;
            }

            mamiferoSelecionado.setNome(nome);
            mamiferoSelecionado.setIdade(idade);
            mamiferoSelecionado.setPeso(peso);
            mamiferoSelecionado.setTemPelos(temPelos);
            mamiferoSelecionado.setTipoAlimentacao(tipoAlimentacao);

            mamiferoDAO.atualizar(mamiferoSelecionado);
            limparCampos();
            ListarOnAction();
            mbox.ShowMessageBox("Sucesso", "Mamífero atualizado com sucesso!");
        } catch (NumberFormatException e) {
            mbox.ShowMessageBox("Erro", "Por favor, insira valores numéricos válidos para idade e peso.");
        } catch (Exception e) {
            mbox.ShowMessageBox("Erro", "Erro ao atualizar mamífero: " + e.getMessage());
        }
    }

    @FXML
    private void ExcluirOnAction() {
        if (mamiferoSelecionado == null) {
            mbox.ShowMessageBox("Erro", "Por favor, selecione um mamífero para excluir.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Exclusão");
        alert.setHeaderText("Excluir Mamífero");
        alert.setContentText("Tem certeza que deseja excluir o mamífero " + mamiferoSelecionado.getNome() + "?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            try {
                mamiferoDAO.excluir(mamiferoSelecionado.getId());
                limparCampos();
                ListarOnAction();
                mbox.ShowMessageBox("Sucesso", "Mamífero excluído com sucesso!");
            } catch (Exception e) {
                mbox.ShowMessageBox("Erro", "Erro ao excluir mamífero: " + e.getMessage());
            }
        }
    }

    @FXML
    private void SomOnAction() {
        if (Verificar()) {
            String title = mamiferoSelecionado.FazerSom();
            String gifPath = getClass().getResource("/fatec/gifs/mamifero-som.gif").toExternalForm();
            String soundPath = getClass().getResource("/fatec/sounds/mamifero-som.mp3").toExternalForm();

            mbox.ReproduzirSom(soundPath);
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    private void AmamentarOnAction() {
        if (Verificar()) {
            String title = mamiferoSelecionado.Amamentar();
            String gifPath = getClass().getResource("/fatec/gifs/mamifero-amamentar.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    private void DormirOnAction() {
        if (Verificar()) {
            String title = mamiferoSelecionado.Dormir();
            String gifPath = getClass().getResource("/fatec/gifs/mamifero-dormir.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    private void ListarOnAction() {
        try {
            tblMamiferos.setItems(FXCollections.observableArrayList(mamiferoDAO.listarTodos()));
        } catch (Exception e) {
            mbox.ShowMessageBox("Erro", "Erro ao listar mamíferos: " + e.getMessage());
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

    private boolean Verificar() {
        if (mamiferoSelecionado == null) {
            mbox.ShowMessageBox("Erro", "Por favor, selecione um mamífero na tabela.");
            return false;
        }
        return true;
    }

    private void limparCampos() {
        txtNome.clear();
        txtIdade.clear();
        txtPeso.clear();
        chkTemPelos.setSelected(false);
        txtTipoAlimentacao.clear();
        tblMamiferos.getSelectionModel().clearSelection();
        mamiferoSelecionado = null;
    }
} 