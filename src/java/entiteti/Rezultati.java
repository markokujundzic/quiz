/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Marko
 */
@Entity
@Table(name = "rezultati")
public class Rezultati {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "anagram")
    private int anagram = 0;

    @Column(name = "mojbroj")
    private int mojbroj = 0;

    @Column(name = "vesala")
    private int vesala = 0;

    @Column(name = "zanimljivageografija")
    private int zanimljivageografija = 0;

    @Column(name = "pehar")
    private int pehar = 0;

    @Column(name = "ukupno")
    private int ukupno = 0;

    @Column(name = "datum")
    private Date datum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAnagram() {
        return anagram;
    }

    public void setAnagram(int anagram) {
        this.anagram = anagram;
    }

    public int getMojbroj() {
        return mojbroj;
    }

    public void setMojbroj(int mojbroj) {
        this.mojbroj = mojbroj;
    }

    public int getVesala() {
        return vesala;
    }

    public void setVesala(int vesala) {
        this.vesala = vesala;
    }

    public int getZanimljivageografija() {
        return zanimljivageografija;
    }

    public void setZanimljivageografija(int zanimljivageografija) {
        this.zanimljivageografija = zanimljivageografija;
    }

    public int getPehar() {
        return pehar;
    }

    public void setPehar(int pehar) {
        this.pehar = pehar;
    }

    public int getUkupno() {
        return ukupno;
    }

    public void setUkupno(int ukupno) {
        this.ukupno = ukupno;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

}
