package hellojpa.V2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMainV2 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        //트랜잭션 시작
        tx.begin();
        try {
            TeamV2 team = new TeamV2();
            team.setName("TeamA");
            em.persist(team);
            //변경용 테이블
            TeamV2 team2 = new TeamV2();
            team2.setName("TeamB");
            em.persist(team2);

            MemberV2 member = new MemberV2();
            member.setUsername("member1");
            //위에서 저장한 객체 자체를 가져와서 담아준다 !
            //단방향 연관 관계, 참조 저장
            member.setTeam(team);
            em.persist(member);
            em.flush();
            MemberV2 findMember = em.find(MemberV2.class, member.getId());
            //객체마다 FK , PK를 가져와서 사용하는 것이 아닌,
            //FK , PK가 가지고 있는 객체 자체를 가져온다.
            //참조로 연관 관계 조회 - 객체 그래프 탐색
            TeamV2 findTeam = findMember.getTeam();
            System.out.println("findTeam = " + findTeam.getName());

            //연관 관계 수정
            TeamV2 newTeam = em.find(TeamV2.class, 2L);
            //Member 객체에 있는 (foreign key) 변경
            findMember.setTeam(newTeam);


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
