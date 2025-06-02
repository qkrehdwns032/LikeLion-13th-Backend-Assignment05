package likelion.likelionassignment.tag.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TagSaveRequestDto(
    @NotBlank(message = "태그 이름은 필수입니다.")
    @Size(min = 1, max = 20)
    String name
) {}
