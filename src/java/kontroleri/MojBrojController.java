/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import db.NewHibernateUtil;
import entiteti.Korisnik;
import entiteti.Rezultati;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
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
@Named(value = "mojbroj")
@SessionScoped
public class MojBrojController implements Serializable {

    private int brojpoena = 0;
    private int moj_broj_tajmer = 60;
    private boolean kreni = false;

    private int trazeni_broj = 0;

    private String resenje = "";

    private boolean pocetak = true;

    private boolean flag_broj1 = false;
    private boolean flag_broj2 = false;
    private boolean flag_broj3 = false;
    private boolean flag_broj4 = false;
    private boolean flag_broj5 = false;
    private boolean flag_broj6 = false;

    private boolean flag_obrisi = true;
    private boolean flag_potvrdi = false;

    private int broj1;
    private int broj2;
    private int broj3;
    private int broj4;
    private int broj5;
    private int broj6;

    private boolean flag_generisi = false;

    private int odgovor;

    private boolean poslednji_unos_broj = false;

    private boolean isteklo_vreme = false;

    private ArrayList<String> simboli = new ArrayList<String>();

    private boolean tajmer_flag = true;

    public void kreni() {
        if (kreni) {
            if (tajmer_flag) {
                --moj_broj_tajmer;
                if (moj_broj_tajmer == 0) {
                    isteklo_vreme = true;
                    tajmer_flag = false;
                    if (resenje.equals("")) {
                        resenje = "0";
                    }
                }
            }
        }
    }

    public void pritisnutbroj1() {
        flag_obrisi = false;
        flag_broj1 = true;
        resenje += Integer.toString(broj1);
        poslednji_unos_broj = true;
        simboli.add(Integer.toString(broj1));
    }

    public void pritisnutbroj2() {
        flag_obrisi = false;
        flag_broj2 = true;
        resenje += Integer.toString(broj2);
        poslednji_unos_broj = true;
        simboli.add(Integer.toString(broj2));
    }

    public void pritisnutbroj3() {
        flag_obrisi = false;
        flag_broj3 = true;
        resenje += Integer.toString(broj3);
        poslednji_unos_broj = true;
        simboli.add(Integer.toString(broj3));
    }

    public void pritisnutbroj4() {
        flag_obrisi = false;
        flag_broj4 = true;
        resenje += Integer.toString(broj4);
        poslednji_unos_broj = true;
        simboli.add(Integer.toString(broj4));
    }

    public void pritisnutbroj5() {
        flag_obrisi = false;
        flag_broj5 = true;
        resenje += Integer.toString(broj5);
        poslednji_unos_broj = true;
        simboli.add(Integer.toString(broj5));
    }

    public void pritisnutbroj6() {
        flag_obrisi = false;
        flag_broj6 = true;
        resenje += Integer.toString(broj6);
        poslednji_unos_broj = true;
        simboli.add(Integer.toString(broj6));
    }

    public void pritisnut_plus() {
        flag_obrisi = false;
        resenje += "+";
        poslednji_unos_broj = false;
        simboli.add("+");
    }

    public void pritisnut_minus() {
        flag_obrisi = false;
        resenje += "-";
        poslednji_unos_broj = false;
        simboli.add("-");
    }

    public void pritisnuto_puta() {
        flag_obrisi = false;
        resenje += "*";
        poslednji_unos_broj = false;
        simboli.add("*");
    }

    public void pritisnuto_podeljeno() {
        flag_obrisi = false;
        resenje += "/";
        poslednji_unos_broj = false;
        simboli.add("/");
    }

    public void pritisnuta_otvorena_zagrada() {
        flag_obrisi = false;
        resenje += "(";
        poslednji_unos_broj = false;
        simboli.add("(");
    }

    public void pritisnuta_zatvorena_zagrada() {
        flag_obrisi = false;
        resenje += ")";
        poslednji_unos_broj = false;
        simboli.add(")");
    }

