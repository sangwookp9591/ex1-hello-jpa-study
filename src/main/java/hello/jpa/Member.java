package hello.jpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Entity
//@SequenceGenerator(name = "member_seq_generator",sequenceName = "member_seq")
public class Member {
    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "member_seq_generator") //SEQUENCE
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",updatable = false)
    private String username;

    public Member() {
    }

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
}