/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import db.NewHibernateUtil;
import entiteti.Anagram;
import entiteti.Odgovori;
import entiteti.Pehar;
import entiteti.Vesanje;
import entiteti.ZanimljivaGeografija;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Marko
 */
@ManagedBean
@SessionScoped
@Named(value = "supervizor")
public class SupervizorController implements Serializable {

    private String anagram_pitanje;
    private String anagram_odgovor;

    private String vesala_rec;

    private String pehar_pitanje_1;
    private String pehar_pitanje_2;
    private String pehar_pitanje_3;
    private String pehar_pitanje_4;
    private String pehar_pitanje_5;
    private String pehar_pitanje_6;
    private String pehar_pitanje_7;
    private String pehar_pitanje_8;
    private String pehar_pitanje_9;
    private String pehar_pitanje_10;
    private String pehar_pitanje_11;
    private String pehar_pitanje_12;
    private String pehar_pitanje_13;

    private String pehar_odgovor_1;
    private String pehar_odgovor_2;
    private String pehar_odgovor_3;
    private String pehar_odgovor_4;
    private String pehar_odgovor_5;
    private String pehar_odgovor_6;
    private String pehar_odgovor_7;
    private String pehar_odgovor_8;
    private String pehar_odgovor_9;
    private String pehar_odgovor_10;
    private String pehar_odgovor_11;
    private String pehar_odgovor_12;
    private String pehar_odgovor_13;

    public static boolean polling_flag = false;

    private boolean flag_drzava = false;
    private boolean flag_grad = false;
    private boolean flag_jezero = false;
    private boolean flag_planina = false;
    private boolean flag_reka = false;
    private boolean flag_zivotinja = false;
    private boolean flag_biljka = false;
    private boolean flag_grupa = false;

    private boolean provera_flag;

    private boolean odbaci_drzava = false;
    private boolean odbaci_grad = false;
    private boolean odbaci_jezero = false;
    private boolean odbaci_planina = false;
    private boolean odbaci_reka = false;
    private boolean odbaci_zivotinja = false;
    private boolean odbaci_biljka = false;
    private boolean odbaci_grupa = false;

    private boolean a = true;
    private boolean b = true;
    private boolean c = true;
    private boolean d = true;
    private boolean e = true;
    private boolean f = true;
    private boolean g = true;
    private boolean h = true;

    private boolean blok;
    private boolean blok1 = true;

    private String vrsta;

    private UploadedFile slika;

    public String povratak() {
        vrsta = null;
        blok1 = true;
        return "supervizor?faces-redirect=true";
    }

