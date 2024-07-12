package Agenda;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import DAO.AgendaDAO;

public class Teste {

    public static void main(String[] args) {
        Date dataHoraAtual = new Date();
        System.out.println(dataHoraAtual);

        String data = new SimpleDateFormat("yyyy-MM-dd").format(dataHoraAtual);
        String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);

        System.out.println(data + " " + hora);

        AgendaDAO dao = new AgendaDAO();

        ArrayList<Agenda> lista = (ArrayList<Agenda>) dao.getList();

        for (Agenda ele : lista) {
            System.out.println(ele.getData());
            System.out.println(ele.getHora());
            System.out.println(ele.getDataHora());
        }

    }

}
