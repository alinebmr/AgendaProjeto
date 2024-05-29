import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import Agenda.Agenda;
import DAO.AgendaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AlterarPessoaController implements Initializable{

    @FXML
    private Button buttonCancelar;

    @FXML
    private Button buttonSalvar;

    @FXML
    private DatePicker campoData;

    @FXML
    private TextField campoHorario;

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoServico;

    @FXML
    private TextField campoTelefone;

    private LocalDate myDate;

    private static Agenda agenda;

    private String myFormattedDate;


    public void fecha(){
        AppAlterarPessoa.getStage().close();
    }

    @FXML
    public void datePicker(ActionEvent event) {
        myDate = campoData.getValue();
        myFormattedDate = (myDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).toString();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initPerson();
        buttonCancelar.setOnMouseClicked((MouseEvent e) -> {
            fecha();
        });
        buttonSalvar.setOnMouseClicked((MouseEvent e) -> {
            atualiza();
        });

    }

    public void initPerson(){
        campoNome.setText(agenda.getNomeCliente());
        campoServico.setText(agenda.getServico());
        campoTelefone.setText(agenda.getTelefone());
        campoHorario.setText(agenda.getHora());
        campoData.setValue(LocalDate.parse(agenda.getData()));


    }

    public static Agenda getAgenda() {
        return agenda;
    }

    public static void setAgenda(Agenda agenda) {
        AlterarPessoaController.agenda = agenda;
    }

    public void atualiza(){
        AgendaDAO dao = new AgendaDAO();
        String nome = campoNome.getText();
        String telefone = campoTelefone.getText();
        String servico = campoServico.getText();
        String hora = campoHorario.getText();
        String dataHora = myFormattedDate + " " + hora;
        Agenda a = new Agenda();
        a.setNomeCliente(nome);
        a.setServico(servico);
        a.setTelefone(telefone);
        a.setTelefone(telefone);
        a.setDataHora(dataHora);
        dao.update(a);

    }

    
    

}
