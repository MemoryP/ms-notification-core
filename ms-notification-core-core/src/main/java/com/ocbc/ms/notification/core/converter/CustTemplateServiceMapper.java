package com.ocbc.ms.notification.core.converter;

import com.ocbc.ms.notification.core.entity.NotificationCustTmplEntity;
import com.ocbc.ms.notification.core.entity.dto.CustTemplateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

/**
 * @author tang tong (A5152682)
 * @date 2023/9/5
 **/
@Mapper
public interface CustTemplateServiceMapper {

    CustTemplateServiceMapper instance = Mappers.getMapper(CustTemplateServiceMapper.class);

    List<CustTemplateDTO> entityConverDtoList(List<NotificationCustTmplEntity> content);

    @Mappings({
            @Mapping(source = "chnlType", target = "deliveryChannel"),
            @Mapping(source = "templateDesc", target = "description"),
            @Mapping(source = "templateText", target = "content"),
            @Mapping(source = "lastUpdateBy", target = "lastUpdatedBy"),
            @Mapping(source = "lastUpdateTime", target = "lastUpdatedTime"),
            @Mapping(source = "applyTo", target = "applyTo")
    })
    CustTemplateDTO entityConvertDto(NotificationCustTmplEntity entity);

    @Mappings({
            @Mapping(target = "chnlType", source = "deliveryChannel"),
            @Mapping(target = "templateDesc", source = "description"),
            @Mapping(target = "templateText", source = "content"),
            @Mapping(target = "lastUpdateBy", source = "lastUpdatedBy"),
            @Mapping(target = "lastUpdateTime", source = "lastUpdatedTime"),
            @Mapping(source = "applyTo", target = "applyTo")
    })
    NotificationCustTmplEntity dtoConvertEntity(CustTemplateDTO dto);

    default List<String> stringToArray(String commaSeparated) {
        if (commaSeparated == null || commaSeparated.isEmpty()) {
            return null; // 返回空数组
        }
        return Arrays.stream(commaSeparated.split(",")).toList(); // 按逗号分割
    }

    default String arrayToString(List<String> commaSeparated) {
        if (commaSeparated == null || commaSeparated.isEmpty()) {
            return null;
        }
        return String.join(",", commaSeparated);
    }
}
