import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

import Agenda.Agenda;
import DAO.AgendaDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AgendaController implements Initializable {

    @FXML
    private Button buttonCancelar;

    @FXML
    private Button buttonCadastro;

    @FXML
    private TextField campoNomeCliente;

    @FXML
    private TextField campoServico;

    @FXML
    private TextField campoTelefoneCliente;

    @FXML
    private Label mensagemCadastro;

    @FXML
    private CheckBox checkBoxPrioridade;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonCadastro.setOnMouseClicked((MouseEvent e) -> {
            cadastrarAtendimento();

        });
        buttonCancelar.setOnMouseClicked((MouseEvent e) -> {
            fecha();
        });

    }

    private void fecha() {
        AppAgenda.getStage().close();
    }

    public void cadastrarAtendimento() {
        String nome = campoNomeCliente.getText();
        String servico = campoServico.getText();
        String telefone = campoTelefoneCliente.getText();

        AgendaDAO dao = new AgendaDAO();
        Agenda agenda = new Agenda();
        if (Objects.isNull(dao.getMaxRegistro())) {
            agenda.setRegistro(1L);
        } else {
            Long registro = dao.getMaxRegistro() + 1;
            agenda.setRegistro(registro);
        }

        Date dataHoraAtual = new Date();

        String data = new SimpleDateFormat("yyyy-MM-dd").format(dataHoraAtual);
        String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);

        if (checkBoxPrioridade.isSelected()) {
            agenda.setPrioridade("Sim");
        } else {
            agenda.setPrioridade("Não");
        }

        agenda.setDataHora(data + " " + hora);
        agenda.setNomeCliente(nome);

        agenda.setServico(servico);
        agenda.setTelefone(telefone);
        agenda.setStatus("Em Espera");

        dao.add(agenda);
        System.out.println("Cadastro realizado com sucesso!");

    }

    public void concluirAtendimento(Agenda agenda) {
        agenda.concluirAtendimento();
    }

    public void retornarParaEspera(Agenda agenda) {
        agenda.retornarParaEspera();
    }

}
