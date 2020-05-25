package br.com.agenda.contato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    private Connection conexao;

    public ContatoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void inserir(Contato contato) {
        String sql = "INSERT INTO CONTATO(NOME, EMAIL, TELEFONE, ESTADOCIVIL) VALUES(?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conexao.prepareStatement(sql);
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getEmail());
            ps.setString(3, contato.getTelefone());
            ps.setString(4, contato.getEstadoCivil().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    public List<Contato> listarPorNome(String nome) {
        String sql = "SELECT ID_CONTATO, NOME, EMAIL, TELEFONE, ESTADOCIVIL WHERE NOME LIKE ?";
        List<Contato> contatos = new ArrayList<Contato>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conexao.prepareStatement(sql);
            ps.setString(1, nome + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Contato contato = new Contato();
                contato.setId(rs.getLong(1));
                contato.setNome(rs.getString(2));
                contato.setEmail(rs.getString(3));
                contato.setEstadoCivil(EstadoCivil.valueOf(rs.getString(5)));
                contatos.add(contato);
            }
            return contatos;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        }
    }

}
