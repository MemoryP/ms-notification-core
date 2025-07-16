package com.ocbc.ms.notification.cn.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * CommonErrorRes
 *
 * @author Felix
 * @date 2023/7/20
 * @description:
 */
@Data
@Builder
public class CommonRes {

    @JsonProperty("Head")
    private Head head;

    @JsonProperty("Body")
    private Body body;

    @Schema(description = "TradCustAddrSeqForDepoQuickResponseDTO_Head")
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
    @Data
    @Builder
    public static class Head {

        @JsonProperty("DasResultCode")
        @Schema(description = "服务处理结果代码")
        private String retCode;


        @JsonProperty("DasResultDetails")
        @Schema(description = "服务处理结果详细信息")
        private String retDetails;

        @JsonProperty("DasResultMessage")
        @Schema(description = "服务处理结果信息")
        private String retMsg;

        @JsonProperty("TransactionTraceCount")
        @Schema(description = "交易流水笔次")
        private Integer transactionTraceCount;

        @JsonProperty("TransactionTraceNo")
        @Schema(description = "流水号")
        private Long transactionTraceNo;

        @JsonProperty("TBLSharding")
        @Schema(description = "分表键值")
        private String tBLSharding;

        @JsonProperty(value = "DBSharding")
        @Schema(description = "分库键值")
        private String dBSharding;
    }

    @Schema(description = "TradCustAddrSeqForDepoQuickResponseDTO_Body")
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
    @Data
    @Builder
    public static class Body {

        @JsonProperty("RtnLst")
        @Schema(description = "返回信息列表")
        private java.util.List<RtnLstTable> rtnLst;

        @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
        @Data
        public static class RtnLstTable {}
    }

    public static Head getStandardHead(String retCode){
        return CommonRes.Head.builder()
                .retCode(retCode)
                .retMsg("")
                .retDetails("")
                .transactionTraceCount(0)
                .transactionTraceNo(0l)
                .tBLSharding("string")
                .dBSharding("string")
                .build();
    }

    public static Head getStandardHead(){
        return CommonRes.Head.builder()
                .retCode("0000")
                .retMsg("交易成功")
                .retDetails("")
                .transactionTraceCount(0)
                .transactionTraceNo(0l)
                .tBLSharding("string")
                .dBSharding("string")
                .build();
    }
}
