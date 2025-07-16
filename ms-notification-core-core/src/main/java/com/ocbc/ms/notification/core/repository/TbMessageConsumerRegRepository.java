package com.ocbc.ms.notification.core.repository;

import com.ocbc.ms.notification.core.entity.TbMessageConsumerReg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TbMessageConsumerRegRepository extends JpaRepository<TbMessageConsumerReg, String> {
}
