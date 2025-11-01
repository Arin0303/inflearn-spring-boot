package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach // 맨 처음에 무조건 실행
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }
    @Test
    void join() {
        // given(새 member 객체 생성)
        Member member = new Member(1L, "memberA", Grade.VIP);
        // when(memberService의 join과 findMember 실행)
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then(findMember가 반환한 객체와 임의로 생성한 객체 일치 확인)
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
