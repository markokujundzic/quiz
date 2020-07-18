/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import db.NewHibernateUtil;
import entiteti.Korisnik;
import entiteti.Rezultati;
import java.io.Serializable;
import java.sql.Date;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
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
@Named(value = "igrac_poeni")
public class IgracPoeniController implements Serializable {

    private Rezultati poeni;

    public IgracPoeniController() {

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession s = (HttpSession) fc.getExternalContext().getSession(false);
        Korisnik trenutni = (Korisnik) s.getAttribute("user");

        Criteria cr = session.createCriteria(Rezultati.class);
        Rezultati rezultat = (Rezultati) cr.add(Restrictions.eq("username", trenutni.getUsername())).add(Restrictions.eq("datum", new Date(System.currentTimeMillis()))).uniqueResult();

        poeni = rezultat;

        session.getTransaction().commit();
        session.close();
    }

    public Rezultati getPoeni() {
        return poeni;
    }

    public void setPoeni(Rezultati poeni) {
        this.poeni = poeni;
    }
}
