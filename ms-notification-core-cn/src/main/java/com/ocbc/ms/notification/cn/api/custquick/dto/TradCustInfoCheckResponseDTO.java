package com.ocbc.ms.notification.cn.api.custquick.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ocbc.ms.notification.cn.constants.Constants;
import com.ocbc.ms.notification.cn.dto.DasResponseBaseDTO;
import com.ocbc.ms.notification.cn.dto.DasServiceResponse;
import com.ocbc.ms.notification.cn.util.StringUtil;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE)
public class TradCustInfoCheckResponseDTO implements DasResponseBaseDTO {

    @JsonProperty("Head")
    private Head head;

    @JsonProperty("Body")
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

	@Schema(description="TradCustInfoCheckResponseDTO_Head")
	@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE)
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









        
    	// 启用转换 外部总线
       @JsonProperty("TransactionTraceCount")
		@Schema(description = "交易流水笔次")
       private Integer transactionTraceCount;
    	// 启用转换 外部总线
       @JsonProperty("TransactionTraceNo")
		@Schema(description = "流水号")
       private Long transactionTraceNo;
    	// 启用转换 外部总线
       @JsonProperty("TBLSharding")
		@Schema(description = "分表键值")
       private String tBLSharding;
    	// 启用转换 外部总线
       @JsonProperty("DBSharding")
		@Schema(description = "分库键值")
       private String dBSharding;
   	
        public String getRetCode() {
            return retCode;
        }

        public void setRetCode(String retCode) {
            this.retCode = retCode;
        }

        public String getRetMsg() {
            return retMsg;
        }

        public void setRetMsg(String retMsg) {
            this.retMsg = retMsg;
        }

        public String getRetDetails() {
            return retDetails;
        }

        public void setRetDetails(String retDetails) {
            this.retDetails = retDetails;
        }
	
        public Integer getTransactionTraceCount() {
            return transactionTraceCount;
        }

        public void setTransactionTraceCount(Integer transactionTraceCount) {
            this.transactionTraceCount = transactionTraceCount;
        }
        public Long getTransactionTraceNo() {
            return transactionTraceNo;
        }

        public void setTransactionTraceNo(Long transactionTraceNo) {
            this.transactionTraceNo = transactionTraceNo;
        }
        public String getTBLSharding() {
            return tBLSharding;
        }

        public void setTBLSharding(String tBLSharding) {
            this.tBLSharding = tBLSharding;
        }
        public String getDBSharding() {
            return dBSharding;
        }

        public void setDBSharding(String dBSharding) {
            this.dBSharding = dBSharding;
        }
        

        public void convertFromResponse(DasServiceResponse response) throws Exception {
					// convert process
            this.retCode = response.get("DasExternalResultCode");
        	this.retDetails = response.get("DasResultDetails");
        	this.retMsg = response.get("DasResultMessage");
        	this.transactionTraceCount = response.get("TransactionTraceCount", Integer.class);
        	this.transactionTraceNo = response.get("TransactionTraceNo", Long.class);
        	this.tBLSharding = response.get("TBLSharding", String.class);
        	this.dBSharding = response.get("DBSharding", String.class);
        }
    
	}

	@Schema(description="TradCustInfoCheckResponseDTO_Body")
	@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE)
    public static class Body {

    	// 启用转换 外部总线
       @JsonProperty("CustId")
		@Schema(description = "客户ID")
       private Long custId;
   	
        public Long getCustId() {
            return custId;
        }

        public void setCustId(Long custId) {
            this.custId = custId;
        }
        

        public void convertFromResponse(DasServiceResponse response) throws Exception {
					// convert process
        	this.custId = response.get("CustId", Long.class);
        }
    }
    
    @JsonIgnore
    @Override
    public void setRetCode(String errorCode) {
        this.head.setRetCode(errorCode);
    }

    @JsonIgnore
    @Override
    public String getRetCode() {
        return this.head.getRetCode();
    }

    @JsonIgnore
    @Override
    public void setRetMsg(String errorMessage) {
        this.head.setRetMsg(errorMessage);
    }

    @JsonIgnore
    @Override
    public String getRetMsg() {
        return this.head.getRetMsg();
    }

    @JsonIgnore
    @Override
    public boolean isSuccess() {
        return !StringUtil.isEmpty(this.head.getRetCode()) && this.head.getRetCode().equals(Constants.DAS_SUCCESS_CODE);
    }

    @JsonIgnore
    @Override
    public boolean isUnknown() {
        return !StringUtil.isEmpty(this.head.getRetCode()) && this.head.getRetCode().equals(Constants.DAS_RESULT_UNKNOWN);
    }

    @JsonIgnore
    @Override
    public boolean isMissingRetCode() {
        return StringUtil.isEmpty(this.head.getRetCode());
    }

    @Override
    public void convertFromResponse(DasServiceResponse response) throws Exception {

        this.head = new Head();
        this.body = new Body();
        this.head.convertFromResponse(response);
        this.body.convertFromResponse(response);

    }
}
