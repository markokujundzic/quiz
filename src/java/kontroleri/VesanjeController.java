/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import db.NewHibernateUtil;
import entiteti.Korisnik;
import entiteti.Rezultati;
import entiteti.Vesanje;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
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
@Named(value = "vesanje")
@SessionScoped
public class VesanjeController implements Serializable {

    private int broj_poena = 0;
    private String slika = "resources/vesala1.jpg";
    private String uneseno_slovo = "";
    private String generisana_rec;
    private String sakrivena_rec = "";
    private int broj_pogresnih_pokusaja = 0;
    private int broj_tacnih_pokusaja = 0;
    private boolean kraj = true;

    private boolean flag1;
    private boolean flag2;
    private boolean flag3;
    private boolean flag4;
    private boolean flag5;
    private boolean flag6;
    private boolean flag7;
    private boolean flag8;
    private boolean flag9;
    private boolean flag10;
    private boolean flag11;
    private boolean flag12;
    private boolean flag13;
    private boolean flag14;
    private boolean flag15;
    private boolean flag16;
    private boolean flag17;
    private boolean flag18;
    private boolean flag19;
    private boolean flag20;
    private boolean flag21;
    private boolean flag22;
    private boolean flag23;
    private boolean flag24;
    private boolean flag25;
    private boolean flag26;
    private boolean flag27;
    private boolean flag28;
    private boolean flag29;
    private boolean flag30;

    private ArrayList<String> slova = new ArrayList<String>();
    
    private boolean promena = false;

    public VesanjeController() {

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria cr = session.createCriteria(Vesanje.class);
        Vesanje vesanje = (Vesanje) cr.add(Restrictions.eq("id", IgraDanaController.id_vesala)).uniqueResult();

        session.getTransaction().commit();
        session.close();

        generisana_rec = vesanje.getRec_koja_se_pogadja();

        for (int i = 0; i < generisana_rec.length(); ++i) {
            if ((i + 1 <= generisana_rec.length() - 1) && generisana_rec.charAt(i) == 'l' && generisana_rec.charAt(i + 1) == 'j') {
                String pomocni = "";
                pomocni += generisana_rec.charAt(i);
                pomocni += generisana_rec.charAt(i + 1);
                slova.add(pomocni);
                i++;
            } else if ((i + 1 <= generisana_rec.length() - 1) && generisana_rec.charAt(i) == 'n' && generisana_rec.charAt(i + 1) == 'j') {
                String pomocni = "";
                pomocni += generisana_rec.charAt(i);
                pomocni += generisana_rec.charAt(i + 1);
                slova.add(pomocni);
                i++;
            } else if ((i + 1 <= generisana_rec.length() - 1) && generisana_rec.charAt(i) == 'd' && generisana_rec.charAt(i + 1) == 'ž') {
                String pomocni = "";
                pomocni += generisana_rec.charAt(i);
                pomocni += generisana_rec.charAt(i + 1);
                slova.add(pomocni);
                i++;
            } else {
                slova.add(Character.toString(generisana_rec.charAt(i)));
            }
        }

        for (int i = 0; i < slova.size(); ++i) {
            sakrivena_rec += "__ ";
        }
        sakrivena_rec = sakrivena_rec.substring(0, sakrivena_rec.length() - 1);
    }

