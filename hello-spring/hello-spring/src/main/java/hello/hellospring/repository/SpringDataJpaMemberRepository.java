package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository  {
    //  JPOL select m from Member m where m.name = ?
    //  메서드 이름을 통해 자동 변환
    @Override
    Optional<Member> findByName(String name);
}
