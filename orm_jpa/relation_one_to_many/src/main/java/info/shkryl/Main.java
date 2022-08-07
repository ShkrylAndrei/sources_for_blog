package info.shkryl;

import info.shkryl.entity.Comment;
import info.shkryl.entity.Message;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

            Message message=new Message();
            message.setText("Мое сообщение");

            Comment comment1 = new Comment();
            comment1.setText("Сообщение 1");
            em.persist(comment1);
            Comment comment2 = new Comment();
            comment2.setText("Сообщение 2");
            em.persist(comment2);
            Set<Comment> setComment = new HashSet<>();
            setComment.add(comment1);
            setComment.add(comment2);

            message.setComment(setComment);
            em.persist(message);

            em.getTransaction().commit();

            System.out.println("Сообщение: "+message);
            System.out.println("Комментарий1: "+comment1);
            System.out.println("Комментарий2: "+comment2);

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
