package com.exemple;
import java.util.List;

import javax.persistence.*;

import com.mysql.cj.xdevapi.Client;

public class TestSystem{

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("persistence");

    public static void main(String[] args){
        System.out.println("##########################");
        /**updateNote(4, 6);
        updateNote(4, 2);
        updateCategorie(4, 3);
        updateCommentaire(4, "commentaire modifié par le programme");
        getLivre(4);
        updateGenre(1, "Dramaturgie");
        addGenre("SciFi");
        getGenres();
        addClient("GOALES", "Théodore", 22, 5, 5, 11, 2);
        addClient("CICEK", "Erdem", 22, 5, 5, 11, 2);
        deleteClient(9);
        updateClient(10, "CICEK", "Erdem", 22, 5, 5, 11, 1);*/
        getClients();
        System.out.println("##########################");
        ENTITY_MANAGER_FACTORY.close();
    }

    public static void getLivre(int pId){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT l FROM Livre l WHERE l.id = :id";
        TypedQuery<Livre> typedQuery = em.createQuery(query, Livre.class);
        typedQuery.setParameter("id", pId);
        Livre livre = null;
        try{
            livre = typedQuery.getSingleResult();
            System.out.println(livre.getId()+" "+livre.getTitre()+" "+livre.getAuteur()+" "+livre.getNote()+" "+livre.getCommentaire()+" "+getGenre(livre.getIdGenre()));
        }catch(NoResultException error){
            error.printStackTrace();
        }finally{
            em.close();
        }
    }

    public static void getLivres(){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT l FROM Livre l";
        TypedQuery typedQuery = em.createQuery(query, Livre.class);
        List<Livre> LivreListe;
        try{
            LivreListe = typedQuery.getResultList();
            LivreListe.forEach(livre -> System.out.println(livre.getTitre()+" "+livre.getAuteur()+" "+livre.getNote()+" "+livre.getCommentaire()+" "+getGenre(livre.getIdGenre())));
        }catch(NoResultException error){
            error.printStackTrace();
        }finally{
            em.close();
        }
    }

    public static String getLivreTitre(int pId){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT l FROM Livre l WHERE l.id = :id";
        TypedQuery<Livre> typedQuery = em.createQuery(query, Livre.class);
        typedQuery.setParameter("id", pId);
        Livre livre = null;
        try{
            livre = typedQuery.getSingleResult();
            return livre.getTitre();
        }catch(NoResultException error){
            error.printStackTrace();
        }finally{
            em.close();
        }
        return null;
    }

    public static void addLivre(String titre, String auteur, int note, String commentaire, int id_genre){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try{
            et=em.getTransaction();
            et.begin();
            Livre livre = new Livre();
            livre.setTitre(titre);
            livre.setAuteur(auteur);
            livre.setNote(note);
            livre.setCommentaire(commentaire);
            livre.setIdGenre(id_genre);
            em.persist(livre);
            et.commit();
        }catch(NoResultException error){
            if(et!=null){
                et.rollback();
            }
            error.printStackTrace();
        }finally{
            em.close();
        }
    }

    public static void addLivre(String titre, String auteur, String commentaire, int id_genre){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try{
            et=em.getTransaction();
            et.begin();
            Livre livre = new Livre();
            livre.setTitre(titre);
            livre.setAuteur(auteur);
            livre.setCommentaire(commentaire);
            livre.setIdGenre(id_genre);
            em.persist(livre);
            et.commit();
        }catch(NoResultException error){
            if(et!=null){
                et.rollback();
            }
            error.printStackTrace();
        }finally{
            em.close();
        }
    }

    public static void updateNote(int pId, int note){
        if(note<6 && note>0){
            EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
            EntityTransaction et = null;
            Livre livre = null;
            try{
                et = em.getTransaction();
                et.begin();
                livre = em.find(Livre.class, pId);
                livre.setNote(note);
                System.out.println("La note de " + livre.getTitre() + " a été changée en " + livre.getNote());
                em.persist(livre);
                et.commit();
            }catch (NoResultException error){
                if(et != null){
                    et.rollback();
                }
                error.printStackTrace();
            }finally{ // S'exécute peu importe le résultat que ce soit un succès ou un échec
                em.close(); // Éviter les pertes de mémoires
            }
        }else{
            System.out.println("La note doit être comprise entre 1 et 5");
        }
    }

    public static void updateCategorie(int pId, int id_genre){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Livre livre = null;
        try{
            et = em.getTransaction();
            et.begin();
            livre = em.find(Livre.class, pId);
            livre.setIdGenre(id_genre);
            System.out.println("Le genre de " + livre.getTitre() + " a été changée en " + getGenre(livre.getIdGenre()));
            em.persist(livre);
            et.commit();
        }catch (NoResultException error){
            if(et != null){
                et.rollback();
            }
            error.printStackTrace();
        }finally{ // S'exécute peu importe le résultat que ce soit un succès ou un échec
            em.close(); // Éviter les pertes de mémoires
        }
    }
   
