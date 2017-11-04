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
public class ProdutoDAO {
    private static final String SQL_INSERIR_PRODUTO = "insert into produto (nome, descricao, id_animal, marca, valor, cd_tipo_produto) values(?,?,?,?,?, ?)";
    private static final String SQL_LISTAR_PRODUTOS = "select * from produto order by nome";
    private static final String SQL_LISTAR_PRODUTOS_POR_TIPO = "select * from produto where cd_tipo_produto = ? order by nome";
    private static final String SQL_CONSULTAR_PRODUTO = "select * from produto where nome like ? order by nome";
    private static final String SQL_EXCLUIR_PRODUTO = "delete from produto where id = ?";
    private static final String SQL_ALTERAR_PRODUTO = "update produto set nome=?, descricao=?,  id_animal = ?, marca=?, valor=?, cd_tipo_produto=? where id=?";

    private Connection connection;

    protected ProdutoDAO() {
    }
    
    public Long adicionar(Produto produto) throws SQLException, ClassNotFoundException {
        Long id = null;
        int i = 1;
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.prepareStatement(SQL_INSERIR_PRODUTO, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(i++, produto.getNome());
                stmt.setString(i++, produto.getDescricao());
                stmt.setLong(i++, produto.getAnimal().getId());
                stmt.setString(i++, produto.getMarca());
                stmt.setDouble(i++, produto.getValor());
                stmt.setInt(i++, produto.getTipo());
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

    public List<Produto> listar() throws SQLException, ClassNotFoundException {
        List<Produto> produtos = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.
                        prepareStatement(SQL_LISTAR_PRODUTOS);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Produto c = new Produto();
                    c.setId(rs.getLong("id"));
                    c.setNome(rs.getString("nome"));
                    c.setDescricao(rs.getString("descricao"));
                    c.getAnimal().setId(rs.getLong("id_animal"));
                    c.setMarca(rs.getString("marca"));
                    c.setValor(rs.getDouble("valor"));
                    c.setTipo(rs.getInt("cd_tipo_produto"));
                    produtos.add(c);
                }
                stmt.close();
                rs.close();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw e;
        }
        return produtos;
    }
    public List<Produto> listarPorTipo(int tipo) throws SQLException, ClassNotFoundException {
        List<Produto> produtos = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.
                        prepareStatement(SQL_LISTAR_PRODUTOS_POR_TIPO);
                stmt.setInt(1, tipo);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Produto c = new Produto();
                    c.setId(rs.getLong("id"));
                    c.setNome(rs.getString("nome"));
                    c.setDescricao(rs.getString("descricao"));
                    c.getAnimal().setId(rs.getLong("id_animal"));
                    c.setMarca(rs.getString("marca"));
                    c.setValor(rs.getDouble("valor"));
                    c.setTipo(rs.getInt("cd_tipo_produto"));
                    produtos.add(c);
                }
                stmt.close();
                rs.close();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw e;
        }
        return produtos;
    }
    
    public List<Produto> consultar(String nome) throws SQLException, ClassNotFoundException {
        List<Produto> produtos = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.
                        prepareStatement(SQL_CONSULTAR_PRODUTO);
                stmt.setString(1, '%' + nome + '%');
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Produto c = new Produto();
                    c.setId(rs.getLong("id"));
                    c.setNome(rs.getString("nome"));
                    c.setDescricao(rs.getString("descricao"));
                    produtos.add(c);
                }
                stmt.close();
                rs.close();
            } finally {
                connection.close();
            }
        } catch (SQLException e) {
            throw e;
        }
        return produtos;
    }
    
    public void excluir(Long id) throws SQLException, ClassNotFoundException {
        try {
            connection = ConnectionFactory.getConnection();
            try {
                PreparedStatement stmt = connection.prepareStatement(SQL_EXCLUIR_PRODUTO);
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

    public void alterar(Produto produto) throws SQLException, ClassNotFoundException {
        int i = 1;
        try {
            connection = ConnectionFactory.getConnection();
            try {
                System.out.println(produto);
                PreparedStatement stmt = connection.prepareStatement(SQL_ALTERAR_PRODUTO);
                stmt.setString(i++, produto.getNome());
                stmt.setString(i++, produto.getDescricao());
                stmt.setLong(i++, produto.getAnimal().getId());
                stmt.setString(i++, produto.getMarca());
                stmt.setDouble(i++, produto.getValor());
                stmt.setInt(i++, produto.getTipo());
                stmt.setLong(i++, produto.getId());
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
