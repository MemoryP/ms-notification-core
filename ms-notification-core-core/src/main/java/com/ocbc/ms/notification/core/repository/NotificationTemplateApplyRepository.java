package com.ocbc.ms.notification.core.repository;


import com.ocbc.ms.notification.core.entity.NotificationTmplApplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface NotificationTemplateApplyRepository extends JpaRepository<NotificationTmplApplyEntity, Integer>, JpaSpecificationExecutor<NotificationTmplApplyEntity> {

}
