package com.ocbc.ms.notification.core.converter;

import com.ocbc.ms.notification.core.entity.NotificationTmplApplyEntity;
import com.ocbc.ms.notification.core.entity.NotificationTmplChnlEntity;
import com.ocbc.ms.notification.core.entity.NotificationTmplLngEntity;
import com.ocbc.ms.notification.core.entity.NotificationTmplTagEntity;
import com.ocbc.ms.notification.core.entity.dto.AlertTemplateGlobalApplyDTO;
import com.ocbc.ms.notification.core.entity.dto.AlertTemplateGlobalChnlDTO;
import com.ocbc.ms.notification.core.entity.dto.AlertTemplateGlobalLngDTO;
import com.ocbc.ms.notification.core.entity.dto.AlertTemplateGlobalTagDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AlertTemplateTagMapper {

    AlertTemplateTagMapper instance = Mappers.getMapper(AlertTemplateTagMapper.class);

    AlertTemplateGlobalTagDTO toTag(NotificationTmplTagEntity entity);

    List<AlertTemplateGlobalTagDTO> toTags(List<NotificationTmplTagEntity> entities);

    AlertTemplateGlobalChnlDTO toChnl(NotificationTmplChnlEntity entity);

    List<AlertTemplateGlobalChnlDTO> toChnls(List<NotificationTmplChnlEntity> entities);

    AlertTemplateGlobalLngDTO toLng(NotificationTmplLngEntity entity);

    List<AlertTemplateGlobalLngDTO> toLngs(List<NotificationTmplLngEntity> entities);

    AlertTemplateGlobalApplyDTO toApply(NotificationTmplApplyEntity entity);

    List<AlertTemplateGlobalApplyDTO> toApplys(List<NotificationTmplApplyEntity> entities);
}
