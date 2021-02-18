package hello.jpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    //@Column(name = "TEAM_ID")
    //private Long teamId;

    @ManyToOne //member랑 team이랑 쿼리가 분리되서 나간다
    @JoinColumn(name = "TEAM_ID") //team reference랑 테이블 연관관계의 team_id랑 join해야함. //intellj에서 name매핑에  빨간줄이 뜨는 것은 DB에 실제로 값이 있어야 찍히는거라서 무시해도된다.
    private Team team;//그냥 이대로 쓰면 error가 난다. jpa한태 말해줘야함 이둘의 관계가 1:N인지 N:1인지
    //맴버입장에서는 하나의 팀에 여러 맴버가 소속되기 때문에 맴버가 N 팀 1

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    //객체 지향 모델링할때는 2개를 설정해야한다. 관계가 뭔지?,이 관계를 할때 조인하는 컬럼은 뭐야?

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> membserProducts =new ArrayList<>();


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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;

        //team.setMembers(this);
    }

//    @Override
//    public String toString() {
//        return "Member{" +
//                "id=" + id +
//                ", username='" + username + '\'' +
//                ", team=" + team +
//                '}';
//    }
}