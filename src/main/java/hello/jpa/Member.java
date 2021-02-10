package hello.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // jpa가 처음 로딩될때 jpa를 사용하는 애구나 얘를 관리해야겠다고 생각함
public class Member {
    @Id // PK 설정
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
