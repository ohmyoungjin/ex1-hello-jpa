package hellojpa.v3;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;


//양방향 연관 관계
public class JpaMainV3_study {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        //트랜잭션 시작
        tx.begin();
        try {
            //1.FK를 가지고 있는 객체에 값을 넣어줘야지 값이 들어간다
            //영속성 컨텍스트에는 1차 캐시 자기가 저장한 객체에 대한 값이 있으므로
            //PK객체에서

            TeamV3 team = new TeamV3();
            team.setName("BBB");
            em.persist(team);

            MemberV3 member = new MemberV3();
            member.setUsername("AAA");
            //member.setTeamV3(team);
            member.changeTeam(team);
            em.persist(member);

//            em.flush();
//            em.clear();
            //V2
            TeamV3 teamV3 = em.find(TeamV3.class, team.getId());
            Long teamId = teamV3.getId();

            MemberV3 findMemberV3 = em.find(MemberV3.class, teamId);
            TeamV3 v3Team = findMemberV3.getTeam();


            //V3
            TeamV3 findTeam = teamV3;
            List<MemberV3> findMember = findTeam.getMembers();
            for (MemberV3 m : findMember) {
                System.out.println("find Member = " + m.getTeam().getId());
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
