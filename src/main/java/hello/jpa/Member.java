package hello.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // jpa가 처음 로딩될때 jpa를 사용하는 애구나 얘를 관리해야겠다고 생각함
//@Table(name = "USER") 테이블 이름이 객체이름이랑 다를경우
public class Member {
    @Id // PK 설정
    private Long id;

    //@Column(name = "username") field 명이 column 이름이랑 다를경우.
    private String name;

    //JPA는 내부적으로 reflection을 쓰기때문에 동적으로 객체를 생성해놔야한다. 그래서 기본생성자가 필요하다.
    public Member() {

    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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
