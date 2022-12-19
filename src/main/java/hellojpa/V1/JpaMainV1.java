package hellojpa.V1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMainV1 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        //트랜잭션 시작
        tx.begin();
        try {
            //객체를 테이블에 맞춰어 데이터 중심으로 모델링하면, 협력 관계를 만들 수 없다.
            //밑과 같이 연관 관계 데이터가 객체 자체가 아닌 데이터로 저장하게 되면(teamId)
            //연관 관계가 있는 teamId로 해당하는 객체를 다시 찾아야 하는 번거로움이 있다.
            //이렇게 디자인 된 엔티티들은 객체지향적이 아닌 테이블을 객체 지향으로 맞춰 넣은 것이다.
            TeamV1 teamV1 = new TeamV1();
            teamV1.setName("TeamA");
            em.persist(teamV1);

            MemberV1 member = new MemberV1();
            member.setUsername("member1");
            member.setTeamId(teamV1.getId());
            em.persist(member);

            MemberV1 findMember = em.find(MemberV1.class, member.getId());

            Long findTeamId = findMember.getTeamId();
            TeamV1 findTeamV1 = em.find(TeamV1.class, findTeamId);


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
