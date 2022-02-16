package com.exemple;
import java.io.Serializable;
import javax.persistence.*;

@Entity //définit quel objet sera enregistré en bdd
@Table(name="Livre")
public class Livre implements Serializable{
    @Id //définit la clé primaire
    //@GeneratedValue(strategy=GerationType.AUTO) génère un ID automatiquement
    @Column(name = "id", unique = true)
    private int id;   
    
    @Column(name="titre", nullable=false) //définit la colonne correspondante à la valeur titre
    private String titre;

    @Column(name="auteur", nullable=false) //définit la colonne correspondante à la valeur auteur
    private String auteur;

    @Column(name="note", nullable=true) //définit la colonne correspondante à la valeur note
    private int note;

    @Column(name="commentaire", nullable=true) //définit la colonne correspondante à la valeur commentaire
    private String commentaire;

    @Column(name="id_genre", nullable=false) //définit la colonne correspondante à la valeur note
    private int id_genre;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getTitre(){
        return titre;
    }

    public void setTitre(String titre){
        this.titre=titre;
    }

    public String getAuteur(){
        return auteur;
    }

    public void setAuteur(String auteur){
        this.auteur=auteur;
    }

    public String getCommentaire(){
        return commentaire;
    }

    public void setCommentaire(String commentaire){
        this.commentaire=commentaire;
    }

    public int getNote(){
        return note;
    }

    public void setNote(int note){
        this.note=note;
    }

    public int getIdGenre(){
        return id_genre;
    }

    public void setIdGenre(int id_genre){
        this.id_genre=id_genre;
    }
    
}

