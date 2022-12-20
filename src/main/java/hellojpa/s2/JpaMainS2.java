package hellojpa.s2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

//단방향
public class JpaMainS2 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            TeamS2 team = new TeamS2();
            team.setName("GOOD");
            em.persist(team);

            MemberS2 member = new MemberS2();
            member.setUserName("maeng2");
            member.setTeam(team);
            em.persist(member);
            MemberS2 findMember = em.find(MemberS2.class, member.getId());
            System.out.println("찾은 팀 이름 : "+findMember.getTeam().getName());

            em.find(MemberS2.class, member.getId());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
