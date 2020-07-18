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
@Table(name = "vesanje")
public class Vesanje {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "rec_koja_se_pogadja")
    private String rec_koja_se_pogadja;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRec_koja_se_pogadja() {
        return rec_koja_se_pogadja;
    }

    public void setRec_koja_se_pogadja(String rec_koja_se_pogadja) {
        this.rec_koja_se_pogadja = rec_koja_se_pogadja;
    }
}
