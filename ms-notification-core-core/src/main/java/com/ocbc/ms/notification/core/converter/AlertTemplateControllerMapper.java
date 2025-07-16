package com.ocbc.ms.notification.core.converter;

import com.ocbc.ms.notification.core.entity.dto.AlertTemplateDTO;
import com.ocbc.ms.notification.core.entity.dto.AlertTemplateInqueryDTO;
import com.ocbc.ms.notification.core.entity.req.AlertTemplateInqueryRequest;
import com.ocbc.ms.notification.core.entity.req.AlertTemplateSaveRequest;
import com.ocbc.ms.notification.core.entity.rsp.AlertTemplateRetrieveResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author A5130904
 * @version 1.0
 * @since 2024/7/4
 */

@Mapper
public interface AlertTemplateControllerMapper {
    AlertTemplateControllerMapper instance = Mappers.getMapper(AlertTemplateControllerMapper.class);


    AlertTemplateInqueryDTO convert(AlertTemplateInqueryRequest request);


    @Mapping(target = "content", source = "templateContent")
    AlertTemplateDTO convert(AlertTemplateSaveRequest requestBody);

    AlertTemplateRetrieveResponse convert(AlertTemplateDTO dto);
}
