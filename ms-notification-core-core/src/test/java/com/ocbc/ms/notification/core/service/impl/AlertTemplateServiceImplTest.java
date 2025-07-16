package com.ocbc.ms.notification.core.service.impl;

import com.ocbc.ms.cbs.core.dto.PageDTO;
import com.ocbc.ms.cbs.core.exception.BizException;
import com.ocbc.ms.notification.core.entity.NotificationTmplEntity;
import com.ocbc.ms.notification.core.entity.dto.AlertTemplateDTO;
import com.ocbc.ms.notification.core.entity.dto.AlertTemplateInqueryDTO;
import com.ocbc.ms.notification.core.entity.dto.AlertTemplateMetaDto;
import com.ocbc.ms.notification.core.repository.NotificationTemplateChnlRepository;
import com.ocbc.ms.notification.core.repository.NotificationTemplateLngRepository;
import com.ocbc.ms.notification.core.repository.NotificationTemplateTagRepository;
import com.ocbc.ms.notification.core.repository.SendEmailNotificationTemplateRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AlertTemplateServiceImplTest {

    @Mock
    private SendEmailNotificationTemplateRepository sendEmailNotificationTemplateRepository;

    @Mock
    private NotificationTemplateTagRepository notificationTemplateTagRepository;

    @Mock
    private NotificationTemplateChnlRepository notificationTemplateChnlRepository;

    @Mock
    private NotificationTemplateLngRepository notificationTemplateLngRepository;

    @InjectMocks
    private AlertTemplateServiceImpl alertTemplateService;
    @Mock
    private Page<NotificationTmplEntity> notificationTmplEntityPage;

    @Test
    public void testPage() {
        // given
        AlertTemplateInqueryDTO dto = new AlertTemplateInqueryDTO();
        dto.setPageNo(1);
        dto.setPageSize(10);
        dto.setTemplateId("1111"); dto.setTemplateId("templateId");
        dto.setLanguage("language");
        dto.setDeliveryChannel("deliveryChannel");
        PageRequest pageRequest = PageRequest.of(dto.getPageNo(), dto.getPageSize(), Sort.by(Sort.Direction.DESC, "id"));

        when(sendEmailNotificationTemplateRepository.findAll(any(Specification.class), any(PageRequest.class))).thenReturn(notificationTmplEntityPage);
        // when
        PageDTO<AlertTemplateDTO> result = alertTemplateService.page(dto);

        // then
        assertNotNull(result);

        Root<NotificationTmplEntity> root = mock(Root.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);

        // when
        Predicate predicate = alertTemplateService.getPredicate(dto, root, criteriaBuilder);

        // then
        assertNull(predicate);
    }

    @Test
    public void testRetrieve() {
        // given
        String templateId = "templateId";
        String language = "language";
        NotificationTmplEntity entity = new NotificationTmplEntity();
        when(sendEmailNotificationTemplateRepository.findByTemplateIdAndLanguage(templateId, language)).thenReturn(Optional.of(entity));

        // when
        AlertTemplateDTO result = alertTemplateService.retrieve(templateId, language);

        // then
        assertNotNull(result);
    }

    @Test
    public void testListMeta() {
        // given
        AlertTemplateMetaDto metaDto = new AlertTemplateMetaDto();
        when(notificationTemplateTagRepository.findAll()).thenReturn(new ArrayList<>());
        when(notificationTemplateLngRepository.findAll()).thenReturn(new ArrayList<>());
        when(notificationTemplateChnlRepository.findAll()).thenReturn(new ArrayList<>());

        // when
        AlertTemplateMetaDto result = alertTemplateService.listMeta();

        // then
        assertNotNull(result);
    }

    @Test
    public void testSave() {
        // given
        AlertTemplateDTO dto = new AlertTemplateDTO();
        dto.setTemplateId("templateId");
        dto.setLanguage("language");
        dto.setDeliveryChannel("deliveryChannel");
        when(sendEmailNotificationTemplateRepository.findByTemplateIdAndLanguageAndChnlType(any(), any(), any())).thenReturn(Optional.empty());

        // when
        alertTemplateService.save(dto);

        // then
        verify(sendEmailNotificationTemplateRepository, times(1)).save(any());
    }

    @Test
    public void testUpdate() {
        // given
        AlertTemplateDTO dto = new AlertTemplateDTO();
        dto.setTemplateId("templateId");
        dto.setLanguage("language");
        dto.setDeliveryChannel("deliveryChannel");
        NotificationTmplEntity entity = new NotificationTmplEntity();
        when(sendEmailNotificationTemplateRepository.findByTemplateIdAndLanguageAndChnlType(any(), any(), any())).thenReturn(Optional.of(entity));

        // when
        alertTemplateService.update(dto);

        // then
        verify(sendEmailNotificationTemplateRepository, times(1)).save(any());
    }

    @Test
    public void testSaveExist() {
        // given
        AlertTemplateDTO dto = new AlertTemplateDTO();
        dto.setTemplateId("templateId");
        dto.setLanguage("language");
        dto.setDeliveryChannel("deliveryChannel");
        NotificationTmplEntity entity = new NotificationTmplEntity();
        when(sendEmailNotificationTemplateRepository.findByTemplateIdAndLanguageAndChnlType(any(), any(), any())).thenReturn(Optional.of(entity));

        Assertions.assertThrows(BizException.class, () -> alertTemplateService.save(dto));
    }
}
