package com.ocbc.ms.notification.core.entity.dto;

import com.ocbc.ms.notification.core.entity.TbMessageConditionDef;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PkgDto implements Serializable {

    private static final long serialVersionUID = 10086L;

    private String message;

    private TbMessageConditionDef tbMessageConditionDef;


}
