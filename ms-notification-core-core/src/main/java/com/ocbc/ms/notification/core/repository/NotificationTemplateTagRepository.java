package com.ocbc.ms.notification.core.repository;


import com.ocbc.ms.notification.core.entity.NotificationTmplTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface NotificationTemplateTagRepository extends JpaRepository<NotificationTmplTagEntity, Integer>, JpaSpecificationExecutor<NotificationTmplTagEntity> {

}
