package info.shkryl;

import info.shkryl.entity.Subscribe;
import info.shkryl.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;
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

            User user1 = new User();
            user1.setLogin("user1");
            user1.setEmail("user1@user.ru");
            User user2 = new User();
            user2.setLogin("user2");
            user2.setEmail("user2@user.ru");

            Subscribe subscribe1 = new Subscribe();
            subscribe1.setName("Subscribe1");
            Set<User> userSet1 = new HashSet<>();
            userSet1.add(user1);
            userSet1.add(user2);
            subscribe1.setUserList(userSet1);
            em.persist(subscribe1);

            Subscribe subscribe2 = new Subscribe();
            subscribe2.setName("Subscribe2");
            Set<User> userSet2 = new HashSet<>();
            userSet2.add(user1);
            userSet2.add(user2);
            subscribe2.setUserList(userSet2);
            em.persist(subscribe2);

            em.persist(user1);
            em.persist(user2);

            em.getTransaction().commit();

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
