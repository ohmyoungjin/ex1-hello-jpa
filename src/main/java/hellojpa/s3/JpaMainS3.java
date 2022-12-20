package hellojpa.s3;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMainS3 {

    public static void main(String[] args) {

        //EntityManagerFactory 생성
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("hello");
        //생성된 emf로 EntityManager 생성
        EntityManager em = emf.createEntityManager();
        //생성된 em으로 EntityTransaction 생성
        EntityTransaction tx = em.getTransaction();
        //트랜잭션 시작 (auto commit = false)
        tx.begin();

        try {
            //소속 팀 생성
            //한 인스턴스에 필드가 담겨져 있다. 너무 당연하지만
            //TeamS3 team = new TeamS3(); 정의하면 이 인스턴스에 대한 주소 값이 부여되고
            //이 인스턴스에 대한 값 ( 필드 , 함수 등) 가지고 있다.
            //양방향 연관 관계로 설계를 했지만 현재는 member에 대한 정보가 없기 때문에
            // member에 대한 세팅이 끝난 후 member에 대한 team 값을 array에 넣어준다.
            TeamS3 team = new TeamS3();
            team.setName("IT");
            //여기에서 persist를 해주는 이유는 table 관점으로 봤을 때
            //TEAM은 member에 대한 값을 가지고 있지 않다.
            //밑에서 사용하는 MemberList는 메모리공간에서 사용된다.
            em.persist(team);


            //1번 유저 등록
            MemberS3 member = new MemberS3();
            member.setUserName("maeng3");
            //외래키를 가지고 있는 객체에 PK에 대한 정보 값을 넣어주지 않게 되면
            //해당 컬럼은 빈 값이 된다.
            //꼭 넣어주도록 하자 !
            //member.setTeam(team);
            //연관관계 편의 메서드
            member.changeMember(team);
            em.persist(member);
            //2번 유저 등록
            MemberS3 member2 = new MemberS3();
            member2.setUserName("maeng5");
            //외래키를 가지고 있는 객체에 PK에 대한 정보 값을 넣어주지 않게 되면
            //해당 컬럼은 빈 값이 된다.
            //꼭 넣어주도록 하자 !
            //member2.setTeam(team);
            //연관 관계 편의 메서드
            member2.changeMember(team);
            em.persist(member2);


            //member에 대한 값이 세팅이 됐기 때문에 이 값을 넣어준다.
            //*로직 흐름으로 보게 되면 Team = > member = > team 같이 왔다갔다하면 헷갈린다 *
            //이를 편하게 사용하기 위해 [연관 관계 편의 메서드] 를 사용하자 !
            //team.getMemberList().add(member);
            //team.getMemberList().add(member2);

            TeamS3 findTeam = em.find(TeamS3.class, team.getId());
            List<MemberS3> memberList = findTeam.getMemberList();
            for (MemberS3 m : memberList) {
                System.out.println("소속팀에 해당된 선수는? : " + m.getUserName());
            }

            member.setUserName("maeng22");
            tx.commit();
        } catch (Exception e) {
            //에러 발생시 rollback
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
