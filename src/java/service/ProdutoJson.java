package service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import model.Produto;

public class ProdutoJson {
    public static String toJSON(Produto produto) {
        JsonObject contaJSON = Json.createObjectBuilder().
        add("id", produto.getId()).
        add("nome", produto.getNome()).
        add("descricao", produto.getDescricao()).
        build();
        return contaJSON.toString();
    }
    public static Produto fromJSONObject(String json) {
        
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject produtoObject = reader.readObject();
        reader.close();
        
        Produto p = new Produto();
        p.setId(new Long(produtoObject.getInt("id")));
        p.setNome(produtoObject.getString("nome"));
        p.setDescricao(produtoObject.getString("descricao"));
        return p;
    }
    public static List<Produto> fromJSONArray( String json) {
        List<Produto> produtos = new ArrayList<>();
        
        JsonReader reader = Json.createReader( new StringReader(json));
        JsonArray produtoArray = reader.readArray();
        reader.close();
        
        for (JsonValue value : produtoArray) {
            JsonObject produtoObject = (JsonObject)value;
            Produto p = new Produto();
            p.setId(new Long(produtoObject.getInt("id")));
            p.setNome(produtoObject.getString("nome"));
            p.setDescricao(produtoObject.getString("descricao"));
            produtos.add(p);
        }
        return produtos;
    }
}
