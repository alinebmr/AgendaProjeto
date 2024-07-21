package Agenda;

import javafx.scene.control.Button;

public class Agenda {

    private Long registro = (long) 1;
    private String nomeCliente;
    private String telefone;
    private String dataHora;
    private String hora;
    private String servico;
    private String data;
    private String status;
    private String prioridade;

    public Agenda() {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public Agenda(String nomeCliente, String telefone_Cliente, String dataHora, String nomeServico, Long registro) {

        this.nomeCliente = nomeCliente;
        this.telefone = telefone_Cliente;
        this.servico = nomeServico;
        this.registro = registro;
        this.status = "Em atendimento";
        this.dataHora = dataHora;

    }

    public void setRegistro(Long registro) {
        this.registro = registro;
    }

    public Long getRegistro() {
        return registro;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;

        String[] dataHoraSplit = dataHora.split(" ");
        String data = dataHoraSplit[0];
        String[] horaSplit = (dataHoraSplit[1]).split(":");
        setData(data);
        setHora(horaSplit[0] + ":" + horaSplit[1]);
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setData(String data) {
        this.data = data;

    }

    public String getServico() {
        return servico;
    }

    public void setServico(String nomeServico) {
        this.servico = nomeServico;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dataHora == null) ? 0 : dataHora.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Agenda other = (Agenda) obj;
        if (dataHora == null) {
            if (other.dataHora != null)
                return false;
        } else if (!dataHora.equals(other.dataHora))
            return false;
        return true;
    }

    public void setRegistro(long l) {
        this.registro = l;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getHora() {
        return this.hora;
    }

    public String getData() {
        return this.data;
    }

    public void concluirAtendimento() {
        this.status = "Atendido";
    }

    public void retornarParaEspera() {
        this.status = "Em Espera";
    }
}