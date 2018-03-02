package cn.jzteam.swift.enums;

import cn.jzteam.swift.i18n.LocaleUtil;

import java.util.Arrays;

public enum EnumSystemError implements IEnumBizError {
    SUCCESS(0, "error.code.sys.success"),
    SYSTEM_ERROR(1, "error.code.sys.error"),
    UNKNOWN_ERROR(-1, "error.code.sys.unknow"),
    API_DISABLED(-2, "error.code.sys.apidisabled"),
    HTTP_MESSAGE_NOT_READABLE(900, "error.code.sys.900"),
    DATA_VALIDATION_FAILURE(901, "error.code.sys.901"),
    DATA_BIND_VALIDATION_FAILURE(902, "error.code.sys.902"),
    SQL_EXECUTE_FAILURE(903, "error.code.sys.903"),
    METHOD_ARGUMENT_NOT_VALID(904, "error.code.sys.902"),
    BAD_REQUEST(400, "error.code.httpstatus.400"),
    UNAUTHORIZED(401, "error.code.httpstatus.401"),
    FORBIDDEN(403, "error.code.httpstatus.403"),
    NOT_FOUND(404, "error.code.httpstatus.404"),
    METHOD_NOT_ALLOWED(405, "error.code.httpstatus.405"),
    UNSUPPORTED_MEDIA_TYPE(415, "error.code.httpstatus.415"),
    INTERNAL_SERVER_ERROR(500, "error.code.httpstatus.500");

    private final int code;
    private final String message;

    private EnumSystemError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static EnumSystemError valueOf(int code) {
        return (EnumSystemError) Arrays.stream(values()).filter((x) -> {
            return x.getCode() == code;
        }).findFirst().orElse(UNKNOWN_ERROR);
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
//        return LocaleUtil.getMessage(this.message);
        // TODO 暂未开发国际化
        return this.message;
    }

    public String toString() {
        return "[" + this.getCode() + "]" + this.getMessage();
    }
}
