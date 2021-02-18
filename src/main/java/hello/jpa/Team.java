package hello.jpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private long id;
    private String name;

    @OneToMany
    @JoinColumn(name = "TEAM_ID")
    private List<Member> members = new ArrayList<>();

//    //양방향
//    @OneToMany(mappedBy = "team")
//    //1:N 관계에서 뭐랑 MAPPING되있는 지를 알아야하기 때문에 mappendBy를 해준다. 여기서는 Member에 있는 team(변수명)과 매핑되어있는것!
//    //나는 변수 team이랑 mappedBy되어 있어
//    private List<Member> members = new ArrayList<>();
//    //이것은 관례이다 이렇게 해야지 add할때 nullPoint가 안뜨기 때문.


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
