package com.ocbc.ms.notification.core.service;

import com.ocbc.ms.cbs.core.dto.PageDTO;
import com.ocbc.ms.notification.core.entity.dto.AlertTemplateDTO;
import com.ocbc.ms.notification.core.entity.dto.AlertTemplateInqueryDTO;
import com.ocbc.ms.notification.core.entity.dto.AlertTemplateMetaDto;

public interface IAlertTemplateService {
    PageDTO<AlertTemplateDTO> page(AlertTemplateInqueryDTO convert);

    void save(AlertTemplateDTO convert);

    AlertTemplateDTO retrieve(String templateId, String language);

    AlertTemplateMetaDto listMeta();

    void update(AlertTemplateDTO dto);
}
