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
@Table(name = "igraodanas")
public class IgraoDanas {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "datum")
    private Date datum;

    @Column(name = "da_li_je_igrao")
    private int da_li_je_igrao;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getDa_li_je_igrao() {
        return da_li_je_igrao;
    }

    public void setDa_li_je_igrao(int da_li_je_igrao) {
        this.da_li_je_igrao = da_li_je_igrao;
    }

}
