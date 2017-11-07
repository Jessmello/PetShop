package service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import model.Usuario;

public class UsuarioJson {
    public static String toJSON(Usuario usuario) {
        JsonObject contaJSON = Json.createObjectBuilder().
        add("id", usuario.getId()).
        add("login", usuario.getLogin()).
        add("senha", usuario.getSenha()).
        add("nome", usuario.getNome()).
        add("admin", usuario.isAdmin()).
        build();
        return contaJSON.toString();
    }
    public static Usuario fromJSONObject(String json) {
        
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject usuarioObject = reader.readObject();
        reader.close();
        Usuario p = new Usuario();
        try{
            p.setId(new Long(usuarioObject.getInt("id")));
            p.setLogin(usuarioObject.getString("login"));
            p.setSenha(usuarioObject.getString("senha"));
            p.setNome(usuarioObject.getString("nome"));
            p.setAdmin(usuarioObject.getBoolean("admin"));
            
        }catch(Exception e){
            return null;
        }
        return p;
    }
    public static List<Usuario> fromJSONArray( String json) {
        List<Usuario> usuarios = new ArrayList<>();
        
        JsonReader reader = Json.createReader( new StringReader(json));
        JsonArray usuarioArray = reader.readArray();
        reader.close();
        
        for (JsonValue value : usuarioArray) {
            JsonObject usuarioObject = (JsonObject)value;
            Usuario p = new Usuario();
             p.setId(new Long(usuarioObject.getInt("id")));
            p.setLogin(usuarioObject.getString("login"));
            p.setSenha(usuarioObject.getString("senha"));
            p.setNome(usuarioObject.getString("nome"));
            p.setAdmin(usuarioObject.getBoolean("admin"));
            usuarios.add(p);
        }
        return usuarios;
    }
}
