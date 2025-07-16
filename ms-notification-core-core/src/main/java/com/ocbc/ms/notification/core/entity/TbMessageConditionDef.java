package com.ocbc.ms.notification.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Entity(name = "tb_message_condition_def")
public class TbMessageConditionDef implements Serializable {

    private static final long serialVersionUID = 10086L;

    @Id
    @Column(name = "id")
    private String id ;
    /** 实体号 */
    @Column(name = "legal_entity")
    private String legalEntity ;
    /** 场景号码 */
    @Column(name = "service_code")
    private String serviceCode ;
    /** 子场景号码 */
    @Column(name = "service_sub_code")
    private String serviceSubCode ;
    /** 消息类型 */
    @Column(name = "message_type")
    private String messageType ;
    /** 客户类型 */
    @Column(name = "customer_type")
    private String customerType ;
    /** 来源渠道码 */
    @Column(name = "transaction_channel_code")
    private String transactionChannelCode ;
    /** 条件 */
    @Column(name = "condition")
    private String condition ;
    /** 借贷标志 */
    @Column(name = "debit_credit_flag")
    private String debitCreditFlag ;
    /** 事件类型号码 */
    @Column(name = "event_type_code")
    private String eventTypeCode ;
    /** 消息主题 */
    @Column(name = "notification_topic")
    private String notificationTopic ;
    /** 消息类型 */
    @Column(name = "notification_type")
    private String notificationType ;
    /** 消息主体 */
    @Column(name = "payload")
    private String payload ;
    /** 操作员 */
    @Column(name = "teller_no")
    private String tellerNo ;
    /** 复核员 */
    @Column(name = "checker_no")
    private String checkerNo ;
    /** 审核员 */
    @Column(name = "approver_no")
    private String approverNo ;
    /** 状态 */
    @Column(name = "status_cd")
    private String statusCd ;
    /** 版本号 */
    @Column(name = "version_no")
    private Integer versionNo ;
    /** 创建人 */
    @Column(name = "created_by")
    private String createdBy ;
    /** 创建时间 */
    @Column(name = "created_time")
    private Date createdTime ;
    /** 更新人 */
    @Column(name = "updated_by")
    private String updatedBy ;
    /** 更新时间 */
    @Column(name = "updated_time")
    private Date updatedTime;
}
