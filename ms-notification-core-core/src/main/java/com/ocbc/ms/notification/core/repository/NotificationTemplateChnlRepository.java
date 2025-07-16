package com.ocbc.ms.notification.core.repository;


import com.ocbc.ms.notification.core.entity.NotificationTmplChnlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface NotificationTemplateChnlRepository extends JpaRepository<NotificationTmplChnlEntity, Integer>, JpaSpecificationExecutor<NotificationTmplChnlEntity> {

}
