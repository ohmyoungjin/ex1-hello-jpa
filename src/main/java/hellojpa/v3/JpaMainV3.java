package hellojpa.v3;

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

            Locker locker = new Locker();
            locker.setLockerName("1번");
            em.persist(locker);

            Locker locker2 = new Locker();
            locker2.setLockerName("2번");
            em.persist(locker2);

            MemberV3 member = new MemberV3();
            member.setUsername("member1");
            //**중요한 부분 연관 관계 주인한테 해당하는 FK를 넣어줘야 한다.
            //member => team 순서로 가면 member Table에는 TEAM_ID = null 이 된다.
            //insert 쿼리는 정상적으로 날아가기 때문에 디버깅이 힘들다.
            member.changeTeam(team);
            member.changeLocker(locker);
            em.persist(member);

            locker2.setLockerName("3번");
            MemberV3 findMember = em.find(MemberV3.class, member.getId());
            findMember.setLocker(locker2);


            //************************
            //양방향 연관 관계인 경우 두 객체 양쪽에 key값을 세팅을 해줘야한다.
            //해줘야 하는 이유 : 밑에서 em.flush , em.clear를 해주는 경우
            //영속성 컨텍스트를 비운 후 다시 select 한 값을 가져오게 되지만
            //밑의 flush , clear가 없는 경우 1차 캐시에 있는 member 객체의 값을 들고오기 때문에
            //team_id = null이 나온다.
            //양 쪽 다 key 세팅 해주자 !
            //team.getMembers().add(member);

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
