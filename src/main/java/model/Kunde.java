package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "kunde")
public class Kunde implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kunde_id")
    private Integer kundeId;

    @Column(name = "kunde_zuname")
    private String kundeZuname;

    @Column(name = "kunde_vorname")
    private String kundeVorname;

    @ManyToMany(mappedBy = "kunden", fetch = FetchType.EAGER)
    private Set<Kurs> kurse = new HashSet<>();

    public Kunde(String kundeZuname, String kundeVorname) {
        this.kundeZuname = kundeZuname;
        this.kundeVorname = kundeVorname;
    }

    public Kunde() {
    }

    public Integer getKundeId() {
        return this.kundeId;
    }

    public void setKundeId(Integer kundeId) {
        this.kundeId = kundeId;
    }

    public String getKundeZuname() {
        return this.kundeZuname;
    }

    public void setKundeZuname(String kundeZuname) {
        this.kundeZuname = kundeZuname;
    }

    public String getKundeVorname() {
        return this.kundeVorname;
    }

    public void setKundeVorname(String kundeVorname) {
        this.kundeVorname = kundeVorname;
    }

    public Set<Kurs> getKurse() {
        return kurse;
    }

    public void setKurse(Set<Kurs> kurse) {
        this.kurse = kurse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kunde kunde = (Kunde) o;
        return kundeId.equals(kunde.kundeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kundeId);
    }

    @Override
    public String toString() {
        return "Kunde{" +
                "kundeId=" + kundeId +
                ", kundeZuname='" + kundeZuname + '\'' +
                ", kundeVorname='" + kundeVorname + '\'' +
                '}';
    }
}
