/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import db.NewHibernateUtil;
import entiteti.Korisnik;
import entiteti.Odgovori;
import entiteti.Rezultati;
import entiteti.ZanimljivaGeografija;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;
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
@Named(value = "zanimljiva_geografija")
public class ZanimljivaGeografijaController implements Serializable {

    private int broj_poena = 0;
    private int generisani_broj = 0;
    private int zanimljiva_geografija_tajmer = 120;

    public static int dodatni_poeni = 0;

    private boolean zakljucan = false;

    public static boolean flag_zavrsi = false;

    private String generisano_slovo = "";

    private String drzava;
    private String grad;
    private String jezero;
    private String planina;
    private String reka;
    private String zivotinja;
    private String biljka;
    private String grupa;

    public static ArrayList<Odgovori> lista_odgovora = new ArrayList<Odgovori>();

    private boolean posalji_flag = false;
    private boolean tajmer_flag = true;

    public ZanimljivaGeografijaController() {

        generisani_broj = new Random().nextInt(25);

        switch (generisani_broj) {
            case 0:
                generisano_slovo = "a";
                break;
            case 1:
                generisano_slovo = "b";
                break;
            case 2:
                generisano_slovo = "c";
                break;
            case 3:
                generisano_slovo = "v";
                break;
            case 4:
                generisano_slovo = "z";
                break;
            case 5:
                generisano_slovo = "d";
                break;
            case 6:
                generisano_slovo = "dz";
                break;
            case 7:
                generisano_slovo = "t";
                break;
            case 8:
                generisano_slovo = "e";
                break;
            case 9:
                generisano_slovo = "f";
                break;
            case 10:
                generisano_slovo = "g";
                break;
            case 11:
                generisano_slovo = "h";
                break;
            case 12:
                generisano_slovo = "i";
                break;
            case 13:
                generisano_slovo = "j";
                break;
            case 14:
                generisano_slovo = "k";
                break;
            case 15:
                generisano_slovo = "l";
                break;
            case 16:
                generisano_slovo = "lj";
                break;
            case 17:
                generisano_slovo = "m";
                break;
            case 18:
                generisano_slovo = "n";
                break;
            case 19:
                generisano_slovo = "nj";
                break;
            case 20:
                generisano_slovo = "o";
                break;
            case 21:
                generisano_slovo = "p";
                break;
            case 22:
                generisano_slovo = "r";
                break;
            case 23:
                generisano_slovo = "s";
                break;
            case 24:
                generisano_slovo = "u";
                break;
        }
    }

    public void kreni() {
        if (tajmer_flag) {
            --zanimljiva_geografija_tajmer;
            if (zanimljiva_geografija_tajmer == 0) {
                tajmer_flag = false;
                zakljucan = true;
            }
        }
    }

