package hello.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); //변수 추출하기 (Extract -> Variable) Ctrl+Alt+v

        EntityManager em = emf.createEntityManager(); //이름 일괄 변경하기 단축키 .. shift +f6 emf가 고객의 요청이 올때마다 em을 생성한다.

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{

            Address address= new Address(
                    "city"
                    ,"street"
                    ,"zipcod");

            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(address);
            em.persist(member);

            Address copyAddress = new Address(
                    address.getCity()
                    ,address.getStreet()
                    ,address.getZipcode());

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setHomeAddress(copyAddress);
            em.persist(member2);

            //첫 번째 member의 주소를 newCity로 바꾸고 싶어.
            member.getHomeAddress().setCity("newCity");

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        }finally {
            em.close(); // EntityManager가 내부적으로 DB CONNECTION을 물고 동작하기때문에 사용다하면 자원해제를 꼭해줘야한다.
        }

        emf.close(); //WebApplication일 경우 was가 내려갈때 emf를 자원해제 해줘야한다 그래야 connection pooling이랑 resource가 내부적으로 release된다.
    }

}
