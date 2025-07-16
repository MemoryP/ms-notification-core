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
@Entity(name = "tb_message_consumer_reg")
public class TbMessageConsumerReg implements Serializable {

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
    /** 消息主题 */
    @Column(name = "payload")
    private String payload ;
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
    private Date updatedTime ;
}
