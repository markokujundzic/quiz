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
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Marko
 */
@ManagedBean
@SessionScoped
@Named(value = "zaboravljenalozinka")
public class ZaboravljenaLozinkaController implements Serializable {

    private String username;
    private String jmbg;
    private String odgovor;
    private String lozinka;
    private String potvrda;

    public void ispitaj() {

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session sesija = sessionFactory.openSession();
        sesija.beginTransaction();

        Criteria upit = sesija.createCriteria(Korisnik.class);

        Korisnik mojkorisnik = (Korisnik) upit.add(Restrictions.eq("username", username)).uniqueResult();
        sesija.getTransaction().commit();
        sesija.close();

        if (mojkorisnik != null) {

            SessionFactory sessionF = NewHibernateUtil.getSessionFactory();
            Session session = sessionF.openSession();
            session.beginTransaction();

            Criteria query = session.createCriteria(Korisnik.class);
            Korisnik trenutni = (Korisnik) query.add(Restrictions.eq("username", username)).add(Restrictions.eq("jmbg", jmbg)).uniqueResult();

            if (trenutni != null) {

                FacesContext fc = FacesContext.getCurrentInstance();
                HttpSession hs = (HttpSession) fc.getExternalContext().getSession(false);
                hs.setAttribute("korisnik", trenutni);
                hs.setAttribute("korime", trenutni.getUsername());

                session.getTransaction().commit();
                session.close();

                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("faces/zaboravio_lozinku_nastavak.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(ZaboravljenaLozinkaController.class.getName()).log(Level.SEVERE, null, ex);
                }

                username = null;
                jmbg = null;

            } else {
                session.getTransaction().commit();
                session.close();

                jmbg = null;
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Loš JMBG!", null));
            }
        } else {
            username = null;
            jmbg = null;
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Takav korisnik ne postoji!", null));
        }
    }

    public void ispitaj2() {

        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession hs = (HttpSession) fc.getExternalContext().getSession(false);

        SessionFactory sessionF = NewHibernateUtil.getSessionFactory();
        Session session = sessionF.openSession();
        session.beginTransaction();

        Criteria query = session.createCriteria(Korisnik.class);
        Korisnik trenutni = (Korisnik) query.add(Restrictions.eq("username", hs.getAttribute("korime"))).uniqueResult();

        session.getTransaction().commit();
        session.close();

        if (trenutni != null) {

            String hash_odgovor = null;
            try {
                hash_odgovor = LogInController.hash(this.odgovor);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (trenutni.getOdgovor().compareTo(hash_odgovor) == 0) {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("faces/finalna_promena.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(ZaboravljenaLozinkaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Odgovor nije zadovoljavajući!", null));
                odgovor = null;
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("faces/index.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(ZaboravljenaLozinkaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Neočekivana greška u radu sa bazom!", null));
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("faces/index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(ZaboravljenaLozinkaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String promeni() {

        if (potvrda.compareTo(lozinka) == 0) {

            String hash_password = null;
            try {
                hash_password = LogInController.hash(this.lozinka);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
            }

            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession hs = (HttpSession) fc.getExternalContext().getSession(false);

            SessionFactory sessionF = NewHibernateUtil.getSessionFactory();
            Session session = sessionF.openSession();
            session.beginTransaction();

            Korisnik korisnik = (Korisnik) hs.getAttribute("korisnik");
            korisnik.setPassword(hash_password);

            session.update(korisnik);
            session.getTransaction().commit();
            session.close();

            return "promena_lozinke_nastavak?faces-redirect=true";

        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Lozinke se ne slažu!", null));
            return "finalna_promena?faces-redirect=true";
        }
    }

    public String getPotvrda() {
        return potvrda;
    }

    public void setPotvrda(String potvrda) {
        this.potvrda = potvrda;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(String odgovor) {
        this.odgovor = odgovor;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }
}
