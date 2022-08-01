package info.shkryl;

import info.shkryl.entity.AddressBook;
import org.hibernate.annotations.QueryHints;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class MainRemoveEntity {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = null;
        EntityManager em = null;
        try {
            emFactory = Persistence
                    .createEntityManagerFactory("PostgreSQLConnect");
            em = emFactory
                    .createEntityManager();

            em.getTransaction().begin();

            AddressBook addressBook = new AddressBook();
            addressBook.setFullName("Иванов Иван Иванович");
            addressBook.setPhoneNumber("+7 111 222 3333");
            addressBook.setDescription("Личный водитель");
            em.persist(addressBook);

            String queryString = "SELECT a FROM AddressBook a WHERE a.id=:id";
            Query query = em.createQuery(queryString)
                    .setHint(QueryHints.READ_ONLY, true);
            query.setParameter("id", 1);
            AddressBook resultQuery = (AddressBook) query.getSingleResult();

            System.out.println("Original object addressBook: "+resultQuery);

            em.remove(resultQuery);
            em.getTransaction().commit();
            System.out.println("Entity was removed.");
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
