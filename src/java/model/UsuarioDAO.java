package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ciro
 */
public class UsuarioDAO {
    private static final String SQL_INSERIR_USUARIO = "insert into usuarios (login, senha, nome, admin) values(?,PASSWORD(?),?, ?)";
    private static final String SQL_CONSULTAR_USUARIO = "select * from usuarios where login = ? and senha = PASSWORD(?)";
    private static final String SQL_VERIFICAR_LOGIN = "select * from usuarios where login = ?";
   
    private Connection connection;

    protected UsuarioDAO() {
    }
    
    public Long adicionar(Usuario usuario) throws SQLException, ClassNotFoundException {
        Long id = null;
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.prepareStatement(SQL_INSERIR_USUARIO, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, usuario.getLogin());
                stmt.setString(2, usuario.getSenha());
                stmt.setString(3, usuario.getNome());
                stmt.setBoolean(4, usuario.isAdmin());
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
    
    public Usuario consultar(String login, String senha) throws SQLException, ClassNotFoundException {
        Usuario usuario = null;
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.
                        prepareStatement(SQL_CONSULTAR_USUARIO);
                stmt.setString(1, login);
                stmt.setString(2, senha);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setId(rs.getLong("id"));
                    usuario.setLogin(rs.getString("login"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setAdmin(rs.getBoolean("admin"));
                }
                stmt.close();
                rs.close();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw e;
        }
        return usuario;
    }
    
    public boolean isLoginNaoCadastrado(String login) throws SQLException, ClassNotFoundException {
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.
                        prepareStatement(SQL_VERIFICAR_LOGIN);
                stmt.setString(1, login);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return false;
                }
                stmt.close();
                rs.close();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw e;
        }
        return true;
        
    }
}
