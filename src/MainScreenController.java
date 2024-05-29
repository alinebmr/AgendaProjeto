import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainScreenController implements Initializable{

    @FXML
    private Button buttonAtualizar;

    @FXML
    private Button buttonCadastrar;

    @FXML
    private Button buttonCancelar;

    @FXML
    private Button buttonEditar;

    @FXML
    private DatePicker campoData;

    @FXML
    private TableColumn<Agenda, String> tableColumnData;

    @FXML
    private TableColumn<Agenda, String> tableColumnHorario;

    @FXML
    private TableColumn<Agenda, String> tableColumnNome;

    @FXML
    private TableColumn<Agenda, String> tableColumnServico;

    @FXML
    private TableColumn<Agenda, String> tableColumnTelefone;

    @FXML
    private TableView<Agenda> tableViewCadastros;

    private String myFormattedDate;

    private Agenda selected;

    public void fecha(){
        AppMainScreen.getStage().close();
    }

    @FXML
    void datePicker(ActionEvent event) {
        LocalDate myDate = campoData.getValue();
        myFormattedDate = (myDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).toString();
        initTableDate(myFormattedDate);
    }

    public void initTableDate(String data){
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        tableColumnHorario.setCellValueFactory(new PropertyValueFactory<>("hora"));
        tableColumnServico.setCellValueFactory(new PropertyValueFactory<>("servico"));
        tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tableColumnData.setCellValueFactory(new PropertyValueFactory<>("data"));
        tableViewCadastros.setItems(atualizaTabelaData(data));
    }

    public ObservableList<Agenda> atualizaTabelaData(String data){
        AgendaDAO dao = new AgendaDAO();
        List<Agenda> lista = dao.getList();
        List<Agenda> saida = new ArrayList<Agenda>();
        for(Agenda ele: lista){
            if(ele.getData().equals(data)){
                saida.add(ele);
            }
        }
        return FXCollections.observableArrayList(saida);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
        buttonAtualizar.setOnMouseClicked((MouseEvent e) -> {
            initTable();;
        });
        buttonCadastrar.setOnMouseClicked((MouseEvent e) ->{
            AppAlterarPessoa.getStage().close();
            AppAgenda appA = new AppAgenda();
            try {
                appA.start(new Stage());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        buttonEditar.setOnMouseClicked((MouseEvent e) -> {
            if(selected != null){
                if(AppAgenda.getStage() != null){
                    AppAgenda.getStage().close();
                }
                AppAlterarPessoa appP = new AppAlterarPessoa(selected);
            try {
                appP.start(new Stage());                
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            }else{
                Alert a = new Alert(AlertType.WARNING);
                a.setHeaderText("Selecione um usuário");
                a.show();
            }
            
        });
        tableViewCadastros.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Object>() {

            @Override
            public void changed(ObservableValue<? extends Object> arg0, Object arg1, Object arg2) {
                selected = (Agenda) arg2;
            }

        });

        buttonCadastrar.setOnMouseClicked((MouseEvent e) -> {
            AppAgenda appA = new AppAgenda();
            try {
                appA.start(new Stage());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

    public void initTable(){
        tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
        tableColumnHorario.setCellValueFactory(new PropertyValueFactory<>("hora"));
        tableColumnServico.setCellValueFactory(new PropertyValueFactory<>("servico"));
        tableColumnTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tableColumnData.setCellValueFactory(new PropertyValueFactory<>("data"));
        tableViewCadastros.setItems(atualizaTabela());
    }

    public ObservableList<Agenda> atualizaTabela(){
        AgendaDAO dao = new AgendaDAO();
        return FXCollections.observableArrayList(dao.getList());
    }

}

