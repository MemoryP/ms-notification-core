package com.ocbc.ms.notification.cn.api.custquick.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ocbc.ms.notification.cn.dto.DasRequestBaseDTO;
import com.ocbc.ms.notification.cn.dto.DasServiceRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;


@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE)
public class TradCustInfoCheckRequestDTO implements DasRequestBaseDTO {

    @JsonProperty("Head")
    @NotNull(message = "解析报文异常，缺少Head标签")
    private Head head;

    @JsonProperty("Body")
    @NotNull(message = "解析报文异常，缺少Body标签")
    private Body body;

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    @Override
    public DasServiceRequest convertToRequest() throws Exception {
        return null;
    }

    @JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE)
    @Data
    public static class Head {

    	// 启用转换 外部总线
       @JsonProperty("SystemDate")
       @Schema(name = "系统日期")
       private Long systemDate;
    	// 启用转换 外部总线
       @JsonProperty("VpdEntity")
       @Schema(name = "多实体号")
       private Long vpdEntity;


    }

	@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE)
    @Data
    public static class Body {

    	// 启用转换 外部总线
       @JsonProperty("CustId")
       private Long custId;
   	
        public Long getCustId() {
            return custId;
        }

        public void setCustId(Long custId) {
            this.custId = custId;
        }

    }

}
