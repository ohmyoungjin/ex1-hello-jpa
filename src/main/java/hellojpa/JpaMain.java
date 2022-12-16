package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        //트랜잭션 시작
        tx.begin();
        try {
            em.persist(new Member(1L, "A"));
            Member member1 = em.find(Member.class,1L);
            System.out.println(member1);
            /* 저장 */
            //비영속 상태
//            em.persist(member);
            //*영속 상태* DB에 저장되진 않는다.
            System.out.println("BEFORE ====================");
            System.out.println("AFTER =====================");
            //***트랜잭션 commit 하는 시점에서 영속성 컨텍스트에 있는 값들이 DB에 들어가게 된다
            tx.commit();
        } catch (Exception e) {
                tx.rollback();
        } finally {
            //code 작성 이후 manager 및 factory 닫아주면 됨
            // jdbc 쓰는 것과 비슷함 resultset, connection 닫는 느낌?
            em.close();
        }
        emf.close();
    }
}
