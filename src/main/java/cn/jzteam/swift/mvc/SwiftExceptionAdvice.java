package cn.jzteam.swift.mvc;

import cn.jzteam.swift.bean.ResponseResult;
import cn.jzteam.swift.enums.EnumSystemError;
import cn.jzteam.swift.exception.BizException;
import cn.jzteam.swift.i18n.LocaleUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
@ResponseBody
@RestControllerAdvice
public class SwiftExceptionAdvice {
    private static final Logger log = LoggerFactory.getLogger(LocaleUtil.class);
    /**
     * 200 - 正常返回
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BizException.class)
    public ResponseResult handleOkExBizException(final BizException e) {
        log.error("BizException {}", e.getMessage());
        return ResponseResult.build(e.getCode(), e.getMessage());
    }

    /**
     * 访问拒绝
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseResult handleException(final AccessDeniedException e) {
        log.error(HttpStatus.FORBIDDEN.getReasonPhrase(), e);
        return ResponseResult.build(EnumSystemError.FORBIDDEN);
    }

}