    public void doUpload() {

        try {
            File file;
            try (InputStream in = slika.getInputstream()) {
                file = new File("C:\\Users\\Asus\\Desktop\\projekat\\projekat\\web\\resources\\" + slika.getFileName());
                file.createNewFile();
                try (FileOutputStream out = new FileOutputStream(file)) {
                    out.write(slika.getContents());
                }
            }
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("path", file.getAbsolutePath());
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void izaberi() {

        blok1 = false;

        if (vrsta.equals("anagram")) {
            blok = true;
        } else {
            blok = false;
        }
    }

    public String unesi_anagram() {

        blok1 = true;

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Anagram anagram = new Anagram();

        if (blok) {
            anagram.setPitanje(anagram_pitanje);
            anagram.setOdgovor(anagram_odgovor);
            anagram.setRebus(0);
            anagram.setSlika("/");

            session.save(anagram);

            session.getTransaction().commit();
            session.close();

            anagram_odgovor = null;
            anagram_pitanje = null;
            vrsta = null;

            return "supervizor?faces-redirect=true";
        } else {

            this.doUpload();

            anagram.setPitanje("Rebus");
            anagram.setOdgovor(anagram_odgovor);
            anagram.setRebus(1);
            anagram.setSlika("resources/".concat(slika.getFileName()));

            session.save(anagram);

            session.getTransaction().commit();
            session.close();

            anagram_odgovor = null;
            vrsta = null;

            return "supervizor?faces-redirect=true";
        }
    }

    public String unesi_vesanja() {

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Vesanje vesala = new Vesanje();

        vesala.setRec_koja_se_pogadja(vesala_rec);

        session.save(vesala);

        session.getTransaction().commit();
        session.close();

        vesala_rec = null;

        return "supervizor?faces-redirect=true";
    }

    public String unesi_pehar() {

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Pehar pehar = new Pehar();

        pehar.setPitanje_broj_1(pehar_pitanje_1);
        pehar.setPitanje_broj_2(pehar_pitanje_2);
        pehar.setPitanje_broj_3(pehar_pitanje_3);
        pehar.setPitanje_broj_4(pehar_pitanje_4);
        pehar.setPitanje_broj_5(pehar_pitanje_5);
        pehar.setPitanje_broj_6(pehar_pitanje_6);
        pehar.setPitanje_broj_7(pehar_pitanje_7);
        pehar.setPitanje_broj_8(pehar_pitanje_8);
        pehar.setPitanje_broj_9(pehar_pitanje_9);
        pehar.setPitanje_broj_10(pehar_pitanje_10);
        pehar.setPitanje_broj_11(pehar_pitanje_11);
        pehar.setPitanje_broj_12(pehar_pitanje_12);
        pehar.setPitanje_broj_13(pehar_pitanje_13);

        pehar.setOdgovor_broj_1(pehar_odgovor_1);
        pehar.setOdgovor_broj_2(pehar_odgovor_2);
        pehar.setOdgovor_broj_3(pehar_odgovor_3);
        pehar.setOdgovor_broj_4(pehar_odgovor_4);
        pehar.setOdgovor_broj_5(pehar_odgovor_5);
        pehar.setOdgovor_broj_6(pehar_odgovor_6);
        pehar.setOdgovor_broj_7(pehar_odgovor_7);
        pehar.setOdgovor_broj_8(pehar_odgovor_8);
        pehar.setOdgovor_broj_9(pehar_odgovor_9);
        pehar.setOdgovor_broj_10(pehar_odgovor_10);
        pehar.setOdgovor_broj_11(pehar_odgovor_11);
        pehar.setOdgovor_broj_12(pehar_odgovor_12);
        pehar.setOdgovor_broj_13(pehar_odgovor_13);

        session.save(pehar);

        session.getTransaction().commit();
        session.close();

        pehar_pitanje_1 = null;
        pehar_pitanje_2 = null;
        pehar_pitanje_3 = null;
        pehar_pitanje_4 = null;
        pehar_pitanje_5 = null;
        pehar_pitanje_6 = null;
        pehar_pitanje_7 = null;
        pehar_pitanje_8 = null;
        pehar_pitanje_9 = null;
        pehar_pitanje_10 = null;
        pehar_pitanje_11 = null;
        pehar_pitanje_12 = null;
        pehar_pitanje_13 = null;

        pehar_odgovor_1 = null;
        pehar_odgovor_2 = null;
        pehar_odgovor_3 = null;
        pehar_odgovor_4 = null;
        pehar_odgovor_5 = null;
        pehar_odgovor_6 = null;
        pehar_odgovor_7 = null;
        pehar_odgovor_8 = null;
        pehar_odgovor_9 = null;
        pehar_odgovor_10 = null;
        pehar_odgovor_11 = null;
        pehar_odgovor_12 = null;
        pehar_odgovor_13 = null;

        return "supervizor?faces-redirect=true";
    }

    public static void proveri() {
        if (polling_flag) {
            polling_flag = false;
            ZanimljivaGeografijaController.flag_zavrsi = true;
        }
    }

    public void dodeli_poene(Odgovori odgovor) {
        ZanimljivaGeografijaController.lista_odgovora.remove(odgovor);
        polling_flag = true;
    }

    public void odobri_drzavu(String drzava) {

        a = false;
        ZanimljivaGeografijaController.dodatni_poeni += 4;
        flag_drzava = true;

        SessionFactory sessionFactory1 = NewHibernateUtil.getSessionFactory();
        Session session1 = sessionFactory1.openSession();
        session1.beginTransaction();

        ZanimljivaGeografija zg = new ZanimljivaGeografija();

        zg.setTip("drzava");
        zg.setPojam(drzava);

        session1.save(zg);

        session1.getTransaction().commit();
        session1.close();
    }

    public void odobri_jezero(String jezero) {

        c = false;
        ZanimljivaGeografijaController.dodatni_poeni += 4;
        flag_jezero = true;

        SessionFactory sessionFactory1 = NewHibernateUtil.getSessionFactory();
        Session session1 = sessionFactory1.openSession();
        session1.beginTransaction();

        ZanimljivaGeografija zg = new ZanimljivaGeografija();

        zg.setTip("jezero");
        zg.setPojam(jezero);

        session1.save(zg);

        session1.getTransaction().commit();
        session1.close();
    }

    public void odobri_reku(String reka) {

        e = false;
        ZanimljivaGeografijaController.dodatni_poeni += 4;
        flag_reka = true;

        SessionFactory sessionFactory1 = NewHibernateUtil.getSessionFactory();
        Session session1 = sessionFactory1.openSession();
        session1.beginTransaction();

        ZanimljivaGeografija zg = new ZanimljivaGeografija();

        zg.setTip("reka");
        zg.setPojam(reka);

        session1.save(zg);

        session1.getTransaction().commit();
        session1.close();
    }

    public void odobri_zivotinju(String zivotinja) {

        f = false;
        ZanimljivaGeografijaController.dodatni_poeni += 4;
        flag_zivotinja = true;

        SessionFactory sessionFactory1 = NewHibernateUtil.getSessionFactory();
        Session session1 = sessionFactory1.openSession();
        session1.beginTransaction();

        ZanimljivaGeografija zg = new ZanimljivaGeografija();

        zg.setTip("zivotinja");
        zg.setPojam(zivotinja);

        session1.save(zg);

        session1.getTransaction().commit();
        session1.close();
    }

    public void odobri_grad(String grad) {

        b = false;
        ZanimljivaGeografijaController.dodatni_poeni += 4;
        flag_grad = true;

        SessionFactory sessionFactory1 = NewHibernateUtil.getSessionFactory();
        Session session1 = sessionFactory1.openSession();
        session1.beginTransaction();

        ZanimljivaGeografija zg = new ZanimljivaGeografija();

        zg.setTip("grad");
        zg.setPojam(grad);

        session1.save(zg);

        session1.getTransaction().commit();
        session1.close();
    }

    public void odobri_planinu(String planina) {

        d = false;
        ZanimljivaGeografijaController.dodatni_poeni += 4;
        flag_planina = true;

        SessionFactory sessionFactory1 = NewHibernateUtil.getSessionFactory();
        Session session1 = sessionFactory1.openSession();
        session1.beginTransaction();

        ZanimljivaGeografija zg = new ZanimljivaGeografija();

        zg.setTip("planina");
        zg.setPojam(planina);

        session1.save(zg);

        session1.getTransaction().commit();
        session1.close();
    }

    public void odobri_biljku(String biljka) {

        g = false;
        ZanimljivaGeografijaController.dodatni_poeni += 4;
        flag_biljka = true;

        SessionFactory sessionFactory1 = NewHibernateUtil.getSessionFactory();
        Session session1 = sessionFactory1.openSession();
        session1.beginTransaction();

        ZanimljivaGeografija zg = new ZanimljivaGeografija();

        zg.setTip("biljka");
        zg.setPojam(biljka);

        session1.save(zg);

        session1.getTransaction().commit();
        session1.close();
    }

    public void odobri_grupu(String grupa) {

        h = false;
        ZanimljivaGeografijaController.dodatni_poeni += 4;
        flag_grupa = true;

        SessionFactory sessionFactory1 = NewHibernateUtil.getSessionFactory();
        Session session1 = sessionFactory1.openSession();
        session1.beginTransaction();

        ZanimljivaGeografija zg = new ZanimljivaGeografija();

        zg.setTip("muzicka_grupa");
        zg.setPojam(grupa);

        session1.save(zg);

        session1.getTransaction().commit();
        session1.close();
    }

    public boolean isA() {
        return a;
    }

    public void setA(boolean a) {
        this.a = a;
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public boolean isC() {
        return c;
    }

    public void setC(boolean c) {
        this.c = c;
    }

    public boolean isD() {
        return d;
    }

    public void setD(boolean d) {
        this.d = d;
    }

    public boolean isE() {
        return e;
    }

    public void setE(boolean e) {
        this.e = e;
    }

    public boolean isF() {
        return f;
    }

    public void setF(boolean f) {
        this.f = f;
    }

    public boolean isG() {
        return g;
    }

    public void setG(boolean g) {
        this.g = g;
    }

    public boolean isH() {
        return h;
    }

    public void setH(boolean h) {
        this.h = h;
    }

    public boolean isOdbaci_jezero() {
        return odbaci_jezero;
    }

    public void setOdbaci_jezero(boolean odbaci_jezero) {
        this.odbaci_jezero = odbaci_jezero;
    }

    public boolean isOdbaci_planina() {
        return odbaci_planina;
    }

    public void setOdbaci_planina(boolean odbaci_planina) {
        this.odbaci_planina = odbaci_planina;
    }

    public boolean isOdbaci_reka() {
        return odbaci_reka;
    }

    public void setOdbaci_reka(boolean odbaci_reka) {
        this.odbaci_reka = odbaci_reka;
    }

    public boolean isOdbaci_zivotinja() {
        return odbaci_zivotinja;
    }

    public void setOdbaci_zivotinja(boolean odbaci_zivotinja) {
        this.odbaci_zivotinja = odbaci_zivotinja;
    }

    public boolean isOdbaci_biljka() {
        return odbaci_biljka;
    }

    public void setOdbaci_biljka(boolean odbaci_biljka) {
        this.odbaci_biljka = odbaci_biljka;
    }

    public boolean isOdbaci_grupa() {
        return odbaci_grupa;
    }

    public void setOdbaci_grupa(boolean odbaci_grupa) {
        this.odbaci_grupa = odbaci_grupa;
    }

    public void odbaci_drzavu() {
        odbaci_drzava = true;
        a = false;
    }

    public void odbaci_grad() {
        odbaci_grad = true;
        b = false;
    }

    public void odbaci_jezero() {
        odbaci_jezero = true;
        c = false;
    }

    public void odbaci_planina() {
        odbaci_planina = true;
        d = false;
    }

    public void odbaci_reka() {
        odbaci_reka = true;
        e = false;
    }

    public void odbaci_zivotinja() {
        odbaci_zivotinja = true;
        f = false;
    }

    public void odbaci_biljka() {
        odbaci_biljka = true;
        g = false;
    }

    public void odbaci_grupa() {
        odbaci_grupa = true;
        h = false;
    }

    public boolean isOdbaci_drzava() {
        return odbaci_drzava;
    }

    public void setOdbaci_drzava(boolean odbaci_drzava) {
        this.odbaci_drzava = odbaci_drzava;
    }

    public boolean isOdbaci_grad() {
        return odbaci_grad;
    }

    public String getVrsta() {
        return vrsta;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }

    public void setOdbaci_grad(boolean odbaci_grad) {
        this.odbaci_grad = odbaci_grad;
    }

    public String getPehar_pitanje_1() {
        return pehar_pitanje_1;
    }

    public void setPehar_pitanje_1(String pehar_pitanje_1) {
        this.pehar_pitanje_1 = pehar_pitanje_1;
    }

    public String getPehar_pitanje_2() {
        return pehar_pitanje_2;
    }

    public void setPehar_pitanje_2(String pehar_pitanje_2) {
        this.pehar_pitanje_2 = pehar_pitanje_2;
    }

    public String getPehar_pitanje_3() {
        return pehar_pitanje_3;
    }

    public boolean isBlok() {
        return blok;
    }

    public void setBlok(boolean blok) {
        this.blok = blok;
    }

    public void setPehar_pitanje_3(String pehar_pitanje_3) {
        this.pehar_pitanje_3 = pehar_pitanje_3;
    }

    public String getPehar_pitanje_4() {
        return pehar_pitanje_4;
    }

    public void setPehar_pitanje_4(String pehar_pitanje_4) {
        this.pehar_pitanje_4 = pehar_pitanje_4;
    }

    public String getPehar_pitanje_5() {
        return pehar_pitanje_5;
    }

    public void setPehar_pitanje_5(String pehar_pitanje_5) {
        this.pehar_pitanje_5 = pehar_pitanje_5;
    }

    public String getPehar_pitanje_6() {
        return pehar_pitanje_6;
    }

    public void setPehar_pitanje_6(String pehar_pitanje_6) {
        this.pehar_pitanje_6 = pehar_pitanje_6;
    }

    public String getPehar_pitanje_7() {
        return pehar_pitanje_7;
    }

    public boolean isBlok1() {
        return blok1;
    }

    public void setBlok1(boolean blok1) {
        this.blok1 = blok1;
    }

    public void setPehar_pitanje_7(String pehar_pitanje_7) {
        this.pehar_pitanje_7 = pehar_pitanje_7;
    }

    public String getPehar_pitanje_8() {
        return pehar_pitanje_8;
    }

    public void setPehar_pitanje_8(String pehar_pitanje_8) {
        this.pehar_pitanje_8 = pehar_pitanje_8;
    }

    public String getPehar_pitanje_9() {
        return pehar_pitanje_9;
    }

    public void setPehar_pitanje_9(String pehar_pitanje_9) {
        this.pehar_pitanje_9 = pehar_pitanje_9;
    }

    public String getPehar_pitanje_10() {
        return pehar_pitanje_10;
    }

    public void setPehar_pitanje_10(String pehar_pitanje_10) {
        this.pehar_pitanje_10 = pehar_pitanje_10;
    }

    public String getPehar_pitanje_11() {
        return pehar_pitanje_11;
    }

    public void setPehar_pitanje_11(String pehar_pitanje_11) {
        this.pehar_pitanje_11 = pehar_pitanje_11;
    }

    public String getPehar_pitanje_12() {
        return pehar_pitanje_12;
    }

    public void setPehar_pitanje_12(String pehar_pitanje_12) {
        this.pehar_pitanje_12 = pehar_pitanje_12;
    }

    public String getPehar_pitanje_13() {
        return pehar_pitanje_13;
    }

    public UploadedFile getSlika() {
        return slika;
    }

    public void setSlika(UploadedFile slika) {
        this.slika = slika;
    }

    public void setPehar_pitanje_13(String pehar_pitanje_13) {
        this.pehar_pitanje_13 = pehar_pitanje_13;
    }

    public String getPehar_odgovor_1() {
        return pehar_odgovor_1;
    }

    public void setPehar_odgovor_1(String pehar_odgovor_1) {
        this.pehar_odgovor_1 = pehar_odgovor_1;
    }

    public String getPehar_odgovor_2() {
        return pehar_odgovor_2;
    }

    public void setPehar_odgovor_2(String pehar_odgovor_2) {
        this.pehar_odgovor_2 = pehar_odgovor_2;
    }

    public String getPehar_odgovor_3() {
        return pehar_odgovor_3;
    }

    public void setPehar_odgovor_3(String pehar_odgovor_3) {
        this.pehar_odgovor_3 = pehar_odgovor_3;
    }

    public String getPehar_odgovor_4() {
        return pehar_odgovor_4;
    }

    public void setPehar_odgovor_4(String pehar_odgovor_4) {
        this.pehar_odgovor_4 = pehar_odgovor_4;
    }

    public String getPehar_odgovor_5() {
        return pehar_odgovor_5;
    }

    public void setPehar_odgovor_5(String pehar_odgovor_5) {
        this.pehar_odgovor_5 = pehar_odgovor_5;
    }

    public String getPehar_odgovor_6() {
        return pehar_odgovor_6;
    }

    public void setPehar_odgovor_6(String pehar_odgovor_6) {
        this.pehar_odgovor_6 = pehar_odgovor_6;
    }

    public String getPehar_odgovor_7() {
        return pehar_odgovor_7;
    }

    public void setPehar_odgovor_7(String pehar_odgovor_7) {
        this.pehar_odgovor_7 = pehar_odgovor_7;
    }

    public String getPehar_odgovor_8() {
        return pehar_odgovor_8;
    }

    public void setPehar_odgovor_8(String pehar_odgovor_8) {
        this.pehar_odgovor_8 = pehar_odgovor_8;
    }

    public String getPehar_odgovor_9() {
        return pehar_odgovor_9;
    }

    public void setPehar_odgovor_9(String pehar_odgovor_9) {
        this.pehar_odgovor_9 = pehar_odgovor_9;
    }

    public String getPehar_odgovor_10() {
        return pehar_odgovor_10;
    }

    public void setPehar_odgovor_10(String pehar_odgovor_10) {
        this.pehar_odgovor_10 = pehar_odgovor_10;
    }

    public String getPehar_odgovor_11() {
        return pehar_odgovor_11;
    }

    public void setPehar_odgovor_11(String pehar_odgovor_11) {
        this.pehar_odgovor_11 = pehar_odgovor_11;
    }

    public String getPehar_odgovor_12() {
        return pehar_odgovor_12;
    }

    public void setPehar_odgovor_12(String pehar_odgovor_12) {
        this.pehar_odgovor_12 = pehar_odgovor_12;
    }

    public String getPehar_odgovor_13() {
        return pehar_odgovor_13;
    }

    public void setPehar_odgovor_13(String pehar_odgovor_13) {
        this.pehar_odgovor_13 = pehar_odgovor_13;
    }

    public String getVesala_rec() {
        return vesala_rec;
    }

    public void setVesala_rec(String vesala_rec) {
        this.vesala_rec = vesala_rec;
    }

    public String getAnagram_pitanje() {
        return anagram_pitanje;
    }

    public void setAnagram_pitanje(String anagram_pitanje) {
        this.anagram_pitanje = anagram_pitanje;
    }

    public String getAnagram_odgovor() {
        return anagram_odgovor;
    }

    public void setAnagram_odgovor(String anagram_odgovor) {
        this.anagram_odgovor = anagram_odgovor;
    }

    public void provera() {
        provera_flag = true;
    }

    public boolean isProvera_flag() {
        return provera_flag;
    }

    public void setProvera_flag(boolean provera_flag) {
        this.provera_flag = provera_flag;
    }

    public static void setPolling_flag(boolean polling_flag) {
        SupervizorController.polling_flag = polling_flag;
    }

    public boolean isFlag_drzava() {
        return flag_drzava;
    }

    public void setFlag_drzava(boolean flag_drzava) {
        this.flag_drzava = flag_drzava;
    }

    public boolean isFlag_grad() {
        return flag_grad;
    }

    public void setFlag_grad(boolean flag_grad) {
        this.flag_grad = flag_grad;
    }

    public boolean isFlag_jezero() {
        return flag_jezero;
    }

    public void setFlag_jezero(boolean flag_jezero) {
        this.flag_jezero = flag_jezero;
    }

    public boolean isFlag_planina() {
        return flag_planina;
    }

    public void setFlag_planina(boolean flag_planina) {
        this.flag_planina = flag_planina;
    }

    public boolean isFlag_reka() {
        return flag_reka;
    }

    public void setFlag_reka(boolean flag_reka) {
        this.flag_reka = flag_reka;
    }

    public boolean isFlag_zivotinja() {
        return flag_zivotinja;
    }

    public void setFlag_zivotinja(boolean flag_zivotinja) {
        this.flag_zivotinja = flag_zivotinja;
    }

    public boolean isFlag_biljka() {
        return flag_biljka;
    }

    public void setFlag_biljka(boolean flag_biljka) {
        this.flag_biljka = flag_biljka;
    }

    public boolean isFlag_grupa() {
        return flag_grupa;
    }

    public void setFlag_grupa(boolean flag_grupa) {
        this.flag_grupa = flag_grupa;
    }

    public static boolean isPolling_flag() {
        return polling_flag;
    }
}
