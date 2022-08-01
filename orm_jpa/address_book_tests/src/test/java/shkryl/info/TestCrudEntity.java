package shkryl.info;

import info.shkryl.entity.AddressBook;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.QueryHints;
import org.junit.jupiter.api.*;

import javax.persistence.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class TestCrudEntity {
    private static EntityManagerFactory emf;
    private EntityManager em;

    @BeforeAll
    public static void setup() {
        emf = Persistence.createEntityManagerFactory("h2Connection");
        log.info("EntityManagerFactory created.");
    }

    @AfterAll
    public static void tearDown() {
        if (emf != null) emf.close();
        log.info("EntityManagerFactory destroyed.");
    }

    @BeforeEach
    public void createEntityManager() {
        em = emf.createEntityManager();
        log.info("EntityManager created.");
    }

    @AfterEach
    public void closeEntityManager() {
        if (em != null) em.close();
        log.info("EntityManager closed.");
    }

    @Test
    public void testCreate() {
        log.info("Running testCreate...");

        AddressBook addressBook = addRecordToAddressBook();

        log.info("Сохранение сущности addressBook с ид = " + addressBook.getId());

        Assertions.assertTrue(em.contains(addressBook));
        Assertions.assertTrue(addressBook.getId() > 0);
    }

    @Test
    public void testUpdate() {
        log.info("Running testUpdate...");

        AddressBook addressBook = addRecordToAddressBook();

        em.getTransaction().begin();
        addressBook.setFullName("Петров Петр Петрович");
        em.merge(addressBook);
        em.getTransaction().commit();

        assertEquals("Петров Петр Петрович", addressBook.getFullName());
    }

    @Test
    public void testGet() {
        log.info("Running testGet...");

        AddressBook addressBook = addRecordToAddressBook();

        String queryString = "SELECT a FROM AddressBook a WHERE a.fullName=:fullName";
        Query query = em.createQuery(queryString)
                .setHint(QueryHints.READ_ONLY, true);
        query.setParameter("fullName", "Иванов Иван Иванович");
        AddressBook entityFromDb = (AddressBook) query.getSingleResult();

        assertEquals("Иванов Иван Иванович", entityFromDb.getFullName());
    }

    @Test
    public void testList() {
        log.info("Running testList...");

        log.info("Add one record");
        AddressBook addressBook = addRecordToAddressBook();
        log.info("Add second record");
        AddressBook addressBook2 = addRecordToAddressBook();

        String queryString = "SELECT a FROM AddressBook a";
        Query query = em.createQuery(queryString)
                .setHint(QueryHints.READ_ONLY, true);
        List<AddressBook> addressBookList = (List<AddressBook>) query.getResultList();

        Assertions.assertFalse(addressBookList.isEmpty());
    }

    @Test
    public void testDelete() {
        log.info("Running testDelete...");

        AddressBook addressBook = addRecordToAddressBook();

        String queryString = "SELECT a FROM AddressBook a WHERE a.fullName=:fullName";
        Query query = em.createQuery(queryString);
        query.setParameter("fullName", "Иванов Иван Иванович");
        List<AddressBook> listEntityFromDb = (List<AddressBook>) query.getResultList();

        em.getTransaction().begin();
        int idDeleteEntity = listEntityFromDb.get(0).getId();
        em.remove(listEntityFromDb.get(0));
        em.getTransaction().commit();

        String queryStringAfterDelete = "SELECT a FROM AddressBook a WHERE a.id=:id";
        Query queryAfterDelete = em.createQuery(queryStringAfterDelete);
        queryAfterDelete.setParameter("id", idDeleteEntity);
        AddressBook entityFromDbAfterDelete=null;
        try {
            entityFromDbAfterDelete = (AddressBook) queryAfterDelete.getSingleResult();
        }catch (NoResultException e){
            log.info("No record found in DataBase");
        }

        Assertions.assertNull(entityFromDbAfterDelete);
    }

    AddressBook addRecordToAddressBook(){
        em.getTransaction().begin();
        AddressBook addressBook = new AddressBook();
        addressBook.setFullName("Иванов Иван Иванович");
        addressBook.setPhoneNumber("+7 987 654 1234");
        addressBook.setDescription("Водитель");

        Assertions.assertFalse(em.contains(addressBook));
        em.persist(addressBook);
        em.getTransaction().commit();
        return  addressBook;
    }
}
