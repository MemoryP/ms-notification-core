package com.ocbc.ms.notification.core.feign.DTO.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffContactResponse {

    private String displayName;

    private String email;

    private String otherMobile;
}
