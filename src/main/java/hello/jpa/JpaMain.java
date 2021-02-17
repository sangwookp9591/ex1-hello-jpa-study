package hello.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member =  new Member();
            member.setUsername("member1");
            member.setTeam(team); //이러면 JPA가 알아서 TEAM에서 PK 값을 꺼내서 INSERT할때 FORGINE KEY 값을 사용한다.
            //주인에다가 team 변경
            em.persist(member);

            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class,team.getId());
            List<Member> members = findTeam.getMembers();
            for (Member m : members) {
                System.out.println("m = "+ m.getUsername());
            }
            //이렇게 하게 되면 캐시값을 초기화하고 다시 조회해서 가져온다.
            //이렇게 해야 깔끔하게 DB에서 값을 가져온다.

           // Member findMember = em.find(Member.class, member.getId());
            //Team findTeam = member.getTeam(); //조회시

            //Team 을 바꿀 경우
            //Team newTeam = em.find(Team.class, 100L);
            //findMember.setTeam(newTeam);

//            //양방향 연관관계
//            Member findMember = em.find(Member.class, member.getId());
//
//            List<Member> members = findMember.getTeam().getMembers();
//            for (Member m : members) {
//                System.out.println("m = "+ m.getUsername());
//            }
            tx.commit();
        } catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }

}