    public void posalji() {

        zakljucan = true;

        zanimljiva_geografija_tajmer = 0;
        tajmer_flag = false;

        Odgovori odgovor = new Odgovori();

        if (generisano_slovo.length() == 2) {

            if (drzava == null || drzava.length() < 2) {
                odgovor.setDrzava("Loš unos");
            } else {
                if (!generisano_slovo.equals(drzava.toLowerCase().substring(0, 2))) {
                    odgovor.setDrzava("Loš unos");
                } else {
                    SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
                    Session session = sessionFactory.openSession();
                    session.beginTransaction();

                    Criteria cr = session.createCriteria(ZanimljivaGeografija.class);
                    ZanimljivaGeografija zg = (ZanimljivaGeografija) cr.add(Restrictions.eq("tip", "drzava")).add(Restrictions.eq("pojam", drzava)).uniqueResult();

                    session.getTransaction().commit();
                    session.close();

                    if (zg != null) {
                        odgovor.setDrzava("Ima u bazi");
                        broj_poena += 2;
                    } else {
                        odgovor.setDrzava(drzava);
                    }
                }
            }

            if (grad == null || grad.length() < 2) {
                odgovor.setGrad("Loš unos");
            } else {
                if (!generisano_slovo.toLowerCase().equals(grad.toLowerCase().substring(0, 2))) {
                    odgovor.setGrad("Loš unos");
                } else {
                    SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
                    Session session = sessionFactory.openSession();
                    session.beginTransaction();

                    Criteria cr = session.createCriteria(ZanimljivaGeografija.class);
                    ZanimljivaGeografija zg = (ZanimljivaGeografija) cr.add(Restrictions.eq("tip", "grad")).add(Restrictions.eq("pojam", grad)).uniqueResult();

                    session.getTransaction().commit();
                    session.close();

                    if (zg != null) {
                        odgovor.setGrad("Ima u bazi");
                        broj_poena += 2;
                    } else {
                        odgovor.setGrad(grad);
                    }
                }
            }

            if (jezero == null || jezero.length() < 2) {
                odgovor.setJezero("Loš unos");
            } else {
                if (!generisano_slovo.toLowerCase().equals(jezero.toLowerCase().substring(0, 2))) {
                    odgovor.setJezero("Loš unos");
                } else {
                    SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
                    Session session = sessionFactory.openSession();
                    session.beginTransaction();

                    Criteria cr = session.createCriteria(ZanimljivaGeografija.class);
                    ZanimljivaGeografija zg = (ZanimljivaGeografija) cr.add(Restrictions.eq("tip", "jezero")).add(Restrictions.eq("pojam", jezero)).uniqueResult();

                    session.getTransaction().commit();
                    session.close();

                    if (zg != null) {
                        odgovor.setJezero("Ima u bazi");
                        broj_poena += 2;
                    } else {
                        odgovor.setJezero(jezero);
                    }
                }

            }

            if (planina == null || planina.length() < 2) {
                odgovor.setPlanina("Loš unos");
            } else {
                if (!generisano_slovo.toLowerCase().equals(planina.toLowerCase().substring(0, 2))) {
                    odgovor.setPlanina("Loš unos");
                } else {
                    SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
                    Session session = sessionFactory.openSession();
                    session.beginTransaction();

                    Criteria cr = session.createCriteria(ZanimljivaGeografija.class);
                    ZanimljivaGeografija zg = (ZanimljivaGeografija) cr.add(Restrictions.eq("tip", "planina")).add(Restrictions.eq("pojam", planina)).uniqueResult();

                    session.getTransaction().commit();
                    session.close();

                    if (zg != null) {
                        odgovor.setPlanina("Ima u bazi");
                        broj_poena += 2;
                    } else {
                        odgovor.setPlanina(planina);
                    }
                }
            }

            if (reka == null || reka.length() < 2) {
                odgovor.setReka("Loš unos");
            } else {
                if (!generisano_slovo.toLowerCase().equals(reka.toLowerCase().substring(0, 2))) {
                    odgovor.setReka("Loš unos");
                } else {
                    SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
                    Session session = sessionFactory.openSession();
                    session.beginTransaction();

                    Criteria cr = session.createCriteria(ZanimljivaGeografija.class);
                    ZanimljivaGeografija zg = (ZanimljivaGeografija) cr.add(Restrictions.eq("tip", "reka")).add(Restrictions.eq("pojam", reka)).uniqueResult();

                    session.getTransaction().commit();
                    session.close();

                    if (zg != null) {
                        odgovor.setReka("Ima u bazi");
                        broj_poena += 2;
                    } else {
                        odgovor.setReka(reka);
                    }
                }
            }

            if (zivotinja == null || zivotinja.length() < 2) {
                odgovor.setZivotinja("Loš unos");
            } else {
                if (!generisano_slovo.toLowerCase().equals(zivotinja.toLowerCase().substring(0, 2))) {
                    odgovor.setZivotinja("Loš unos");
                } else {
                    SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
                    Session session = sessionFactory.openSession();
                    session.beginTransaction();

                    Criteria cr = session.createCriteria(ZanimljivaGeografija.class);
                    ZanimljivaGeografija zg = (ZanimljivaGeografija) cr.add(Restrictions.eq("tip", "zivotinja")).add(Restrictions.eq("pojam", zivotinja)).uniqueResult();

                    session.getTransaction().commit();
                    session.close();

                    if (zg != null) {
                        odgovor.setZivotinja("Ima u bazi");
                        broj_poena += 2;
                    } else {
                        odgovor.setZivotinja(zivotinja);
                    }
                }
            }

            if (biljka == null || biljka.length() < 2) {
                odgovor.setBiljka("Loš unos");
            } else {
                if (!generisano_slovo.toLowerCase().equals(biljka.toLowerCase().substring(0, 2))) {
                    odgovor.setBiljka("Loš unos");
                } else {
                    SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
                    Session session = sessionFactory.openSession();
                    session.beginTransaction();

                    Criteria cr = session.createCriteria(ZanimljivaGeografija.class);
                    ZanimljivaGeografija zg = (ZanimljivaGeografija) cr.add(Restrictions.eq("tip", "biljka")).add(Restrictions.eq("pojam", biljka)).uniqueResult();

                    session.getTransaction().commit();
                    session.close();

                    if (zg != null) {
                        odgovor.setBiljka("Ima u bazi");
                        broj_poena += 2;
                    } else {
                        odgovor.setBiljka(biljka);
                    }
                }
            }

            if (grupa == null || grupa.length() < 2) {
                odgovor.setGrupa("Loš unos");
            } else {
                if (!generisano_slovo.toLowerCase().equals(grupa.toLowerCase().substring(0, 2))) {
                    odgovor.setGrupa("Loš unos");
                } else {
                    SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
                    Session session = sessionFactory.openSession();
                    session.beginTransaction();

                    Criteria cr = session.createCriteria(ZanimljivaGeografija.class);
                    ZanimljivaGeografija zg = (ZanimljivaGeografija) cr.add(Restrictions.eq("tip", "muzicka_grupa")).add(Restrictions.eq("pojam", grupa)).uniqueResult();

                    session.getTransaction().commit();
                    session.close();

                    if (zg != null) {
                        odgovor.setGrupa("Ima u bazi");
                        broj_poena += 2;
                    } else {
                        odgovor.setGrupa(grupa);
                    }
                }
            }

        } else if (generisano_slovo.length() == 1) {

            if (drzava == null || drzava.length() < 1) {
                odgovor.setDrzava("Loš unos");
            } else {
                if (!generisano_slovo.toLowerCase().equals(drzava.toLowerCase().substring(0, 1))) {
                    odgovor.setDrzava("Loš unos");
                } else {
                    SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
                    Session session = sessionFactory.openSession();
                    session.beginTransaction();

                    Criteria cr = session.createCriteria(ZanimljivaGeografija.class);
                    ZanimljivaGeografija zg = (ZanimljivaGeografija) cr.add(Restrictions.eq("tip", "drzava")).add(Restrictions.eq("pojam", drzava)).uniqueResult();

                    session.getTransaction().commit();
                    session.close();

                    if (zg != null) {
                        odgovor.setDrzava("Ima u bazi");
                        broj_poena += 2;
                    } else {
                        odgovor.setDrzava(drzava);
                    }
                }
            }

            if (grad == null || grad.length() < 1) {
                odgovor.setGrad("Loš unos");
            } else {
                if (!generisano_slovo.toLowerCase().equals(grad.toLowerCase().substring(0, 1))) {
                    odgovor.setGrad("Loš unos");
                } else {
                    SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
                    Session session = sessionFactory.openSession();
                    session.beginTransaction();

                    Criteria cr = session.createCriteria(ZanimljivaGeografija.class);
                    ZanimljivaGeografija zg = (ZanimljivaGeografija) cr.add(Restrictions.eq("tip", "grad")).add(Restrictions.eq("pojam", grad)).uniqueResult();

                    session.getTransaction().commit();
                    session.close();

                    if (zg != null) {
                        odgovor.setGrad("Ima u bazi");
                        broj_poena += 2;
                    } else {
                        odgovor.setGrad(grad);
                    }
                }
            }

            if (jezero == null || jezero.length() < 1) {
                odgovor.setJezero("Loš unos");
            } else {
                if (!generisano_slovo.toLowerCase().equals(jezero.toLowerCase().substring(0, 1))) {
                    odgovor.setJezero("Loš unos");
                } else {
                    SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
                    Session session = sessionFactory.openSession();
                    session.beginTransaction();

                    Criteria cr = session.createCriteria(ZanimljivaGeografija.class);
                    ZanimljivaGeografija zg = (ZanimljivaGeografija) cr.add(Restrictions.eq("tip", "jezero")).add(Restrictions.eq("pojam", jezero)).uniqueResult();

                    session.getTransaction().commit();
                    session.close();

                    if (zg != null) {
                        odgovor.setJezero("Ima u bazi");
                        broj_poena += 2;
                    } else {
                        odgovor.setJezero(jezero);
                    }
                }
            }

            if (planina == null || planina.length() < 1) {
                odgovor.setPlanina("Loš unos");
            } else {
                if (!generisano_slovo.toLowerCase().equals(planina.toLowerCase().substring(0, 1))) {
                    odgovor.setPlanina("Loš unos");
                } else {
                    SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
                    Session session = sessionFactory.openSession();
                    session.beginTransaction();

                    Criteria cr = session.createCriteria(ZanimljivaGeografija.class);
                    ZanimljivaGeografija zg = (ZanimljivaGeografija) cr.add(Restrictions.eq("tip", "planina")).add(Restrictions.eq("pojam", planina)).uniqueResult();

                    session.getTransaction().commit();
                    session.close();

                    if (zg != null) {
                        odgovor.setPlanina("Ima u bazi");
                        broj_poena += 2;
                    } else {
                        odgovor.setPlanina(planina);
                    }
                }
            }

            if (reka == null || reka.length() < 1) {
                odgovor.setReka("Loš unos");
            } else {
                if (!generisano_slovo.toLowerCase().equals(reka.toLowerCase().substring(0, 1))) {
                    odgovor.setReka("Loš unos");
                } else {
                    SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
                    Session session = sessionFactory.openSession();
                    session.beginTransaction();

                    Criteria cr = session.createCriteria(ZanimljivaGeografija.class);
                    ZanimljivaGeografija zg = (ZanimljivaGeografija) cr.add(Restrictions.eq("tip", "reka")).add(Restrictions.eq("pojam", reka)).uniqueResult();

                    session.getTransaction().commit();
                    session.close();

                    if (zg != null) {
                        odgovor.setReka("Ima u bazi");
                        broj_poena += 2;
                    } else {
                        odgovor.setReka(reka);
                    }
                }
            }

            if (zivotinja == null || zivotinja.length() < 1) {
                odgovor.setZivotinja("Loš unos");
            } else {
                if (!generisano_slovo.toLowerCase().equals(zivotinja.toLowerCase().substring(0, 1))) {
                    odgovor.setZivotinja("Loš unos");
                } else {
                    SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
                    Session session = sessionFactory.openSession();
                    session.beginTransaction();

                    Criteria cr = session.createCriteria(ZanimljivaGeografija.class);
                    ZanimljivaGeografija zg = (ZanimljivaGeografija) cr.add(Restrictions.eq("tip", "zivotinja")).add(Restrictions.eq("pojam", zivotinja)).uniqueResult();

                    session.getTransaction().commit();
                    session.close();

                    if (zg != null) {
                        odgovor.setZivotinja("Ima u bazi");
                        broj_poena += 2;
                    } else {
                        odgovor.setZivotinja(zivotinja);
                    }
                }
            }

            if (biljka == null || biljka.length() < 1) {
                odgovor.setBiljka("Loš unos");
            } else {
                if (!generisano_slovo.toLowerCase().equals(biljka.toLowerCase().substring(0, 1))) {
                    odgovor.setBiljka("Loš unos");
                } else {
                    SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
                    Session session = sessionFactory.openSession();
                    session.beginTransaction();

                    Criteria cr = session.createCriteria(ZanimljivaGeografija.class);
                    ZanimljivaGeografija zg = (ZanimljivaGeografija) cr.add(Restrictions.eq("tip", "biljka")).add(Restrictions.eq("pojam", biljka)).uniqueResult();

                    session.getTransaction().commit();
                    session.close();

                    if (zg != null) {
                        odgovor.setBiljka("Ima u bazi");
                        broj_poena += 2;
                    } else {
                        odgovor.setBiljka(biljka);
                    }
                }
            }

            if (grupa == null || grupa.length() < 1) {
                odgovor.setGrupa("Loš unos");
            } else {
                if (!generisano_slovo.toLowerCase().equals(grupa.toLowerCase().substring(0, 1))) {
                    odgovor.setGrupa("Loš unos");
                } else {
                    SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
                    Session session = sessionFactory.openSession();
                    session.beginTransaction();

                    Criteria cr = session.createCriteria(ZanimljivaGeografija.class);
                    ZanimljivaGeografija zg = (ZanimljivaGeografija) cr.add(Restrictions.eq("tip", "muzicka_grupa")).add(Restrictions.eq("pojam", grupa)).uniqueResult();

                    session.getTransaction().commit();
                    session.close();

                    if (zg != null) {
                        odgovor.setGrupa("Ima u bazi");
                        broj_poena += 2;
                    } else {
                        odgovor.setGrupa(grupa);
                    }
                }
            }

        }
        lista_odgovora.add(odgovor);
        posalji_flag = true;
    }

