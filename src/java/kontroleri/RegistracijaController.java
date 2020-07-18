/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroleri;

import db.NewHibernateUtil;
import entiteti.Korisnik;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Marko
 */
@ManagedBean
@SessionScoped
@Named(value = "registracija")
public class RegistracijaController implements Serializable {

    private String ime;
    private String prezime;
    private String username;
    private String password;
    private String check_password;
    private String zanimanje;
    private String pol;
    private String jmbg;
    private String email;
    private String pitanje;
    private String odgovor;
    private String tip;

    private UploadedFile slika;

    public void doUpload() {

        try {
            File f;
            try (InputStream in = slika.getInputstream()) {
                f = new File("C:\\Users\\Asus\\Desktop\\projekat\\projekat\\web\\resources\\fotografije\\" + slika.getFileName());
                f.createNewFile();
                try (FileOutputStream out = new FileOutputStream(f)) {
                    out.write(slika.getContents());
                }
            }
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("path", f.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public String registracija() {

        if (jmbg.length() == 13) {

            if (Integer.parseInt(jmbg.substring(0, 2)) > 31) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "JMBG je u lošem formatu, loše su prve dve cifre!", null));
                return "registracija?faces-redirect=true";
            }

            if (Integer.parseInt(jmbg.substring(2, 4)) > 12) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "JMBG je u lošem formatu, loše su druge dve cifre!", null));
                return "registracija?faces-redirect=true";
            }

            SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            Criteria cr = session.createCriteria(Korisnik.class);

            Korisnik mojkorisnik = (Korisnik) cr.add(Restrictions.eq("username", username)).uniqueResult();

            session.getTransaction().commit();
            session.close();

            if (mojkorisnik == null) {

                this.doUpload();

                SessionFactory sessionF = NewHibernateUtil.getSessionFactory();
                Session sesija = sessionF.openSession();
                sesija.beginTransaction();

                sesija.getTransaction().commit();
                sesija.close();

                Korisnik novikorisnik = new Korisnik();

                String sif_lozinka = null;
                String sif_odgovor = null;

                try {
                    sif_lozinka = LogInController.hash(password);
                    sif_odgovor = LogInController.hash(odgovor);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(RegistracijaController.class.getName()).log(Level.SEVERE, null, ex);
                }

                novikorisnik.setPassword(sif_lozinka);
                novikorisnik.setIme(ime);
                novikorisnik.setPrezime(prezime);
                novikorisnik.setPitanje(pitanje);
                novikorisnik.setOdgovor(sif_odgovor);
                novikorisnik.setTip(tip);
                novikorisnik.setEmail(email);
                novikorisnik.setUsername(username);
                novikorisnik.setZanimanje(zanimanje);
                novikorisnik.setPol(pol);
                novikorisnik.setJmbg(jmbg);

                AdministratorController.lista_zahteva.add(novikorisnik);

                prezime = null;
                pitanje = null;
                odgovor = null;
                username = null;
                password = null;
                zanimanje = null;
                ime = null;
                pol = null;
                jmbg = null;
                email = null;
                tip = null;

                return "registracija_nastavak?faces-redirect=true";
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Korisnik već postoji u bazi!", null));
                return "registracija?faces-redirect=true";
            }
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Dužina JMBG nije zadovoljavajuća!", null));
            return "registracija?faces-redirect=true";
        }
    }

    public void obrisi(Korisnik korisnik) {
        AdministratorController.lista_zahteva.remove(korisnik);
    }

    public UploadedFile getSlika() {
        return slika;
    }

    public void setSlika(UploadedFile slika) {
        this.slika = slika;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCheck_password() {
        return check_password;
    }

    public void setCheck_password(String check_password) {
        this.check_password = check_password;
    }

    public String getZanimanje() {
        return zanimanje;
    }

    public void setZanimanje(String zanimanje) {
        this.zanimanje = zanimanje;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
