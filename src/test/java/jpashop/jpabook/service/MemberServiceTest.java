package jpashop.jpabook.service;

import jpashop.jpabook.domain.Member;
import jpashop.jpabook.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest // 스프링을 띄운 상태로 테스트를 진행할 수 있게 함
@Transactional
//@Rollback(false)를 쓰면 db에 들어간 것을 확인 할 수 있다.
public class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;


    @Test
    public void 회원가입_test() throws Exception{
    //given
        Member member = new Member();
        member.setName("Yu");
    //when
        Long savedId = memberService.join(member);
        System.out.println(member.getClass());
    //then
        Assertions.assertThat(member).isEqualTo(memberRepository.findOne(savedId));
    }
    @Test
    public void 중복_회원_예외_test() throws Exception{
    //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");
    //when
        memberService.join(member1);
        try {
            memberService.join(member2); //예외가 발생해야 함.
        } catch (IllegalStateException e){
            return;
        }
        Assertions.fail("예외가 발생해야 한다.");


    //then

    }
}
