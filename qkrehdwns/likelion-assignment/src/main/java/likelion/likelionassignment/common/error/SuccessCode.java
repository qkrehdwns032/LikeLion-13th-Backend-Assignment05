package likelion.likelionassignment.common.error;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {

    GET_SUCCESS(HttpStatus.OK, "성공적으로 조회되었습니다."),
    MEMBER_UPDATE_SUCCESS(HttpStatus.OK, "성공적으로 수정되었습니다."),
    POST_UPDATE_SUCCESS(HttpStatus.OK, "성공적으로 수정되었습니다."),
    POST_DELETE_SUCCESS(HttpStatus.OK, "성공적으로 삭제되었습니다."),
    MEMBER_DELETE_SUCCESS(HttpStatus.OK, "성공적으로 삭제되었습니다."),

    MEMEBER_SAVE_SUCCESS(HttpStatus.CREATED, "성공적으로 저장되었습니다."),
    POST_SAVE_SUCCESS(HttpStatus.CREATED, "성공적으로 저장되었습니다.");

    private final HttpStatus httpStatus;
    private final String message;


    public int getHttpStatusCode() {
        return httpStatus.value();
    }

}
