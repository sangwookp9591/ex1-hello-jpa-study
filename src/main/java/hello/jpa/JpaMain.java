package hello.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); //변수 추출하기 (Extract -> Variable) 변수 추출하기 (Extract -> Variable)
/*        EntityManagerFactory 는  application loading 시점에 딱하나만 만들어놔야한다.
          실제 db에 저장하는 transcation 단위 고객이 행우니 상품을 장바구니에 담을때마다 DB CONNECTION 을 얻어서 QUERY를 날리고 종료되는 한 일괄적인 단위를 할때마다 EM을 만들어줘야한다.
*/
        /*
        *JPA에선 Tra
        * */
        EntityManager em = emf.createEntityManager(); //이름 일괄 변경하기 단축키 .. shift +f6
        //code
        Member member =new Member();
        member.setId(1L);
        member.setName("HelloA");

        em.persist(member);


        em.close();

        emf.close();
    }
}
