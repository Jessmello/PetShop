package model;

/**
 *
 * @author Jess
 */
public class DAOFactory {
    
    public static ProdutoDAO getProdutoDAO(){
        return new ProdutoDAO();
    }
    
    public static UsuarioDAO getUsuarioDAO(){
        return new UsuarioDAO();
    }
    
}
