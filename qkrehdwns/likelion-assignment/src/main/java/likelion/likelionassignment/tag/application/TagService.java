package likelion.likelionassignment.tag.application;

import likelion.likelionassignment.tag.api.dto.request.TagSaveRequestDto;
import likelion.likelionassignment.tag.api.dto.request.TagUpdateRequestDto;
import likelion.likelionassignment.tag.api.dto.response.TagInfoResponseDto;
import likelion.likelionassignment.tag.domain.Tag;
import likelion.likelionassignment.tag.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    @Transactional
    public Long saveTag(TagSaveRequestDto tagSaveRequestDto) {
        if (tagRepository.existsByName(tagSaveRequestDto.name())) {
            throw new IllegalArgumentException("이미 존재하는 태그 이름입니다.");
        }

        Tag tag = Tag.builder()
            .name(tagSaveRequestDto.name())
            .build();

        return tagRepository.save(tag).getTagId();
    }

    @Transactional(readOnly = true)
    public List<TagInfoResponseDto> getAllTags() {
        return tagRepository.findAll().stream()
            .map(TagInfoResponseDto::from)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TagInfoResponseDto getTag(Long tagId) {
        Tag tag = tagRepository.findById(tagId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 태그입니다."));
        return TagInfoResponseDto.from(tag);
    }

    @Transactional
    public void updateTag(Long tagId, TagUpdateRequestDto tagUpdateRequestDto) {
        Tag tag = tagRepository.findById(tagId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 태그입니다."));

        if (tagRepository.existsByName(tagUpdateRequestDto.name()) &&
            !tag.getName().equals(tagUpdateRequestDto.name())) {
            throw new IllegalArgumentException("이미 존재하는 태그 이름입니다.");
        }

        tag.updateName(tagUpdateRequestDto.name());
    }

    @Transactional
    public void deleteTag(Long tagId) {
        Tag tag = tagRepository.findById(tagId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 태그입니다."));

        tagRepository.delete(tag);
    }
}
