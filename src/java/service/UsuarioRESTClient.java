package service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import model.Usuario;
public class UsuarioRESTClient {
    public static final String WEBSERVICE_URL = "http://localhost:8080/PetShopWebService/webresources/";

    private String response;
    public Usuario autenticar(String login, String senha) {
        Client client = ClientBuilder.newClient();
        response = client.target(WEBSERVICE_URL + "petshop.usuarios/"+login+"/"+senha).
        request(MediaType.APPLICATION_JSON).
        get(String.class);
        client.close();
        return UsuarioJson.fromJSONObject(response);
    }
    
    public Usuario findByLogin(String login) {
        Client client = ClientBuilder.newClient();
        response = client.target(WEBSERVICE_URL + "petshop.usuarios/searchLogin/" + login).
        request(MediaType.APPLICATION_JSON).
        get(String.class);
        client.close();
        return UsuarioJson.fromJSONObject(response);
    }
    
    public Usuario find(Long id) {
        Client client = ClientBuilder.newClient();
        response = client.target(WEBSERVICE_URL + "petshop.usuarios/" + id)
                        .request(MediaType.APPLICATION_JSON)
                        .get(String.class);
        client.close();
        return UsuarioJson.fromJSONObject(response);
    }
    public void create(Usuario usuario) {
        Client client = ClientBuilder.newClient();
        client.target(WEBSERVICE_URL + "petshop.usuarios")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(usuario,MediaType.APPLICATION_JSON));
        client.close();
    }

    public void edit(Usuario usuario) {
        Client client = ClientBuilder.newClient();
        client.target(WEBSERVICE_URL + "petshop.usuarios/" + usuario.getId())
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(usuario,MediaType.APPLICATION_JSON));
        client.close();
    }
    public void delete(Long id) {
        Client client = ClientBuilder.newClient();
        client.target(WEBSERVICE_URL + "petshop.usuarios/" + id)
                .request(MediaType.APPLICATION_JSON)
                .delete();
        client.close();
    }
}