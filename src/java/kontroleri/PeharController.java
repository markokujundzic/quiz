/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import db.NewHibernateUtil;
import entiteti.Korisnik;
import entiteti.Pehar;
import entiteti.Rezultati;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Named(value = "pehar")
public class PeharController implements Serializable {

    private ArrayList<String> pitanja = new ArrayList<String>();

    private int broj_poena = 0;

    private int index = 0;

    private int pehar_tajmer = 30;

    private String trenutno_pitanje;

    private boolean dugme1 = true;
    private boolean dugme2 = true;
    private boolean dugme3 = true;
    private boolean dugme4 = true;
    private boolean dugme5 = true;
    private boolean dugme6 = true;
    private boolean dugme7 = true;
    private boolean dugme8 = true;
    private boolean dugme9 = true;
    private boolean dugme10 = true;
    private boolean dugme11 = true;
    private boolean dugme12 = true;
    private boolean dugme13 = true;
    private boolean dugme_kraj = true;

    private String odgovor1 = "";
    private String odgovor2 = "";
    private String odgovor3 = "";
    private String odgovor4 = "";
    private String odgovor5 = "";
    private String odgovor6 = "";
    private String odgovor7 = "";
    private String odgovor8 = "";
    private String odgovor9 = "";
    private String odgovor10 = "";
    private String odgovor11 = "";
    private String odgovor12 = "";
    private String odgovor13 = "";

    private boolean pogresno1 = false;
    private boolean pogresno2 = false;
    private boolean pogresno3 = false;
    private boolean pogresno4 = false;
    private boolean pogresno5 = false;
    private boolean pogresno6 = false;
    private boolean pogresno7 = false;
    private boolean pogresno8 = false;
    private boolean pogresno9 = false;
    private boolean pogresno10 = false;
    private boolean pogresno11 = false;
    private boolean pogresno12 = false;
    private boolean pogresno13 = false;

    public PeharController() {

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria cr = session.createCriteria(Pehar.class);
        Pehar pehar = (Pehar) cr.add(Restrictions.eq("id", IgraDanaController.id_pehar)).uniqueResult();

        session.getTransaction().commit();
        session.close();

        pitanja.add(pehar.getPitanje_broj_1());
        pitanja.add(pehar.getPitanje_broj_2());
        pitanja.add(pehar.getPitanje_broj_3());
        pitanja.add(pehar.getPitanje_broj_4());
        pitanja.add(pehar.getPitanje_broj_5());
        pitanja.add(pehar.getPitanje_broj_6());
        pitanja.add(pehar.getPitanje_broj_7());
        pitanja.add(pehar.getPitanje_broj_8());
        pitanja.add(pehar.getPitanje_broj_9());
        pitanja.add(pehar.getPitanje_broj_10());
        pitanja.add(pehar.getPitanje_broj_11());
        pitanja.add(pehar.getPitanje_broj_12());
        pitanja.add(pehar.getPitanje_broj_13());

        trenutno_pitanje = pitanja.get(index);

        dugme1 = false;
    }

    public void kreni() {
        if (pehar_tajmer > 0) {
            --pehar_tajmer;
        }
        if (pehar_tajmer == 0) {
            switch (index) {
                case 0:
                    odgovori1();
                    break;
                case 1:
                    odgovori2();
                    break;
                case 2:
                    odgovori3();
                    break;
                case 3:
                    odgovori4();
                    break;
                case 4:
                    odgovori5();
                    break;
                case 5:
                    odgovori6();
                    break;
                case 6:
                    odgovori7();
                    break;
                case 7:
                    odgovori8();
                    break;
                case 8:
                    odgovori9();
                    break;
                case 9:
                    odgovori10();
                    break;
                case 10:
                    odgovori11();
                    break;
                case 11:
                    odgovori12();
                    break;
                case 12:
                    odgovori13();
                    break;
            }
        }
    }

