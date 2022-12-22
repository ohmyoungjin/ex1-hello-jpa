package hellojpa.v3;

import org.hibernate.Hibernate;

import javax.persistence.*;
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
            TeamV3 team = new TeamV3();
            team.setName("AA");
            em.persist(team);

            TeamV3 team2 = new TeamV3();
            team2.setName("BB");
            em.persist(team2);

            MemberV3 member = new MemberV3();
            member.setUsername("myoung");
            member.setCreatedDate(LocalDateTime.now());
            member.changeTeam(team);
            em.persist(member);

            MemberV3 member2 = new MemberV3();
            member2.setUsername("myoung2");
            member2.setCreatedDate(LocalDateTime.now());
            member2.changeTeam(team2);

            em.persist(member2);

            em.flush();
            em.clear();

//            MemberV3 findMember = em.find(MemberV3.class, member.getId());
            List<MemberV3> members = em.createQuery("select m from MemberV3 m join fetch m.teamV3", MemberV3.class)
                    .getResultList();

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
