package com.ocbc.ms.notification.core.service;

import com.ocbc.ms.cbs.core.dto.CommonResult;
import com.ocbc.ms.notification.core.entity.req.CommNoticeRequest;
import com.ocbc.ms.notification.core.entity.vo.CheckRequestVo;
import org.springframework.http.HttpHeaders;

import java.util.List;

/**
 * @ Author Ou Qiliang
 * @ Created on 2024/09/20
 * @ Description This is to simplify the workflow task related service
 **/
public interface CommonNoticeService {

    CheckRequestVo notify(CommNoticeRequest context);
}
