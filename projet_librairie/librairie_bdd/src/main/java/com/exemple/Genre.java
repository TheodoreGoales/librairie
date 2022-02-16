package com.exemple;
import java.io.Serializable;
import javax.persistence.*;

@Entity //définit quel objet sera enregistré en bdd
@Table(name="genre")
public class Genre implements Serializable{
    @Id //définit la clé primaire
    //@GeneratedValue(strategy=GerationType.AUTO) génère un ID automatiquement
    @Column(name = "id_genre", unique = true)
    private int id_genre;   
    
    @Column(name="nom_genre", nullable=false) //définit la colonne correspondante à la valeur titre
    private String nom_genre;

    public int getIdGenre(){
        return id_genre;
    }

    public void setId(int id_genre){
        this.id_genre=id_genre;
    }

    public String getNomGenre(){
        return nom_genre;
    }

    public void setNomGenre(String nom_genre){
        this.nom_genre=nom_genre;
    }
    
}

