package service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
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
        add("idAnimal", "{\"id\":\""+produto.getIdAnimal().getId()+"\"}").
        add("marca", produto.getMarca()).
        add("valor", produto.getValor()).
        add("cdTipoProduto", produto.getCdTipoProduto()).
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
        p.getIdAnimal().setId(new Long(produtoObject.getJsonObject("idAnimal").getInt("id")));
        p.setMarca(produtoObject.getString("marca"));
        p.setValor(produtoObject.getJsonNumber("valor").doubleValue());
        p.setCdTipoProduto(produtoObject.getInt("cdTipoProduto"));
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
            p.getIdAnimal().setId(new Long(produtoObject.getJsonObject("idAnimal").getInt("id")));
            p.setMarca(produtoObject.getString("marca"));
            p.setValor(produtoObject.getJsonNumber("valor").doubleValue());
            p.setCdTipoProduto(produtoObject.getInt("cdTipoProduto"));
            produtos.add(p);
        }
        return produtos;
    }
}