    public void stop() {

        pocetak = false;

        kreni = true;

        flag_broj1 = false;
        flag_broj2 = false;
        flag_broj3 = false;
        flag_broj4 = false;
        flag_broj5 = false;
        flag_broj6 = false;

        Random random = new Random();

        int broj1_generator = 0;
        int broj2_generator = 0;
        int broj3_generator = 0;
        int broj4_generator = 0;
        int broj5_generator = 0;
        int broj6_generator = 0;
        int trazeni_broj_generator = 0;

        while (true) {
            broj1_generator = random.nextInt(10);
            if (broj1_generator != 0) {
                break;
            }
        }

        while (true) {
            broj2_generator = random.nextInt(10);
            if (broj2_generator != 0) {
                break;
            }
        }

        while (true) {
            broj3_generator = random.nextInt(10);
            if (broj3_generator != 0) {
                break;
            }
        }

        while (true) {
            broj4_generator = random.nextInt(10);
            if (broj4_generator != 0) {
                break;
            }
        }

        broj1 = broj1_generator;
        broj2 = broj2_generator;
        broj3 = broj3_generator;
        broj4 = broj4_generator;

        broj5_generator = random.nextInt(3);

        if (broj5_generator == 0) {
            broj5 = 10;
        }
        if (broj5_generator == 1) {
            broj5 = 15;
        }
        if (broj5_generator == 2) {
            broj5 = 20;
        }

        broj6_generator = random.nextInt(4);

        if (broj6_generator == 0) {
            broj6 = 25;
        }
        if (broj6_generator == 1) {
            broj6 = 50;
        }
        if (broj6_generator == 2) {
            broj6 = 75;
        }
        if (broj6_generator == 3) {
            broj6 = 100;
        }

        while (true) {
            trazeni_broj_generator = random.nextInt(1000);
            if (trazeni_broj_generator != 0) {
                break;
            }
        }

        trazeni_broj = trazeni_broj_generator;

        flag_generisi = true;
    }

    public void resi() {
        
        moj_broj_tajmer = 0;
        tajmer_flag = false;
        flag_potvrdi = true;

        if (resenje.equals("")) {
            resenje = "0";
        }
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        try {
            odgovor = (int) engine.eval(resenje);
        } catch (ScriptException ex) {
            Logger.getLogger(MojBrojController.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Loš rezultat zbog nedozvoljenog redosleda operacija, 0 poena!", null));

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("faces/vesanje.xhtml");
            } catch (IOException exp) {
                Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, exp);
            }
        }

        if (odgovor == trazeni_broj) {
            brojpoena = 10;
        }

        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession s = (HttpSession) fc.getExternalContext().getSession(false);
        Korisnik trenutni = (Korisnik) s.getAttribute("user");

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria cr = session.createCriteria(Rezultati.class);
        Rezultati rezultat = (Rezultati) cr.add(Restrictions.eq("username", trenutni.getUsername())).add(Restrictions.eq("datum", new Date(System.currentTimeMillis()))).uniqueResult();

        rezultat.setMojbroj(brojpoena);
        rezultat.setUkupno(rezultat.getUkupno() + brojpoena);

        session.saveOrUpdate(rezultat);

        session.getTransaction().commit();
        session.close();

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/vesanje.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void obrisi() {

        poslednji_unos_broj = false;

        String pomocni = simboli.remove(simboli.size() - 1);

        if (pomocni.equals(Integer.toString(broj1))) {
            flag_broj1 = false;
        }
        if (pomocni.equals(Integer.toString(broj2))) {
            flag_broj2 = false;
        }
        if (pomocni.equals(Integer.toString(broj3))) {
            flag_broj3 = false;
        }
        if (pomocni.equals(Integer.toString(broj4))) {
            flag_broj4 = false;
        }
        if (pomocni.equals(Integer.toString(broj5))) {
            flag_broj5 = false;
        }
        if (pomocni.equals(Integer.toString(broj6))) {
            flag_broj6 = false;
        }

        if (pomocni.equals("10")) {
            resenje = resenje.substring(0, resenje.length() - 2);
            return;
        }

        if (pomocni.equals("15")) {
            resenje = resenje.substring(0, resenje.length() - 2);
            return;
        }

        if (pomocni.equals("20")) {
            resenje = resenje.substring(0, resenje.length() - 2);
            return;
        }

        if (pomocni.equals("25")) {
            resenje = resenje.substring(0, resenje.length() - 2);
            return;
        }

        if (pomocni.equals("50")) {
            resenje = resenje.substring(0, resenje.length() - 2);
            return;
        }

        if (pomocni.equals("75")) {
            resenje = resenje.substring(0, resenje.length() - 2);
            return;
        }

        if (pomocni.equals("100")) {
            resenje = resenje.substring(0, resenje.length() - 3);
            return;
        }

        resenje = resenje.substring(0, resenje.length() - 1);
    }

    public boolean isPocetak() {
        return pocetak;
    }

