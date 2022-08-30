package src.br.com.agenda.aplicacao;

import src.br.com.agenda.dao.ContatoDAO;
import src.br.com.agenda.model.Contato;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        ContatoDAO contatoDao = new ContatoDAO();

        Contato contato = new Contato();
        contato.setNome("João Gabriel");
        contato.setIdade(19);
        contato.setDataCadastrado(new Date());

        contatoDao.save(contato);
    }
}
