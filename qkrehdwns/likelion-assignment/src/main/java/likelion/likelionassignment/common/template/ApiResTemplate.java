package likelion.likelionassignment.common.template;

import likelion.likelionassignment.common.error.ErrorCode;
import likelion.likelionassignment.common.error.SuccessCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResTemplate<T>{

    private final int code;
    private final String message;
    private T data; // 응답 데이터

    public static ApiResTemplate successWithNoContent(SuccessCode successCode) {
        return new ApiResTemplate<>(successCode.getHttpStatusCode(),successCode.getMessage());
    }

    public static  <T> ApiResTemplate<T> successResponse(SuccessCode successCode, T data){
        return new ApiResTemplate<>(successCode.getHttpStatusCode(),successCode.getMessage(), data);
    }

    public static ApiResTemplate errorResponse(ErrorCode errorCode, String customMessage) {
        return new ApiResTemplate<>(errorCode.getHttpStatusCode(), customMessage);
    }


}