    public void unesi() {

        switch (uneseno_slovo) {
            case "a":
                flag1 = true;
                break;
            case "b":
                flag2 = true;
                break;
            case "c":
                flag3 = true;
                break;
            case "č":
                flag4 = true;
                break;
            case "ć":
                flag5 = true;
                break;
            case "d":
                flag6 = true;
                break;
            case "dž":
                flag7 = true;
                break;
            case "đ":
                flag8 = true;
                break;
            case "e":
                flag9 = true;
                break;
            case "f":
                flag10 = true;
                break;
            case "g":
                flag11 = true;
                break;
            case "h":
                flag12 = true;
                break;
            case "i":
                flag13 = true;
                break;
            case "j":
                flag14 = true;
                break;
            case "k":
                flag15 = true;
                break;
            case "l":
                flag16 = true;
                break;
            case "lj":
                flag17 = true;
                break;
            case "m":
                flag18 = true;
                break;
            case "n":
                flag19 = true;
                break;
            case "nj":
                flag20 = true;
                break;
            case "o":
                flag21 = true;
                break;
            case "p":
                flag22 = true;
                break;
            case "r":
                flag23 = true;
                break;
            case "s":
                flag24 = true;
                break;
            case "š":
                flag25 = true;
                break;
            case "t":
                flag26 = true;
                break;
            case "u":
                flag27 = true;
                break;
            case "v":
                flag28 = true;
                break;
            case "z":
                flag29 = true;
                break;
            case "ž":
                flag30 = true;
                break;
        }
        for (int i = 0; i < slova.size(); ++i) {

            if (uneseno_slovo.equals(slova.get(i))) {

                ++broj_tacnih_pokusaja;

                String pomocni = sakrivena_rec;

                if (uneseno_slovo.length() == 2) {
                    if (i == 0) {
                        sakrivena_rec = uneseno_slovo.concat(pomocni.substring(2));
                    } else {
                        sakrivena_rec = pomocni.substring(0, 3 * i).concat(uneseno_slovo).concat(pomocni.substring(3 * i + 2));
                    }
                } else {
                    if (i == 0) {
                        sakrivena_rec = uneseno_slovo.concat(" ").concat(pomocni.substring(2));
                    } else {
                        sakrivena_rec = pomocni.substring(0, 3 * i).concat(uneseno_slovo).concat(" ").concat(pomocni.substring(3 * i + 2));
                    }
                }
            }
        }

        if (!slova.contains(uneseno_slovo)) {
            ++broj_pogresnih_pokusaja;
        }

        switch (broj_pogresnih_pokusaja) {
            case 1:
                slika = "resources/vesala2.jpg";
                break;
            case 2:
                slika = "resources/vesala3.jpg";
                break;
            case 3:
                slika = "resources/vesala4.jpg";
                break;
            case 4:
                slika = "resources/vesala5.jpg";
                break;
        }
        
        if (broj_pogresnih_pokusaja == 4) {
            String pomocni = "";
            for (int i = 0; i < slova.size(); ++i) {
                pomocni += slova.get(i);
                pomocni += " ";
            }
            promena = true;
            pomocni = pomocni.substring(0, pomocni.length() - 1);
            
            generisana_rec = pomocni;
        }

        if (broj_pogresnih_pokusaja == 4 || broj_tacnih_pokusaja == slova.size()) {
            kraj = false;
        }
        if (broj_tacnih_pokusaja == slova.size()) {
            broj_poena = 10;
        }

        uneseno_slovo = null;
    }

    public String sudija_svira_kraj() {

        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession s = (HttpSession) fc.getExternalContext().getSession(false);
        Korisnik trenutni = (Korisnik) s.getAttribute("user");

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria cr = session.createCriteria(Rezultati.class
        );
        Rezultati rezultat = (Rezultati) cr.add(Restrictions.eq("username", trenutni.getUsername())).add(Restrictions.eq("datum", new Date(System.currentTimeMillis()))).uniqueResult();

        rezultat.setVesala(broj_poena);
        rezultat.setUkupno(rezultat.getUkupno() + broj_poena);

        session.saveOrUpdate(rezultat);

        session.getTransaction().commit();
        session.close();

        return "zanimljiva_geografija?faces-redirect=true";
    }

    public boolean isFlag1() {
        return flag1;
    }

    public void setFlag1(boolean flag1) {
        this.flag1 = flag1;
    }

    public boolean isFlag2() {
        return flag2;
    }

    public void setFlag2(boolean flag2) {
        this.flag2 = flag2;
    }

    public boolean isFlag3() {
        return flag3;
    }

    public void setFlag3(boolean flag3) {
        this.flag3 = flag3;
    }

    public boolean isFlag4() {
        return flag4;
    }

    public void setFlag4(boolean flag4) {
        this.flag4 = flag4;
    }

    public boolean isFlag5() {
        return flag5;
    }

    public void setFlag5(boolean flag5) {
        this.flag5 = flag5;
    }

    public boolean isFlag6() {
        return flag6;
    }

    public void setFlag6(boolean flag6) {
        this.flag6 = flag6;
    }

    public boolean isFlag7() {
        return flag7;
    }

    public void setFlag7(boolean flag7) {
        this.flag7 = flag7;
    }

    public boolean isFlag8() {
        return flag8;
    }

    public void setFlag8(boolean flag8) {
        this.flag8 = flag8;
    }

    public boolean isFlag9() {
        return flag9;
    }

    public boolean isPromena() {
        return promena;
    }

    public void setPromena(boolean promena) {
        this.promena = promena;
    }

    public void setFlag9(boolean flag9) {
        this.flag9 = flag9;
    }

    public boolean isFlag10() {
        return flag10;
    }

    public void setFlag10(boolean flag10) {
        this.flag10 = flag10;
    }

