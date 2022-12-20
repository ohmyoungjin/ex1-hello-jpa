package hellojpa.OneToN;

import hellojpa.s2.MemberS2;
import hellojpa.s2.TeamS2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

//1 : N 로직
//1이 연관관계 주인이다.
//가급적 사용하지 않는게 좋다.
//반대편 테이블의 외래 키를 관리하는 특이한 구조이다.
//@JoinColumn을 꼭 사용해야 함. 그렇지 않으면 조인 테이블
//방식을 사용함(중간에 테이블을 하나 추가함)
public class JpaMainOTN {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            MemberOTN1 member = new MemberOTN1();
            member.setUserName("maeng2");

            em.persist(member);

            TeamOTN1 team = new TeamOTN1();
            team.setName("ITPTPTPT");
            team.getMemberList().add(member);

            em.persist(team);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
