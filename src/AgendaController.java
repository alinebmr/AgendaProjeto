import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import Agenda.Agenda;
import DAO.AgendaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AgendaController implements Initializable {


    @FXML
    private Button buttonCancelar;

    @FXML
    private Button buttonCadastro;

    @FXML
    private DatePicker campoData;

    @FXML
    private TextField campoHorario;

    @FXML
    private TextField campoNomeCliente;

    @FXML
    private TextField campoServico;

    @FXML
    private TextField campoTelefoneCliente;

    @FXML
    private Label mensagemCadastro;

    private String myFormattedDate;

    @FXML
    void datePicker(ActionEvent event) {
        LocalDate myDate = campoData.getValue();
        myFormattedDate = (myDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).toString();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonCadastro.setOnMouseClicked((MouseEvent e) -> {
            cadastrarAtendimento();
        });
        buttonCancelar.setOnMouseClicked((MouseEvent e) ->{
            fecha();
        });
    }

    private void fecha() {
        AppAgenda.getStage().close();
    }


    public void cadastrarAtendimento(){
        String nome = campoNomeCliente.getText();
        String servico = campoServico.getText();
        String telefone = campoTelefoneCliente.getText();
        String hora = campoHorario.getText() + ":00";
        String dataHora = myFormattedDate + " " + hora;
        AgendaDAO dao = new AgendaDAO();
        Agenda agenda = new Agenda();
        if(Objects.isNull(dao.getMaxRegistro())){
            agenda.setRegistro(1L);
        }else{
            Long registro = dao.getMaxRegistro() + 1;
            agenda.setRegistro(registro);
        }

        agenda.setNomeCliente(nome);
        agenda.setDataHora(dataHora);
        agenda.setServico(servico);
        agenda.setTelefone(telefone);
        agenda.setStatus('A');   

        dao.add(agenda);  
        
    }

    public void cancelarHorario(Agenda a){
        a.cancelarHorario();
    }

    

}

