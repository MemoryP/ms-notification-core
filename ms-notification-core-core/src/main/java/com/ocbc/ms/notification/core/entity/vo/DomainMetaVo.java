package com.ocbc.ms.notification.core.entity.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @Author A5139374 Huang Jia Xi (Beck) <BeckHuang@ocbc.com>
 * @Date 2020/9/27 18:22:28
 * @Description
 * @Version 1.0
 * @Change log
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DomainMetaVo {

    @JsonProperty(value = "legalEntity")
    private String legalEntity;

    @JsonProperty(value = "trxDomain")
    private String trxDomain;

    @JsonProperty(value = "trxType")
    private String trxType;

    @JsonProperty(value = "trxID")
    private String trxID;

    @JsonProperty(value = "detailPageURL")
    private String detailPageUrl;

    @JsonProperty(value = "approvalCallbackURL")
    private String approvalCallbackUrl;

    @JsonProperty(value = "businessHandlingUrl")
    private String businessHandlingUrl;

    @JsonProperty(value = "businessHandlingPath")
    private String businessHandlingPath;

    @JsonProperty(value = "useCbsRequestBodyForPayload")
    private Boolean useCbsRequestBodyForPayload;

    @JsonProperty(value = "overrideControlLevel")
    private String overrideControlLevel;

    @JsonProperty(value = "originalRemark")
    private String originalRemark;

    @JsonProperty(value = "maker")
    private UserInfoVo maker;

    @JsonProperty(value = "onBehalfOf")
    private List<UserInfoVo> onBehalfOf;

    @JsonProperty(value = "payload")
    private Map<String,Object> payload;

    @JsonProperty(value = "mode")
    private String mode;

}
