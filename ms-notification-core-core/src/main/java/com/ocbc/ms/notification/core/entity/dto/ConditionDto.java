package com.ocbc.ms.notification.core.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ConditionDto implements Serializable {

    private static final long serialVersionUID = 10086L;

    private String legalEntity;

    private String serviceCode;

    private String serviceSubCode;

    private String payload;

}
