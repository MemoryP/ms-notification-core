package com.ocbc.ms.notification.core.service.impl;

import com.ocbc.ms.notification.core.entity.TbMessageConditionDef;
import com.ocbc.ms.notification.core.entity.dto.ConditionDto;
import com.ocbc.ms.notification.core.repository.TbMessageConditionDefRepository;
import com.ocbc.ms.notification.core.service.TbMessageConditionDefService;
import com.ocbc.ms.notification.core.utils.MVELExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TbMessageConditionDefServiceImpl implements TbMessageConditionDefService {

    private final TbMessageConditionDefRepository tbMessageConditionDefRepository;

    private final MVELExpression mvelExpression;

    @Override
    public List<TbMessageConditionDef> getTbMessageConditionDef(ConditionDto conditionDto) {
        String legalEntity = conditionDto.getLegalEntity();
        String serviceCode = conditionDto.getServiceCode();
        String serviceSubCode = conditionDto.getServiceSubCode();
        String payLoad = conditionDto.getPayload();
        //1.根据 基础值 sql查询条件匹配
        List<TbMessageConditionDef> conditionList = tbMessageConditionDefRepository
                .findAllByLegalEntityAndServiceCodeAndServiceSubCode(legalEntity, serviceCode, serviceSubCode);
        List<TbMessageConditionDef> conditionDefs = new ArrayList<>();
        //2.根据 业务数据 通过条件表达式 匹配
        for(TbMessageConditionDef def : conditionList){
            if("".equals(def.getCondition())){
                conditionDefs.add(def);
            }else{
                //符合条件的 添加
                if(mvelExpression.evaluateExpression(payLoad, def.getCondition())){
                    conditionDefs.add(def);
                }
            }
        }
        return conditionDefs;
    }
}
