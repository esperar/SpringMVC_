package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    // 멤버 저장
    @Override
    public Member save(Member member) {
        // sequence 를 통해 중복을 검사합니다.
        member.setId(++sequence);
        // store 에 아이디값과 멤버를 저장합니다.
        store.put(member.getId() , member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id){
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Member> findAll(){
        // 모든 값들을 반환합니다.
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Member> findByName(String name){
        return store.values().stream().
                filter(member -> member.getName().equals(name)).
                findAny();
    }

    public void cleanStore(){
        store.clear();
    }
}
