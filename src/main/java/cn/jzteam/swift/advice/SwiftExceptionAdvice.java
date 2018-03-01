package cn.jzteam.swift.advice;

import java.nio.file.AccessDeniedException;

@ResponseBody
@RestControllerAdvice
public class SwiftExceptionAdvice {
    /**
     * 200 -
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(OkUsersBizException.class)
    public ResponseResult handleOkExBizException(final OkUsersBizException e) {
        log.error("OkExBizException {}", e.getMessage());
        return ResponseResult.failure(e.getCode(), e.getMessage());
    }

    /**
     * 访问拒绝
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseResult handleException(final AccessDeniedException e) {
        log.error(HttpStatus.FORBIDDEN.getReasonPhrase(), e);
        return ResponseResult.failure(SystemErrorCode.FORBIDDEN, e);
    }

    /**
     * 认证异常
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseResult handleAuthenticationException(final AuthenticationException e) {
        log.error(HttpStatus.UNAUTHORIZED.getReasonPhrase(), e);
        return ResponseResult.failure(SystemErrorCode.UNAUTHORIZED, e);
    }
}