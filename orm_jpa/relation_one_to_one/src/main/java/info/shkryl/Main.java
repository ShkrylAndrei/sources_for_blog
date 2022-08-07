package info.shkryl;

import info.shkryl.entity.Author;
import info.shkryl.entity.Message;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = null;
        EntityManager em = null;
        try {
            emFactory = Persistence
                    .createEntityManagerFactory("PostgreSQLConnect");
            em = emFactory
                    .createEntityManager();

            em.getTransaction().begin();

            Author author = new Author();
            author.setAuthor("Иванов");

            Message message = new Message();
            message.setMessage("Приветственное сообщение");
            em.persist(message);
            author.setMessage(message);
            em.persist(author);

            em.getTransaction().commit();

            System.out.println(author);
        } catch (Exception e) {
            System.out.println("Exception was generated: ");
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            if (emFactory != null) {
                emFactory.close();
            }
        }
    }
}
