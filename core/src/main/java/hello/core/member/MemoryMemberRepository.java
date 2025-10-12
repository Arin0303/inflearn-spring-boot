package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    // Map(설계): Long 타입 키로 Member 객체들을 저장
    // HashMap(구현): Map 설계를 바탕으로 실제 데이터 저장소 객체 store를 생성
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
