package cn.jzteam.swift.enums;

import cn.jzteam.swift.i18n.LocaleUtil;
import cn.jzteam.swift.util.ZHConvertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * 业务错误枚举接口
 */
public interface IEnumBizError {


    int getCode();

    default String getMessage() {
        String msg = LocaleUtil.getMessage("error.code.biz." + getCode());
        final Locale locale = LocaleContextHolder.getLocale();

        // 简体转繁体
        if (StringUtils.containsAny(locale.toString().toLowerCase(), LocaleUtil.TRADITIONAL_CHINESE)) {
            msg = ZHConvertUtil.convert(msg, ZHConvertUtil.TRADITIONAL);
        }

        return msg;
    }

    default String info() {
        return "[" + this.getCode() + "]" + this.getMessage();
    }

}
