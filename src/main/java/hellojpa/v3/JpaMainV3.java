package hellojpa.v3;

import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;


//양방향 연관 관계
public class JpaMainV3 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        //트랜잭션 시작
        tx.begin();
        try {
            MemberV3 member = new MemberV3();
            member.setUsername("myoung");
            member.setCreatedDate(LocalDateTime.now());

            em.persist(member);

            em.flush();
            em.clear();

            MemberV3 findMemberPRX = em.getReference(MemberV3.class, member.getId());
            System.out.println("findMemberPRX = " + findMemberPRX.getClass());
            Hibernate.initialize(findMemberPRX);
            System.out.println("강제초기화 이후 : " + (emf.getPersistenceUnitUtil().isLoaded(findMemberPRX)));

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

    private static void getPrintMember(MemberV3 member) {
        System.out.println("findMember id  = " + member.getId());
        System.out.println("findMember useranme  = " + member.getUsername());

    }

    private static void getPrintMemberAndTeam(MemberV3 member) {
        String userName = member.getUsername();
        System.out.println("userName = " + userName);

        TeamV3 findTeam = member.getTeam();
        System.out.println("findTeam = " + findTeam.getName());

    }
}
