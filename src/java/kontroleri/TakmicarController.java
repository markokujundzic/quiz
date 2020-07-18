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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Marko
 */
@ManagedBean
@SessionScoped
@Named(value = "takmicar")
public class TakmicarController implements Serializable {

    private List<Rezultati> rezultati = new ArrayList<Rezultati>();
    private List<Rezultati> finalna_lista = new ArrayList<Rezultati>();

    public TakmicarController() {

        boolean flag = false;

        List<Rezultati> pomocni = new ArrayList<Rezultati>();

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria cr = session.createCriteria(Rezultati.class);

        rezultati = cr.list();

        session.getTransaction().commit();
        session.close();

        for (int i = 0; i < rezultati.size(); ++i) {
            if (rezultati.get(i).getDatum().compareTo(Date.valueOf(LocalDate.now())) != 0) {
                pomocni.add(rezultati.get(i));
            }
        }

        rezultati.removeAll(pomocni);

        rezultati.sort(Comparator.comparing(Rezultati::getUkupno).reversed());

        finalna_lista = rezultati;

        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession s = (HttpSession) fc.getExternalContext().getSession(false);
        Korisnik trenutni = (Korisnik) s.getAttribute("user");

        if (rezultati.size() > 10) {
            for (int i = 0; i < rezultati.size(); ++i) {
                if (rezultati.get(i).getUsername().equals(trenutni.getUsername())) {
                    if (rezultati.indexOf(rezultati.get(i)) > 10) {
                        rezultati.add(10, rezultati.get(i));
                        finalna_lista = rezultati.stream().limit(11).collect(Collectors.toList());
                        flag = true;
                        break;
                    } else {
                        finalna_lista = rezultati.stream().limit(10).collect(Collectors.toList());
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) {
                finalna_lista = rezultati.stream().limit(10).collect(Collectors.toList());
            }
        }
    }

    public void update() {
        boolean flag = false;

        List<Rezultati> pomocni = new ArrayList<Rezultati>();

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria cr = session.createCriteria(Rezultati.class);

        rezultati = cr.list();

        session.getTransaction().commit();
        session.close();

        for (int i = 0; i < rezultati.size(); ++i) {
            if (rezultati.get(i).getDatum().compareTo(Date.valueOf(LocalDate.now())) != 0) {
                pomocni.add(rezultati.get(i));
            }
        }

        rezultati.removeAll(pomocni);

        rezultati.sort(Comparator.comparing(Rezultati::getUkupno).reversed());

        finalna_lista = rezultati;

        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession s = (HttpSession) fc.getExternalContext().getSession(false);
        Korisnik trenutni = (Korisnik) s.getAttribute("user");

        if (rezultati.size() > 10) {
            for (int i = 0; i < rezultati.size(); ++i) {
                if (rezultati.get(i).getUsername().equals(trenutni.getUsername())) {
                    if (rezultati.indexOf(rezultati.get(i)) > 10) {
                        rezultati.add(10, rezultati.get(i));
                        finalna_lista = rezultati.stream().limit(11).collect(Collectors.toList());
                        flag = true;
                        break;
                    } else {
                        finalna_lista = rezultati.stream().limit(10).collect(Collectors.toList());
                        flag = true;
                        break;
                    }
                }
            }
            if (!flag) {
                finalna_lista = rezultati.stream().limit(10).collect(Collectors.toList());
            }
        }
    }

    public List<Rezultati> getFinalna_lista() {
        return finalna_lista;
    }

    public void setFinalna_lista(List<Rezultati> finalna_lista) {
        this.finalna_lista = finalna_lista;
    }

    public List<Rezultati> getRezultati() {
        return rezultati;
    }

    public void setRezultati(ArrayList<Rezultati> rezultati) {
        this.rezultati = rezultati;
    }
}
