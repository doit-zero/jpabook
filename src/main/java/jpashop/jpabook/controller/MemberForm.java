package jpashop.jpabook.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * MemberForm을 쓰는 이유는 화면에서 넘어오는 validation과 domain에서 원하는 validation이 다를 수 있기 때문이다.
 * 화면에 핏한 폼 데이터를 만들고, 엔터티는 따로 만드는게 검증 관련해서도 낫다.
 * */
@Data
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수입니다!")
    private String name;
    private String city;
    private String street;
    private String zipcode;
}
