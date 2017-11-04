package util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ciro
 */
public class Session {
    public static void setAttribute(String name, Object value) {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.setAttribute(name, value);
    }
    
    public static Object getAttribute(String name) {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session == null)
            return null;
        return session.getAttribute(name);
    } 
    
}
