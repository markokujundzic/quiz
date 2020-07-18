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
@Table(name = "zanimljiva_geografija")
public class ZanimljivaGeografija {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "tip")
    private String tip;

    @Column(name = "pojam")
    private String pojam;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getPojam() {
        return pojam;
    }

    public void setPojam(String pojam) {
        this.pojam = pojam;
    }
}
