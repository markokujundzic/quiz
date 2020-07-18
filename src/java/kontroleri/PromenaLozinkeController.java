/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import db.NewHibernateUtil;
import entiteti.Korisnik;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
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
@Named(value = "promenalozinke")
public class PromenaLozinkeController implements Serializable {

    private String username;
    private String password;
    private String nova;
    private String nova_final;

    public String promeni() {

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session sesija = sessionFactory.openSession();
        sesija.beginTransaction();

        Criteria upit = sesija.createCriteria(Korisnik.class);

        Korisnik trenutni = (Korisnik) upit.add(Restrictions.eq("username", username)).uniqueResult();
        sesija.getTransaction().commit();
        sesija.close();

        if (trenutni != null) {
            
            if (nova_final.compareTo(nova) == 0) {
                String konacna = null;
                String osnovna = null;
                try {
                    konacna = LogInController.hash(nova);
                    osnovna = LogInController.hash(password);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
                }

                SessionFactory sessionF = NewHibernateUtil.getSessionFactory();
                Session session = sessionF.openSession();
                session.beginTransaction();

                Criteria query = session.createCriteria(Korisnik.class);
                Korisnik korisnik = (Korisnik) query.add(Restrictions.eq("username", username)).add(Restrictions.eq("password", osnovna)).uniqueResult();

                if (korisnik != null) {
                    
                    korisnik.setPassword(konacna);
                    session.saveOrUpdate(korisnik);
                    session.getTransaction().commit();
                    session.close();

                    username = null;

                    return "promena_lozinke_nastavak";
                } else {
                    
                    session.getTransaction().commit();
                    session.close();
                    
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Uneta je loša lozinka!", null));

                    return "promena_lozinke";
                }
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unesene lozinke se razlikuju!", null));
                return "promena_lozinke";
            }
        } else {
            username = null;
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Takav korisnik ne postoji!", null));
            return "promena_lozinke";
        }
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

    public String getNova() {
        return nova;
    }

    public void setNova(String nova) {
        this.nova = nova;
    }

    public String getNova_final() {
        return nova_final;
    }

    public void setNova_final(String nova_final) {
        this.nova_final = nova_final;
    }
}
