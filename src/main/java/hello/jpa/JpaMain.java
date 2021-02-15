package hello.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); //변수 추출하기 (Extract -> Variable) Ctrl+Alt+v
/*        EntityManagerFactory 는  application loading 시점에 딱하나만 만들어놔야한다.
          실제 db에 저장하는 transcation 단위 고객이 행위, 상품을 장바구니에 담을때마다 DB CONNECTION 을 얻어서 QUERY를 날리고 종료되는 한 일괄적인 단위를 할때마다 EM을 만들어줘야한다.
*/

        EntityManager em = emf.createEntityManager(); //이름 일괄 변경하기 단축키 .. shift +f6 emf가 고객의 요청이 올때마다 em을 생성한다.

        /*
         *JPA에선 Transcation 단위가 중요하다. 모든 data를 변경하는 작업은 transction 안에서 해야한다.
         * */
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{
//            //비영속
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("HelloJPA");
//
//            //영속
//            System.out.println("=== BEFORE ===");
//            em.persist(member);
//            System.out.println("=== AFTER ===");
//
//            Member findMember = em.find(Member.class, 101L);
//            //find를 했는데 왜 select 쿼리가 나오지 않는가? persist할때 값을 저장하고 1차 캐시에 저장했기 때문이다.!
//
//            System.out.println("findMember.id = "+findMember.getId());
//            System.out.println("findMember.name = "+findMember.getName());

//            Member findMember1 = em.find(Member.class, 101L);
//            Member findMember2 = em.find(Member.class, 101L);
//
//            System.out.println("result = "+ (findMember1 == findMember2));
//
//            Member member1 = new Member(150l,"A");
//            Member member2 = new Member(160l,"B");
//
//            em.persist(member1);
//            em.persist(member2);
            //영속성 컨텍스트 1차 캐시 및 쓰기 지연 sql 저장소에 차곡차곡 저장이 된다.
            //<property name="hibernate.jdbc.batch_size" value="10"/> 를 하게 되면 , size만큼 모아서 db에 한번에 network로 query를 보낸다.

            Member member = new Member(200l,"member200");
            em.persist(member);

            em.flush(); //db에 쿼리 즉시 발생
            System.out.println("=======================");
            tx.commit(); //commit시점에 query가 날라가게 된다.
        } catch (Exception e){
            tx.rollback();
        }finally {
            em.close(); // EntityManager가 내부적으로 DB CONNECTION을 물고 동작하기때문에 사용다하면 자원해제를 꼭해줘야한다.
        }
        //code
//        try {
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
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(10)
//                    .getResultList();
//            for (Member member : result) {
//                System.out.println("member.name = "+member.getName());
//            }
//            tx.commit(); //트랜젝션 commit 하는 순간에 영속성 컨텍스트가 db에 날라가게 된다.
//        } catch (Exception e){
//            tx.rollback();
//        }finally {
//            em.close(); // EntityManager가 내부적으로 DB CONNECTION을 물고 동작하기때문에 사용다하면 자원해제를 꼭해줘야하낟.
//        }

        //위의 코드는 실제로는 사용할 필요가없다 . Spring framework가 다해주기때문에.

        emf.close(); //WebApplication일 경우 was가 내려갈때 emf를 자원해제 해줘야한다 그래야 connection pooling이랑 resource가 내부적으로 release된다.
    }
    //영속성 컨텍스트 -> 실제 JPA가 내부에서 어떻게 동작해.
    //비영속상태 JPA와 전혀 관계가 없는 상태
    //em.remove 객체 삭제 실제로 db삭제를 요청한 상태
}
