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

    private String mesgId;

    private String regNo;

    private String legalEntity;

    private String txChannelCode;

    private String extServiceCode;

    private String txChannelDevCode;

    private String systemCode;

    private String subSystemCode;

    private String businessCode;

    private String businessSubCode;

    private String serviceCode;

    private String serviceSubCode;

    private String txBrNo;

    private String txTellerNo;

    private String txDate;

    private String txTraceNo;

    private String txTime;

    private String acNo;

    private String acName;

    private String acSeqn;

    private String custType;

    private String acType;

    private String innerAcId;

    private String dcFlag;

    private String cashFlag;

    private String transferType;

    private String curCode;

    private String txAmt;

    private String bal;

    private String oppositeAcNo;

    private String oppositeAcName;

    private String memoManual;

    private String mesgTempletAssistantField1;

    private String mesgTempletAssistantField2;

    private String mesgSpareField1;

    private String mesgSpareField2;

    private String sendType;

    private String sendNo;

    private String avlBal;

    private String txRefNo;

    private String reqSerNo;

    private String prdtNo;

    private String intAmt;

    private String mdmCode;

    private String custId;

    private String rmrk;

}
