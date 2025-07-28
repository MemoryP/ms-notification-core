package com.ocbc.ms.notification.core.feign.DTO.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

@Getter
public enum OperatorEnum {

    @JsonProperty("GT")
    GREATER_THAN("GT"),

    @JsonProperty("GE")
    GREATER_THAN_OR_EQUAL("GE"),

    @JsonProperty("LT")
    LESS_THAN("LT"),

    @JsonProperty("LE")
    LESS_THAN_OR_EQUAL("LE"),

    @JsonProperty("EQ")
    EQUAL("EQ"),

    @JsonProperty("NE")
    NOT_EQUAL("NE"),

    @JsonProperty("IN")
    IN("IN"),

    @JsonProperty("LK")
    LIKE("LK"),

    @JsonProperty("LL")
    LEFT_LIKE("LL"),

    @JsonProperty("RL")
    RIGHT_LIKE("RL"),
    ;

    private final String value;


    OperatorEnum(final String value) {
        this.value = value;
    }

    public static OperatorEnum getOperatorValue(String value){
        return map.get(value);
    }

    public static final Map<String, OperatorEnum> map = Maps.uniqueIndex(Arrays.asList(OperatorEnum.values()), OperatorEnum::getValue);
}
