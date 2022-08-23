package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /*
    회원가입 기능
     */
    public Long join(Member member){

        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }
    // 중복을 검사하는 기능
    private void validateDuplicateMember(Member member){
        memberRepository.findByName(member.getName()).
                ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*
    전체 회원 조회하기
     */
    public List<Member> findMember(){
        return memberRepository.findAll();
    }

    // 아이디로 회원 조회하기.
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}

