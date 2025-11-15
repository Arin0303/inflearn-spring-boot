
// =========메모리 회원 저장소 구현체==========
// 데이터베이스 확정이 되지 않았을 때 사용할 단순한 메모리 회원 저장소
package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository{

    // Map(설계): Long 타입 키로 Member 객체들을 저장
    // HashMap(구현): Map 설계를 바탕으로 실제 데이터 저장소 객체 store를 생성
    // HashMap은 동시성 이슈 발생 가능성 있음 -> ConcurrentHashMap 사용하기
    private static Map<Long, Member> store = new HashMap<>();
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
