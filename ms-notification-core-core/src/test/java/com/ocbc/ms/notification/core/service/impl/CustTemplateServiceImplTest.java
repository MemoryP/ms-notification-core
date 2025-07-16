package com.ocbc.ms.notification.core.service.impl;

import com.ocbc.ms.cbs.core.dto.PageDTO;
import com.ocbc.ms.cbs.core.exception.BizException;
import com.ocbc.ms.notification.core.converter.AlertTemplateTagMapper;
import com.ocbc.ms.notification.core.entity.NotificationCustTmplEntity;
import com.ocbc.ms.notification.core.entity.dto.AlertTemplateMetaDto;
import com.ocbc.ms.notification.core.entity.dto.CustTemplateDTO;
import com.ocbc.ms.notification.core.entity.dto.CustTemplateInqueryDTO;
import com.ocbc.ms.notification.core.repository.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustTemplateServiceImplTest {

    @Mock
    private CustNotificationTemplateRepository templateRepository;

    @Mock
    private NotificationTemplateTagRepository notificationTemplateTagRepository;

    @Mock
    private NotificationTemplateChnlRepository notificationTemplateChnlRepository;

    @Mock
    private NotificationTemplateLngRepository notificationTemplateLngRepository;

    @Mock
    private NotificationTemplateApplyRepository notificationTemplateApplyRepository;

    @InjectMocks
    private CustTemplateServiceImpl custTemplateService;
    @Mock
    Page<NotificationCustTmplEntity> page;

    @Test
    public void testPage() {
        // given
        CustTemplateInqueryDTO dto = new CustTemplateInqueryDTO();
        dto.setPageNo(1);
        dto.setPageSize(10);
        dto.setTemplateId("1111"); dto.setTemplateId("templateId");
        dto.setLanguage("language");
        dto.setDeliveryChannel("deliveryChannel");
        dto.setApplyTo(Lists.list("111"));
        doReturn(page).when(templateRepository).findAll(any(Specification.class), any(PageRequest.class));

        // when
        PageDTO<CustTemplateDTO> result = custTemplateService.page(dto);

        // then
        assertNotNull(result);
        assertEquals(0, result.getTotalElements());

        Root<NotificationCustTmplEntity> root = mock(Root.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);

        // when
        Predicate predicate = custTemplateService.getPredicate(dto, root, criteriaBuilder);

        // then
        assertNull(predicate);

    }

    @Test
    public void testRetrieve() {
        // given
        String templateId = "templateId";
        String language = "language";

        NotificationCustTmplEntity entity = new NotificationCustTmplEntity();
        doReturn(Optional.of(entity)).when(templateRepository).findByTemplateIdAndLanguage(templateId, language);

        // when
        CustTemplateDTO result = custTemplateService.retrieve(templateId, language);

        // then
        assertNotNull(result);

    }

    @Test
    public void testListMeta() {
        // given
        AlertTemplateMetaDto metaDto = new AlertTemplateMetaDto();
        metaDto.setTags(AlertTemplateTagMapper.instance.toTags(notificationTemplateTagRepository.findAll()));
        metaDto.setLngs(AlertTemplateTagMapper.instance.toLngs(notificationTemplateLngRepository.findAll()));
        metaDto.setChnls(AlertTemplateTagMapper.instance.toChnls(notificationTemplateChnlRepository.findAll()));
        metaDto.setApplys(AlertTemplateTagMapper.instance.toApplys(notificationTemplateApplyRepository.findAll()));

        // when
        AlertTemplateMetaDto result = custTemplateService.listMeta();

        // then
        assertNotNull(result);
        assertEquals(metaDto.getTags(), result.getTags());
        assertEquals(metaDto.getLngs(), result.getLngs());
        assertEquals(metaDto.getChnls(), result.getChnls());
        assertEquals(metaDto.getApplys(), result.getApplys());
    }

    @Test
    public void testSave() {
        // given
        CustTemplateDTO dto = new CustTemplateDTO();

        // when
        custTemplateService.save(dto);

        // then
        verify(templateRepository).save(any());
    }

    @Test
    public void testUpdate() {
        // given
        CustTemplateDTO dto = new CustTemplateDTO();

        NotificationCustTmplEntity entity = new NotificationCustTmplEntity();
        doReturn(Optional.of(entity)).when(templateRepository).findByTemplateIdAndLanguageAndChnlType(dto.getTemplateId(), dto.getLanguage(), dto.getDeliveryChannel());

        // when
        custTemplateService.update(dto);

        // then
        verify(templateRepository).save(any());
    }

    @Test
    public void testSaveExist() {
        // given
        CustTemplateDTO dto = new CustTemplateDTO();

        NotificationCustTmplEntity entity = new NotificationCustTmplEntity();
        doReturn(Optional.of(entity)).when(templateRepository).findByTemplateIdAndLanguageAndChnlType(dto.getTemplateId(), dto.getLanguage(), dto.getDeliveryChannel());

        // when and then
        assertThrows(BizException.class, () -> custTemplateService.save(dto));
    }
}
