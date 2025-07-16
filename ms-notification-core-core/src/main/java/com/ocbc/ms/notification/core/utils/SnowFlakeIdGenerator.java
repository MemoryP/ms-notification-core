package com.ocbc.ms.notification.core.utils;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Auther: maganglei
 * Date:2024/5/9
 */
@Component
@Slf4j
public class SnowFlakeIdGenerator implements IdentifierGenerator {

    //重写IdentifierGenerator的方法
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object o) throws HibernateException {
        return IdUtil.getSnowflakeNextIdStr();
    }

    public String generate(){
        return IdUtil.getSnowflakeNextIdStr();
    }

}
