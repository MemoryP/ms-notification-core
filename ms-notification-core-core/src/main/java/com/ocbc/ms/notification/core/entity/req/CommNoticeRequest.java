package com.ocbc.ms.notification.core.entity.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ocbc.ms.notification.core.entity.vo.UserInfoVo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class CommNoticeRequest {

    @NotBlank
    private String notificationType;
    @NotBlank
    private String type;
    @NotBlank
    private String id;
    @NotBlank
    private String name;
    private Object remark;
    private String cc;
    private String bcc;
    @NotBlank
    private String workPhone;
    @NotBlank
    private String email;
    @NotBlank
    private String subject;
    @NotBlank
    private String message;

    private Map<String,Object> payload;

}
