package cn.jzteam.swift.i18n;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.util.Locale;

public class CustomCookieLocaleResolver extends CookieLocaleResolver {
    private static final char UNDERLINE = '_';
    private static final char DASH = '-';

    public CustomCookieLocaleResolver() {
    }

    protected Locale parseLocaleValue(String locale) {
        return locale == null ? this.getDefaultLocale() : StringUtils.parseLocaleString(locale.replace('-', '_'));
    }

    protected String toLocaleValue(Locale locale) {
        return locale.toString();
    }
}
