package com.ocbc.ms.notification.core.service.impl;

import com.ocbc.ms.cbs.core.dto.PageDTO;
import com.ocbc.ms.cbs.core.exception.BizException;
import com.ocbc.ms.notification.core.converter.AlertTemplateTagMapper;
import com.ocbc.ms.notification.core.converter.CustTemplateServiceMapper;
import com.ocbc.ms.notification.core.entity.NotificationCustTmplEntity;
import com.ocbc.ms.notification.core.entity.dto.AlertTemplateMetaDto;
import com.ocbc.ms.notification.core.entity.dto.CustTemplateDTO;
import com.ocbc.ms.notification.core.entity.dto.CustTemplateInqueryDTO;
import com.ocbc.ms.notification.core.enums.ExceptionEnum;
import com.ocbc.ms.notification.core.repository.CustNotificationTemplateRepository;
import com.ocbc.ms.notification.core.repository.NotificationTemplateChnlRepository;
import com.ocbc.ms.notification.core.repository.NotificationTemplateLngRepository;
import com.ocbc.ms.notification.core.repository.NotificationTemplateTagRepository;
import com.ocbc.ms.notification.core.repository.NotificationTemplateApplyRepository;
import com.ocbc.ms.notification.core.service.ICustTemplateService;
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
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
//@AllArgsConstructor
@NoArgsConstructor
public class CustTemplateServiceImpl implements ICustTemplateService {

    private final CustTemplateServiceMapper dtoMapper = CustTemplateServiceMapper.instance;

    @Autowired
    CustNotificationTemplateRepository templateRepository;
    @Autowired
    NotificationTemplateTagRepository notificationTemplateTagRepository;
    @Autowired
    NotificationTemplateChnlRepository notificationTemplateChnlRepository;
    @Autowired
    NotificationTemplateLngRepository notificationTemplateLngRepository;
    @Autowired
    NotificationTemplateApplyRepository notificationTemplateApplyRepository;

    @Override
    public PageDTO<CustTemplateDTO> page(CustTemplateInqueryDTO dto) {
        PageRequest pageRequest = PageRequest.of(dto.getPageNo(), dto.getPageSize(), Sort.by(Sort.Direction.DESC, "id"));
        Page<NotificationCustTmplEntity> page = templateRepository.findAll(buildSpecification(dto), pageRequest);
        List<CustTemplateDTO> dtoList = dtoMapper.entityConverDtoList(page.getContent());
        return PageDTO.of(dtoList, page.getTotalElements());
    }

    private Specification<NotificationCustTmplEntity> buildSpecification(CustTemplateInqueryDTO dto) {
        return (root, query, criteriaBuilder) -> getPredicate(dto, root, criteriaBuilder);
    }

    static Predicate getPredicate(CustTemplateInqueryDTO dto, Root<NotificationCustTmplEntity> root, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        Arrays.stream(new Object[][]{
                        {dto.getTemplateId(), "templateId"},
                        {dto.getLanguage(), "language"},
                        {dto.getDeliveryChannel(), "deliveryChannel"}
                })
                .filter(data -> Strings.isNotEmpty((String) data[0]))
                .forEach(data -> predicates.add(criteriaBuilder.like(root.get((String) data[1]), "%" + data[0] + "%")));

        if (!CollectionUtils.isEmpty(dto.getApplyTo()) && !dto.getApplyTo().contains("ALL")){
            Predicate[] applyTos = dto.getApplyTo().stream().map(tag -> criteriaBuilder.like(root.get("applyTo"), "%" + tag + "%")).toArray(Predicate[]::new);
            predicates.add(criteriaBuilder.or(applyTos));
        }
        return predicates.isEmpty() ? criteriaBuilder.conjunction() : criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }



    @Override
    public CustTemplateDTO retrieve(String templateId, String language) {
        NotificationCustTmplEntity entity = templateRepository.findByTemplateIdAndLanguage(templateId, language).orElse(new NotificationCustTmplEntity());
        return dtoMapper.entityConvertDto(entity);
    }

    @Override
    public AlertTemplateMetaDto listMeta() {
        AlertTemplateMetaDto alertTemplateMetaDto = new AlertTemplateMetaDto();
        alertTemplateMetaDto.setTags(AlertTemplateTagMapper.instance.toTags(notificationTemplateTagRepository.findAll()));
        alertTemplateMetaDto.setLngs(AlertTemplateTagMapper.instance.toLngs(notificationTemplateLngRepository.findAll()));
        alertTemplateMetaDto.setChnls(AlertTemplateTagMapper.instance.toChnls(notificationTemplateChnlRepository.findAll()));
        alertTemplateMetaDto.setApplys(AlertTemplateTagMapper.instance.toApplys(notificationTemplateApplyRepository.findAll()));
        return alertTemplateMetaDto;
    }


    @Override
    public void save(CustTemplateDTO dto) {
        NotificationCustTmplEntity entityFromDto = dtoMapper.dtoConvertEntity(dto);

        Optional<NotificationCustTmplEntity> entityOptional = templateRepository.findByTemplateIdAndLanguageAndChnlType(dto.getTemplateId(), dto.getLanguage(), dto.getDeliveryChannel());
        if (entityOptional.isPresent()) {
            throw new BizException(ExceptionEnum.TEMPLATE_EXIST);
        }
        entityFromDto.setTemplateCode(String.join("|", dto.getTemplateId(), dto.getDeliveryChannel(), dto.getLanguage()));
        templateRepository.save(entityFromDto);
    }

    @Override
    public void update(CustTemplateDTO dto) {
        NotificationCustTmplEntity entityFromDto = dtoMapper.dtoConvertEntity(dto);
        Optional<NotificationCustTmplEntity> entityOptional = templateRepository.findByTemplateIdAndLanguageAndChnlType(dto.getTemplateId(), dto.getLanguage(), dto.getDeliveryChannel());
        if (entityOptional.isPresent()) {
            NotificationCustTmplEntity entity = entityOptional.get();
            entity.setTemplateText(entityFromDto.getTemplateText());
            entity.setTemplateDesc(entityFromDto.getTemplateDesc());
            entity.setApplyTo(entityFromDto.getApplyTo());
            entity.setExpiryDate(entityFromDto.getExpiryDate());
            entity.setEffectiveDate(entityFromDto.getEffectiveDate());
            entity.setLastUpdateBy(entityFromDto.getLastUpdateBy());
            entity.setLastUpdateTime(LocalDateTime.now());
            templateRepository.save(entity);
        }
    }
}
