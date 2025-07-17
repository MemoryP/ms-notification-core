package com.ocbc.ms.notification.core.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ConsumerDto implements Serializable {

    private static final long serialVersionUID = 10086L;

    private String txChannelCode;
    private String remark;
    private String serviceCode;
    private String serviceSubCode;
    private String acNo;
    private String legalEntity;
    private String memoManual;
    private String messageType;
    private String custCategory;
    private String custId;
    private String messagedd;
    private String message;
    private String xCorrelationId;
    private String xSourceId;
    private String xSourceCountry;

}
