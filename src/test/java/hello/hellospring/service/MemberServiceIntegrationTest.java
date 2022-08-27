package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

/*
   스프링 테스트는 통합테스트로 더 오래걸린다. (어쩔 수 없이 컨테이너를 올려야 하는 상황이오면 사용한다.)
   스프링 컨테이너 없이 하는 테스는 단위테스트로 빠르게 실행이된다.
   (최대한 단위 테스트로 진행하도록 노력해보자)
 */
@SpringBootTest // 스프링 컨테이너와 함께 테스트를 실행
@Transactional // 테스트 시작 전에 트랜잭션을 실행하고, 테스트 완료 후에 항상 롤백한다 >> DB에 데이터가 남지 않으므로 다음 테스트의 영향을 주지 않는다.
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;


    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    void 중복_회원_예외() {
        //Given

        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //When
        memberService.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2)); // 예외가 발생해야 한다.

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //Then
    }

}