    public String zavrsi() {

        broj_poena += dodatni_poeni;

        dodatni_poeni = 0;

        flag_zavrsi = false;

        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession s = (HttpSession) fc.getExternalContext().getSession(false);
        Korisnik trenutni = (Korisnik) s.getAttribute("user");

        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Criteria cr = session.createCriteria(Rezultati.class);
        Rezultati rezultat = (Rezultati) cr.add(Restrictions.eq("username", trenutni.getUsername())).add(Restrictions.eq("datum", new Date(System.currentTimeMillis()))).uniqueResult();

        rezultat.setZanimljivageografija(broj_poena);
        rezultat.setUkupno(rezultat.getUkupno() + broj_poena);

        session.saveOrUpdate(rezultat);

        session.getTransaction().commit();
        session.close();

        return "pehar?faces-redirect=true";
    }

    public boolean isPosalji_flag() {
        return posalji_flag;
    }

    public void setPosalji_flag(boolean posalji_flag) {
        this.posalji_flag = posalji_flag;
    }

    public ArrayList<Odgovori> getLista_odgovora() {
        return lista_odgovora;
    }

    public void setLista_odgovora(ArrayList<Odgovori> lista_odgovora) {
        ZanimljivaGeografijaController.lista_odgovora = lista_odgovora;
    }

