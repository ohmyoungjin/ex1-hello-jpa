package hellojpa.resulttype;


import hellojpa.v3.Child;
import hellojpa.v3.Parent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        //트랜잭션 시작
        tx.begin();
        try {
            Address address = new Address("city", "street", "1000");

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setHomeAddress(address);
            member1.setPeriod(new Period(LocalDateTime.now(), LocalDateTime.now()));
            em.persist(member1);


            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setHomeAddress(address);
            member2.setPeriod(new Period(LocalDateTime.now(), LocalDateTime.now()));
            em.persist(member2);

            member1.getAddress().setCity("new City");


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.out.println("Exception : " + e);
        } finally {
            //code 작성 이후 manager 및 factory 닫아주면 됨
            // jdbc 쓰는 것과 비슷함 resultset, connection 닫는 느낌?
            em.close();
        }
        emf.close();
    }


}
