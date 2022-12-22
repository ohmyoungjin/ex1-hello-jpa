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

            Child child1 = new Child();
            Child child2 = new Child();


            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);

            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class, parent.getId());

            em.remove(findParent.getId());
            //영속성 전이를 통한 자식 엔티티 자동 persist
            //em.persist(child1);
            //em.persist(child2);





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
