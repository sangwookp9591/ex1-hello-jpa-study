package hello.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS") // 추가
public class AddressEntity {
    @Id
    @GeneratedValue
    private Long id;

    private Address address; //값 타입

    public AddressEntity() {
    }

    public AddressEntity(Long id, Address address) {
        this.id = id;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
