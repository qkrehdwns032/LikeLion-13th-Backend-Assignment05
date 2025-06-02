package likelion.likelionassignment.tag.api.dto;

import jakarta.validation.Valid;
import likelion.likelionassignment.tag.api.dto.request.TagSaveRequestDto;
import likelion.likelionassignment.tag.api.dto.request.TagUpdateRequestDto;
import likelion.likelionassignment.tag.api.dto.response.TagInfoResponseDto;
import likelion.likelionassignment.tag.application.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tags")
public class TagController {

    private final TagService tagService;

    @PostMapping
    public ResponseEntity<String> saveTag(@RequestBody @Valid TagSaveRequestDto tagSaveRequestDto) {
        Long tagId = tagService.saveTag(tagSaveRequestDto);
        return new ResponseEntity<>("태그 저장 완료! ID: " + tagId, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TagInfoResponseDto>> getAllTags() {
        List<TagInfoResponseDto> tags = tagService.getAllTags();
        return ResponseEntity.ok(tags);
    }

    @GetMapping("/{tagId}")
    public ResponseEntity<TagInfoResponseDto> getTag(@PathVariable Long tagId) {
        TagInfoResponseDto tag = tagService.getTag(tagId);
        return ResponseEntity.ok(tag);
    }

    @PutMapping("/{tagId}")
    public ResponseEntity<String> updateTag(@PathVariable Long tagId,
                                            @RequestBody @Valid TagUpdateRequestDto tagUpdateRequestDto) {
        tagService.updateTag(tagId, tagUpdateRequestDto);
        return ResponseEntity.ok("태그 수정 완료!");
    }

    @DeleteMapping("/{tagId}")
    public ResponseEntity<String> deleteTag(@PathVariable Long tagId) {
        tagService.deleteTag(tagId);
        return ResponseEntity.ok("태그 삭제 완료!");
    }
}
