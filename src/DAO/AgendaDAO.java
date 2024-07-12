package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Agenda.Agenda;
import ConexaoFireBird.Conexao;

public class AgendaDAO {

    private Connection con;

    public AgendaDAO() {
        this.con = new Conexao().conectar();
    }

    public boolean add(Agenda a) {
        String sql = "INSERT INTO AGENDA(NOME_CLIENTE,TELEFONE_CLIENTE,DATAHORA_SERVICO,REGISTRO,NOME_SERVICO,STATUS,PRIORIDADE) VALUES(?,?,?,?,?,?,?);";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, a.getNomeCliente());
            stmt.setString(2, a.getTelefone());
            stmt.setString(3, a.getDataHora());
            stmt.setLong(4, getMaxRegistro() + 1);
            stmt.setString(5, a.getServico());
            stmt.setString(6, a.getStatus());
            stmt.setString(7, a.getPrioridade());
            stmt.execute();
            stmt.close();
            con.close();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }

    }

    public boolean update(Agenda a) {
        String sql = "UPDATE AGENDA SET NOME_CLIENTE = ? , TELEFONE_CLIENTE = ? ,DATAHORA_SERVICO = ?,NOME_SERVICO = ?, STATUS = ?,PRIORIDADE=? WHERE REGISTRO = ?;";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, a.getNomeCliente());
            stmt.setString(2, a.getTelefone());
            stmt.setString(3, a.getDataHora());
            stmt.setString(4, a.getServico());
            stmt.setString(5, a.getStatus());
            stmt.setString(6, a.getPrioridade());
            stmt.setLong(7, a.getRegistro());
            stmt.execute();
            stmt.close();
            con.close();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }

    }

    public boolean delete(Agenda a) {
        String sql = "DELETE FROM AGENDA WHERE REGISTRO = ?;";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, a.getRegistro());
            stmt.execute();
            stmt.close();
            con.close();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }

    }

    public List<Agenda> getList() {
        List<Agenda> agenda = new ArrayList<>();
        String sql = "SELECT * FROM AGENDA";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet st = stmt.executeQuery();
            while (st.next()) {
                Agenda a = new Agenda();
                a.setRegistro(st.getLong("REGISTRO"));
                a.setNomeCliente(st.getString("NOME_CLIENTE"));
                a.setDataHora(st.getString("DATAHORA_SERVICO"));
                a.setServico(st.getString("NOME_SERVICO"));
                a.setTelefone(st.getString("TELEFONE_CLIENTE"));
                a.setStatus(st.getString("STATUS"));
                a.setPrioridade(st.getString("PRIORIDADE"));

                agenda.add(a);
            }
            stmt.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("ERRO, Lista nao foi retornada");
            return null;
        }
        return agenda;

    }

    public long getMaxRegistro() {
        Long numMax = 0L;
        String sql = "select max(AGENDA.registro) as max_registro from AGENDA";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                numMax = rs.getLong("max_registro");
            }
        } catch (SQLException e) {
            Logger.getLogger(AgendaDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return numMax;

    }

}
