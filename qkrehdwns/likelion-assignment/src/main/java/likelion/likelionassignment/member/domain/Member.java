package likelion.likelionassignment.member.domain;

import jakarta.persistence.*;
import likelion.likelionassignment.member.api.dto.request.MemberUpdateRequestDto;
import likelion.likelionassignment.post.api.domain.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    private int age;

    @Enumerated(EnumType.STRING)
    private Part part;

    private String email;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    private Member(String name, String email, int age, Part part) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.part = part;
    }

    public void update(MemberUpdateRequestDto memberUpdateRequestDto)  {
        this.name = memberUpdateRequestDto.name();
        this.age = memberUpdateRequestDto.age();
    }
}
