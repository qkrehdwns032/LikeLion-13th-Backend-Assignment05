package likelion.likelionassignment.post.api.domain;

import jakarta.persistence.*;
import likelion.likelionassignment.member.api.dto.request.PostUpdateRequestDto;
import likelion.likelionassignment.member.domain.Member;
import likelion.likelionassignment.posttag.domain.PostTag;
import likelion.likelionassignment.tag.domain.Tag;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostTag> postTags = new ArrayList<>();

    @Builder
    private Post(String title, String contents, Member member) {
        this.title = title;
        this.contents = contents;
        this.member = member;
    }

    // 태그 추가 메서드
    public void addTag(Tag tag) {
        PostTag postTag = PostTag.builder()
            .post(this)
            .tag(tag)
            .build();
        this.postTags.add(postTag);
    }

    // 태그들 일괄 추가
    public void addTags(List<Tag> tags) {
        if (tags != null) {
            tags.forEach(this::addTag);
        }
    }

    public void update(PostUpdateRequestDto postUpdateRequestDto) {
        this.title = postUpdateRequestDto.title();
        this.contents = postUpdateRequestDto.contents();
    }
}
