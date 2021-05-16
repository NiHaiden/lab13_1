package model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "kurs")
public class Kurs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kurs_id")
    private Integer kursId;

    @ManyToOne
    @JoinColumn(name = "kurs_typ")
    private Kurstyp kursTyp;

    @ManyToOne
    @JoinColumn(name = "kurs_doz_id")
    private Dozent dozent;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "kurs_kunde", joinColumns = @JoinColumn(name = "kurs_id"),
            inverseJoinColumns = @JoinColumn(name = "kunde_id"))
    private Set<Kunde> kunden = new HashSet<>();

    @Column(name = "kurs_bezeichnung", length = 100)
    private String kursBezeichnung;

    @Column(name = "kurs_beginndatum")
    private LocalDate kursBeginndatum;

    public Kurs(Kurstyp kursTyp, String kursBezeichnung, LocalDate kursBeginndatum) {
        this.kursTyp = kursTyp;
        this.kursBezeichnung = kursBezeichnung;
        this.kursBeginndatum = kursBeginndatum;
    }

    public Kurs() {

    }

    public Integer getKursId() {
        return this.kursId;
    }

    public void setKursId(Integer kursId) {
        this.kursId = kursId;
    }

    public Kurstyp getKursTyp() {
        return this.kursTyp;
    }

    public void setKursTyp(Kurstyp kursTyp) {
        this.kursTyp = kursTyp;
    }

    public Dozent getDozent() {
        return this.dozent;
    }

    public void setKursDozId(Dozent dozent) {
        this.dozent = dozent;
    }

    public String getKursBezeichnung() {
        return this.kursBezeichnung;
    }

    public void setKursBezeichnung(String kursBezeichnung) {
        this.kursBezeichnung = kursBezeichnung;
    }

    public LocalDate getKursBeginndatum() {
        return this.kursBeginndatum;
    }

    public void setKursBeginndatum(LocalDate kursBeginndatum) {
        this.kursBeginndatum = kursBeginndatum;
    }

    public void setDozent(Dozent dozent) {
        this.dozent = dozent;
    }

    public Set<Kunde> getKunden() {
        return kunden;
    }

    public void setKunden(Set<Kunde> kunden) {
        this.kunden = kunden;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kurs kurs = (Kurs) o;
        return kursId.equals(kurs.kursId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kursId);
    }

    @Override
    public String toString() {
        return "Kurs{" +
                "kursId=" + kursId +
                ", kursTyp=" + kursTyp +
                ", kursBezeichnung='" + kursBezeichnung + '\'' +
                ", kursBeginndatum=" + kursBeginndatum +
                '}';
    }
}
