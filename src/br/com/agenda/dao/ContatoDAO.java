package src.br.com.agenda.dao;

import com.mysql.jdbc.PreparedStatement;
import src.br.com.agenda.factory.ConnectionFactory;
import src.br.com.agenda.model.Contato;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    /*
    * CRUD
    * c: CREATE
    * r: READ
    * u: UPDATE
    * d: DELETE
    */

    // Create
    public void save(Contato contato) {
        String sql = "INSERT INTO contatos(nome, idade, dataCadastrado) VALUES (?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            // Create connection BD
            conn = ConnectionFactory.createConnectionToMySQL();

            // Create PreparedStatement, to execute query
            pstm = (PreparedStatement) conn.prepareStatement(sql);

            // Add values await query
            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getIdade());
            pstm.setDate(3, new Date(contato.getDataCadastrado().getTime()));

            // Execute Query
            pstm.execute();

            System.out.println("Contact created success!");
        } catch (Exception err) {
            err.printStackTrace();
        } finally {

            // Close Connections
            try {
                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception err) {
                err.printStackTrace();
            }

        }
    }

    public List<Contato> getContatos() {
        String sql = "SELECT * FROM contatos";

        List<Contato> contatos = new ArrayList<Contato>();

        Connection conn = null;
        PreparedStatement pstm = null;

        //Class recover data BD;
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()) {

                Contato contato = new Contato();

                // Recover ID
                contato.setId(rset.getInt("id"));

                // Recover Name
                contato.setNome(rset.getString("nome"));

                // Recover Age
                contato.setIdade(rset.getInt("idade"));

                // Recover Date Register
                contato.setDataCadastrado(rset.getDate("dataCadastrado"));

                contatos.add(contato);
            }

        } catch (Exception err) {
            err.printStackTrace();
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }

                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception err) {
                err.printStackTrace();
            }
        }

        return contatos;
    }
}
