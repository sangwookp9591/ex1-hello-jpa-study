package hello.jpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Embedded
    private Period workPeriod;

    @Embedded
    private Address homeAddress;

    @ElementCollection //값 타입 컬렉션
    @CollectionTable(name = "FAVORITE_FOODS" //테이블명 지정
            , joinColumns = @JoinColumn(name = "MEMBER_ID"))// @JoinColumn = ""외래키로 잡게된다.
    @Column(name = "FOOD_NAME") //얘는 string 하나이기때문에 특이하게 얘만 매핑하게 허용을 해줌. 값이하나고 정의한게 아니기떄문에 예외적으로 설정.
    private Set<String> favoriteFoods = new HashSet<>();

    //값타입 매핑
//    @ElementCollection //값 타입 컬렉션
//    @CollectionTable(name = "ADDRESS" //테이블명 지정
//            , joinColumns = @JoinColumn(name = "MEMBER_ID"))// @JoinColumn = ""외래키로 잡게된다.
//    private List<Address> addressHistory =  new ArrayList<>();

    //엔티티 매핑
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    // 양방향으로 ManyToOne으로 해도 되지만 얘는 특별한 경우이기 때문에 caseCade로 정리
    @JoinColumn(name= "MEMBER_ID")
    private List<AddressEntity> addressHistory = new ArrayList<>();
    //이렇게하면 값타입보다 쿼리 최적화에 훨씬 유횽하다


//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name="city",
//                    column=@Column(name = "HOME_CITY")),
//            @AttributeOverride(name="street",
//                    column=@Column(name = "HOME_STREET")),
//            @AttributeOverride(name="zipcode",
//                    column=@Column(name = "HOME_ZIPCODE"))
//    })
//    private Address workAddress;


//    @ManyToOne
//    @JoinColumn(name = "TEAM_ID")
//    private Team team;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public Team getTeam() {
//        return team;
//    }
//
//    public void setTeam(Team team) {
//        this.team = team;
//   }

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public List<AddressEntity> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<AddressEntity> addressHistory) {
        this.addressHistory = addressHistory;
    }
}