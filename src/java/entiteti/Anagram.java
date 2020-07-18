/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Marko
 */
@Entity
@Table(name = "anagram")
public class Anagram {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "pitanje")
    private String pitanje;

    @Column(name = "odgovor")
    private String odgovor;

    @Column(name = "rebus")
    private int rebus;

    @Column(name = "slika")
    private String slika;

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

    public int getRebus() {
        return rebus;
    }

    public void setRebus(int rebus) {
        this.rebus = rebus;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
