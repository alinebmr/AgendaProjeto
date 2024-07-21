import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import Agenda.Agenda;
import DAO.AgendaDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MainScreenController implements Initializable {

    @FXML
    private Button buttonAtualizar;

    @FXML
    private Button buttonCadastrar;

    @FXML
    private Button buttonCancelar;

    @FXML
    private DatePicker campoData;

    @FXML
    private Button buttonConcluido;

    @FXML
    private Button buttonEmProgresso;

    @FXML
    private ComboBox<String> comboBoxStatus;

    @FXML
    private TableColumn<Agenda, String> tableColumnHorario;

    @FXML
    private TableColumn<Agenda, String> tableColumnNome;

    @FXML
    private TableColumn<Agenda, String> tableColumnPrioridade;

    @FXML
    private TableColumn<Agenda, String> tableColumnStatus;

    @FXML
    private TableColumn<Agenda, String> tableColumnServico;

    @FXML
    private TableColumn<Agenda, String> tableColumnTelefone;

    @FXML
    private TableView<Agenda> tableViewCadastros;

    @FXML
    private TableColumn tableColumnAction;

    private String myFormattedDate;

    public void fecha() {
        AppMainScreen.getStage().close();
    }

    @FXML
    void datePicker(ActionEvent event) {
        LocalDate myDate = campoData.getValue();
        myFormattedDate = (myDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).toString();
        initTableDate(myFormattedDate);
    }

    public void initTableDate(String data) {
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        tableColumnHorario.setCellValueFactory(new PropertyValueFactory<>("hora"));
        tableColumnServico.setCellValueFactory(new PropertyValueFactory<>("servico"));
        tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tableColumnPrioridade.setCellValueFactory(new PropertyValueFactory<>("prioridade"));
        tableColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableColumnAction.setCellValueFactory(new PropertyValueFactory<>(""));

        // Resolver
        Callback<TableColumn<Agenda, String>, TableCell<Agenda, String>> cellFactory = (param) -> {

            final TableCell<Agenda, String> cell = new TableCell<Agenda, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final Button concluseButton = new Button("Atendido");
                        concluseButton.setOnAction(event -> {
                            Agenda agenda = getTableView().getItems().get(getIndex());
                            atendeCliente(agenda);
                            showItems("Em Espera");
                            initTableDate(myFormattedDate);

                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("Voce concluiu o atendimento de " + agenda.getNomeCliente());
                            alert.show();

                        });
                        setGraphic(concluseButton);
                        setText(null);

                    }

                }

            };
            return cell;

        };
        tableColumnAction.setCellFactory(cellFactory);
        tableViewCadastros.setItems(atualizaTabelaData(data));

    }

    public ObservableList<Agenda> atualizaTabelaData(String data) {
        AgendaDAO dao = new AgendaDAO();
        List<Agenda> lista = dao.getList();
        List<Agenda> saida = new ArrayList<Agenda>();
        for (Agenda ele : lista) {
            if (ele.getData().equals(data)) {
                saida.add(ele);
            }
        }
        return FXCollections.observableArrayList(saida);
    }

    private void editData() {
        tableColumnNome.setCellFactory(TextFieldTableCell.<Agenda>forTableColumn());
        tableColumnNome.setOnEditCommit(event -> {
            Agenda agenda = event.getTableView().getItems().get(event.getTablePosition().getRow());
            agenda.setNomeCliente(event.getNewValue());
        });
        tableColumnHorario.setCellFactory(TextFieldTableCell.<Agenda>forTableColumn());
        tableColumnHorario.setOnEditCommit(event -> {
            Agenda agenda = event.getTableView().getItems().get(event.getTablePosition().getRow());
            agenda.setHora(event.getNewValue());
        });
        tableColumnTelefone.setCellFactory(TextFieldTableCell.<Agenda>forTableColumn());
        tableColumnTelefone.setOnEditCommit(event -> {
            Agenda agenda = event.getTableView().getItems().get(event.getTablePosition().getRow());
            agenda.setTelefone(event.getNewValue());
        });
        tableColumnServico.setCellFactory(TextFieldTableCell.<Agenda>forTableColumn());
        tableColumnServico.setOnEditCommit(event -> {
            Agenda agenda = event.getTableView().getItems().get(event.getTablePosition().getRow());
            agenda.setServico(event.getNewValue());
        });

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String[] items = { "Todos", "Em Espera", "Atendido" };
        comboBoxStatus.getItems().addAll(items);

        Date dataHoraAtual = new Date();

        String data = new SimpleDateFormat("yyyy-MM-dd").format(dataHoraAtual);
        myFormattedDate = data;

        campoData.setValue(LocalDate.now());

        initTableDate(data);
        buttonAtualizar.setOnMouseClicked((MouseEvent e) -> {
            initTableDate(myFormattedDate);

        });
        buttonCadastrar.setOnMouseClicked((MouseEvent e) -> {
            AppAgenda appA = new AppAgenda();
            try {

                appA.start(new Stage());

            } catch (Exception e1) {
                e1.printStackTrace();
            }

        });

        comboBoxStatus.setOnAction((event) -> {
            String selectedItem = comboBoxStatus.getSelectionModel().getSelectedItem();
            tableViewCadastros.setItems(showItems(selectedItem));
        });
        editData();

    }

    public void initTable() {
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        tableColumnHorario.setCellValueFactory(new PropertyValueFactory<>("hora"));
        tableColumnServico.setCellValueFactory(new PropertyValueFactory<>("servico"));
        tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tableColumnPrioridade.setCellValueFactory(new PropertyValueFactory<>("prioridade"));
        tableColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableColumnAction.setCellValueFactory(new PropertyValueFactory<>("button"));
        tableViewCadastros.setItems(atualizaTabela());
    }

    public ObservableList<Agenda> atualizaTabela() {
        AgendaDAO dao = new AgendaDAO();
        return FXCollections.observableArrayList(dao.getList());
    }

    // Retorna a lista de items de acordo com a seleçao no ComboBox
    public ObservableList<Agenda> showItems(String selectedItem) {

        AgendaDAO dao = new AgendaDAO();
        List<Agenda> lista = dao.getList();
        List<Agenda> saida = new ArrayList<Agenda>();
        for (Agenda ele : lista) {
            if (ele.getData().equals(myFormattedDate) && ele.getStatus().equals(selectedItem)) {
                saida.add(ele);
            }
        }

        if (selectedItem.equals("Todos"))
            return atualizaTabelaData(myFormattedDate);
        else {
            return FXCollections.observableArrayList(saida);
        }

    }

    // Registra quando um cliente foi atendido
    public void atendeCliente(Agenda agenda) {
        agenda.concluirAtendimento();

    }

    // Retorna cliente para lista de espera caso erro
    public void retornaClienteParaEspera(Agenda agenda) {
        agenda.retornarParaEspera();
    }

}
