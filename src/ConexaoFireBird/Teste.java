package ConexaoFireBird;

import java.sql.Connection;

import Agenda.Agenda;
import DAO.AgendaDAO;

public class Teste {

    public static void main(String[] args) {
        Agenda a = new Agenda();
        AgendaDAO dao = new AgendaDAO();
        System.out.println(dao.getMaxRegistro());
    }
    
}
