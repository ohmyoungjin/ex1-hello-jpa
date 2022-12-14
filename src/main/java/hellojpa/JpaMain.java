package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        //트랜잭션 시작
        tx.begin();
        try {
            /* 저장
                Member member = new Member();
                member.setId(1L);
                member.setName("HelloA");
                em.persist(meㄴmber);
            */
            /*수정*/
            Member findMember = em.find(Member.class, 1L);
            //update 개념은 트랜잭션이 해당하는 객체에 대한 데이터를 가지고 있다가
            //마지막 트랜잭션 커밋 시점에서 바뀐 데이터가 있으면 update 해준다.
            findMember.setName("helloJPA");
            //커밋
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
