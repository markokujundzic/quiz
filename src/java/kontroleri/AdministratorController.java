/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import db.NewHibernateUtil;
import entiteti.Anagram;
import entiteti.IgraDana;
import entiteti.IgraoDanas;
import entiteti.Korisnik;
import entiteti.Pehar;
import entiteti.Rezultati;
import entiteti.Vesanje;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
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
@Named(value = "administrator")
public class AdministratorController implements Serializable {

    private List<Anagram> lista_anagrama;
    private List<Vesanje> lista_vesala;
    private List<Pehar> lista_pehara;

    public static ArrayList<Korisnik> lista_zahteva = new ArrayList<Korisnik>();

    private boolean flag_anagram = false;
    private boolean flag_vesala = false;
    private boolean flag_pehar = false;

    private int id_anagram;
    private int id_vesala;
    private int id_pehar;

    private Date trenutni_datum;

    private java.util.Date odabrani_datum;

    private boolean flag_provera = false;
    private boolean napravi = true;
    private boolean azuriraj = true;
    private boolean ne_azuriraj = true;
    private Date datum;
    
    private boolean blok = true;

    public AdministratorController() {

        trenutni_datum = new Date(System.currentTimeMillis());
        odabrani_datum = null;

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria cr = session.createCriteria(Anagram.class);

        lista_anagrama = cr.list();

        session.getTransaction().commit();
        session.close();

        SessionFactory sessionFactory1 = NewHibernateUtil.getSessionFactory();
        Session session1 = sessionFactory1.openSession();
        session1.beginTransaction();

        Criteria cr1 = session1.createCriteria(Vesanje.class);

        lista_vesala = cr1.list();

        session1.getTransaction().commit();
        session1.close();

        SessionFactory sessionFactory2 = NewHibernateUtil.getSessionFactory();
        Session session2 = sessionFactory2.openSession();
        session2.beginTransaction();

        Criteria cr2 = session2.createCriteria(Pehar.class);

        lista_pehara = cr2.list();

        session2.getTransaction().commit();
        session2.close();
    }

    public void provera() {
        
        blok = false;

        datum = new java.sql.Date(odabrani_datum.getTime());
        boolean pomoc = false;

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria cr = session.createCriteria(Rezultati.class);
        List<Rezultati> rezultati = cr.list();

        session.getTransaction().commit();
        session.close();

        for (int i = 0; i < rezultati.size(); ++i) {
            if (rezultati.get(i).getDatum().compareTo(datum) == 0) {
                pomoc = true;
            }
        }

        if (pomoc) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ne može da se menja igra na ovaj datum!", null));
            odabrani_datum = null;
            datum = null;
            blok = true;
            return;
        }

        SessionFactory sessionFactory1 = NewHibernateUtil.getSessionFactory();
        Session session1 = sessionFactory1.openSession();
        session1.beginTransaction();

        Criteria cr1 = session1.createCriteria(IgraDana.class);
        IgraDana igra = (IgraDana) cr1.add(Restrictions.eq("datum", datum)).uniqueResult();

        session1.getTransaction().commit();
        session1.close();

