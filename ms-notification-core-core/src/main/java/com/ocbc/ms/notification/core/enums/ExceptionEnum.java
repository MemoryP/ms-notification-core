package com.ocbc.ms.notification.core.enums;

import com.ocbc.ms.cbs.core.exception.ErrorCode;

import java.util.HashMap;
import java.util.Map;

/**
 * ExceptionEnum
 *
 * @author Tang PengHui
 * @version 1.0
 * @date 2020/8/10
 */
public enum ExceptionEnum implements ErrorCode {
    TEMPLATE_EXIST("10000", "Template already exists"),

    PARSE_JWT_FAILED("40001","Parse jwt failed."),
    JWT_EXPIRED("40002","This token is expired."),
    WRONG_CUSTOM_FORMAT_IN_JWT("40003","Wrong custom format in jwt."),

    JWT_IS_BLANK("40005","JWT is blank."),
    NO_USER_ID_IN_CUSTOM("40004","No user id in custom map."),
    RSA_KEY_ERROR("50004","Exception occurred while load RSA key."),
    KAFKA_SENDMSG_ERROR("70600","kafka send message failed.");


    private String code;
    private String messagePattern;
    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessagePattern() {
        return this.messagePattern;
    }

    ExceptionEnum(String code, String messagePattern) {
        this.code = code;
        this.messagePattern = messagePattern;
    }

    private final Map<String, String> errorDescription = new HashMap<>();
}
