package beans;

import java.io.Serializable;
import java.sql.SQLException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.New;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import model.Usuario;
import service.UsuarioRESTClient;
import util.Messages;
import util.Session;

@Named(value = "usuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable {

    @Inject @New
    private Usuario usuario;
    private String confirmeSenha;

    public UsuarioBean() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getConfirmeSenha() {
        return confirmeSenha;
    }

    public void setConfirmeSenha(String confirmeSenha) {
        this.confirmeSenha = confirmeSenha;
    }

    public String logar() throws SQLException, ClassNotFoundException {
        usuario = new UsuarioRESTClient().autenticar(usuario.getLogin(), usuario.getSenha());
        if (usuario != null) {
            Session.setAttribute("usuario", usuario);
            return new ProdutoBean().paginaPrincipal();
        }
        usuario = new Usuario();
        Messages.addMessage("frm:login", Messages.getString("msgs", "loginSenhaInvalidos"));
        return null;
    }

    public String sair() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index";
    }

    public String novoUsuario() {
        usuario = new Usuario();
        return "/novousuario";
    }

    public String cadastrar() throws SQLException, ClassNotFoundException {
        if (confirmeSenha.equals(usuario.getSenha())) {
            if (new UsuarioRESTClient().findByLogin(usuario.getLogin()) == null) {
                new UsuarioRESTClient().create(usuario);
                usuario = new UsuarioRESTClient().findByLogin(usuario.getLogin());
                Session.setAttribute("usuario", usuario);
                return new ProdutoBean().paginaPrincipal();
            }
            Messages.addMessage("frm:login", Messages.getString("msgs", "loginJaCadastrado"));
            return null;
        }
        Messages.addMessage("frm:confirmesenha", Messages.getString("msgs", "erroSenha"));
        return null;
    }
}
    
