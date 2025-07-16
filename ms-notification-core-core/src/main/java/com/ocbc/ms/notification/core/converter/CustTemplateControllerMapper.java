package com.ocbc.ms.notification.core.converter;

import com.ocbc.ms.notification.core.entity.dto.CustTemplateDTO;
import com.ocbc.ms.notification.core.entity.dto.CustTemplateInqueryDTO;
import com.ocbc.ms.notification.core.entity.req.CustTemplateSaveRequest;
import com.ocbc.ms.notification.core.entity.rsp.CustTemplateRetrieveResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author A5130904
 * @version 1.0
 * @since 2024/7/4
 */

@Mapper
public interface CustTemplateControllerMapper {
    CustTemplateControllerMapper instance = Mappers.getMapper(CustTemplateControllerMapper.class);


    CustTemplateInqueryDTO convert(CustTemplateInqueryDTO request);


    @Mapping(target = "content", source = "templateContent")
    CustTemplateDTO convert(CustTemplateSaveRequest requestBody);

    CustTemplateRetrieveResponse convert(CustTemplateDTO dto);
}
