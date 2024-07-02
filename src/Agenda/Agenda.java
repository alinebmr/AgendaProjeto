package Agenda;

public class Agenda {

    private Long registro = (long) 1;
    private String nomeCliente;
    private String telefone;
    private String dataHora;
    private String hora;
    private String servico;
    private String data;
    private char status;

    public Agenda() {

    }

    public Agenda(String nomeCliente, String telefone_Cliente, String dataHora, String nomeServico, Long registro) {

        this.nomeCliente = nomeCliente;
        this.telefone = telefone_Cliente;
        this.servico = nomeServico;
        this.registro = registro;
        this.status = 'A';
        this.dataHora = dataHora;
        setData(dataHora);
        setHora(dataHora);

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

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
        if (dataHora != null) {
            String dataHoraSplit[] = dataHora.split(" ");
            this.data = dataHoraSplit[0];
            String horaSplit[] = dataHoraSplit[1].split(":");
            hora = horaSplit[0] + ":" + horaSplit[1];

        }
        setData(data);
        setHora(hora);
    }

    public void setHora(String hora) {
        if (dataHora != null) {
            String dataHoraSplit[] = dataHora.split(" ");
            this.hora = horaSplit[0] + ":" + horaSplit[1];

        }
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

    public String getHora() {
        return this.hora;
    }

    public String getData() {
        return this.data;
    }

    public void cancelarHorario() {
        this.status = 'C';
    }

    public char getStatus() {
        return this.status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

}