    public void kraj() {

        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession s = (HttpSession) fc.getExternalContext().getSession(false);
        Korisnik trenutni = (Korisnik) s.getAttribute("user");

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria cr = session.createCriteria(Rezultati.class);
        Rezultati rezultat = (Rezultati) cr.add(Restrictions.eq("username", trenutni.getUsername())).add(Restrictions.eq("datum", new Date(System.currentTimeMillis()))).uniqueResult();

        rezultat.setPehar(broj_poena);
        rezultat.setUkupno(rezultat.getUkupno() + broj_poena);

        session.saveOrUpdate(rezultat);

        session.getTransaction().commit();
        session.close();

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/igrac_poeni.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void odgovori1() {

        pehar_tajmer = 30;

        ++index;

        dugme1 = true;
        dugme2 = false;

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria cr = session.createCriteria(Pehar.class);
        Pehar pehar = (Pehar) cr.add(Restrictions.eq("id", IgraDanaController.id_pehar)).uniqueResult();

        session.getTransaction().commit();
        session.close();

        if (odgovor1.equals(pehar.getOdgovor_broj_1())) {
            broj_poena += 2;
        } else {
            odgovor1 = pehar.getOdgovor_broj_1();
            pogresno1 = true;
        }

        trenutno_pitanje = pitanja.get(index);
    }

    public void odgovori2() {

        pehar_tajmer = 30;

        ++index;

        dugme2 = true;
        dugme3 = false;

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria cr = session.createCriteria(Pehar.class);
        Pehar pehar = (Pehar) cr.add(Restrictions.eq("id", IgraDanaController.id_pehar)).uniqueResult();

        session.getTransaction().commit();
        session.close();

        if (odgovor2.equals(pehar.getOdgovor_broj_2())) {
            broj_poena += 2;
        } else {
            odgovor2 = pehar.getOdgovor_broj_2();
            pogresno2 = true;
        }

        trenutno_pitanje = pitanja.get(index);
    }

    public void odgovori3() {

        pehar_tajmer = 30;

        ++index;

        dugme3 = true;
        dugme4 = false;

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria cr = session.createCriteria(Pehar.class);
        Pehar pehar = (Pehar) cr.add(Restrictions.eq("id", IgraDanaController.id_pehar)).uniqueResult();

        session.getTransaction().commit();
        session.close();

        if (odgovor3.equals(pehar.getOdgovor_broj_3())) {
            broj_poena += 2;
        } else {
            odgovor3 = pehar.getOdgovor_broj_3();
            pogresno3 = true;
        }

        trenutno_pitanje = pitanja.get(index);
    }

    public void odgovori4() {

        pehar_tajmer = 30;

        ++index;

        dugme4 = true;
        dugme5 = false;

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria cr = session.createCriteria(Pehar.class);
        Pehar pehar = (Pehar) cr.add(Restrictions.eq("id", IgraDanaController.id_pehar)).uniqueResult();

        session.getTransaction().commit();
        session.close();

        if (odgovor5.equals(pehar.getOdgovor_broj_5())) {
            broj_poena += 2;
        } else {
            odgovor4 = pehar.getOdgovor_broj_4();
            pogresno4 = true;
        }

        trenutno_pitanje = pitanja.get(index);
    }

    public void odgovori5() {

        pehar_tajmer = 30;

        ++index;

        dugme5 = true;
        dugme6 = false;

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria cr = session.createCriteria(Pehar.class);
        Pehar pehar = (Pehar) cr.add(Restrictions.eq("id", IgraDanaController.id_pehar)).uniqueResult();

        session.getTransaction().commit();
        session.close();

        if (odgovor5.equals(pehar.getOdgovor_broj_5())) {
            broj_poena += 2;
        } else {
            odgovor5 = pehar.getOdgovor_broj_5();
            pogresno5 = true;
        }

        trenutno_pitanje = pitanja.get(index);
    }

    public void odgovori6() {

        pehar_tajmer = 30;

        ++index;

        dugme6 = true;
        dugme7 = false;

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria cr = session.createCriteria(Pehar.class);
        Pehar pehar = (Pehar) cr.add(Restrictions.eq("id", IgraDanaController.id_pehar)).uniqueResult();

        session.getTransaction().commit();
        session.close();

        if (odgovor6.equals(pehar.getOdgovor_broj_6())) {
            broj_poena += 2;
        } else {
            odgovor6 = pehar.getOdgovor_broj_6();
            pogresno6 = true;
        }

        trenutno_pitanje = pitanja.get(index);
    }

    public void odgovori7() {

        pehar_tajmer = 30;

        ++index;

        dugme7 = true;
        dugme8 = false;

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria cr = session.createCriteria(Pehar.class);
        Pehar pehar = (Pehar) cr.add(Restrictions.eq("id", IgraDanaController.id_pehar)).uniqueResult();

        session.getTransaction().commit();
        session.close();

        if (odgovor7.equals(pehar.getOdgovor_broj_7())) {
            broj_poena += 2;
        } else {
            odgovor7 = pehar.getOdgovor_broj_7();
            pogresno7 = true;
        }

        trenutno_pitanje = pitanja.get(index);
    }

    public void odgovori8() {

        pehar_tajmer = 30;

        ++index;

        dugme8 = true;
        dugme9 = false;

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria cr = session.createCriteria(Pehar.class);
        Pehar pehar = (Pehar) cr.add(Restrictions.eq("id", IgraDanaController.id_pehar)).uniqueResult();

        session.getTransaction().commit();
        session.close();

        if (odgovor8.equals(pehar.getOdgovor_broj_8())) {
            broj_poena += 2;
        } else {
            odgovor8 = pehar.getOdgovor_broj_8();
            pogresno8 = true;
        }

        trenutno_pitanje = pitanja.get(index);
    }

    public void odgovori9() {

        pehar_tajmer = 30;

        ++index;

        dugme9 = true;
        dugme10 = false;

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria cr = session.createCriteria(Pehar.class);
        Pehar pehar = (Pehar) cr.add(Restrictions.eq("id", IgraDanaController.id_pehar)).uniqueResult();

        session.getTransaction().commit();
        session.close();

        if (odgovor9.equals(pehar.getOdgovor_broj_9())) {
            broj_poena += 2;
        } else {
            odgovor9 = pehar.getOdgovor_broj_9();
            pogresno9 = true;
        }

        trenutno_pitanje = pitanja.get(index);
    }

    public void odgovori10() {

        pehar_tajmer = 30;

        ++index;

        dugme10 = true;
        dugme11 = false;

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria cr = session.createCriteria(Pehar.class);
        Pehar pehar = (Pehar) cr.add(Restrictions.eq("id", IgraDanaController.id_pehar)).uniqueResult();

        session.getTransaction().commit();
        session.close();

        if (odgovor10.equals(pehar.getOdgovor_broj_10())) {
            broj_poena += 2;
        } else {
            odgovor10 = pehar.getOdgovor_broj_10();
            pogresno10 = true;
        }

        trenutno_pitanje = pitanja.get(index);
    }

    public void odgovori11() {

        pehar_tajmer = 30;

        ++index;

        dugme11 = true;
        dugme12 = false;

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria cr = session.createCriteria(Pehar.class);
        Pehar pehar = (Pehar) cr.add(Restrictions.eq("id", IgraDanaController.id_pehar)).uniqueResult();

        session.getTransaction().commit();
        session.close();

        if (odgovor11.equals(pehar.getOdgovor_broj_11())) {
            broj_poena += 2;
        } else {
            odgovor11 = pehar.getOdgovor_broj_11();
            pogresno11 = true;
        }

        trenutno_pitanje = pitanja.get(index);
    }

    public void odgovori12() {

        pehar_tajmer = 30;

        ++index;

        dugme12 = true;
        dugme13 = false;

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria cr = session.createCriteria(Pehar.class);
        Pehar pehar = (Pehar) cr.add(Restrictions.eq("id", IgraDanaController.id_pehar)).uniqueResult();

        session.getTransaction().commit();
        session.close();

        if (odgovor12.equals(pehar.getOdgovor_broj_12())) {
            broj_poena += 2;
        } else {
            odgovor12 = pehar.getOdgovor_broj_12();
            pogresno12 = true;
        }

        trenutno_pitanje = pitanja.get(index);
    }

    public void odgovori13() {

        pehar_tajmer = 0;

        ++index;

        dugme13 = true;
        dugme_kraj = false;

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria cr = session.createCriteria(Pehar.class);
        Pehar pehar = (Pehar) cr.add(Restrictions.eq("id", IgraDanaController.id_pehar)).uniqueResult();

        session.getTransaction().commit();
        session.close();

        if (odgovor13.equals(pehar.getOdgovor_broj_13())) {
            broj_poena += 2;
        } else {
            odgovor13 = pehar.getOdgovor_broj_13();
            pogresno13 = true;
        }

        trenutno_pitanje = null;
    }

    public ArrayList<String> getPitanja() {
        return pitanja;
    }

    public void setPitanja(ArrayList<String> pitanja) {
        this.pitanja = pitanja;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTrenutno_pitanje() {
        return trenutno_pitanje;
    }

    public void setTrenutno_pitanje(String trenutno_pitanje) {
        this.trenutno_pitanje = trenutno_pitanje;
    }

    public String getOdgovor1() {
        return odgovor1;
    }

    public void setOdgovor1(String odgovor1) {
        this.odgovor1 = odgovor1;
    }

    public String getOdgovor2() {
        return odgovor2;
    }

    public void setOdgovor2(String odgovor2) {
        this.odgovor2 = odgovor2;
    }

    public String getOdgovor3() {
        return odgovor3;
    }

    public void setOdgovor3(String odgovor3) {
        this.odgovor3 = odgovor3;
    }

    public String getOdgovor4() {
        return odgovor4;
    }

    public void setOdgovor4(String odgovor4) {
        this.odgovor4 = odgovor4;
    }

    public String getOdgovor5() {
        return odgovor5;
    }

    public void setOdgovor5(String odgovor5) {
        this.odgovor5 = odgovor5;
    }

    public String getOdgovor6() {
        return odgovor6;
    }

    public void setOdgovor6(String odgovor6) {
        this.odgovor6 = odgovor6;
    }

    public String getOdgovor7() {
        return odgovor7;
    }

    public void setOdgovor7(String odgovor7) {
        this.odgovor7 = odgovor7;
    }

    public String getOdgovor8() {
        return odgovor8;
    }

    public void setOdgovor8(String odgovor8) {
        this.odgovor8 = odgovor8;
    }

    public String getOdgovor9() {
        return odgovor9;
    }

    public void setOdgovor9(String odgovor9) {
        this.odgovor9 = odgovor9;
    }

    public String getOdgovor10() {
        return odgovor10;
    }

    public void setOdgovor10(String odgovor10) {
        this.odgovor10 = odgovor10;
    }

    public String getOdgovor11() {
        return odgovor11;
    }

    public void setOdgovor11(String odgovor11) {
        this.odgovor11 = odgovor11;
    }

    public String getOdgovor12() {
        return odgovor12;
    }

    public void setOdgovor12(String odgovor12) {
        this.odgovor12 = odgovor12;
    }

    public String getOdgovor13() {
        return odgovor13;
    }

    public void setOdgovor13(String odgovor13) {
        this.odgovor13 = odgovor13;
    }

    public boolean isDugme1() {
        return dugme1;
    }

    public void setDugme1(boolean dugme1) {
        this.dugme1 = dugme1;
    }

    public boolean isDugme2() {
        return dugme2;
    }

    public void setDugme2(boolean dugme2) {
        this.dugme2 = dugme2;
    }

    public boolean isDugme3() {
        return dugme3;
    }

    public void setDugme3(boolean dugme3) {
        this.dugme3 = dugme3;
    }

    public boolean isDugme4() {
        return dugme4;
    }

    public void setDugme4(boolean dugme4) {
        this.dugme4 = dugme4;
    }

    public boolean isDugme5() {
        return dugme5;
    }

    public void setDugme5(boolean dugme5) {
        this.dugme5 = dugme5;
    }

    public boolean isPogresno2() {
        return pogresno2;
    }

    public void setPogresno2(boolean pogresno2) {
        this.pogresno2 = pogresno2;
    }

    public boolean isPogresno3() {
        return pogresno3;
    }

    public void setPogresno3(boolean pogresno3) {
        this.pogresno3 = pogresno3;
    }

    public boolean isPogresno4() {
        return pogresno4;
    }

    public void setPogresno4(boolean pogresno4) {
        this.pogresno4 = pogresno4;
    }

    public boolean isPogresno5() {
        return pogresno5;
    }

    public void setPogresno5(boolean pogresno5) {
        this.pogresno5 = pogresno5;
    }

    public boolean isPogresno6() {
        return pogresno6;
    }

    public void setPogresno6(boolean pogresno6) {
        this.pogresno6 = pogresno6;
    }

    public boolean isPogresno7() {
        return pogresno7;
    }

    public void setPogresno7(boolean pogresno7) {
        this.pogresno7 = pogresno7;
    }

    public boolean isPogresno8() {
        return pogresno8;
    }

    public void setPogresno8(boolean pogresno8) {
        this.pogresno8 = pogresno8;
    }

    public boolean isPogresno9() {
        return pogresno9;
    }

    public void setPogresno9(boolean pogresno9) {
        this.pogresno9 = pogresno9;
    }

    public boolean isPogresno10() {
        return pogresno10;
    }

    public void setPogresno10(boolean pogresno10) {
        this.pogresno10 = pogresno10;
    }

    public boolean isPogresno11() {
        return pogresno11;
    }

    public void setPogresno11(boolean pogresno11) {
        this.pogresno11 = pogresno11;
    }

    public boolean isPogresno12() {
        return pogresno12;
    }

    public void setPogresno12(boolean pogresno12) {
        this.pogresno12 = pogresno12;
    }

    public boolean isPogresno13() {
        return pogresno13;
    }

    public void setPogresno13(boolean pogresno13) {
        this.pogresno13 = pogresno13;
    }

    public boolean isDugme6() {
        return dugme6;
    }

    public void setDugme6(boolean dugme6) {
        this.dugme6 = dugme6;
    }

    public boolean isDugme7() {
        return dugme7;
    }

    public void setDugme7(boolean dugme7) {
        this.dugme7 = dugme7;
    }

    public boolean isDugme8() {
        return dugme8;
    }

    public void setDugme8(boolean dugme8) {
        this.dugme8 = dugme8;
    }

    public boolean isDugme9() {
        return dugme9;
    }

    public void setDugme9(boolean dugme9) {
        this.dugme9 = dugme9;
    }

    public boolean isDugme10() {
        return dugme10;
    }

    public void setDugme10(boolean dugme10) {
        this.dugme10 = dugme10;
    }

    public boolean isDugme11() {
        return dugme11;
    }

    public void setDugme11(boolean dugme11) {
        this.dugme11 = dugme11;
    }

    public boolean isDugme12() {
        return dugme12;
    }

    public void setDugme12(boolean dugme12) {
        this.dugme12 = dugme12;
    }

    public boolean isDugme13() {
        return dugme13;
    }

    public void setDugme13(boolean dugme13) {
        this.dugme13 = dugme13;
    }

    public boolean isDugme_kraj() {
        return dugme_kraj;
    }

    public void setDugme_kraj(boolean dugme_kraj) {
        this.dugme_kraj = dugme_kraj;
    }

    public int getPehar_tajmer() {
        return pehar_tajmer;
    }

    public void setPehar_tajmer(int pehar_tajmer) {
        this.pehar_tajmer = pehar_tajmer;
    }

    public int getBroj_poena() {
        return broj_poena;
    }

    public void setBroj_poena(int broj_poena) {
        this.broj_poena = broj_poena;
    }

    public boolean isPogresno1() {
        return pogresno1;
    }

    public void setPogresno1(boolean pogresno1) {
        this.pogresno1 = pogresno1;
    }
}
