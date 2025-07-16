package com.ocbc.ms.notification.core.converter;

import com.ocbc.ms.notification.core.entity.NotificationTmplEntity;
import com.ocbc.ms.notification.core.entity.dto.AlertTemplateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author tang tong (A5152682)
 * @date 2023/9/5
 **/
@Mapper
public interface AlertTemplateServiceMapper {

    AlertTemplateServiceMapper instance = Mappers.getMapper(AlertTemplateServiceMapper.class);

    List<AlertTemplateDTO> entityConverDtoList(List<NotificationTmplEntity> content);

    @Mappings({
            @Mapping(source = "chnlType", target = "deliveryChannel"),
            @Mapping(source = "templateDesc", target = "description"),
            @Mapping(source = "templateText", target = "content"),
            @Mapping(source = "lastUpdateBy", target = "lastUpdatedBy"),
            @Mapping(source = "lastUpdateTime", target = "lastUpdatedTime")
    })
    AlertTemplateDTO entityConvertDto(NotificationTmplEntity entity);

    @Mappings({
            @Mapping(target = "chnlType", source = "deliveryChannel"),
            @Mapping(target = "templateDesc", source = "description"),
            @Mapping(target = "templateText", source = "content"),
            @Mapping(target = "lastUpdateBy", source = "lastUpdatedBy"),
            @Mapping(target = "lastUpdateTime", source = "lastUpdatedTime")
    })
    NotificationTmplEntity dtoConvertEntity(AlertTemplateDTO dto);


}
