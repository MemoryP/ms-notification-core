package com.ocbc.ms.notification.core.service.impl;

import com.ocbc.ms.cbs.core.dto.PageDTO;
import com.ocbc.ms.cbs.core.exception.BizException;
import com.ocbc.ms.notification.core.converter.AlertTemplateServiceMapper;
import com.ocbc.ms.notification.core.converter.AlertTemplateTagMapper;
import com.ocbc.ms.notification.core.entity.NotificationTmplEntity;
import com.ocbc.ms.notification.core.entity.dto.AlertTemplateDTO;
import com.ocbc.ms.notification.core.entity.dto.AlertTemplateInqueryDTO;
import com.ocbc.ms.notification.core.entity.dto.AlertTemplateMetaDto;
import com.ocbc.ms.notification.core.enums.ExceptionEnum;
import com.ocbc.ms.notification.core.repository.NotificationTemplateChnlRepository;
import com.ocbc.ms.notification.core.repository.NotificationTemplateLngRepository;
import com.ocbc.ms.notification.core.repository.NotificationTemplateTagRepository;
import com.ocbc.ms.notification.core.repository.SendEmailNotificationTemplateRepository;
import com.ocbc.ms.notification.core.service.IAlertTemplateService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.ap.internal.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
//@AllArgsConstructor
@NoArgsConstructor
public class AlertTemplateServiceImpl implements IAlertTemplateService {

    private final AlertTemplateServiceMapper dtoMapper = AlertTemplateServiceMapper.instance;

    @Autowired
    SendEmailNotificationTemplateRepository sendEmailNotificationTemplateRepository;
    @Autowired
    NotificationTemplateTagRepository notificationTemplateTagRepository;
    @Autowired
    NotificationTemplateChnlRepository notificationTemplateChnlRepository;
    @Autowired
    NotificationTemplateLngRepository notificationTemplateLngRepository;

    @Override
    public PageDTO<AlertTemplateDTO> page(AlertTemplateInqueryDTO dto) {
        PageRequest pageRequest = PageRequest.of(dto.getPageNo(), dto.getPageSize(), Sort.by(Sort.Direction.DESC, "id"));
        Page<NotificationTmplEntity> page = sendEmailNotificationTemplateRepository.findAll(buildSpecification(dto), pageRequest);
        List<AlertTemplateDTO> dtoList = dtoMapper.entityConverDtoList(page.getContent());
        return PageDTO.of(dtoList, page.getTotalElements());
    }

    private Specification<NotificationTmplEntity> buildSpecification(AlertTemplateInqueryDTO dto) {
        return (root, query, criteriaBuilder) -> getPredicate(dto, root, criteriaBuilder);
    }

    static Predicate getPredicate(AlertTemplateInqueryDTO dto, Root<NotificationTmplEntity> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        Arrays.stream(new Object[][]{
                        {dto.getTemplateId(), "templateId"},
                        {dto.getLanguage(), "language"},
                        {dto.getDeliveryChannel(), "deliveryChannel"}
                })
                .filter(data -> Strings.isNotEmpty((String) data[0]))
                .forEach(data -> predicates.add(criteriaBuilder.like(root.get((String) data[1]), "%" + data[0] + "%")));
        return predicates.isEmpty() ? criteriaBuilder.conjunction() : criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }



    @Override
    public AlertTemplateDTO retrieve(String templateId, String language) {
        NotificationTmplEntity entity = sendEmailNotificationTemplateRepository.findByTemplateIdAndLanguage(templateId, language).orElse(new NotificationTmplEntity());
        return dtoMapper.entityConvertDto(entity);
    }

    @Override
    public AlertTemplateMetaDto listMeta() {
        AlertTemplateMetaDto alertTemplateMetaDto = new AlertTemplateMetaDto();
        alertTemplateMetaDto.setTags(AlertTemplateTagMapper.instance.toTags(notificationTemplateTagRepository.findAll()));
        alertTemplateMetaDto.setLngs(AlertTemplateTagMapper.instance.toLngs(notificationTemplateLngRepository.findAll()));
        alertTemplateMetaDto.setChnls(AlertTemplateTagMapper.instance.toChnls(notificationTemplateChnlRepository.findAll()));
        return alertTemplateMetaDto;
    }


    @Override
    public void save(AlertTemplateDTO dto) {
        NotificationTmplEntity entityFromDto = dtoMapper.dtoConvertEntity(dto);

        Optional<NotificationTmplEntity> entityOptional = sendEmailNotificationTemplateRepository.findByTemplateIdAndLanguageAndChnlType(dto.getTemplateId(), dto.getLanguage(), dto.getDeliveryChannel());
        if (entityOptional.isPresent()) {
            throw new BizException(ExceptionEnum.TEMPLATE_EXIST);
        }
        entityFromDto.setTemplateCode(String.join("|", dto.getTemplateId(), dto.getDeliveryChannel(), dto.getLanguage()));
        sendEmailNotificationTemplateRepository.save(entityFromDto);
    }

    @Override
    public void update(AlertTemplateDTO dto) {
        NotificationTmplEntity entityFromDto = dtoMapper.dtoConvertEntity(dto);
        Optional<NotificationTmplEntity> entityOptional = sendEmailNotificationTemplateRepository.findByTemplateIdAndLanguageAndChnlType(dto.getTemplateId(), dto.getLanguage(), dto.getDeliveryChannel());
        if (entityOptional.isPresent()) {
            NotificationTmplEntity entity = entityOptional.get();
            entity.setTemplateText(entityFromDto.getTemplateText());
            entity.setTemplateDesc(entityFromDto.getTemplateDesc());
            entity.setLastUpdateBy(entityFromDto.getLastUpdateBy());
            entity.setLastUpdateTime(LocalDateTime.now());
            sendEmailNotificationTemplateRepository.save(entity);
        }
    }
}
