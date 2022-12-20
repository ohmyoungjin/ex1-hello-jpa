package hellojpa.NtoN;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class JpaMainNTN {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        System.out.println("================");
        tx.begin();
        try {
            MemberNTN member = new MemberNTN();
            member.setUserName("AA");
            em.persist(member);

            Product product = new Product();
            product.setName("apple");
            em.persist(product);

            Order order = new Order();
            order.setOrderAmount(2);
            order.setOrderDate(LocalDateTime.now());
            order.setMember(member);
            order.setProduct(product);
            em.persist(order);




            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
