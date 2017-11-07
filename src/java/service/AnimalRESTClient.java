package service;

import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import model.Animal;
public class AnimalRESTClient {
    public static final String WEBSERVICE_URL = "http://localhost:8080/PetShopWebService/webresources/";

    private String response;
    public List<Animal> findAll() {
        Client client = ClientBuilder.newClient();
        response = client.target(WEBSERVICE_URL + "petshop.animal").
        request(MediaType.APPLICATION_JSON).
        get(String.class);
        client.close();
        return AnimalJson.fromJSONArray(response);
    }
    
    public List<Animal> findByName(String nome) {
        Client client = ClientBuilder.newClient();
        response = client.target(WEBSERVICE_URL + "petshop.animal/search/" + nome).
        request(MediaType.APPLICATION_JSON).
        get(String.class);
        client.close();
        return AnimalJson.fromJSONArray(response);
    }

    public void create(Animal animal) {
        Client client = ClientBuilder.newClient();
        client.target(WEBSERVICE_URL + "petshop.animal")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(animal,MediaType.APPLICATION_JSON));
        client.close();
    }

    public void edit(Animal animal) {
        Client client = ClientBuilder.newClient();
        client.target(WEBSERVICE_URL + "petshop.animal/" + animal.getId())
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(animal,MediaType.APPLICATION_JSON));
        client.close();
    }
    public void delete(Long id) {
        Client client = ClientBuilder.newClient();
        client.target(WEBSERVICE_URL + "petshop.animal/" + id)
                .request(MediaType.APPLICATION_JSON)
                .delete();
        client.close();
    }
}