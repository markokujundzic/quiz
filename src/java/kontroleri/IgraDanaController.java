/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import db.NewHibernateUtil;
import entiteti.IgraDana;
import entiteti.IgraoDanas;
import entiteti.Korisnik;
import entiteti.Rezultati;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
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
@Named(value = "igradana")
public class IgraDanaController implements Serializable {

    public static int id_anagram;
    public static int id_pehar;
    public static int id_vesala;

    public String proveri() {

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria cr = session.createCriteria(IgraDana.class);
        IgraDana igra = (IgraDana) cr.add(Restrictions.eq("datum", new Date(System.currentTimeMillis()))).uniqueResult();

        session.getTransaction().commit();
        session.close();

        if (igra != null) {

            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession s = (HttpSession) fc.getExternalContext().getSession(false);
            Korisnik trenutni = (Korisnik) s.getAttribute("user");

            SessionFactory sessionFactory1 = NewHibernateUtil.getSessionFactory();
            Session session1 = sessionFactory1.openSession();
            session1.beginTransaction();

            Criteria cr1 = session1.createCriteria(IgraoDanas.class);
            IgraoDanas igraodanas = (IgraoDanas) cr1.add(Restrictions.eq("username", trenutni.getUsername())).uniqueResult();

            if (Date.valueOf(LocalDate.now()).compareTo(igraodanas.getDatum()) != 0) {
                igraodanas.setDatum(new Date(System.currentTimeMillis()));
                igraodanas.setDa_li_je_igrao(0);
            }

            if (igraodanas.getDa_li_je_igrao() != 0) {
                session1.getTransaction().commit();
                session1.close();

                return "vec_odigrana_igra_dana?faces-redirect=true";
            } else {
                
                igraodanas.setDa_li_je_igrao(1);
                session1.saveOrUpdate(igraodanas);
                session1.getTransaction().commit();
                session1.close();

                SessionFactory SF = NewHibernateUtil.getSessionFactory();
                Session sesija = SF.openSession();
                sesija.beginTransaction();

                Rezultati rezultat = new Rezultati();
                rezultat.setUsername(trenutni.getUsername());
                rezultat.setAnagram(0);
                rezultat.setMojbroj(0);
                rezultat.setPehar(0);
                rezultat.setVesala(0);
                rezultat.setZanimljivageografija(0);
                rezultat.setDatum(new Date(System.currentTimeMillis()));
                rezultat.setUkupno(0);

                sesija.save(rezultat);

                sesija.getTransaction().commit();
                sesija.close();

                id_anagram = igra.getId_anagram();
                id_pehar = igra.getId_pehar();
                id_vesala = igra.getId_vesala();

                return "anagram?faces-redirect=true";
            }
        } else {
            return "nepostojeca_igra_dana?faces-redirect=true";
        }
    }

    public static int getId_vesala() {
        return id_vesala;
    }

    public static void setId_vesala(int id_vesala) {
        IgraDanaController.id_vesala = id_vesala;
    }

    public int getId_anagram() {
        return id_anagram;
    }

    public void setId_anagram(int id_anagram) {
        IgraDanaController.id_anagram = id_anagram;
    }

    public int getId_pehar() {
        return id_pehar;
    }

    public void setId_pehar(int id_pehar) {
        IgraDanaController.id_pehar = id_pehar;
    }
}
