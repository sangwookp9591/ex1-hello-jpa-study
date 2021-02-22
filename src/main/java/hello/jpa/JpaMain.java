package hello.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); //변수 추출하기 (Extract -> Variable) Ctrl+Alt+v

        EntityManager em = emf.createEntityManager(); //이름 일괄 변경하기 단축키 .. shift +f6 emf가 고객의 요청이 올때마다 em을 생성한다.

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{

           //갑타입 저장 예제
            Member member =new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity","street","zipcode"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new AddressEntity("old1","street","zipcode"));
            member.getAddressHistory().add(new AddressEntity("old2","street","zipcode"));

            em.persist(member);

            //조회 예제
//            em.flush();
//            em.clear();
//
//            System.out.println(" =========================START=========================");
//            Member findMember = em.find(Member.class, member.getId());
//
//            List<Address> addressHistory = findMember.getAddressHistory();
//            for (Address address : addressHistory) {
//                System.out.println("address.getCity() = " + address.getCity());
//            }
//
//            Set<String> favoriteFoods = findMember.getFavoriteFoods();
//            for (String favoriteFood : favoriteFoods) {
//                System.out.println("favoriteFood = " + favoriteFood);
//            }

//            //수정 예제
//            em.flush();
//            em.clear();
//
//            System.out.println(" =========================START=========================");
//            Member findMember = em.find(Member.class, member.getId());
//
//            //homeCity -> newCity
//
//            //findMember.getHomeAddress().setCity("newCity");
//            //이렇게 하면 되지 않나? -> x 이전에도 말했듯이 값타입이라는것은 이뮤터블 해야한다.
//            // why? 잘못하면 사이드 이팩트가 생기기 때문에!! 결론  setter를 쓰면안된다.
//            Address oldAddr = findMember.getHomeAddress();
//            findMember.setHomeAddress(new Address("newCity",oldAddr.getStreet(),oldAddr.getZipcode())); //아에 새로 넣어야한다.
//
//            //치킨 -> 한식
//            findMember.getFavoriteFoods().remove("치킨"); //치킨 지우고
//            findMember.getFavoriteFoods().add("한식"); //새로 add
//            //이것도 값타입이기때문에 통째로 갈아껴야한다 update를 할 수 없다.
//
//            //old1 -> new1
//            findMember.getAddressHistory().remove(new Address("old1","street","zipcode"));
//            //기본적인 컬렌셕은 대부분 이런 대상을 찾을 때 equals를 사용한다.
//            //그래서 아에 똑같은 것을 넣어야한다. 여기서 중요한게 이래서 equals와 hashCode를 제대로 구현해야한다.
//            // 제대로 구현하지 않으면 안지워진다.!
//            findMember.getAddressHistory().add(new AddressEntity("newCity1","street","zipcode"));

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        }finally {
            em.close(); // EntityManager가 내부적으로 DB CONNECTION을 물고 동작하기때문에 사용다하면 자원해제를 꼭해줘야한다.
        }

        emf.close(); //WebApplication일 경우 was가 내려갈때 emf를 자원해제 해줘야한다 그래야 connection pooling이랑 resource가 내부적으로 release된다.
    }

}
