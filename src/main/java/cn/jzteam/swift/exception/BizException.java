package cn.jzteam.swift.exception;

import cn.jzteam.swift.enums.IEnumBizError;

/**
 * 统一业务异常，不受检
 */
public class BizException extends RuntimeException {
    private int code;

    public BizException() {
        super();
    }

    public BizException(final IEnumBizError bizError) {
        super(bizError.getMessage());
        code = bizError.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
