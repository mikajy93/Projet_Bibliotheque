package bibliotheque.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class StatusExemplaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_status_exemplaire;

    @ManyToOne
    @JoinColumn(name = "id_exemplaire", nullable = false)
    private Exemplaire exemplaire;

    @Column(name = "date_changement", nullable = false)
    private Date dateChangement;

    @ManyToOne
    @JoinColumn(name = "id_etat", nullable = false)
    private EtatExemplaire etat;

    @ManyToOne
    @JoinColumn(name = "id_biblio")
    private Bibliothecaire bibliothecaire;

    public StatusExemplaire() {}

    public int getId_status_exemplaire() {
        return id_status_exemplaire;
    }

    public void setId_status_exemplaire(int id_status_exemplaire) {
        this.id_status_exemplaire = id_status_exemplaire;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    public Date getDateChangement() {
        return dateChangement;
    }

    public void setDateChangement(Date dateChangement) {
        this.dateChangement = dateChangement;
    }

    public EtatExemplaire getEtat() {
        return etat;
    }

    public void setEtat(EtatExemplaire etat) {
        this.etat = etat;
    }

    public Bibliothecaire getBibliothecaire() {
        return bibliothecaire;
    }

    public void setBibliothecaire(Bibliothecaire bibliothecaire) {
        this.bibliothecaire = bibliothecaire;
    }
}