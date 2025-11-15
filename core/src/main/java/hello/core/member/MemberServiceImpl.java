
// =======회원 서비스 구현체=========
package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
// impl: 인터페이스에 대한 구현체가 하나만 있을 때 관례적으로  사용
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    @Autowired
    // 생성자
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // MemberRepository(설계) 인터페이스가 아니라 MemoryMemberRepository(구현)에 있는 메서드가 호출된다(다형성)
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
