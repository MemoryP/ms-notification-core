package com.ocbc.ms.notification.core.repository;


import com.ocbc.ms.notification.core.entity.NotificationCustTmplEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CustNotificationTemplateRepository extends JpaRepository<NotificationCustTmplEntity, Integer>, JpaSpecificationExecutor<NotificationCustTmplEntity> {


    @Query(value = "select  * FROM ntf_cust_tmpl WHERE tmpl_code =:tmplCode",
            nativeQuery = true)
    NotificationCustTmplEntity findByTmplCodeAndType(@Param("tmplCode") String tmplCode);

    Page<NotificationCustTmplEntity> findByTemplateIdContainsIgnoreCaseAndLanguageAndChnlType(String templateId, String language, String chnlType, PageRequest of);

    Optional<NotificationCustTmplEntity> findByTemplateIdAndLanguage(String templateId, String language);
    Optional<NotificationCustTmplEntity> findByTemplateIdAndLanguageAndChnlType(String templateId, String language, String chnlType);
}
