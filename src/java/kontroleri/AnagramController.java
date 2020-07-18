/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import db.NewHibernateUtil;
import entiteti.Anagram;
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
@Named(value = "anagram")
public class AnagramController implements Serializable {

    private String pitanje;
    private String odgovor;
    private String tacan_odgovor;

    private boolean zakljucan = false;

    private int anagram_tajmer = 60;

    private int brojpoena = 0;

    private boolean kraj = false;

    private boolean rebus;

    private String slika;

    private boolean tajmer_flag = true;

    public AnagramController() {

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria cr = session.createCriteria(Anagram.class);
        Anagram anagram = (Anagram) cr.add(Restrictions.eq("id", IgraDanaController.id_anagram)).uniqueResult();

        session.getTransaction().commit();
        session.close();

        if (anagram != null) {

            if (anagram.getRebus() == 0) {
                this.pitanje = anagram.getPitanje();
                this.tacan_odgovor = anagram.getOdgovor();
                rebus = false;
            } else {
                this.tacan_odgovor = anagram.getOdgovor();
                this.slika = anagram.getSlika();
                rebus = true;
            }
        }
    }

    public void kreni() {
        if (tajmer_flag) {
            --anagram_tajmer;
            if (anagram_tajmer == 0) {
                zakljucan = true;
                tajmer_flag = false;
            }
        }
    }

    public String resi() {

        kraj = true;
        tajmer_flag = false;
        anagram_tajmer = 0;

        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession s = (HttpSession) fc.getExternalContext().getSession(false);
        Korisnik trenutni = (Korisnik) s.getAttribute("user");

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria cr = session.createCriteria(Rezultati.class);
        Rezultati rezultat = (Rezultati) cr.add(Restrictions.eq("username", trenutni.getUsername())).add(Restrictions.eq("datum", new Date(System.currentTimeMillis()))).uniqueResult();

        if (odgovor.toLowerCase().compareTo(tacan_odgovor.toLowerCase()) == 0) {
            brojpoena = 10;
        }

        rezultat.setAnagram(brojpoena);
        rezultat.setUkupno(brojpoena + rezultat.getUkupno());

        session.saveOrUpdate(rezultat);

        session.getTransaction().commit();
        session.close();

        return "mojbroj?faces-redirect=true";
    }

    public String getPitanje() {
        return pitanje;
    }

    public void setPitanje(String pitanje) {
        this.pitanje = pitanje;
    }

    public String getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(String odgovor) {
        this.odgovor = odgovor;
    }

    public String getTacan_odgovor() {
        return tacan_odgovor;
    }

    public boolean isTajmer_flag() {
        return tajmer_flag;
    }

    public void setTajmer_flag(boolean tajmer_flag) {
        this.tajmer_flag = tajmer_flag;
    }

    public void setTacan_odgovor(String tacan_odgovor) {
        this.tacan_odgovor = tacan_odgovor;
    }

    public boolean isZakljucan() {
        return zakljucan;
    }

    public boolean isRebus() {
        return rebus;
    }

    public void setRebus(boolean rebus) {
        this.rebus = rebus;
    }

    public void setZakljucan(boolean zakljucan) {
        this.zakljucan = zakljucan;
    }

    public int getAnagram_tajmer() {
        return anagram_tajmer;
    }

    public void setAnagram_tajmer(int anagram_tajmer) {
        this.anagram_tajmer = anagram_tajmer;
    }

    public boolean isKraj() {
        return kraj;
    }

    public void setKraj(boolean kraj) {
        this.kraj = kraj;
    }

    public int getBrojpoena() {
        return brojpoena;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public void setBrojpoena(int brojpoena) {
        this.brojpoena = brojpoena;
    }
}
