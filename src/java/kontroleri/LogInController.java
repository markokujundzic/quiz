/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import db.NewHibernateUtil;
import entiteti.Korisnik;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Marko
 */
@ManagedBean
@SessionScoped
@Named(value = "logInController")
public class LogInController implements Serializable {

    private String username;
    private String password;

    public void login() {

        SessionFactory sessionF = NewHibernateUtil.getSessionFactory();
        Session session = sessionF.openSession();
        session.beginTransaction();

        Criteria query = session.createCriteria(Korisnik.class);

        Korisnik korisnik = (Korisnik) query.add(Restrictions.eq("username", username)).uniqueResult();
        session.getTransaction().commit();
        session.close();

        if (korisnik != null) {

            String sifra = null;
            try {
                sifra = LogInController.hash(password);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
            }

            SessionFactory sessionF1 = NewHibernateUtil.getSessionFactory();
            Session session1 = sessionF1.openSession();
            session1.beginTransaction();

            Criteria query1 = session1.createCriteria(Korisnik.class);

            Korisnik korisnik1 = (Korisnik) query1.add(Restrictions.eq("username", username)).add(Restrictions.eq("password", sifra)).uniqueResult();
            session1.getTransaction().commit();
            session1.close();

            if (korisnik1 != null) {

                FacesContext fc = FacesContext.getCurrentInstance();
                HttpSession hs = (HttpSession) fc.getExternalContext().getSession(false);
                hs.setAttribute("user", korisnik);

                switch (korisnik.getTip()) {
                    case "administrator":
                        try {
                            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/administrator.xhtml");
                        } catch (IOException ex) {
                            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case "takmicar":
                        try {
                            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/takmicar.xhtml");
                        } catch (IOException ex) {
                            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    default:
                        try {
                            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/supervizor.xhtml");
                        } catch (IOException ex) {
                            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                }
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Pogrešna lozinka!", null));
            }

        } else {
            username = null;
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Takav korisnik ne postoji!", null));
        }
    }

    public static String hash(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
