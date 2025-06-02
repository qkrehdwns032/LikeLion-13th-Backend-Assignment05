package likelion.likelionassignment.post.api.domain.repository;

import likelion.likelionassignment.member.domain.Member;
import likelion.likelionassignment.post.api.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByMember(Member member);
}
