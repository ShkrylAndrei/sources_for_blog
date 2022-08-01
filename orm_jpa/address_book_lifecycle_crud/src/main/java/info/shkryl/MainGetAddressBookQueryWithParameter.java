package info.shkryl;

import info.shkryl.entity.AddressBook;
import org.hibernate.annotations.QueryHints;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Iterator;
import java.util.List;

public class MainGetAddressBookQueryWithParameter {
    public static void main(String[] args) {
        try {
            EntityManagerFactory emFactory = Persistence
                    .createEntityManagerFactory("PostgreSQLConnect");
            EntityManager em = emFactory
                    .createEntityManager();

            em.getTransaction().begin();

            String queryString = "SELECT a FROM AddressBook a WHERE a.description=:description";
            Query query = em.createQuery(queryString)
                    .setHint(QueryHints.READ_ONLY, true);
            query.setParameter("description", "Водитель");
            List<AddressBook> addressBookList = (List<AddressBook>) query.getResultList();

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
