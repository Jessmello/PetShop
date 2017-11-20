package beans;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.New;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import model.Produto;
import service.ProdutoRESTClient;

@Named(value = "produtoBean")
@SessionScoped
public class ProdutoBean implements Serializable {
    private @Inject
    @New
    Produto produto;
    private List<Produto> produtos;
    private List<Produto> produtosFiltrados;
    private final static int ACESSORIOS = 1;
    private final static int ALIMENTOS = 2;
    private final static int BRINQUEDOS = 3;
    private final static int HIGIENE = 4;
    private final static int REMEDIOS = 5;
    

    public ProdutoBean() throws SQLException, ClassNotFoundException {
        produtos = new ProdutoRESTClient().findAll();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Produto> getProdutosFiltrados() {
        return produtosFiltrados;
    }

    public void setProdutosFiltrados(List<Produto> produtosFiltrados) {
        this.produtosFiltrados = produtosFiltrados;
    }
    
    public String consultarAcessorios() throws SQLException, ClassNotFoundException {
        produtos = new ProdutoRESTClient().findByType(ACESSORIOS);
        return "/protected/acessorio";
    }
    
    public String consultarAlimentos()  throws SQLException, ClassNotFoundException {
        produtos = new ProdutoRESTClient().findByType(ALIMENTOS);
        return "/protected/alimento";
    }
    
    public String consultarBrinquedos()  throws SQLException, ClassNotFoundException {
        produtos = new ProdutoRESTClient().findByType(BRINQUEDOS);
        return "/protected/brinquedo";
    }
    
    public String consultarHigiene()  throws SQLException, ClassNotFoundException {
        produtos = new ProdutoRESTClient().findByType(HIGIENE);
        return "/protected/higiene";
    }
    
    public String consultarProduto() throws SQLException, ClassNotFoundException {
        produtos = new ProdutoRESTClient().findByName(produto.getNome());
        return null;
    }
    
    public String consultarRemedios() throws SQLException, ClassNotFoundException {
        produtos = new ProdutoRESTClient().findByType(REMEDIOS);
        return "/protected/remedio";
    }
    
    public String cadastrarProduto() {
        produto = new Produto();
        return "/protected/novoProduto";
    }
    
    public String novoProduto() throws SQLException, ClassNotFoundException {
        new ProdutoRESTClient().create(produto);
        return paginaPrincipal();
    }
    
    public String alterarProduto(Produto p){
        produto = p;
        return "/protected/alterarProduto";
    }
    
    public String salvarAlteracaoProduto() throws SQLException, ClassNotFoundException{
        new ProdutoRESTClient().edit(produto);
        return paginaPrincipal();
    }
    
    public String excluir(Produto p) throws SQLException, ClassNotFoundException{
        new ProdutoRESTClient().delete(produto.getId());
        return paginaPrincipal();
    }
    
    public String paginaPrincipal() throws SQLException, ClassNotFoundException{
        produtos = new ProdutoRESTClient().findAll();
        return "/protected/paginaPrincipal";
    }
    
    public List<SelectItem> getTiposProdutos() {
        List<SelectItem> tiposDeProdutos = new ArrayList<>();
        tiposDeProdutos.add(new SelectItem(ACESSORIOS, "Acessórios"));
        tiposDeProdutos.add(new SelectItem(ALIMENTOS, "Alimentos"));
        tiposDeProdutos.add(new SelectItem(BRINQUEDOS, "Brinquedos"));
        tiposDeProdutos.add(new SelectItem(HIGIENE, "Higiene"));
        tiposDeProdutos.add(new SelectItem(REMEDIOS, "Remédios"));
        return tiposDeProdutos;
    }
}
