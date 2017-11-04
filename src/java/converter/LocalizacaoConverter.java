package converter;

import java.io.Serializable;
import java.util.Locale;
import javax.enterprise.context.ConversationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ciro
 */
@FacesConverter("converter.LocalizacaoConverter")
@ConversationScoped
public class LocalizacaoConverter implements Converter, Serializable {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return Locale.forLanguageTag(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Locale)value).toLanguageTag();
    }
}
