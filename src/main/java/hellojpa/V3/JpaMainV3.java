package hellojpa.V3;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
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
            team.setName("TeamA");
            em.persist(team);
            //변경용 테이블
            TeamV3 team2 = new TeamV3();
            team2.setName("TeamB");
            em.persist(team2);

            MemberV3 member = new MemberV3();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            MemberV3 findMember = em.find(MemberV3.class, member.getId());
            List<MemberV3> members = findMember.getTeam().getMembers();

            for (MemberV3 m : members) {
                System.out.println("m = " + m.getUsername());
            }
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