    public static void updateCommentaire(int pId, String commentaire){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Livre livre = null;
        try{
            et = em.getTransaction();
            et.begin();
            livre = em.find(Livre.class, pId);
            livre.setCommentaire(commentaire);
            System.out.println("Le commenaitre de " + livre.getTitre() + " a été changée en " + livre.getCommentaire());
            em.persist(livre);
            et.commit();
        }catch (NoResultException error){
            if(et != null){
                et.rollback();
            }
            error.printStackTrace();
        }finally{ // S'exécute peu importe le résultat que ce soit un succès ou un échec
            em.close(); // Éviter les pertes de mémoires
        }
    }
   
    public static String getGenre(int pId){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT g FROM Genre g WHERE g.id = :id";
        TypedQuery<Genre> typedQuery = em.createQuery(query, Genre.class);
        typedQuery.setParameter("id", pId);
        Genre genre = null;
        try{
            genre = typedQuery.getSingleResult();
            return genre.getNomGenre();
        }catch(NoResultException error){
            error.printStackTrace();
        }finally{
            em.close();
        }
        return null;
    }

    public static void getGenres(){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT g FROM Genre g";
        TypedQuery typedQuery = em.createQuery(query, Genre.class);
        List<Genre> GenreListe;
        try{
            GenreListe = typedQuery.getResultList();
            GenreListe.forEach(genre -> System.out.println(genre.getNomGenre()));
        }catch(NoResultException error){
            error.printStackTrace();
        }finally{
            em.close();
        }
    }

    public static void updateGenre(int pId, String nom_genre){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Genre genre = null;
        try{
            et = em.getTransaction();
            et.begin();
            genre = em.find(Genre.class, pId);
            System.out.print("Le genre " + genre.getNomGenre());
            genre.setNomGenre(nom_genre);
            System.out.println(" a été changée en " + genre.getNomGenre());
            em.persist(genre);
            et.commit();
        }catch (NoResultException error){
            if(et != null){
                et.rollback();
            }
            error.printStackTrace();
        }finally{ // S'exécute peu importe le résultat que ce soit un succès ou un échec
            em.close(); // Éviter les pertes de mémoires
        }
    }

    public static void addGenre(String nom_genre){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try{
            et=em.getTransaction();
            et.begin();
            Genre genre = new Genre();
            genre.setNomGenre(nom_genre);
            em.persist(genre);
            et.commit();
        }catch(NoResultException error){
            if(et!=null){
                et.rollback();
            }
            error.printStackTrace();
        }finally{
            em.close();
        }
    }

    public static void getClients(){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT c FROM ClientFidele c";
        TypedQuery typedQuery = em.createQuery(query, ClientFidele.class);
        List<ClientFidele> clientFidele;
        try{
            clientFidele = typedQuery.getResultList();
            clientFidele.forEach(client -> System.out.println("Le client "+client.getPrenom()+" "+client.getNom()+" a "+client.getAge()+" ans et est membre depuis "+client.getDureeMembre()+" ans. Ses livres favoris sont "+getLivreTitre(client.getLivre1())+" et "+getLivreTitre(client.getLivre2())+", et il n'aime pas le genre "+getGenre(client.getGenreId())));
        }catch(NoResultException error){
            error.printStackTrace();
        }finally{
            em.close();
        }
    }

    public static void addClient(String nom, String prenom, int age, int duree_membre, int livre_1, int livre_2, int genre_id){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try{
            et=em.getTransaction();
            et.begin();
            ClientFidele client = new ClientFidele();
            client.setNom(nom);
            client.setPrenom(prenom);
            client.setAge(age);
            client.setDureeMembre(duree_membre);
            client.setLivre1(livre_1);
            client.setLivre2(livre_2);
            client.setGenreId(genre_id);
            em.persist(client);
            et.commit();
        }catch(NoResultException error){
            if(et!=null){
                et.rollback();
            }
            error.printStackTrace();
        }finally{
            em.close();
        }
    }

    public static void deleteClient(int pId){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        ClientFidele client = null;
        try{
            et=em.getTransaction();
            et.begin();
            client=em.find(ClientFidele.class, pId);
            em.remove(client);
            et.commit();
        }catch(NoResultException error){
            if(et!=null){
                et.rollback();
            }
            error.printStackTrace();
        }finally{
            em.close();
        }
    }

    public static void updateClient(int pId, String nom, String prenom, int age, int duree_membre, int livre_1, int livre_2, int genre_id){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        ClientFidele client = null;
        try{
            et = em.getTransaction();
            et.begin();
            client = em.find(ClientFidele.class, pId);
            client.setNom(nom);
            client.setPrenom(prenom);
            client.setAge(age);
            client.setDureeMembre(duree_membre);
            client.setLivre1(livre_1);
            client.setLivre2(livre_2);
            client.setGenreId(genre_id);
            em.persist(client);
            et.commit();
        }catch (NoResultException error){
            if(et != null){
                et.rollback();
            }
            error.printStackTrace();
        }finally{ // S'exécute peu importe le résultat que ce soit un succès ou un échec
            em.close(); // Éviter les pertes de mémoires
        }
    }

}
