package likelion.likelionassignment.member.domain.repository;

import likelion.likelionassignment.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
