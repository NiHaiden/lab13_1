package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "dozent")
public class Dozent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doz_id")
    private Integer dozId;

    @Column(name = "doz_zuname", length = 25)
    private String dozZuname;

    @Column(name = "doz_vorname", length = 25)
    private String dozVorname;

    @OneToMany(mappedBy = "dozent")
    private Set<Kurs> kurse = new HashSet<>();

    public Dozent(String dozZuname, String dozVorname) {
        this.dozZuname = dozZuname;
        this.dozVorname = dozVorname;
    }

    public Dozent() {

    }

    public Integer getDozId() {
        return this.dozId;
    }

    public void setDozId(Integer dozId) {
        this.dozId = dozId;
    }

    public String getDozZuname() {
        return this.dozZuname;
    }

    public void setDozZuname(String dozZuname) {
        this.dozZuname = dozZuname;
    }

    public String getDozVorname() {
        return this.dozVorname;
    }

    public void setDozVorname(String dozVorname) {
        this.dozVorname = dozVorname;
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
        Dozent dozent = (Dozent) o;
        return dozId.equals(dozent.dozId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dozId);
    }

    @Override
    public String toString() {
        return "Dozent{" +
                "dozId=" + dozId +
                ", dozZuname='" + dozZuname + '\'' +
                ", dozVorname='" + dozVorname + '\'' +
                ", kurse=" + kurse +
                '}';
    }
}
