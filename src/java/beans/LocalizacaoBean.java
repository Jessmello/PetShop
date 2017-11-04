/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Jess
 */


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "localizacaoBean")
@SessionScoped
public class LocalizacaoBean implements Serializable {
    private Locale localizacao;
    private static final Locale[] COUNTRIES = {Locale.forLanguageTag("pt-br"), Locale.ENGLISH};
    
    public Locale getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Locale localizacao) {
        this.localizacao = localizacao;
    }
    
    public List<SelectItem> getIdiomasSuportados() {
        List<SelectItem> idiomas = new ArrayList<>();
        for (Locale loc : COUNTRIES) {
            idiomas.add(new SelectItem(loc,
                    loc.getDisplayLanguage()));
        }
        return idiomas;
    }
}

