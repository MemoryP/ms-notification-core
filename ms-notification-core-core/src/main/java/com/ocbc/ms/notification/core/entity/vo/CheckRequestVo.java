package com.ocbc.ms.notification.core.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CheckRequestVo implements Serializable {

    private static final long serialVersionUID = 10086L;

    private Boolean flag =true;

    private String errorMsg;

    private String data;
}
