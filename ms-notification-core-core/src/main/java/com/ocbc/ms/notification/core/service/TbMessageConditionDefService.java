package com.ocbc.ms.notification.core.service;

import com.ocbc.ms.notification.core.entity.TbMessageConditionDef;
import com.ocbc.ms.notification.core.entity.dto.ConditionDto;

import java.util.List;

//规则匹配
public interface TbMessageConditionDefService {

    List<TbMessageConditionDef> getTbMessageConditionDef(ConditionDto conditionDto);

}
