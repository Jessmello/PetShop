/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public Animal find(Long id) {
        Client client = ClientBuilder.newClient();
        response = client.target(WEBSERVICE_URL + "petshop.animal/" + id)
                        .request(MediaType.APPLICATION_JSON)
                        .get(String.class);
        client.close();
        return AnimalJson.fromJSONObject(response);
    }
    public void create(Animal conta) {
        Client client = ClientBuilder.newClient();
        client.target(WEBSERVICE_URL + "petshop.animal")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(conta,MediaType.APPLICATION_JSON));
        client.close();
    }

    public void edit(Animal conta) {
        Client client = ClientBuilder.newClient();
        client.target(WEBSERVICE_URL + "petshop.animal/" + conta.getId())
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(conta,MediaType.APPLICATION_JSON));
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