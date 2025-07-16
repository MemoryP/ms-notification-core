package com.ocbc.ms.notification.core.service;

import com.ocbc.ms.cbs.core.dto.PageDTO;
import com.ocbc.ms.notification.core.entity.dto.AlertTemplateMetaDto;
import com.ocbc.ms.notification.core.entity.dto.CustTemplateDTO;
import com.ocbc.ms.notification.core.entity.dto.CustTemplateInqueryDTO;

public interface ICustTemplateService {
    PageDTO<CustTemplateDTO> page(CustTemplateInqueryDTO convert);

    void save(CustTemplateDTO convert);

    CustTemplateDTO retrieve(String templateId, String language);

    AlertTemplateMetaDto listMeta();

    void update(CustTemplateDTO dto);
}