    public boolean isIsteklo_vreme() {
        return isteklo_vreme;
    }

    public void setIsteklo_vreme(boolean isteklo_vreme) {
        this.isteklo_vreme = isteklo_vreme;
    }

    public void setPocetak(boolean pocetak) {
        this.pocetak = pocetak;
    }

    public ArrayList<String> getSimboli() {
        return simboli;
    }

    public void setSimboli(ArrayList<String> simboli) {
        this.simboli = simboli;
    }

    public boolean isPoslednji_unos_broj() {
        return poslednji_unos_broj;
    }

    public void setPoslednji_unos_broj(boolean poslednji_unos_broj) {
        this.poslednji_unos_broj = poslednji_unos_broj;
    }

    public boolean isFlag_potvrdi() {
        return flag_potvrdi;
    }

    public void setFlag_potvrdi(boolean flag_potvrdi) {
        this.flag_potvrdi = flag_potvrdi;
    }

    public boolean isFlag_obrisi() {
        return flag_obrisi;
    }

    public void setFlag_obrisi(boolean flag_obrisi) {
        this.flag_obrisi = flag_obrisi;
    }

    public int getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(int odgovor) {
        this.odgovor = odgovor;
    }

    public String getResenje() {
        return resenje;
    }

    public void setResenje(String resenje) {
        this.resenje = resenje;
    }

    public boolean isTajmer_flag() {
        return tajmer_flag;
    }

    public void setTajmer_flag(boolean tajmer_flag) {
        this.tajmer_flag = tajmer_flag;
    }

    public boolean isFlag_generisi() {
        return flag_generisi;
    }

    public void setFlag_generisi(boolean flag_generisi) {
        this.flag_generisi = flag_generisi;
    }

    public int getTrazeni_broj() {
        return trazeni_broj;
    }

    public void setTrazeni_broj(int trazeni_broj) {
        this.trazeni_broj = trazeni_broj;
    }

    public boolean isFlag_broj1() {
        return flag_broj1;
    }

    public void setFlag_broj1(boolean flag_broj1) {
        this.flag_broj1 = flag_broj1;
    }

    public boolean isFlag_broj2() {
        return flag_broj2;
    }

    public void setFlag_broj2(boolean flag_broj2) {
        this.flag_broj2 = flag_broj2;
    }

    public boolean isFlag_broj3() {
        return flag_broj3;
    }

    public void setFlag_broj3(boolean flag_broj3) {
        this.flag_broj3 = flag_broj3;
    }

    public boolean isFlag_broj4() {
        return flag_broj4;
    }

    public void setFlag_broj4(boolean flag_broj4) {
        this.flag_broj4 = flag_broj4;
    }

    public boolean isFlag_broj5() {
        return flag_broj5;
    }

    public void setFlag_broj5(boolean flag_broj5) {
        this.flag_broj5 = flag_broj5;
    }

    public boolean isFlag_broj6() {
        return flag_broj6;
    }

    public void setFlag_broj6(boolean flag_broj6) {
        this.flag_broj6 = flag_broj6;
    }

    public int getBroj1() {
        return broj1;
    }

    public void setBroj1(int broj1) {
        this.broj1 = broj1;
    }

    public int getBroj2() {
        return broj2;
    }

    public void setBroj2(int broj2) {
        this.broj2 = broj2;
    }

    public int getBroj3() {
        return broj3;
    }

    public void setBroj3(int broj3) {
        this.broj3 = broj3;
    }

    public int getBroj4() {
        return broj4;
    }

    public void setBroj4(int broj4) {
        this.broj4 = broj4;
    }

    public int getBroj5() {
        return broj5;
    }

    public void setBroj5(int broj5) {
        this.broj5 = broj5;
    }

    public int getBroj6() {
        return broj6;
    }

    public void setBroj6(int broj6) {
        this.broj6 = broj6;
    }

    public int getBrojpoena() {
        return brojpoena;
    }

    public void setBrojpoena(int brojpoena) {
        this.brojpoena = brojpoena;
    }

    public int getMoj_broj_tajmer() {
        return moj_broj_tajmer;
    }

    public void setMoj_broj_tajmer(int moj_broj_tajmer) {
        this.moj_broj_tajmer = moj_broj_tajmer;
    }

    public boolean isKreni() {
        return kreni;
    }

    public void setKreni(boolean kreni) {
        this.kreni = kreni;
    }
}
