package com.ocbc.ms.notification.core.feign.DTO.param;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BasAcctAcoDto {

    private Long seq;

    private String legalEntity;

    private String acoCode;

    private String acoName;

    private String staffId;

    private String hierId;

    private String primaryHierId;

    private String brNo;

    private String active;

    private LocalDateTime updTime;
}
