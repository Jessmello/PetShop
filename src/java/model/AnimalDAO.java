package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jess
 */
public class AnimalDAO {
    private static final String SQL_INSERIR_CONTATO = "insert into animal (nome, descricao) values(?,?)";
    private static final String SQL_LISTAR_CONTATOS = "select * from animal order by nome";
    private static final String SQL_CONSULTAR_CONTATO = "select * from animal where nome like ? order by nome";
    private static final String SQL_EXCLUIR_CONTATO = "delete from animal where id = ?";
    private static final String SQL_ALTERAR_CONTATO = "update animal set nome=?, descricao=? where id=?";

    private Connection connection;

    protected AnimalDAO() {
    }
    
    public Long adicionar(Animal animal) throws SQLException, ClassNotFoundException {
        Long id = null;
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.prepareStatement(SQL_INSERIR_CONTATO, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, animal.getNome());
                stmt.setString(2, animal.getDescricao());
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getLong(1);
                }
                stmt.close();
            } finally {
                connection.close();
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return id;
    }

    public List<Animal> listar() throws SQLException, ClassNotFoundException {
        List<Animal> animals = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.
                        prepareStatement(SQL_LISTAR_CONTATOS);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Animal c = new Animal();
                    c.setId(rs.getLong("id"));
                    c.setNome(rs.getString("nome"));
                    c.setDescricao(rs.getString("descricao"));
                    animals.add(c);
                }
                stmt.close();
                rs.close();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw e;
        }
        return animals;
    }
    
    public List<Animal> consultar(String nome) throws SQLException, ClassNotFoundException {
        List<Animal> animals = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.
                        prepareStatement(SQL_CONSULTAR_CONTATO);
                stmt.setString(1, '%' + nome + '%');
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Animal c = new Animal();
                    c.setId(rs.getLong("id"));
                    c.setNome(rs.getString("nome"));
                    c.setDescricao(rs.getString("descricao"));
                    animals.add(c);
                }
                stmt.close();
                rs.close();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw e;
        }
        return animals;
    }
    
    public void excluir(Long id) throws SQLException, ClassNotFoundException {
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.prepareStatement(SQL_EXCLUIR_CONTATO);
                stmt.setLong(1, id);
                stmt.execute();
                stmt.close();
            } finally {
                connection.close();
            }
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public void alterar(Animal animal) throws SQLException, ClassNotFoundException {
        try {
            connection = ConnectionFactory.getConnection();
            try {
                System.out.println(animal);
                PreparedStatement stmt = connection.prepareStatement(SQL_ALTERAR_CONTATO);
                stmt.setString(1, animal.getNome());
                stmt.setString(2, animal.getDescricao());
                stmt.setLong(3, animal.getId());
                stmt.execute();
                stmt.close();
            } finally {
                connection.close();
            }
        } catch (SQLException ex) {
            throw ex;
        }
    }
}
