package fatec.controllers;

import java.io.IOException;
import java.util.List;

import fatec.classes.Reptil;
import fatec.dao.ReptilDAO;
import fatec.utils.mbox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RepteisController {

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtIdade;

    @FXML
    private TextField txtPeso;

    @FXML
    private CheckBox chkTemEscamas;

    @FXML
    private TextField txtTipoPele;

    @FXML
    private Button btnCriar;

    @FXML
    private Button btnSom;

    @FXML
    private Button btnTrocarPele;

    @FXML
    private Button btnTomarSol;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnListar;

    @FXML
    private TableView<Reptil> tblRepteis;

    @FXML
    private TableColumn<Reptil, Integer> colId;

    @FXML
    private TableColumn<Reptil, String> colNome;

    @FXML
    private TableColumn<Reptil, Integer> colIdade;

    @FXML
    private TableColumn<Reptil, Double> colPeso;

    @FXML
    private TableColumn<Reptil, Boolean> colTemEscamas;

    @FXML
    private TableColumn<Reptil, String> colTipoPele;

    private ReptilDAO reptilDAO;
    private Reptil reptil;
    private ObservableList<Reptil> repteisList;

    @FXML
    public void initialize() {
        reptilDAO = new ReptilDAO();
        repteisList = FXCollections.observableArrayList();
        
        // Configurar as colunas da tabela
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
        colPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        colTemEscamas.setCellValueFactory(new PropertyValueFactory<>("temEscamas"));
        colTipoPele.setCellValueFactory(new PropertyValueFactory<>("tipoPele"));

        // Adicionar listener para seleção na tabela
        tblRepteis.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                reptil = newSelection;
                preencherCampos(reptil);
            }
        });

        // Carregar dados iniciais
        atualizarTabela();
    }

    private void preencherCampos(Reptil reptil) {
        txtNome.setText(reptil.getNome());
        txtIdade.setText(String.valueOf(reptil.getIdade()));
        txtPeso.setText(String.valueOf(reptil.getPeso()));
        chkTemEscamas.setSelected(reptil.isTemEscamas());
        txtTipoPele.setText(reptil.getTipoPele());
    }

    private void atualizarTabela() {
        try {
            List<Reptil> repteis = reptilDAO.listarTodos();
            repteisList.clear();
            repteisList.addAll(repteis);
            tblRepteis.setItems(repteisList);
        } catch (Exception e) {
            mbox.ShowMessageBox("Erro", "Erro ao carregar répteis: " + e.getMessage());
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

            String tipoPele = txtTipoPele.getText();
            if (tipoPele.isEmpty()) {
                mbox.ShowMessageBox("Erro", "Tipo de pele não pode ser vazio!");
                return;
            }

            boolean temEscamas = chkTemEscamas.isSelected();

            reptil = new Reptil(nome, idade, peso, temEscamas, tipoPele);
            reptilDAO.inserir(reptil);

            mbox.ShowMessageBox("Sucesso", "Réptil criado e salvo no banco de dados!");
            limparCampos();
            atualizarTabela();
        } catch (Exception e) {
            mbox.ShowMessageBox("Erro", "Erro ao salvar réptil: " + e.getMessage());
        }
    }

    @FXML
    private void CadastroOnAction() {
        try {
            if (reptil == null) {
                mbox.ShowMessageBox("Erro", "Por favor, selecione um réptil na tabela.");
                return;
            }

            String message = "Nome: " + reptil.getNome() +
                    "\nIdade: " + reptil.getIdade() +
                    "\nPeso: " + reptil.getPeso() +
                    "\nTem Escamas: " + (reptil.isTemEscamas() ? "Sim" : "Não") +
                    "\nTipo de Pele: " + reptil.getTipoPele();

            mbox.ShowMessageBox("Réptil cadastrado", message);
        } catch (Exception e) {
            mbox.ShowMessageBox("Erro", "Erro ao mostrar cadastro: " + e.getMessage());
        }
    }

    @FXML
    public void SomOnAction() {
        if (Verificar()) {
            String title = reptil.FazerSom();
            String gifPath = getClass().getResource("/fatec/gifs/reptil-som.gif").toExternalForm();
            String soundPath = getClass().getResource("/fatec/sounds/reptil-som.mp3").toExternalForm();

            mbox.ReproduzirSom(soundPath);
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void TrocarPeleOnAction() {
        if (Verificar()) {
            String title = reptil.TrocarPele();
            String gifPath = getClass().getResource("/fatec/gifs/reptil-trocar-pele.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void TomarSolOnAction() {
        if (Verificar()) {
            String title = reptil.TomarSol();
            String gifPath = getClass().getResource("/fatec/gifs/reptil-tomar-sol.gif").toExternalForm();
            mbox.ShowGifMessageBox(title, gifPath);
        }
    }

    @FXML
    public void ListarOnAction() {
        atualizarTabela();
    }

    @FXML
    public void VoltarOnAction() throws IOException {
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fatec/views/inicial-view.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    private boolean Verificar() {
        if (reptil == null) {
            mbox.ShowMessageBox("Erro", "Por favor, selecione um réptil na tabela.");
            return false;
        }
        return true;
    }

    private void limparCampos() {
        txtNome.clear();
        txtIdade.clear();
        txtPeso.clear();
        txtTipoPele.clear();
        chkTemEscamas.setSelected(false);
        tblRepteis.getSelectionModel().clearSelection();
        reptil = null;
    }

    @FXML
    private void AtualizarOnAction() {
        if (reptil == null) {
            mbox.ShowMessageBox("Erro", "Por favor, selecione um réptil para atualizar.");
            return;
        }
        try {
            String nome = txtNome.getText();
            int idade = Integer.parseInt(txtIdade.getText());
            double peso = Double.parseDouble(txtPeso.getText());
            String tipoPele = txtTipoPele.getText();
            boolean temEscamas = chkTemEscamas.isSelected();

            if (nome.isEmpty() || tipoPele.isEmpty()) {
                mbox.ShowMessageBox("Erro", "Por favor, preencha todos os campos.");
                return;
            }

            reptil.setNome(nome);
            reptil.setIdade(idade);
            reptil.setPeso(peso);
            reptil.setTipoPele(tipoPele);
            reptil.setTemEscamas(temEscamas);

            reptilDAO.atualizar(reptil);
            limparCampos();
            atualizarTabela();
            mbox.ShowMessageBox("Sucesso", "Réptil atualizado com sucesso!");
        } catch (NumberFormatException e) {
            mbox.ShowMessageBox("Erro", "Por favor, insira valores numéricos válidos para idade e peso.");
        } catch (Exception e) {
            mbox.ShowMessageBox("Erro", "Erro ao atualizar réptil: " + e.getMessage());
        }
    }

    @FXML
    private void ExcluirOnAction() {
        if (reptil == null) {
            mbox.ShowMessageBox("Erro", "Por favor, selecione um réptil para excluir.");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Exclusão");
        alert.setHeaderText("Excluir Réptil");
        alert.setContentText("Tem certeza que deseja excluir o réptil " + reptil.getNome() + "?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            try {
                reptilDAO.excluir(reptil.getId());
                limparCampos();
                atualizarTabela();
                mbox.ShowMessageBox("Sucesso", "Réptil excluído com sucesso!");
            } catch (Exception e) {
                mbox.ShowMessageBox("Erro", "Erro ao excluir réptil: " + e.getMessage());
            }
        }
    }
} 