        if (igra != null) {
            flag_provera = true;
            azuriraj = false;
            ne_azuriraj = false;
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Igra već postoji za ovaj dan, možete da je ažurirate ako želite!", null));
            return;
        }

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Igra ne postoji za ovaj dan, možete je napraviti!", null));
        flag_provera = true;
        napravi = false;

    }

    public void napravi() {
        
        blok = true;

        datum = new java.sql.Date(odabrani_datum.getTime());

        SessionFactory sessionFactory2 = NewHibernateUtil.getSessionFactory();
        Session session2 = sessionFactory2.openSession();
        session2.beginTransaction();

        IgraDana igra2 = new IgraDana();

        igra2.setId_anagram(id_anagram);
        igra2.setId_pehar(id_pehar);
        igra2.setId_vesala(id_vesala);
        igra2.setDatum(datum);

        session2.save(igra2);

        session2.getTransaction().commit();
        session2.close();

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Igra uspešno dodata!", null));

        flag_pehar = false;
        flag_anagram = false;
        flag_vesala = false;
        napravi = true;
        flag_provera = false;
        datum = null;
        odabrani_datum = null;

    }

    public void azuriraj() {
        
        blok = true;
        
        SessionFactory sessionFactory1 = NewHibernateUtil.getSessionFactory();
        Session session1 = sessionFactory1.openSession();
        session1.beginTransaction();

        Criteria cr1 = session1.createCriteria(IgraDana.class);
        IgraDana igra = (IgraDana) cr1.add(Restrictions.eq("datum", datum)).uniqueResult();

        igra.setId_anagram(id_anagram);
        igra.setId_pehar(id_pehar);
        igra.setId_vesala(id_vesala);

        session1.update(igra);

        session1.getTransaction().commit();
        session1.close();

        flag_anagram = false;
        flag_pehar = false;
        flag_vesala = false;
        odabrani_datum = null;
        azuriraj = true;
        ne_azuriraj = true;
        flag_provera = false;
        datum = null;

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Igra uspešno ažurirana!", null));
    }

    public void ne_azuriraj() {
        
        blok = true;
        
        azuriraj = true;
        ne_azuriraj = true;
        flag_provera = false;
        odabrani_datum = null;
        datum = null;

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Igra nije ažurirana!", null));
    }

    public void odobri(Korisnik korisnik) {

        lista_zahteva.remove(korisnik);

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(korisnik);

        session.getTransaction().commit();
        session.close();

        IgraoDanas id = new IgraoDanas();
        id.setDatum(new Date(System.currentTimeMillis()));
        id.setUsername(korisnik.getUsername());
        id.setDa_li_je_igrao(0);

        SessionFactory sessionFactory1 = NewHibernateUtil.getSessionFactory();
        Session s = sessionFactory1.openSession();
        s.beginTransaction();

        s.save(id);

        s.getTransaction().commit();
        s.close();
    }

    public void odbi(Korisnik korisnik) {
        lista_zahteva.remove(korisnik);
    }
    
    public void izaberi_anagram(int id) {
        flag_anagram = true;
        id_anagram = id;
    }

    public void izaberi_vesala(int id) {
        flag_vesala = true;
        id_vesala = id;
    }

    public void izaberi_pehar(int id) {
        flag_pehar = true;
        id_pehar = id;
    }

    public ArrayList<Korisnik> getLista_zahteva() {
        return lista_zahteva;
    }

    public void setLista_zahteva(ArrayList<Korisnik> lista_zahteva) {
        AdministratorController.lista_zahteva = lista_zahteva;
    }

    public boolean isNapravi() {
        return napravi;
    }

    public void setNapravi(boolean napravi) {
        this.napravi = napravi;
    }

    public boolean isAzuriraj() {
        return azuriraj;
    }

    public void setAzuriraj(boolean azuriraj) {
        this.azuriraj = azuriraj;
    }

    public boolean isNe_azuriraj() {
        return ne_azuriraj;
    }

    public void setNe_azuriraj(boolean ne_azuriraj) {
        this.ne_azuriraj = ne_azuriraj;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public boolean isFlag_provera() {
        return flag_provera;
    }

    public void setFlag_provera(boolean flag_provera) {
        this.flag_provera = flag_provera;
    }

    public Date getTrenutni_datum() {
        return trenutni_datum;
    }

    public void setTrenutni_datum(Date trenutni_datum) {
        this.trenutni_datum = trenutni_datum;
    }

    public java.util.Date getOdabrani_datum() {
        return odabrani_datum;
    }

    public void setOdabrani_datum(java.util.Date odabrani_datum) {
        this.odabrani_datum = odabrani_datum;
    }

    public int getId_anagram() {
        return id_anagram;
    }

    public void setId_anagram(int id_anagram) {
        this.id_anagram = id_anagram;
    }

    public boolean isBlok() {
        return blok;
    }

    public void setBlok(boolean blok) {
        this.blok = blok;
    }

    public int getId_vesala() {
        return id_vesala;
    }

    public void setId_vesala(int id_vesala) {
        this.id_vesala = id_vesala;
    }

    public int getId_pehar() {
        return id_pehar;
    }

    public void setId_pehar(int id_pehar) {
        this.id_pehar = id_pehar;
    }

    public boolean isFlag_anagram() {
        return flag_anagram;
    }

    public void setFlag_anagram(boolean flag_anagram) {
        this.flag_anagram = flag_anagram;
    }

    public boolean isFlag_vesala() {
        return flag_vesala;
    }

    public void setFlag_vesala(boolean flag_vesala) {
        this.flag_vesala = flag_vesala;
    }

    public boolean isFlag_pehar() {
        return flag_pehar;
    }

    public void setFlag_pehar(boolean flag_pehar) {
        this.flag_pehar = flag_pehar;
    }

    public List<Anagram> getLista_anagrama() {
        return lista_anagrama;
    }

    public void setLista_anagrama(List<Anagram> lista_anagrama) {
        this.lista_anagrama = lista_anagrama;
    }

    public List<Vesanje> getLista_vesala() {
        return lista_vesala;
    }

    public void setLista_vesala(List<Vesanje> lista_vesala) {
        this.lista_vesala = lista_vesala;
    }

    public List<Pehar> getLista_pehara() {
        return lista_pehara;
    }

    public void setLista_pehara(List<Pehar> lista_pehara) {
        this.lista_pehara = lista_pehara;
    }
}
