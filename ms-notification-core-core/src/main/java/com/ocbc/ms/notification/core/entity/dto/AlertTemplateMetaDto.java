package com.ocbc.ms.notification.core.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class AlertTemplateMetaDto {

    private List<AlertTemplateGlobalTagDTO> tags;

    private List<AlertTemplateGlobalChnlDTO> chnls;

    private List<AlertTemplateGlobalLngDTO> lngs;

    private List<AlertTemplateGlobalApplyDTO> applys;
}
