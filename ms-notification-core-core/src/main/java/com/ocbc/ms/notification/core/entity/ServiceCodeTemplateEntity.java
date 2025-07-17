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
@Table(name = "SERVICE_CODE_TEMPLATE")
public class ServiceCodeTemplateEntity {
    @Id
    @Column(name = "SERVICE_CODE")
    private String serviceCode;

    @Column(name = "ALTER_TEMPLATE_CODE")
    private String alterTemplateCode;

    @Column(name = "NOTIFICATION_TEMPLATE_CODE")
    private String notificationTemplateCode;

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
