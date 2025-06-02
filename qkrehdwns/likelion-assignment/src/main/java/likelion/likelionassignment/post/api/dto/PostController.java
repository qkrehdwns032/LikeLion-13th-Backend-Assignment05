package likelion.likelionassignment.post.api.dto;

import jakarta.validation.Valid;
import likelion.likelionassignment.post.api.application.PostService;
import likelion.likelionassignment.post.api.dto.request.PostSaveRequestDto;
import likelion.likelionassignment.post.api.dto.response.PostInfoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping("/save")
    public ResponseEntity<String> postSave(@RequestBody @Valid PostSaveRequestDto postSaveRequestDto) {
        postService.postSave(postSaveRequestDto);
        return new ResponseEntity<>("게시물 저장 완료!", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostInfoResponseDto>> getAllPosts() {
        List<PostInfoResponseDto> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostInfoResponseDto> getPost(@PathVariable Long postId) {
        PostInfoResponseDto post = postService.getPost(postId);
        return ResponseEntity.ok(post);
    }
}
