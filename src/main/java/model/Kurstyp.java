package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "kurstyp")
public class Kurstyp {
    @Id
    @Column(name = "typ_id", nullable = false)
    private Character typ_id;

    @Column(name = "typ_bezeichnung", length = 100)
    private String typBezeichnung;

    @OneToMany(mappedBy = "kursTyp")
    private Set<Kurs> kurse = new HashSet<>();

    public Kurstyp(Character typId, String typBezeichnung) {
        this.typ_id = typId;
        this.typBezeichnung = typBezeichnung;
    }

    public Kurstyp() {

    }

    public Character getTypId() {
        return this.typ_id;
    }

    public void setTypId(Character typId) {
        this.typ_id = typId;
    }

    public String getTypBezeichnung() {
        return this.typBezeichnung;
    }

    public void setTypBezeichnung(String typBezeichnung) {
        this.typBezeichnung = typBezeichnung;
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
        Kurstyp kurstyp = (Kurstyp) o;
        return typ_id.equals(kurstyp.typ_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typ_id);
    }

    @Override
    public String toString() {
        return "Kurstyp{" +
                "typId=" + typ_id +
                ", typBezeichnung='" + typBezeichnung + '\'' +
                '}';
    }
}
