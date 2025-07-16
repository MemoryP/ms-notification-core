package com.ocbc.ms.notification.core.repository;

import com.ocbc.ms.notification.core.entity.TbMessageConditionDef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbMessageConditionDefRepository extends JpaRepository<TbMessageConditionDef, String> {

    List<TbMessageConditionDef> findAllByLegalEntityAndServiceCodeAndServiceSubCode(String legalEntity, String serviceCode, String serviceSubCode);

}
