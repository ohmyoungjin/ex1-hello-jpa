package hellojpa.s1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMainS1 {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        //트랜잭션 시작
        tx.begin();
        try {
            TeamS1 team = new TeamS1();
            //팀 이름
            team.setName("BBB");
            //팀 생성
            em.persist(team);
            MemberS1 member = new MemberS1();
            //유저 이름
            member.setUsername("AAA");
            member.setTeamId(team.getId());
            em.persist(member);

            //멤버 객체에서 team에 대한 정보 조회
            MemberS1 findMember = em.find(MemberS1.class, member.getId());
            Long teamId = findMember.getTeamId();

            TeamS1 findTeam = em.find(TeamS1.class, teamId);
            System.out.println("User name : + " + member.getUsername()+ " Team name : " + findTeam.getName());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
