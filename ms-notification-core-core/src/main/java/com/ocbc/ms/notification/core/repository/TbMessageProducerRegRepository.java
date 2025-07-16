package com.ocbc.ms.notification.core.repository;

import com.ocbc.ms.notification.core.entity.TbMessageProducerReg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbMessageProducerRegRepository extends JpaRepository<TbMessageProducerReg, String> {
}
