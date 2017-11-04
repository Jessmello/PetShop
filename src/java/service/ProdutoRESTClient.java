package service;

import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import model.Produto;
public class ProdutoRESTClient {
    public static final String WEBSERVICE_URL = "http://localhost:8080/PetShopWebService/webresources/";

    private String response;
    public List<Produto> findAll() {
        Client client = ClientBuilder.newClient();
        response = client.target(WEBSERVICE_URL + "petshop.produto").
        request(MediaType.APPLICATION_JSON).
        get(String.class);
        client.close();
        return ProdutoJson.fromJSONArray(response);
    }
    
    public List<Produto> findByName(String nome) {
        Client client = ClientBuilder.newClient();
        response = client.target(WEBSERVICE_URL + "petshop.produto/search/" + nome).
        request(MediaType.APPLICATION_JSON).
        get(String.class);
        client.close();
        return ProdutoJson.fromJSONArray(response);
    }

    public Produto find(Long id) {
        Client client = ClientBuilder.newClient();
        response = client.target(WEBSERVICE_URL + "petshop.produto/" + id)
                        .request(MediaType.APPLICATION_JSON)
                        .get(String.class);
        client.close();
        return ProdutoJson.fromJSONObject(response);
    }
    public void create(Produto produto) {
        Client client = ClientBuilder.newClient();
        client.target(WEBSERVICE_URL + "petshop.produto")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(produto,MediaType.APPLICATION_JSON));
        client.close();
    }

    public void edit(Produto produto) {
        Client client = ClientBuilder.newClient();
        client.target(WEBSERVICE_URL + "petshop.produto/" + produto.getId())
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(produto,MediaType.APPLICATION_JSON));
        client.close();
    }
    public void delete(Long id) {
        Client client = ClientBuilder.newClient();
        client.target(WEBSERVICE_URL + "petshop.produto/" + id)
                .request(MediaType.APPLICATION_JSON)
                .delete();
        client.close();
    }
}