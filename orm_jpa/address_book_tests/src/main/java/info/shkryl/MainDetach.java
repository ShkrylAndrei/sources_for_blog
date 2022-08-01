package info.shkryl;

import info.shkryl.entity.AddressBook;
import org.hibernate.annotations.QueryHints;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class MainDetach {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = null;
        EntityManager em = null;
        try {
            emFactory = Persistence
                    .createEntityManagerFactory("PostgreSQLConnect");
            em = emFactory
                    .createEntityManager();

            em.getTransaction().begin();

            String queryString = "SELECT a FROM AddressBook a WHERE a.id=:id";
            Query query = em.createQuery(queryString)
                    .setHint(QueryHints.READ_ONLY, true);
            query.setParameter("id", 1);
            AddressBook addressBook = (AddressBook) query.getSingleResult();
            em.getTransaction().commit();

            System.out.println("Entity: " + addressBook);
            System.out.println("Is entity in entityManager (status Managed):" + em.contains(addressBook));

            em.detach(addressBook);

            System.out.println("Is entity in entityManager:" + em.contains(addressBook));
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