    public boolean isFlag11() {
        return flag11;
    }

    public void setFlag11(boolean flag11) {
        this.flag11 = flag11;
    }

    public boolean isFlag12() {
        return flag12;
    }

    public void setFlag12(boolean flag12) {
        this.flag12 = flag12;
    }

    public boolean isFlag13() {
        return flag13;
    }

    public void setFlag13(boolean flag13) {
        this.flag13 = flag13;
    }

    public boolean isFlag14() {
        return flag14;
    }

    public void setFlag14(boolean flag14) {
        this.flag14 = flag14;
    }

    public boolean isFlag15() {
        return flag15;
    }

    public void setFlag15(boolean flag15) {
        this.flag15 = flag15;
    }

    public boolean isFlag16() {
        return flag16;
    }

    public void setFlag16(boolean flag16) {
        this.flag16 = flag16;
    }

    public boolean isFlag17() {
        return flag17;
    }

    public void setFlag17(boolean flag17) {
        this.flag17 = flag17;
    }

    public boolean isFlag18() {
        return flag18;
    }

    public void setFlag18(boolean flag18) {
        this.flag18 = flag18;
    }

    public boolean isFlag19() {
        return flag19;
    }

    public void setFlag19(boolean flag19) {
        this.flag19 = flag19;
    }

    public boolean isFlag20() {
        return flag20;
    }

    public void setFlag20(boolean flag20) {
        this.flag20 = flag20;
    }

    public boolean isFlag21() {
        return flag21;
    }

    public void setFlag21(boolean flag21) {
        this.flag21 = flag21;
    }

    public boolean isFlag22() {
        return flag22;
    }

    public void setFlag22(boolean flag22) {
        this.flag22 = flag22;
    }

    public boolean isFlag23() {
        return flag23;
    }

    public void setFlag23(boolean flag23) {
        this.flag23 = flag23;
    }

    public boolean isFlag24() {
        return flag24;
    }

    public void setFlag24(boolean flag24) {
        this.flag24 = flag24;
    }

    public boolean isFlag25() {
        return flag25;
    }

    public void setFlag25(boolean flag25) {
        this.flag25 = flag25;
    }

    public boolean isFlag26() {
        return flag26;
    }

    public void setFlag26(boolean flag26) {
        this.flag26 = flag26;
    }

    public boolean isFlag27() {
        return flag27;
    }

    public void setFlag27(boolean flag27) {
        this.flag27 = flag27;
    }

    public boolean isFlag28() {
        return flag28;
    }

    public void setFlag28(boolean flag28) {
        this.flag28 = flag28;
    }

    public boolean isFlag29() {
        return flag29;
    }

    public void setFlag29(boolean flag29) {
        this.flag29 = flag29;
    }

    public boolean isFlag30() {
        return flag30;
    }

    public void setFlag30(boolean flag30) {
        this.flag30 = flag30;
    }

    public boolean isKraj() {
        return kraj;
    }

    public void setKraj(boolean kraj) {
        this.kraj = kraj;
    }

    public int getBroj_poena() {
        return broj_poena;
    }

    public void setBroj_poena(int broj_poena) {
        this.broj_poena = broj_poena;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public String getUneseno_slovo() {
        return uneseno_slovo;
    }

    public void setUneseno_slovo(String uneseno_slovo) {
        this.uneseno_slovo = uneseno_slovo;
    }

    public String getGenerisana_rec() {
        return generisana_rec;
    }

    public void setGenerisana_rec(String generisana_rec) {
        this.generisana_rec = generisana_rec;
    }

    public String getSakrivena_rec() {
        return sakrivena_rec;
    }

    public void setSakrivena_rec(String sakrivena_rec) {
        this.sakrivena_rec = sakrivena_rec;
    }

    public int getBroj_pogresnih_pokusaja() {
        return broj_pogresnih_pokusaja;
    }

    public void setBroj_pogresnih_pokusaja(int broj_pogresnih_pokusaja) {
        this.broj_pogresnih_pokusaja = broj_pogresnih_pokusaja;
    }

    public int getBroj_tacnih_pokusaja() {
        return broj_tacnih_pokusaja;
    }

    public void setBroj_tacnih_pokusaja(int broj_tacnih_pokusaja) {
        this.broj_tacnih_pokusaja = broj_tacnih_pokusaja;
    }

    public ArrayList<String> getSlova() {
        return slova;
    }

    public void setSlova(ArrayList<String> slova) {
        this.slova = slova;
    }
}
