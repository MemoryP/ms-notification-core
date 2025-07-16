package com.ocbc.ms.notification.cn.api.custquick.controller;

import com.ocbc.ms.notification.cn.api.custquick.dto.TradCustInfoCheckRequestDTO;
import com.ocbc.ms.notification.cn.api.custquick.dto.TradCustInfoCheckResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Tag(name = "/Service/custquick")
public class CNTradCustInfoCheckController {

    @PostMapping("/tradcustinfocheck")
    @Operation(method = "POST", description = "账户开销户查询", summary = "CustIn")
    public TradCustInfoCheckResponseDTO invoke(@RequestBody @Valid TradCustInfoCheckRequestDTO requestDTO) throws Exception {
        TradCustInfoCheckResponseDTO custDupTelNumQuickResponseDTO = new TradCustInfoCheckResponseDTO();
        TradCustInfoCheckResponseDTO.Head head = new TradCustInfoCheckResponseDTO.Head();
        head.setRetCode("0000");
        head.setDBSharding("");
        head.setDBSharding("");
        head.setRetMsg("交易成功");
        custDupTelNumQuickResponseDTO.setHead(head);
        TradCustInfoCheckResponseDTO.Body body = new TradCustInfoCheckResponseDTO.Body();
        body.setCustId(1111L);
        custDupTelNumQuickResponseDTO.setBody(body);
        return custDupTelNumQuickResponseDTO;

    }

}
