package likelion.likelionassignment.post.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record PostSaveRequestDto(
    @NotNull(message = "작성자는 필수로 입력해야 합니다.")
    Long memberId,

    @NotBlank(message = "제목은 필수로 입력해야 합니다.")
    @Size(min = 2, max = 100)
    String title,

    @NotBlank(message = "내용은 필수로 입력해야 합니다.")
    @Size(min = 2, max = 1000)
    String contents,

    List<String> tagNames  // 태그 이름들로 받음
) {}
