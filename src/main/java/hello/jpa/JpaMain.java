package hello.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); //변수 추출하기 (Extract -> Variable) 변수 추출하기 (Extract -> Variable)
/*        EntityManagerFactory 는  application loading 시점에 딱하나만 만들어놔야한다.
          실제 db에 저장하는 transcation 단위 고객이 행우니 상품을 장바구니에 담을때마다 DB CONNECTION 을 얻어서 QUERY를 날리고 종료되는 한 일괄적인 단위를 할때마다 EM을 만들어줘야한다.
*/
        /*
        *JPA에선 Transcation 단위가 중요하다. 모든 data를 변경하는 작업은 transction 안에서 해야한다.
        * */
        EntityManager em = emf.createEntityManager(); //이름 일괄 변경하기 단축키 .. shift +f6

        EntityTransaction tx = em.getTransaction(); //emf가 고객의 요청이 올때마다 em을 생성한다.

        tx.begin();
        //code
        try {
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//            em.persist(member); //쿼리를 직접 만든적은 없지만 JPA가 Mapping 정보를 보고 직접 넣어준 것이다.

            //찾기
//            Member findMember = em.find(Member.class, 1L); //em.find (type, value)
            //수정
//            findMember.setName("HelloJPA");
            //setName을 한 이후 다시 persist로 저장을 할 필요가 없다. why? 자바 collection기준으로 만들어졋기 때문이다.
            //또한 JPA를 통해서 이렇게 Entity를 가져오면 JPA가 관리를 하게 된다. JPA가뭔가 변경이 됐는지 안됐는지 Transaction을 커밋하는 시점에 체크하고 뭔가 바껴있으면 update쿼리를 날린다.

            //JPQL -> 객체를 대상으로하는 객체 지향적 쿼리 -방언에 맞춰서 각DB에 맞게 표현을 해준다.
            //전체조회
            // 조회 대상이 일반 쿼리와 다르게 table 기준이아니라 객체이다. Member 객체 전체를 가지고와!
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .getResultList();
            //페이징 1번부터 ~몇개 가지고와
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();
            for (Member member : result) {
                System.out.println("member.name = "+member.getName());
            }
            tx.commit();
        } catch (Exception e){
            tx.rollback();
        }finally {
            em.close(); // EntityManager가 내부적으로 DB CONNECTION을 물고 동작하기때문에 사용다하면 자원해제를 꼭해줘야하낟.
        }

        //위의 코드는 실제로는 사용할 필요가없다 . Spring framework가 다해주기때문에.

        emf.close(); //WebApplication일 경우 was가 내려갈때 emf를 자원해제 해줘야한다 그래야 connection pooling이랑 resource가 내부적으로 release된다.
    }

}
