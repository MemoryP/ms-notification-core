package com.ocbc.ms.notification.core.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Proxy;

import java.time.LocalDateTime;

@Slf4j
@Getter
@Setter
@Entity
@Table(name = "ntf_cust_tmpl")
//@IdClass(NotificationTmplPrivateKey.class)
@Proxy(lazy = false)
//@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class NotificationCustTmplEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "TMPL_CODE")
    private String templateCode;

    @Column(name = "TMPL_ID")
    private String templateId;

    @Column(name = "CHNL_TYPE")
    private String chnlType;

    @Column(name = "APPLY_TO")
    private String applyTo;

    @Column(name = "EFFECTIVE_DATE")
    private LocalDateTime effectiveDate;

    @Column(name = "EXPIRY_DATE")
    private LocalDateTime expiryDate;

    @Column(name = "LANGUAGE")
    private String language;

    @Column(name = "TMPL_DESC")
    private String templateDesc;

    @Column(name = "TMPL_TXT")
//    @Type(type = "jsonb")
    private String templateText;

    @Column(name = "TMPL_CONFIG")
//    @Type(type = "jsonb")
    private String templateConfig;

    @Column(name = "TOPIC")
    private String topic;

    @Column(name = "EVT_TYPE")
    private String eventType;

    @Column(name = "STS_CD")
    private String stsCode;

    @Column(name = "VER_NBR")
    private String verNumber;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_TIME")
    private LocalDateTime createdTime;

    @Column(name = "LAST_UPDATED_BY")
    private String lastUpdateBy;

    @Column(name = "LAST_UPDATED_TIME")
    private LocalDateTime lastUpdateTime;

    @Override
    public String toString(){
        String serialized ="";
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            serialized = objectMapper.writeValueAsString(this);
        }catch(Exception jpe){
            log.error("writeValueAsString catch exception: ",jpe);
        }
        return serialized;
    }
}
