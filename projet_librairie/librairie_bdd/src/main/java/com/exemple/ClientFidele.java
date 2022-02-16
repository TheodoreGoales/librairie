package com.exemple;
import java.io.Serializable;
import javax.persistence.*;

@Entity //définit quel objet sera enregistré en bdd
@Table(name="clients_fidele")
public class ClientFidele implements Serializable{
    @Id //définit la clé primaire
    //@GeneratedValue(strategy=GerationType.AUTO) génère un ID automatiquement
    @Column(name = "id", unique = true)
    private int id;   
    
    @Column(name="nom", nullable=false) //définit la colonne correspondante à la valeur titre
    private String nom;

    @Column(name="prenom", nullable=false) //définit la colonne correspondante à la valeur auteur
    private String prenom;

    @Column(name="age", nullable=false) //définit la colonne correspondante à la valeur note
    private int age;

    @Column(name="duree_membre", nullable=false) //définit la colonne correspondante à la valeur commentaire
    private int duree_membre;

    @Column(name="livre_1", nullable=false) //définit la colonne correspondante à la valeur note
    private int livre_1;

    @Column(name="livre_2", nullable=false) //définit la colonne correspondante à la valeur note
    private int livre_2;

    @Column(name="genre_id", nullable=false) //définit la colonne correspondante à la valeur note
    private int genre_id;

    public String getNom(){
        return nom;
    }

    public void setNom(String nom){
        this.nom=nom;
    }

    public String getPrenom(){
        return prenom;
    }

    public void setPrenom(String prenom){
        this.prenom=prenom;
    }


    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age=age;
    }

    public int getDureeMembre(){
        return duree_membre;
    }

    public void setDureeMembre(int duree_membre){
        this.duree_membre=duree_membre;
    }

    public int getLivre1(){
        return livre_1;
    }

    public void setLivre1(int livre_1){
        this.livre_1=livre_1;
    }

    public int getLivre2(){
        return livre_2;
    }

    public void setLivre2(int livre_2){
        this.livre_2=livre_2;
    }

    public int getGenreId(){
        return genre_id;
    }

    public void setGenreId(int genre_id){
        this.genre_id=genre_id;
    }
    
}

