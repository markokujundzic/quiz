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
@Table(name = "igradana")
public class IgraDana {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "datum")
    private Date datum;

    @Column(name = "id_anagram")
    private int id_anagram;

    @Column(name = "id_pehar")
    private int id_pehar;

    @Column(name = "id_vesala")
    private int id_vesala;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getId_anagram() {
        return id_anagram;
    }

    public void setId_anagram(int id_anagram) {
        this.id_anagram = id_anagram;
    }

    public int getId_pehar() {
        return id_pehar;
    }

    public void setId_pehar(int id_pehar) {
        this.id_pehar = id_pehar;
    }

    public int getId_vesala() {
        return id_vesala;
    }

    public void setId_vesala(int id_vesala) {
        this.id_vesala = id_vesala;
    }
}
