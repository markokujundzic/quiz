/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import db.NewHibernateUtil;
import entiteti.Rezultati;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Marko
 */
@ManagedBean
@SessionScoped
@Named(value = "gost")
public class GostController implements Serializable {

    private List<Rezultati> tekuci_mesec = new ArrayList<Rezultati>();
    private List<Rezultati> poslednjih20_dana = new ArrayList<Rezultati>();

    public GostController() {

        List<Rezultati> pomocni = new ArrayList<Rezultati>();
        List<Rezultati> pomocni2 = new ArrayList<Rezultati>();

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria cr = session.createCriteria(Rezultati.class);

        tekuci_mesec = cr.list();
        poslednjih20_dana = cr.list();

        session.getTransaction().commit();
        session.close();

        for (int i = 0; i < tekuci_mesec.size(); ++i) {
            if (!tekuci_mesec.get(i).getDatum().toString().substring(0, 7).equals(new Date(System.currentTimeMillis()).toString().substring(0, 7))) {
                pomocni.add(tekuci_mesec.get(i));
            }
        }

        tekuci_mesec.removeAll(pomocni);

        tekuci_mesec.sort(Comparator.comparing(Rezultati::getUkupno).reversed());

        tekuci_mesec = tekuci_mesec.stream().limit(10).collect(Collectors.toList());

        java.util.Date trazeni = new java.util.Date(System.currentTimeMillis() - 20 * 1000 * 60 * 60 * 24);

        Date dvadeseti_dan_unazad = new Date(trazeni.getTime());

        for (int i = 0; i < poslednjih20_dana.size(); ++i) {
            if (poslednjih20_dana.get(i).getDatum().compareTo(dvadeseti_dan_unazad) < 0) {
                pomocni2.add(poslednjih20_dana.get(i));
            }
        }

        poslednjih20_dana.removeAll(pomocni2);

        poslednjih20_dana.sort(Comparator.comparing(Rezultati::getUkupno).reversed());

        poslednjih20_dana = poslednjih20_dana.stream().limit(10).collect(Collectors.toList());

    }

    public void update() {
        List<Rezultati> pomocni = new ArrayList<Rezultati>();
        List<Rezultati> pomocni2 = new ArrayList<Rezultati>();

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria cr = session.createCriteria(Rezultati.class);

        tekuci_mesec = cr.list();
        poslednjih20_dana = cr.list();

        session.getTransaction().commit();
        session.close();

        for (int i = 0; i < tekuci_mesec.size(); ++i) {
            if (!tekuci_mesec.get(i).getDatum().toString().substring(0, 7).equals(new Date(System.currentTimeMillis()).toString().substring(0, 7))) {
                pomocni.add(tekuci_mesec.get(i));
            }
        }

        tekuci_mesec.removeAll(pomocni);

        tekuci_mesec.sort(Comparator.comparing(Rezultati::getUkupno).reversed());

        tekuci_mesec = tekuci_mesec.stream().limit(10).collect(Collectors.toList());

        java.util.Date trazeni = new java.util.Date(System.currentTimeMillis() - 20 * 1000 * 60 * 60 * 24);

        Date dvadeseti_dan_unazad = new Date(trazeni.getTime());

        for (int i = 0; i < poslednjih20_dana.size(); ++i) {
            if (poslednjih20_dana.get(i).getDatum().compareTo(dvadeseti_dan_unazad) < 0) {
                pomocni2.add(poslednjih20_dana.get(i));
            }
        }

        poslednjih20_dana.removeAll(pomocni2);

        poslednjih20_dana.sort(Comparator.comparing(Rezultati::getUkupno).reversed());

        poslednjih20_dana = poslednjih20_dana.stream().limit(10).collect(Collectors.toList());
    }

    public List<Rezultati> getPoslednjih20_dana() {
        return poslednjih20_dana;
    }

    public void setPoslednjih20_dana(List<Rezultati> poslednjih20_dana) {
        this.poslednjih20_dana = poslednjih20_dana;
    }

    public List<Rezultati> getTekuci_mesec() {
        return tekuci_mesec;
    }

    public void setTekuci_mesec(List<Rezultati> tekuci_mesec) {
        this.tekuci_mesec = tekuci_mesec;
    }
}
