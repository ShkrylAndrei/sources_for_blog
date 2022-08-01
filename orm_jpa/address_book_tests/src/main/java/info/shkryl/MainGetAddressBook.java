package info.shkryl;

import info.shkryl.entity.AddressBook;
import org.hibernate.annotations.QueryHints;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Iterator;
import java.util.List;

public class MainGetAddressBook {
    public static void main(String[] args) {
        try {
            EntityManagerFactory emFactory = Persistence
                    .createEntityManagerFactory("PostgreSQLConnect");
            EntityManager em = emFactory
                    .createEntityManager();

            em.getTransaction().begin();

            List<AddressBook> addressBookList = (List<AddressBook>) em
                            .createQuery("SELECT a FROM AddressBook a")
                            .setHint(QueryHints.READ_ONLY, true)
                            .getResultList();
            System.out.println("List of address: ");
            Iterator i = addressBookList.iterator();
            AddressBook addressBook;
            while (i.hasNext()) {
                addressBook = (AddressBook) i.next();
                System.out.println(addressBook);
            }

            em.getTransaction().commit();
            em.close();
            emFactory.close();
        } catch (Exception e) {
            System.out.println("Exception was generated: ");
            e.printStackTrace();
        }
    }
}