    public static int getDodatni_poeni() {
        return dodatni_poeni;
    }

    public static void setDodatni_poeni(int dodatni_poeni) {
        ZanimljivaGeografijaController.dodatni_poeni = dodatni_poeni;
    }

    public boolean isFlag_zavrsi() {
        return flag_zavrsi;
    }

    public int getBroj_poena() {
        return broj_poena;
    }

    public void setBroj_poena(int broj_poena) {
        this.broj_poena = broj_poena;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getJezero() {
        return jezero;
    }

    public void setJezero(String jezero) {
        this.jezero = jezero;
    }

    public String getPlanina() {
        return planina;
    }

    public void setPlanina(String planina) {
        this.planina = planina;
    }

    public String getReka() {
        return reka;
    }

    public void setReka(String reka) {
        this.reka = reka;
    }

    public String getZivotinja() {
        return zivotinja;
    }

    public void setZivotinja(String zivotinja) {
        this.zivotinja = zivotinja;
    }

    public String getBiljka() {
        return biljka;
    }

    public void setBiljka(String biljka) {
        this.biljka = biljka;
    }

    public String getGrupa() {
        return grupa;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }

    public int getGenerisani_broj() {
        return generisani_broj;
    }

    public void setGenerisani_broj(int generisani_broj) {
        this.generisani_broj = generisani_broj;
    }

    public String getGenerisano_slovo() {
        return generisano_slovo;
    }

    public void setGenerisano_slovo(String generisano_slovo) {
        this.generisano_slovo = generisano_slovo;
    }

    public int getZanimljiva_geografija_tajmer() {
        return zanimljiva_geografija_tajmer;
    }

    public void setZanimljiva_geografija_tajmer(int zanimljiva_geografija_tajmer) {
        this.zanimljiva_geografija_tajmer = zanimljiva_geografija_tajmer;
    }

    public boolean getZakljucan() {
        return zakljucan;
    }

    public boolean isTajmer_flag() {
        return tajmer_flag;
    }

    public void setTajmer_flag(boolean tajmer_flag) {
        this.tajmer_flag = tajmer_flag;
    }

    public void setZakljucan(boolean zakljucan) {
        this.zakljucan = zakljucan;
    }
}
