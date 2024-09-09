package jpashop.jpabook.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    // jpa 스펙상 리플렉션 기술을 이용하기 때문에 기본 생성자 필요
    // 최대한 다른 곳에서 쓸 수 없도록 protected로 함
    protected Address() {
    }
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
