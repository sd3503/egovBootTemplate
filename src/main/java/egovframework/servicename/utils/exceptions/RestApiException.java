package egovframework.servicename.utils.exceptions;


import egovframework.servicename.utils.codeEnums.COMM_ERR_CODE;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RestApiException extends RuntimeException{
    private final COMM_ERR_CODE errorCode;

    public RestApiException(Throwable cause, COMM_ERR_CODE errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }
}
