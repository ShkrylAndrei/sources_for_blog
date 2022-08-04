package info.shkryl;

import info.shkryl.entity.Shape;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TablePrimaryKey {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = null;
        EntityManager em = null;
        try {
            emFactory = Persistence
                    .createEntityManagerFactory("PostgreSQLConnect");
            em = emFactory
                    .createEntityManager();

            em.getTransaction().begin();

            Shape square = new Shape();
            square.setName("квадрат");

            Shape rectangle = new Shape();
            rectangle.setName("треугольник");

            em.persist(square);
            em.persist(rectangle);

            em.getTransaction().commit();

            System.out.println("Id for square: " + square.getId());
            System.out.println("Id for rectangle: "+rectangle.getId());

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
