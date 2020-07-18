/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Marko
 */
@ManagedBean
@SessionScoped
@Named("logOutController")
public class LogOutController implements Serializable {

    public String logout() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession s = (HttpSession) fc.getExternalContext().getSession(false);
        s.invalidate();
        return "index?faces-redirect=true";
    }
}
