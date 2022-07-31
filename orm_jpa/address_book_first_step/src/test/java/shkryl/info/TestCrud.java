//package shkryl.info;
//
//import info.shkryl.entity.AddressBook;
//import org.junit.jupiter.api.*;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
////TODO взял отсюда
////https://www.codejava.net/testing/junit-5-tutorial-for-beginner-test-crud-for-hibernate
////поменять название методов, а то будет плагиат
//public class TestCrud {
//    private static EntityManagerFactory emf;
//    private EntityManager em;
//
//    @BeforeAll
//    public static void setup() {
//        emf = Persistence.createEntityManagerFactory("h2Connection");
//        //TODO заменить вывод на log.info
//        System.out.println("EntityManagerFactory created.");
//    }
//
//    @AfterAll
//    public static void tearDown() {
//        if (emf != null) emf.close();
//        System.out.println("EntityManagerFactory destroyed.");
//    }
//
//    @Test
//    @Order(1)
//    public void testCreate() {
//        System.out.println("Running testCreate...");
//
//        em.getTransaction().begin();
//
//        AddressBook addressBook = new AddressBook();
//        addressBook.setFullName("Иванов Иван Иванович");
//        addressBook.setPhoneNumber("+7 987 654 1234");
//        addressBook.setDescription("Личный водитель");
//
//        Assertions.assertFalse(em.contains(addressBook));
//
//        em.persist(addressBook);
//
//        em.getTransaction().commit();
//
//        System.out.println("Сохранение сущности addressBook с ид = " + addressBook.getId());
//
//        Assertions.assertTrue(em.contains(addressBook));
//        Assertions.assertTrue(addressBook.getId() > 0);
//
//    }
//
//    @Test
//    @Order(2)
//    public void testUpdate() {
//        System.out.println("Running testUpdate...");
//
//        em.getTransaction().begin();
//        AddressBook addressBook = new AddressBook();
//        addressBook.setFullName("Иванов Иван Иванович");
//        addressBook.setPhoneNumber("+7 987 654 1234");
//        addressBook.setDescription("Личный водитель");
//
//        Assertions.assertFalse(em.contains(addressBook));
//        em.persist(addressBook);
//        em.getTransaction().commit();
//
//        em.getTransaction().begin();
//        addressBook.setFullName("Петров Петр Петрович");
//        em.merge(addressBook);
//        em.getTransaction().commit();
//
//        AddressBook addressBookFromEm = em.find(AddressBook.class, 1);
//
//        assertEquals("Петров Петр Петрович", addressBookFromEm.getFullName());
//    }
//
//    @Test
//    @Order(3)
//    public void testGet() {
//        System.out.println("Running testGet...");
//        Integer id = 1;
//        AddressBook addressBook = em.find(AddressBook.class,id);
//
//        assertEquals("Петров Петр Петрович", addressBook.getFullName());
//    }
//
//    @Test
//    public void testList() {
//        System.out.println("Running testList...");
//
//        Query<Product> query = session.createQuery("from Product", Product.class);
//        List<Product> resultList = query.getResultList();
//
//        Assertions.assertFalse(resultList.isEmpty());
//    }
//
//    @Test
//    public void testDelete() {
//        System.out.println("Running testDelete...");
//
//        Integer id = 1;
//        Product product = session.find(Product.class, id);
//
//        session.beginTransaction();
//        session.delete(product);
//        session.getTransaction().commit();
//
//        Product deletedProduct = session.find(Product.class, id);
//
//        Assertions.assertNull(deletedProduct);
//    }
//
//    @BeforeEach
//    public void openSession() {
//        em = emf.createEntityManager();
//        System.out.println("EntityManager created.");
//    }
//
//    @AfterEach
//    public void closeSession() {
//        if (em != null) em.close();
//        System.out.println("EntityManager closed.");
//    }
//}
