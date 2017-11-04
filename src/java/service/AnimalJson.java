package service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import model.Animal;

/**
 *
 * @author Jess
 */
public class AnimalJson {
    public static String toJSON(Animal animal) {
        JsonObject contaJSON = Json.createObjectBuilder().
        add("id", animal.getId()).
        add("nome", animal.getNome()).
        add("descricao", animal.getDescricao()).
        build();
        return contaJSON.toString();
    }
    public static Animal fromJSONObject(String json) {
        
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject animalObject = reader.readObject();
        reader.close();
        
        Animal a = new Animal();
        a.setId(new Long(animalObject.getInt("id")));
        a.setNome(animalObject.getString("nome"));
        a.setDescricao(animalObject.getString("descricao"));
        return a;
    }
    public static List<Animal> fromJSONArray( String json) {
        List<Animal> animais = new ArrayList<>();
        
        JsonReader reader = Json.createReader( new StringReader(json));
        JsonArray contaArray = reader.readArray();
        reader.close();
        
        for (JsonValue value : contaArray) {
            JsonObject obj = (JsonObject)value;
            Animal a = new Animal();
            a.setId(new Long(obj.getInt("id")));
            a.setNome(obj.getString("nome"));
            a.setDescricao(obj.getString("descricao"));
            animais.add(a);
        }
        return animais;
    }
}
