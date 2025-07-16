package com.ocbc.ms.notification.core.repository;


import com.ocbc.ms.notification.core.entity.NotificationTmplEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SendEmailNotificationTemplateRepository extends JpaRepository<NotificationTmplEntity, Integer>, JpaSpecificationExecutor<NotificationTmplEntity> {


    @Query(value = "select  * FROM ntf_tmpl WHERE tmpl_code =:tmplCode",
            nativeQuery = true)
    NotificationTmplEntity findByTmplCodeAndType(@Param("tmplCode") String tmplCode);

    Page<NotificationTmplEntity> findByTemplateIdContainsIgnoreCaseAndLanguageAndChnlType(String templateId, String language, String chnlType, PageRequest of);

    Optional<NotificationTmplEntity> findByTemplateIdAndLanguage(String templateId, String language);
    Optional<NotificationTmplEntity> findByTemplateIdAndLanguageAndChnlType(String templateId, String language, String chnlType);
}
