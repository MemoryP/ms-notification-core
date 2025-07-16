package com.ocbc.ms.notification.core.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Proxy;

import java.io.Serializable;

/**
 * This stores all generated&sent notifications
 */

@Slf4j
@Getter
@Setter
@Entity
@Table(name = "ntf_cust_tmpl_apply")
@Proxy(lazy = false)
@Data
public class NotificationTmplApplyEntity implements Serializable {

    private static final long serialVersionUID = -6545064633960658783L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "[DESC]")
    private String desc;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_TIME")
    private String createdTime;

    @Column(name = "LAST_UPDATED_BY")
    private String lastUpdateBy;

    @Column(name = "LAST_UPDATED_TIME")
    private String lastUpdateTime;

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
