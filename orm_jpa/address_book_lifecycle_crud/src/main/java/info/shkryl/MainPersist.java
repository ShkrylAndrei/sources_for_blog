package info.shkryl;

import info.shkryl.entity.AddressBook;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainPersist {
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
            addressBook.setDescription("Водитель");

            em.persist(addressBook);

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
