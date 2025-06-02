package likelion.likelionassignment.post.api.application;

import likelion.likelionassignment.member.domain.Member;
import likelion.likelionassignment.member.domain.repository.MemberRepository;
import likelion.likelionassignment.post.api.domain.Post;
import likelion.likelionassignment.post.api.domain.repository.PostRepository;
import likelion.likelionassignment.post.api.dto.request.PostSaveRequestDto;
import likelion.likelionassignment.post.api.dto.response.PostInfoResponseDto;
import likelion.likelionassignment.tag.domain.Tag;
import likelion.likelionassignment.tag.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final TagRepository tagRepository;

    @Transactional
    public void postSave(PostSaveRequestDto postSaveRequestDto) {
        Member member = memberRepository.findById(postSaveRequestDto.memberId())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        Post post = Post.builder()
            .title(postSaveRequestDto.title())
            .contents(postSaveRequestDto.contents())
            .member(member)
            .build();

        if (postSaveRequestDto.tagNames() != null && !postSaveRequestDto.tagNames().isEmpty()) {
            List<Tag> tags = getOrCreateTags(postSaveRequestDto.tagNames());
            post.addTags(tags);
        }

        postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public List<PostInfoResponseDto> getAllPosts() {
        return postRepository.findAll().stream()
            .map(PostInfoResponseDto::from)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PostInfoResponseDto getPost(Long postId) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));
        return PostInfoResponseDto.from(post);
    }

    private List<Tag> getOrCreateTags(List<String> tagNames) {
        return tagNames.stream()
            .distinct() // 중복 제거
            .map(tagName -> tagRepository.findByName(tagName)
                .orElseGet(() -> tagRepository.save(Tag.builder().name(tagName).build())))
            .collect(Collectors.toList());
    }
}
